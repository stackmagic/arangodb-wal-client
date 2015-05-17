package net.swisstech.arangodb;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/** factory for jackson object mappers */
public class ObjectMapperFactory {

	/** returns a properly configured object mapper */
	public static ObjectMapper create() {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}
}
