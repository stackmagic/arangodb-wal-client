package net.swisstech.arangodb.model.wal;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class WalEventTypeTest {

	@Test
	public void jsonToString() {
		assertEquals(WalEventType.UNKNOWN.toString(), "-1");

		assertEquals(WalEventType.REPLICATION_INVALID.toString(), "0");

		assertEquals(WalEventType.REPLICATION_STOP.toString(), "1000");
		assertEquals(WalEventType.REPLICATION_START.toString(), "1001");

		assertEquals(WalEventType.REPLICATION_COLLECTION_CREATE.toString(), "2000");
		assertEquals(WalEventType.REPLICATION_COLLECTION_DROP.toString(), "2001");
		assertEquals(WalEventType.REPLICATION_COLLECTION_RENAME.toString(), "2002");
		assertEquals(WalEventType.REPLICATION_COLLECTION_CHANGE.toString(), "2003");

		assertEquals(WalEventType.REPLICATION_INDEX_CREATE.toString(), "2100");
		assertEquals(WalEventType.REPLICATION_INDEX_DROP.toString(), "2101");

		assertEquals(WalEventType.REPLICATION_TRANSACTION_START.toString(), "2200");
		assertEquals(WalEventType.REPLICATION_TRANSACTION_COMMIT.toString(), "2201");
		assertEquals(WalEventType.REPLICATION_TRANSACTION_ABORT.toString(), "2202");

		assertEquals(WalEventType.REPLICATION_MARKER_DOCUMENT.toString(), "2300");
		assertEquals(WalEventType.REPLICATION_MARKER_EDGE.toString(), "2301");
		assertEquals(WalEventType.REPLICATION_MARKER_REMOVE.toString(), "2302");
	}

	@Test
	public void jsonCreate() {
		assertEquals(WalEventType.create("-1"), WalEventType.UNKNOWN);
		assertEquals(WalEventType.create("0"), WalEventType.REPLICATION_INVALID);

		assertEquals(WalEventType.create("1000"), WalEventType.REPLICATION_STOP);
		assertEquals(WalEventType.create("1001"), WalEventType.REPLICATION_START);

		assertEquals(WalEventType.create("2000"), WalEventType.REPLICATION_COLLECTION_CREATE);
		assertEquals(WalEventType.create("2001"), WalEventType.REPLICATION_COLLECTION_DROP);
		assertEquals(WalEventType.create("2002"), WalEventType.REPLICATION_COLLECTION_RENAME);
		assertEquals(WalEventType.create("2003"), WalEventType.REPLICATION_COLLECTION_CHANGE);

		assertEquals(WalEventType.create("2100"), WalEventType.REPLICATION_INDEX_CREATE);
		assertEquals(WalEventType.create("2101"), WalEventType.REPLICATION_INDEX_DROP);

		assertEquals(WalEventType.create("2200"), WalEventType.REPLICATION_TRANSACTION_START);
		assertEquals(WalEventType.create("2201"), WalEventType.REPLICATION_TRANSACTION_COMMIT);
		assertEquals(WalEventType.create("2202"), WalEventType.REPLICATION_TRANSACTION_ABORT);

		assertEquals(WalEventType.create("2300"), WalEventType.REPLICATION_MARKER_DOCUMENT);
		assertEquals(WalEventType.create("2301"), WalEventType.REPLICATION_MARKER_EDGE);
		assertEquals(WalEventType.create("2302"), WalEventType.REPLICATION_MARKER_REMOVE);

		assertEquals(WalEventType.create("1"), WalEventType.UNKNOWN);
		assertEquals(WalEventType.create("nope"), WalEventType.UNKNOWN);
		assertEquals(WalEventType.create(null), WalEventType.UNKNOWN);
	}
}
