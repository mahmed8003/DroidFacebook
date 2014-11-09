package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Page extends Entity {
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("about")
	private String about;
	
	@SerializedName("can_post")
	private Boolean can_post;
	
	@SerializedName("category")
	private String category;
	
	@SerializedName("checkins")
	private Integer checkins;
	
	@SerializedName("cover")
	private Cover cover;
	
	@SerializedName("description")
	private String description;
	
	@SerializedName("has_added_app")
	private Boolean hasAddedApp;
	
	@SerializedName("is_community_page")
	private Boolean isCommunityPage;
	
	@SerializedName("is_published")
	private Boolean isPublished;
	
	@SerializedName("likes")
	private Integer likes;
	
	@SerializedName("link")
	private String link;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("parking")
	private Parking parking;
	
	@SerializedName("talking_about_count")
	private Integer talkingAboutCount;
	
	@SerializedName("username")
	private String username;
	
	@SerializedName("website")
	private String website;
	
	@SerializedName("were_here_count")
	private Integer wereHereCount;

	@Override
	public String getId() {
		return id;
	}

	public String getAbout() {
		return about;
	}

	public Boolean getCan_post() {
		return can_post;
	}

	public String getCategory() {
		return category;
	}

	public Integer getCheckins() {
		return checkins;
	}

	public Cover getCover() {
		return cover;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getHasAddedApp() {
		return hasAddedApp;
	}

	public Boolean getIsCommunityPage() {
		return isCommunityPage;
	}

	public Boolean getIsPublished() {
		return isPublished;
	}

	public Integer getLikes() {
		return likes;
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return name;
	}

	public Parking getParking() {
		return parking;
	}

	public Integer getTalkingAboutCount() {
		return talkingAboutCount;
	}

	public String getUsername() {
		return username;
	}

	public String getWebsite() {
		return website;
	}

	public Integer getWereHereCount() {
		return wereHereCount;
	}
	

}
