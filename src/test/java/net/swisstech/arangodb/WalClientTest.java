package net.swisstech.arangodb;

import java.io.IOException;

import net.swisstech.arangodb.model.Inventory;

import org.testng.annotations.Test;

public class WalClientTest {

	@Test
	public void testInventory() throws IOException {
		WalClient wc = new WalClient("http://localhost:8529", Integer.MAX_VALUE);
		Inventory i = wc.inventory();
		System.out.println(i);
	}
}
