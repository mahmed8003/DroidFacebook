package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Image extends Entity {

	@SerializedName("height")
	private String height;
	
	@SerializedName("width")
	private String width;
	
	@SerializedName("source")
	private String source;
	
	
	@Override
	public String getId() {
		return null;
	}


	public String getHeight() {
		return height;
	}


	public String getWidth() {
		return width;
	}


	public String getSource() {
		return source;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public void setWidth(String width) {
		this.width = width;
	}


	public void setSource(String source) {
		this.source = source;
	}

	
}
