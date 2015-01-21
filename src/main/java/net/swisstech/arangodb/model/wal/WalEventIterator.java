package net.swisstech.arangodb.model.wal;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.LineIterator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WalEventIterator implements Iterator<WalEvent>, Iterable<WalEvent> {

	private final ObjectMapper mapper = new ObjectMapper();

	private final LineIterator lineIterator;

	public WalEventIterator(LineIterator lineIterator) {
		this.lineIterator = lineIterator;
	}

	@Override
	public Iterator<WalEvent> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		return lineIterator.hasNext();
	}

	/** @throws IllegalStateException if there is an error reading the next line or unmarshalling it */
	@Override
	public WalEvent next() {
		try {
			String line = lineIterator.next();
			return mapper.readValue(line, WalEvent.class);
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void remove() {
		lineIterator.remove();
	}
}
