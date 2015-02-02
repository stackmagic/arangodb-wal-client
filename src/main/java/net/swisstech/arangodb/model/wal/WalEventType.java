package net.swisstech.arangodb.model.wal;

import static net.swisstech.swissarmyknife.lang.Strings.isBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * a WAL event. the types model exactly what arangodb returns. see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html and
 * https://github.com/arangodb/arangodb/blob/master/arangod/VocBase/replication-common.h
 */
public enum WalEventType {

	UNKNOWN(-1), //

	REPLICATION_INVALID(0), //

	REPLICATION_STOP(1000), // not used anymore in arangodb 2.2 and higher
	REPLICATION_START(1001), // not used anymore in arangodb 2.2 and higher

	REPLICATION_COLLECTION_CREATE(2000), //
	REPLICATION_COLLECTION_DROP(2001), //
	REPLICATION_COLLECTION_RENAME(2002), //
	REPLICATION_COLLECTION_CHANGE(2003), //

	REPLICATION_INDEX_CREATE(2100), //
	REPLICATION_INDEX_DROP(2101), //

	REPLICATION_TRANSACTION_START(2200), //
	REPLICATION_TRANSACTION_COMMIT(2201), //
	REPLICATION_TRANSACTION_ABORT(2202), //

	REPLICATION_MARKER_DOCUMENT(2300), //
	REPLICATION_MARKER_EDGE(2301), //
	REPLICATION_MARKER_REMOVE(2302);

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
			return UNKNOWN;
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
			return UNKNOWN;
		}

		return UNKNOWN;
	}

	public int getId() {
		return id;
	}
}
