package net.swisstech.arangodb;

import static java.lang.Boolean.parseBoolean;
import static net.swisstech.swissarmyknife.lang.Numbers.tryParseLong;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import net.swisstech.arangodb.model.Inventory;
import net.swisstech.arangodb.model.LoggerState;
import net.swisstech.arangodb.model.ServerId;
import net.swisstech.arangodb.model.wal.WalDump;
import net.swisstech.arangodb.model.wal.WalEventIterator;
import net.swisstech.arangodb.model.wal.WalHeaders;

import org.apache.commons.io.LineIterator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class WalClient {

	private String baseUrl;

	private final OkHttpClient httpClient = new OkHttpClient();
	private final ObjectMapper mapper = new ObjectMapper();

	public WalClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public WalClient(String baseUrl, String user, String pass) {
		this(baseUrl);
		httpClient.setAuthenticator(new UsernamePasswordAuthenticator(user, pass));
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
		Inventory inventory = mapper.readValue(input, Inventory.class);
		inventory.setResponseCode(response.code());
		return inventory;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationDump.html */
	public WalDump dump(String collection, long fromTick) throws IOException {
		String url = baseUrl + "/_api/replication/dump?collection=" + collection + "&from=" + fromTick;
		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		Reader reader = response.body().charStream();

		LineIterator li = new LineIterator(reader);
		WalEventIterator we = new WalEventIterator(li);

		String active = response.header("x-arango-replication-active");
		String lastInc = response.header("x-arango-replication-lastincluded");
		String checkMore = response.header("x-arango-replication-checkmore");

		WalHeaders wh = new WalHeaders();
		wh.setReplicationActive(parseBoolean(active));
		wh.setReplicationLastincluded(tryParseLong(lastInc, 0l));
		wh.setReplicationCheckmore(parseBoolean(checkMore));

		WalDump wd = new WalDump();
		wd.setResponseCode(response.code());
		wd.setHeaders(wh);
		wd.setEvents(we);
		return wd;
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public WalDump loggerFollow() throws IOException {
		throw new UnsupportedOperationException();
	}

	/** see: https://docs.arangodb.com/HttpReplications/ReplicationLogger.html */
	public LoggerState loggerState() throws IOException {
		String url = baseUrl + "/_api/replication/logger-state";
		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		LoggerState loggerState = mapper.readValue(input, LoggerState.class);
		loggerState.setResponseCode(response.code());
		return loggerState;
	}

	/** see: https://docs.arangodb.com/HttpReplications/OtherReplication.html */
	public ServerId serverId() throws IOException {
		String url = baseUrl + "/_api/replication/server-id";
		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		ServerId serverId = mapper.readValue(input, ServerId.class);
		serverId.setResponseCode(response.code());
		return serverId;
	}
}
