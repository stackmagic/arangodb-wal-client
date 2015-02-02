package net.swisstech.arangodb.model.wal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertSame;

import org.testng.annotations.Test;

public class WalEventTypeTest {

	@Test
	public void testValues() {

		assertEquals(WalEventType.create(null), WalEventType.UNKNOWN);
		assertEquals(WalEventType.create("12"), WalEventType.UNKNOWN);
		assertEquals(WalEventType.create("hi"), WalEventType.UNKNOWN);

		for (WalEventType val : WalEventType.values()) {
			assertSame(WalEventType.create(Integer.toString(val.getId())), val);
			assertSame(WalEventType.create(val.toString()), val);
			assertSame(WalEventType.valueOf(val.name()), val);
		}
	}
}
