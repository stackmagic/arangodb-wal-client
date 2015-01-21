package net.swisstech.arangodb.model.idx;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class Geo1IndexTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new Geo1Index());
	}

	@Test
	public void checkType() {
		assertEquals(new Geo1Index().getType(), IndexTypes.GEO1);
	}
}
