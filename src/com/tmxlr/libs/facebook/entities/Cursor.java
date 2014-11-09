package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Cursor {
	
	@SerializedName("after")
	private String after;
	
	@SerializedName("before")
	private String before;

	public String getAfter() {
		return after;
	}

	public String getBefore() {
		return before;
	}

	
}
