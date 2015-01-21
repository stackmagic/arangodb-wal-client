package net.swisstech.arangodb.model;

import static net.swisstech.swissarmyknife.lang.Strings.isBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CollectionType {

	DOCUMENT(2),
	EDGE(3);

	private final int id;

	private CollectionType(int id) {
		this.id = id;
	}

	/** jackson calls toString() to serialize */
	@Override
	public String toString() {
		System.out.println("CollectionType.toString " + id);
		return Integer.toString(id);
	}

	/** jackson calls this method to deserialize */
	@JsonCreator
	public static CollectionType create(String value) {
		System.out.println("CollectionType.create " + value);
		if (isBlank(value)) {
			return null;
		}

		int id = Integer.parseInt(value);
		for (CollectionType cp : values()) {
			if (cp.id == id) {
				return cp;
			}
		}

		return null;
	}
}
