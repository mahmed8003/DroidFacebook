package com.tmxlr.libs.facebook.entities;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Post extends Entity {

	@SerializedName("id")
	private String id;

	@SerializedName("caption")
	private String caption;

	@SerializedName("created_time")
	private Date createdTime;

	@SerializedName("description")
	private String description;

	@SerializedName("from")
	private User from;

	@SerializedName("icon")
	private String icon;

	@SerializedName("is_hidden")
	private boolean isHidden;

	@SerializedName("link")
	private String link;

	@SerializedName("message")
	private String message;

	@SerializedName("name")
	private String name;

	@SerializedName("object_id")
	private String objectId;

	@SerializedName("picture")
	private String picture;

	@SerializedName("status_type")
	private String statusType;

	@SerializedName("story")
	private String story;

	@SerializedName("type")
	private String type;

	@SerializedName("updated_time")
	private Date updatedTime;

	private Integer likeCount = 0;
	private Boolean userLikes = false;
	private FObject object;

	@Override
	public String getId() {
		return id;
	}

	public String getCaption() {
		return caption;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getDescription() {
		return description;
	}

	public User getFrom() {
		return from;
	}

	public String getIcon() {
		return icon;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public String getLink() {
		return link;
	}

	public String getMessage() {
		return message;
	}

	public String getName() {
		return name;
	}

	public String getObjectId() {
		return objectId;
	}

	public String getPicture() {
		return picture;
	}

	public String getStatusType() {
		return statusType;
	}

	public String getStory() {
		return story;
	}

	public String getType() {
		return type;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Boolean getUserLikes() {
		return userLikes;
	}

	public void setUserLikes(Boolean userLikes) {
		this.userLikes = userLikes;
	}

	public FObject getObject() {
		return object;
	}

	public void setObject(FObject object) {
		this.object = object;
	}

}
