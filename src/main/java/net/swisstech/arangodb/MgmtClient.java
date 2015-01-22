package net.swisstech.arangodb;

import java.io.IOException;
import java.io.InputStream;

import net.swisstech.arangodb.model.CollectionType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/** TODO this was meant as a client to setup and teardown integration tests but might be useful for other means too */
public class MgmtClient {

	private static final MediaType MEDIATYPE_JSON = MediaType.parse("application/json; charset=utf-8");

	private String baseUrl;

	private final OkHttpClient httpClient = new OkHttpClient();
	private final ObjectMapper mapper = new ObjectMapper();

	public MgmtClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public CreateCollectionResponse createCollection(String collectionName) throws IOException {
		String url = baseUrl + "/_api/collection";
		String data = mapper.writeValueAsString(new CreateCollectionRequest(collectionName));
		RequestBody body = RequestBody.create(MEDIATYPE_JSON, data);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		return mapper.readValue(input, CreateCollectionResponse.class);
	}

	public DeleteCollectionResponse deleteCollection(String collectionName) throws IOException {
		String url = baseUrl + "/_api/collection/" + collectionName;
		Request request = new Request.Builder().url(url).delete().build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		return mapper.readValue(input, DeleteCollectionResponse.class);
	}

	public CreateDocumentResponse create(String collectionName, Object document) throws IOException {
		String url = baseUrl + "/_api/document?collection=" + collectionName;
		String data = mapper.writeValueAsString(document);
		RequestBody body = RequestBody.create(MEDIATYPE_JSON, data);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response = httpClient.newCall(request).execute();
		InputStream input = response.body().byteStream();
		return mapper.readValue(input, CreateDocumentResponse.class);
	}

	public static final class CreateCollectionRequest {

		private String name;

		public CreateCollectionRequest() {}

		public CreateCollectionRequest(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public static final class CreateCollectionResponse {

		private String id;
		private String name;
		private boolean waitForSync;
		private boolean isVolatile;
		private boolean isSystem;
		private int status;
		private CollectionType type;
		private boolean error;
		private int code;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean getWaitForSync() {
			return waitForSync;
		}

		public void setWaitForSync(boolean waitForSync) {
			this.waitForSync = waitForSync;
		}

		public boolean getIsVolatile() {
			return isVolatile;
		}

		public void setVolatile(boolean isVolatile) {
			this.isVolatile = isVolatile;
		}

		public boolean getIsSystem() {
			return isSystem;
		}

		public void setSystem(boolean isSystem) {
			this.isSystem = isSystem;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public CollectionType getType() {
			return type;
		}

		public void setType(CollectionType type) {
			this.type = type;
		}

		public boolean getError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}

	public static class DeleteCollectionResponse {

		private String id;
		private boolean error;
		private int code;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public boolean getError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}
	}

	public static class CreateDocumentResponse {

		private boolean error;
		private String id;
		private String rev;
		private String key;
		private String errorMessage;
		private long errorCode;
		private long code;

		public boolean getError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		@JsonProperty("_id")
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@JsonProperty("_rev")
		public String getRev() {
			return rev;
		}

		public void setRev(String rev) {
			this.rev = rev;
		}

		@JsonProperty("_key")
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public long getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(long errorCode) {
			this.errorCode = errorCode;
		}

		public long getCode() {
			return code;
		}

		public void setCode(long code) {
			this.code = code;
		}
	}
}
