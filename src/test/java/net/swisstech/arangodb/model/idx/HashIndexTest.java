package net.swisstech.arangodb.model.idx;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class HashIndexTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new HashIndex());
	}

	@Test
	public void checkType() {
		assertEquals(new HashIndex().getType(), IndexTypes.HASH);
	}

}
