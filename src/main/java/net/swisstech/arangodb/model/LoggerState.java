package net.swisstech.arangodb.model;

import java.util.List;

/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
public class LoggerState extends AbstractInfo {

	private Server server;

	// https://github.com/triAGENS/ArangoDB/issues/1219
	@Deprecated
	private List<Object> clients;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	// https://github.com/triAGENS/ArangoDB/issues/1219
	@Deprecated
	public List<Object> getClients() {
		return clients;
	}

	// https://github.com/triAGENS/ArangoDB/issues/1219
	@Deprecated
	public void setClients(List<Object> clients) {
		this.clients = clients;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public static class Server extends ServerId {

		private String version;

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		@Override
		public String toString() {
			return "Server version: " + version + " serverId: " + getServerId();
		}
	}
}
