package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Cover extends Entity {
	
	@SerializedName("cover_id")
	private String id;
	
	@SerializedName("offset_x")
	private Integer offsetX;
	
	@SerializedName("offset_y")
	private Integer offsetY;
	
	@SerializedName("source")
	private String source;

	@Override
	public String getId() {
		return id;
	}

	public Integer getOffsetX() {
		return offsetX;
	}

	public Integer getOffsetY() {
		return offsetY;
	}

	public String getSource() {
		return source;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOffsetX(Integer offsetX) {
		this.offsetX = offsetX;
	}

	public void setOffsetY(Integer offsetY) {
		this.offsetY = offsetY;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	
}
