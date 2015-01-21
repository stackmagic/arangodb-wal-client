package net.swisstech.arangodb.model;

/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
public class Server extends ServerId {

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