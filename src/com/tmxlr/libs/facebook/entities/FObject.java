package com.tmxlr.libs.facebook.entities;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FObject extends Entity {

	@SerializedName("id")
	private String id;

	@SerializedName("created_time")
	private Date createdTime;

	@SerializedName("height")
	private int height;

	@SerializedName("width")
	private int width;

	@SerializedName("icon")
	private String icon;

	@SerializedName("images")
	private List<Image> images;

	@SerializedName("link")
	private String link;

	@SerializedName("name")
	private String name;

	@SerializedName("picture")
	private String picture;

	@SerializedName("source")
	private String source;

	@SerializedName("updated_time")
	private Date updatedTime;

	@Override
	public String getId() {
		return id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getIcon() {
		return icon;
	}

	public List<Image> getImages() {
		return images;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public String getPicture() {
		return picture;
	}

	public String getSource() {
		return source;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

}
