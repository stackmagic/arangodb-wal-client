package net.swisstech.arangodb.model;

import java.util.List;

/** https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
public class Inventory extends LoggerState {

	private List<ArangoDbCollection> collections;
	private String tick;

	public List<ArangoDbCollection> getCollections() {
		return collections;
	}

	public void setCollections(List<ArangoDbCollection> collections) {
		this.collections = collections;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

	@Override
	public String toString() {
		String len = "<null>";
		if (collections != null) {
			len = Integer.toString(collections.size());
		}
		return "Inventory: " + len + " collections";
	}
}
