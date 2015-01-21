package net.swisstech.arangodb;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.LoggerState;
import net.swisstech.arangodb.model.ServerId;

public class WalClient {

	private String baseUrl;
	private final int chunkSize;

	public WalClient(String baseUrl, int chunkSize) {
		this.baseUrl = baseUrl;
		this.chunkSize = chunkSize;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public Inventory inventory() {
		return inventory(true);
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public Inventory inventory(boolean includeSystem) {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public String dump() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public String loggerFollow() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public LoggerState loggerState() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/OtherReplication.html */
	public ServerId serverId() {
		throw new UnsupportedOperationException();
	}
}
