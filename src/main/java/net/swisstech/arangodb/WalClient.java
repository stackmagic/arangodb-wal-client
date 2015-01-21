package net.swisstech.arangodb;

import java.io.IOException;
import java.io.InputStream;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.LoggerState;
import net.swisstech.arangodb.model.ServerId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class WalClient {

	private String baseUrl;
	private final int chunkSize;

	private final OkHttpClient httpClient = new OkHttpClient();
	private final ObjectMapper mapper = new ObjectMapper();

	public WalClient(String baseUrl, int chunkSize) {
		this.baseUrl = baseUrl;
		this.chunkSize = chunkSize;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public Inventory inventory() throws IOException {
		return inventory(true);
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public Inventory inventory(boolean includeSystem) throws IOException {
		String url = baseUrl + "/_api/replication/inventory?includeSystem=" + includeSystem;
		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		return mapper.readValue(input, Inventory.class);
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public String dump() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public String loggerFollow() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public LoggerState loggerState() {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/OtherReplication.html */
	public ServerId serverId() throws IOException {
		String url = baseUrl + "/_api/replication/server-id";
		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		return mapper.readValue(input, ServerId.class);
	}
}
