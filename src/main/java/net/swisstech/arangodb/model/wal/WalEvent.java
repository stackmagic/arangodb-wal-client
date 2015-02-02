package net.swisstech.arangodb.model.wal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * a single line from a Write-Ahead-Log dump. only the 'important' fields are unmarshalled at this time and extra fields are ignored since we have no use for
 * them (yet)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WalEvent {

	private String key;
	private String rev;
	private Long tick;
	private WalEventType type;
	private JsonNode data;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	public Long getTick() {
		return tick;
	}

	public void setTick(Long tick) {
		this.tick = tick;
	}

	public WalEventType getType() {
		return type;
	}

	public void setType(WalEventType type) {
		this.type = type;
	}

	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.data = data;
	}
}
