package net.swisstech.arangodb.model.wal;

/**
 * arangodb specific response headers for calling /dump or /logger-follow. this allows to pace the client, and indicates wheter or not one should re-check for
 * more data immediately.
 */
public class WalHeaders {

	private Boolean replicationActive;
	private long replicationLastincluded = 0;
	private Boolean replicationCheckmore;

	public Boolean getReplicationActive() {
		return replicationActive;
	}

	public void setReplicationActive(Boolean replicationActive) {
		this.replicationActive = replicationActive;
	}

	public long getReplicationLastincluded() {
		return replicationLastincluded;
	}

	public void setReplicationLastincluded(long replicationLastincluded) {
		this.replicationLastincluded = replicationLastincluded;
	}

	public Boolean getReplicationCheckmore() {
		return replicationCheckmore;
	}

	public void setReplicationCheckmore(Boolean replicationCheckmore) {
		this.replicationCheckmore = replicationCheckmore;
	}

	@Override
	public String toString() {
		return String.format("active: %s / lastIncluded: %d / checkMore: %s", replicationActive, replicationLastincluded, replicationCheckmore);
	}
}
