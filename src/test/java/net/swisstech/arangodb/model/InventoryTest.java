package net.swisstech.arangodb.model;

import java.io.IOException;
import java.util.ArrayList;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class InventoryTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new Inventory());

		// cover both cases in toString()
		Inventory i = new Inventory();
		i.setCollections(new ArrayList<ArangoDbCollection>());
		i.toString();
	}
}
