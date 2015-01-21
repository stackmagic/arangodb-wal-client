package net.swisstech.arangodb.model.wal;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.LineIterator;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * iterates over a WAL dump line by line, and unmarshalls each line into an event. This uses a LineIterator which in turn uses a BufferedReader to read the
 * dump. The intention of this being that we can stream data from the server in large chunks and don't have to load the entire dump into memory before starting
 * to process it line by line.
 */
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
		throw new UnsupportedOperationException();
	}
}
