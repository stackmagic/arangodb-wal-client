package net.swisstech.arangodb.model.idx;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class Geo2IndexTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new Geo2Index());
	}

	@Test
	public void checkType() {
		assertEquals(new Geo2Index().getType(), IndexTypes.GEO2);
	}
}
