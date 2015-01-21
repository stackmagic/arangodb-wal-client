package net.swisstech.arangodb;

import static net.swisstech.swissarmyknife.lang.Strings.notBlank;
import static net.swisstech.swissarmyknife.test.Assert.assertNotEmpty;
import static org.testng.Assert.assertNotNull;

import java.io.IOException;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.LoggerState;
import net.swisstech.arangodb.model.ServerId;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WalClientTest {

	@Test
	@Parameters("ARANGODB_PORT")
	public void testInventory(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		Inventory inv = wc.inventory();
		assertNotNull(inv);
		assertNotEmpty(inv.getCollections());
		assertNotNull(inv.getState());
		notBlank(inv.getTick());
	}

	@Test
	@Parameters("ARANGODB_PORT")
	public void testServerId(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		ServerId sid = wc.serverId();
		assertNotNull(sid);
		notBlank(sid.getServerId());
	}

	@Test
	@Parameters("ARANGODB_PORT")
	public void testLoggerState(int port) throws IOException {
		WalClient wc = new WalClient("http://localhost:" + port);
		LoggerState ls = wc.loggerState();
		assertNotNull(ls);
		assertNotNull(ls.getServer());
		assertNotNull(ls.getState());
		assertNotNull(ls.getClients());
	}
}
