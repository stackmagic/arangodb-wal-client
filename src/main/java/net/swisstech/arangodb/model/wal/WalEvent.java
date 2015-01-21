package net.swisstech.arangodb.model.wal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * a single line from a Write-Ahead-Log dump. only the 'important' fields are unmarshalled at this time and extra fields are ignored since we have no use for
 * them (yet)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalEvent {

	private String tick;
	private WalEventType type;
	private String data;

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

	public WalEventType getType() {
		return type;
	}

	public void setType(WalEventType type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
