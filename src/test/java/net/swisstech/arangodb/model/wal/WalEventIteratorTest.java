package net.swisstech.arangodb.model.wal;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertSame;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.LineIterator;
import org.testng.annotations.Test;

public class WalEventIteratorTest {

	@Test
	public void test() {
		Reader reader = new StringReader("{\"tick\":\"123\",\"type\":2300,\"key\":\"456\",\"rev\":\"789\",\"data\":{\"_key\":\"456\",\"_rev\":\"789\",\"hello\":\"world\"}}\n{}\n\n\n\n");
		LineIterator li = new LineIterator(reader);

		WalEventIterator we = new WalEventIterator(li);
		assertSame(we, we.iterator());
		assertTrue(we.hasNext());

		WalEvent we1 = we.next();
		assertEquals(we1.getTick().longValue(), 123);
		assertEquals(we1.getType(), WalEventType.REPLICATION_MARKER_DOCUMENT);
		assertNotNull(we1.getData());
		assertEquals(we1.getData().toString(), "{\"_key\":\"456\",\"_rev\":\"789\",\"hello\":\"world\"}");

		assertTrue(we.hasNext());
		WalEvent we2 = we.next();
		assertNull(we2.getTick());
		assertNull(we2.getType());
		assertNull(we2.getData());

		assertTrue(we.hasNext());
		try {
			we.next();
			fail("this should throw an exception");
		}
		catch (IllegalStateException e) {}

		try {
			we.remove();
			fail("this should throw an exception");
		}
		catch (UnsupportedOperationException e) {}
	}
}
