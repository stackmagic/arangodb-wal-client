package net.swisstech.arangodb.model;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class CollectionParametersTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new CollectionParameters());

		// cover both cases in toString
		CollectionParameters cp = new CollectionParameters();
		cp.setType(CollectionType.DOCUMENT);
		cp.toString();
	}
}
