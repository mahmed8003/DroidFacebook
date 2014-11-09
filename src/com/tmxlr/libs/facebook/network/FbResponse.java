package com.tmxlr.libs.facebook.network;


public class FbResponse {
	
	private final int statusCode;
	private final String body;
	
	public FbResponse(final FbRequest request) {
		statusCode = request.code();
		body = request.body();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getBody() {
		return body;
	}
	
	public boolean ok() {
		return statusCode < 400;
	}

	
}
