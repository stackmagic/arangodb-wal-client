package net.swisstech.arangodb;

import static net.swisstech.swissarmyknife.lang.Longs.positive;
import static net.swisstech.swissarmyknife.lang.Longs.zero;
import static net.swisstech.swissarmyknife.lang.Strings.notBlank;
import static net.swisstech.swissarmyknife.lang.Threads.sleepFor;
import static net.swisstech.swissarmyknife.test.Assert.assertSameSize;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import net.swisstech.arangodb.MgmtClient.CreateCollectionResponse;
import net.swisstech.arangodb.MgmtClient.CreateDocumentResponse;
import net.swisstech.arangodb.model.ArangoDbCollection;
import net.swisstech.arangodb.model.wal.WalDump;
import net.swisstech.arangodb.model.wal.WalEvent;
import net.swisstech.arangodb.model.wal.WalEventIterator;
import net.swisstech.arangodb.model.wal.WalEventType;
import net.swisstech.arangodb.model.wal.WalHeaders;
import net.swisstech.log.Logger;
import net.swisstech.log.LoggerFactory;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExtendedWalClientTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExtendedWalClientTest.class);

	@Test
	@Parameters("ARANGODB_PORT")
	public void test(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		MgmtClient mc = new MgmtClient("http://localhost:" + port);
		String cn = "test_" + System.currentTimeMillis();

		/*
		 * collection must not yet exist
		 */

		assertEquals(wc.dump(cn, 0).getResponseCode(), 404);

		for (ArangoDbCollection coll : wc.inventory(false).getCollections()) {
			if (cn.equals(coll.getParameters().getName())) {
				fail("SETUP collection " + cn + " already exists but it shouldn't");
			}
		}

		/*
		 * create collection
		 */

		CreateCollectionResponse ccresp = mc.createCollection(cn);
		assertEquals(ccresp.getCode(), 200);

		/*
		 * collection must now exist
		 */

		assertEquals(wc.dump(cn, 0).getResponseCode(), 204);

		boolean created = false;
		for (ArangoDbCollection coll : wc.inventory(false).getCollections()) {
			if (cn.equals(coll.getParameters().getName())) {
				created = true;
			}
		}
		assertTrue(created);

		/*
		 * start loading documents
		 */

		Set<String> keysCreated = new HashSet<>();
		Set<String> keysReceived = new HashSet<>();

		DocLoader dl = new DocLoader(mc, cn, keysCreated);
		dl.start();

		/*
		 * start reading write-ahead-log
		 */

		long lastTick = readWriteAheadLog(wc, cn, keysReceived, 10_000, 0);

		/*
		 * stop loading documents
		 */

		dl.shutdown();

		/*
		 * write-ahead-log should settle and we should have received all documents
		 */

		readWriteAheadLog(wc, cn, keysReceived, 10_000, lastTick);

		assertSameSize(keysCreated, keysReceived);
		assertTrue(keysCreated.containsAll(keysReceived));
		assertTrue(keysReceived.containsAll(keysCreated));

		/*
		 * cleanup test
		 */

		mc.deleteCollection(cn);

		for (ArangoDbCollection coll : wc.inventory(false).getCollections()) {
			if (cn.equals(coll.getParameters().getName())) {
				fail("TEARDOWN collection " + cn + " should have been deleted");
			}
		}
	}

	private long readWriteAheadLog(WalClient wc, String cn, Set<String> keysFromWal, long duration, long lastTick) throws IOException {
		long noCheckMoreCtr = 0;
		long end = System.currentTimeMillis() + duration;
		while (end > System.currentTimeMillis()) {
			LOG.info("Loading WAL from tick: %d", lastTick);
			WalDump dump = wc.dump(cn, lastTick);

			WalEventIterator events = dump.getEvents();
			int ctr = 0;
			for (WalEvent we : events) {
				ctr++;
				String key = we.getKey();
				notBlank(key);
				assertTrue(keysFromWal.add(key), key);
				notBlank(we.getTick());
				assertNotNull(we.getData());
				assertEquals(we.getType(), WalEventType.DOCUMENT_INSERT_UPDATE);
			}

			LOG.info("Received %d events from WAL", ctr);

			WalHeaders wh = dump.getHeaders();
			if (wh.getReplicationCheckmore()) {
				LOG.info("Checkmore == true: carrying on");
				noCheckMoreCtr = 0;
			}
			else {
				LOG.info("Checkmore == false: sleeping");
				sleepFor(1000);
				noCheckMoreCtr++;
			}

			LOG.info("Response Headers: %s", wh);
			long lastTickHeader = wh.getReplicationLastincluded();
			if (ctr == 0) {
				assertEquals(dump.getResponseCode(), 204);
				zero(lastTickHeader);
			}
			else {
				assertEquals(dump.getResponseCode(), 200);
				lastTick = positive(lastTickHeader);
			}

			// abort if there was no new data for a while
			if (noCheckMoreCtr >= 3) {
				break;
			}
		}

		return lastTick;
	}

	public static class DocLoader extends Thread {

		private volatile boolean keepRunning = true;

		private final MgmtClient mc;
		private final String collectionName;
		private final Set<String> keysCreated;

		public DocLoader(MgmtClient mc, String collectionName, Set<String> keysCreated) {
			this.mc = mc;
			this.collectionName = collectionName;
			this.keysCreated = keysCreated;
		}

		@Override
		public void run() {
			sleepFor(100);
			long i = 0;
			while (keepRunning) {
				try {
					i++;
					CreateDocumentResponse rsp = mc.create(collectionName, new VeryImportantDocument(i));
					assertFalse(rsp.getError());
					String key = rsp.getKey();
					keysCreated.add(key);
					sleepFor(20);
				}
				catch (IOException e) {
					LOG.error("Error creating document", e);
				}
			}
		}

		public void shutdown() {
			keepRunning = false;
		}
	}

	public static class VeryImportantDocument {

		private long secret = 0;

		public VeryImportantDocument(long secret) {
			this.secret = secret;
		}

		public long getSecret() {
			return secret;
		}

		public void setSecret(long secret) {
			this.secret = secret;
		}
	}
}
