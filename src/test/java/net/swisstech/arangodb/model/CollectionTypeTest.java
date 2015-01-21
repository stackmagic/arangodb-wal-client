package net.swisstech.arangodb.model;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.testng.annotations.Test;

public class CollectionTypeTest {

	@Test
	public void jsonToString() {
		assertEquals(CollectionType.DOCUMENT.toString(), "2");
		assertEquals(CollectionType.EDGE.toString(), "3");
	}

	@Test
	public void jsonCreate() {
		assertNull(CollectionType.create(null));
		assertEquals(CollectionType.create("2"), CollectionType.DOCUMENT);
		assertEquals(CollectionType.create("3"), CollectionType.EDGE);
		assertEquals(CollectionType.create("0"), null);
		assertEquals(CollectionType.create("x"), null);
	}
}
