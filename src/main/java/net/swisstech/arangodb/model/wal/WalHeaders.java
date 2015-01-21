package net.swisstech.arangodb.model.wal;

/**
 * arangodb specific response headers for calling /dump or /logger-follow. this allows to pace the client, and indicates wheter or not one should re-check for
 * more data immediately.
 */
public class WalHeaders {

	private boolean xArangoReplicationActive;
	private long xArangoReplicationLastincluded;
	private long xArangoReplicationLasttick;
	private boolean xArangoReplicationCheckmore;

	public boolean isxArangoReplicationActive() {
		return xArangoReplicationActive;
	}

	public void setxArangoReplicationActive(boolean xArangoReplicationActive) {
		this.xArangoReplicationActive = xArangoReplicationActive;
	}

	public long getxArangoReplicationLastincluded() {
		return xArangoReplicationLastincluded;
	}

	public void setxArangoReplicationLastincluded(long xArangoReplicationLastincluded) {
		this.xArangoReplicationLastincluded = xArangoReplicationLastincluded;
	}

	public long getxArangoReplicationLasttick() {
		return xArangoReplicationLasttick;
	}

	public void setxArangoReplicationLasttick(long xArangoReplicationLasttick) {
		this.xArangoReplicationLasttick = xArangoReplicationLasttick;
	}

	public boolean isxArangoReplicationCheckmore() {
		return xArangoReplicationCheckmore;
	}

	public void setxArangoReplicationCheckmore(boolean xArangoReplicationCheckmore) {
		this.xArangoReplicationCheckmore = xArangoReplicationCheckmore;
	}
}
