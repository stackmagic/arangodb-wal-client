package net.swisstech.arangodb.model;

public abstract class AbstractResponse {

	private int responseCode;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
}
