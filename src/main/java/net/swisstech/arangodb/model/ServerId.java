package net.swisstech.arangodb.model;


/** see: https://docs.arangodb.com/HttpReplications/OtherReplication.html */
public class ServerId extends AbstractResponse {

	private String serverId;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	@Override
	public String toString() {
		return "serverId " + serverId;
	}
}
