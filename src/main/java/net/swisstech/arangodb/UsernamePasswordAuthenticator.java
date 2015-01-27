package net.swisstech.arangodb;

import java.io.IOException;
import java.net.Proxy;

import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Credentials;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class UsernamePasswordAuthenticator implements Authenticator {

	private static final String AUTHORIZATION = "Authorization";

	private final String username;
	private final String password;

	public UsernamePasswordAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Request authenticate(Proxy proxy, Response response) throws IOException {
		String credentials = Credentials.basic(username, password);
		return response //
			.request() //
			.newBuilder() //
			.header(AUTHORIZATION, credentials) //
			.build();
	}

	@Override
	public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
		return null;
	}
}
