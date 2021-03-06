package net.swisstech.arangodb.model.idx;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class CapIndexTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new CapIndex());
	}

	@Test
	public void checkType() {
		assertEquals(new CapIndex().getType(), IndexTypes.CAP);
	}

}
