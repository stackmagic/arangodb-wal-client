package net.swisstech.arangodb.model;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class ArangoDbCollectionTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new ArangoDbCollection());

		// cover both cases in toString
		CollectionParameters cp = new CollectionParameters();
		cp.setName("hello world");

		ArangoDbCollection adc = new ArangoDbCollection();
		adc.setParameters(cp);
		adc.toString();
	}
}
