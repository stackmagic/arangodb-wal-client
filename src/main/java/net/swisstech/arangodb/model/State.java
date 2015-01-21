// Copyright (C) Layzapp AG. All Rights Reserved.
package net.swisstech.arangodb.model;

/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
public class State {

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