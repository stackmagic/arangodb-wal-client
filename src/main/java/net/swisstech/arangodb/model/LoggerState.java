package net.swisstech.arangodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
@JsonIgnoreProperties("clients")
public class LoggerState extends AbstractInfo {

	private Server server;

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
}
