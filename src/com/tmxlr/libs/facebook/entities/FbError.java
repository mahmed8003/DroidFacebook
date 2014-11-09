package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class FbError {

	@SerializedName("message")
	private String message;

	@SerializedName("type")
	private String type;

	@SerializedName("code")
	private int code;

	public FbError(String type, String message) {
		this(type, message, 0);
	}

	public FbError(String type, String message, int code) {
		this.type = type;
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public String getType() {
		return type;
	}

	public int getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		String msg = String.format("code: %d, type: %s, message: %s", code, type, message);
		return msg;
	}

}
