package net.swisstech.arangodb.model;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class ServerIdTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new ServerId());
	}
}
