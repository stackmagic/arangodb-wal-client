package net.swisstech.arangodb;

import java.io.IOException;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.ServerId;

import org.testng.annotations.Test;

public class WalClientTest {

	@Test
	public void testInventory() throws IOException {
		WalClient wc = new WalClient("http://localhost:8529");
		Inventory i = wc.inventory();
		System.out.println(i);
	}

	@Test
	public void testServerId() throws IOException {
		WalClient wc = new WalClient("http://localhost:8529");
		ServerId i = wc.serverId();
		System.out.println(i);
	}
}
