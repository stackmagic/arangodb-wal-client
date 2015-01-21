package net.swisstech.arangodb.model;

/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
public class LoggerState {

	private State state;
	private Server server;

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	@Override
	public String toString() {
		return "LoggerState server: " + server + " state: " + state;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public static class State {

		private boolean running;
		private String lastLogTick;
		private long totalEvents;
		private String time;

		public boolean isRunning() {
			return running;
		}

		public void setRunning(boolean running) {
			this.running = running;
		}

		public String getLastLogTick() {
			return lastLogTick;
		}

		public void setLastLogTick(String lastLogTick) {
			this.lastLogTick = lastLogTick;
		}

		public long getTotalEvents() {
			return totalEvents;
		}

		public void setTotalEvents(long totalEvents) {
			this.totalEvents = totalEvents;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		@Override
		public String toString() {
			return "State running: " + running;
		}
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
