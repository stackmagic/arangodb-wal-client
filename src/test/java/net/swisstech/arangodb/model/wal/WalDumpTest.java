package net.swisstech.arangodb.model.wal;

import java.io.IOException;

import net.swisstech.swissarmyknife.test.DtoTesterUtil;

import org.testng.annotations.Test;

public class WalDumpTest {

	@Test
	public void propertiesCoverage() throws IOException {
		DtoTesterUtil.testAllProperties(new WalDump());
	}
}
