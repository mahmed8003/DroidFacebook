package com.tmxlr.libs.facebook.entities;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Comment extends Entity {

	@SerializedName("id")
	private String id;

	@SerializedName("from")
	private User from;

	@SerializedName("message")
	private String message;

	@SerializedName("can_remove")
	private Boolean canRemove;

	@SerializedName("created_time")
	private Date createdTime;

	@SerializedName("like_count")
	private Integer likeCount;

	@SerializedName("user_likes")
	private Boolean userLikes;

	@Override
	public String getId() {
		return id;
	}

	public User getFrom() {
		return from;
	}

	public String getMessage() {
		return message;
	}

	public Boolean getCanRemove() {
		return canRemove;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public Boolean getUserLikes() {
		return userLikes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCanRemove(Boolean canRemove) {
		this.canRemove = canRemove;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public void setUserLikes(Boolean userLikes) {
		this.userLikes = userLikes;
	}

}
