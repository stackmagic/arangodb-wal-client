package net.swisstech.arangodb;

import static net.swisstech.swissarmyknife.lang.Strings.notBlank;
import static net.swisstech.swissarmyknife.test.Assert.assertGreaterThan;
import static net.swisstech.swissarmyknife.test.Assert.assertNotEmpty;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.io.IOException;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.LoggerState;
import net.swisstech.arangodb.model.ServerId;
import net.swisstech.arangodb.model.wal.WalDump;
import net.swisstech.arangodb.model.wal.WalEvent;
import net.swisstech.arangodb.model.wal.WalEventIterator;
import net.swisstech.arangodb.model.wal.WalHeaders;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SimpleWalClientTest {

	@Test
	@Parameters("ARANGODB_PORT")
	public void testInventory(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		Inventory inv = wc.inventory();
		assertNotNull(inv);
		assertNotEmpty(inv.getCollections());
		assertNotNull(inv.getState());
		notBlank(inv.getTick());
	}

	@Test
	@Parameters("ARANGODB_PORT")
	public void testServerId(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		ServerId sid = wc.serverId();
		assertNotNull(sid);
		notBlank(sid.getServerId());
	}

	@Test
	@Parameters("ARANGODB_PORT")
	public void testLoggerState(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		LoggerState ls = wc.loggerState();
		assertNotNull(ls);
		assertNotNull(ls.getServer());
		assertNotNull(ls.getState());
		assertNotNull(ls.getClients());
	}

	@Test
	@Parameters("ARANGODB_PORT")
	public void testDump(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		WalDump wd = wc.dump("_users", 0);
		assertNotNull(wd);

		WalHeaders headers = wd.getHeaders();
		assertNotNull(headers);
		assertNotNull(headers.getReplicationActive());
		assertNotNull(headers.getReplicationCheckmore());
		assertNotNull(headers.getReplicationLastincluded());
		assertNull(headers.getReplicationLasttick());

		WalEventIterator events = wd.getEvents();
		assertNotNull(events);
		int counter = 0;
		for (WalEvent event : events) {
			counter++;
			assertNotNull(event);
		}
		assertGreaterThan(counter, 0);
	}
}
