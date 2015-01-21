package net.swisstech.arangodb.model.wal;

import static net.swisstech.swissarmyknife.lang.Strings.isBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

/** a WAL event. the types model exactly what arangodb returns. see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
public enum WalEventType {

	DOCUMENT_INSERT_UPDATE(2300), //
	EDGE_INSERT_UPDATE(2301), //
	DELETE(2302);

	private final int id;

	private WalEventType(int id) {
		this.id = id;
	}

	/** jackson calls toString() to serialize */
	@Override
	public String toString() {
		return Integer.toString(id);
	}

	/** jackson calls this method to deserialize */
	@JsonCreator
	public static WalEventType create(String value) {
		if (isBlank(value)) {
			return null;
		}

		try {
			int id = Integer.parseInt(value);
			for (WalEventType cp : values()) {
				if (cp.id == id) {
					return cp;
				}
			}
		}
		catch (NumberFormatException e) {
			return null;
		}

		return null;
	}
}
