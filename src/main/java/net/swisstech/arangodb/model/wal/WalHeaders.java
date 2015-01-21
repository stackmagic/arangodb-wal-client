package net.swisstech.arangodb.model.wal;

/**
 * arangodb specific response headers for calling /dump or /logger-follow. this allows to pace the client, and indicates wheter or not one should re-check for
 * more data immediately.
 */
public class WalHeaders {

	private Boolean replicationActive;
	private String replicationLastincluded;
	private String replicationLasttick;
	private Boolean replicationCheckmore;

	public Boolean getReplicationActive() {
		return replicationActive;
	}

	public void setReplicationActive(Boolean replicationActive) {
		this.replicationActive = replicationActive;
	}

	public String getReplicationLastincluded() {
		return replicationLastincluded;
	}

	public void setReplicationLastincluded(String replicationLastincluded) {
		this.replicationLastincluded = replicationLastincluded;
	}

	public String getReplicationLasttick() {
		return replicationLasttick;
	}

	public void setReplicationLasttick(String replicationLasttick) {
		this.replicationLasttick = replicationLasttick;
	}

	public Boolean getReplicationCheckmore() {
		return replicationCheckmore;
	}

	public void setReplicationCheckmore(Boolean replicationCheckmore) {
		this.replicationCheckmore = replicationCheckmore;
	}
}
