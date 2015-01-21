package net.swisstech.arangodb.model.wal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

public class WalEventTypeTest {

	@Test
	public void jsonToString() {
		assertEquals(WalEventType.DOCUMENT_INSERT_UPDATE.toString(), "2300");
		assertEquals(WalEventType.EDGE_INSERT_UPDATE.toString(), "2301");
		assertEquals(WalEventType.DELETE.toString(), "2302");
	}

	@Test
	public void jsonCreate() {
		assertNull(WalEventType.create(null));
		assertEquals(WalEventType.create("2300"), WalEventType.DOCUMENT_INSERT_UPDATE);
		assertEquals(WalEventType.create("2301"), WalEventType.EDGE_INSERT_UPDATE);
		assertEquals(WalEventType.create("2302"), WalEventType.DELETE);
		assertEquals(WalEventType.create("0000"), null);
		assertEquals(WalEventType.create("nope"), null);
	}
}
