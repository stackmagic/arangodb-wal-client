package net.swisstech.arangodb.model.wal;

/**
 * a (partial) dump of a Write-Ahead-Log (WAL). includes http response headers so the caller can determine which from-tick he should request next from the
 * server.
 */
public class WalDump {

	private WalHeaders headers;
	private WalEventIterator events;

	public WalHeaders getHeaders() {
		return headers;
	}

	public void setHeaders(WalHeaders headers) {
		this.headers = headers;
	}

	public WalEventIterator getEvents() {
		return events;
	}

	public void setEvents(WalEventIterator iterator) {
		events = iterator;
	}
}
