package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class User extends Entity {
	
	@SerializedName("id")
	private String id;
	
	@SerializedName("name")
	private String name;
	
	
	@Override
	public String getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public String getProfilePicture() {
		return String.format("http://graph.facebook.com/%s/picture?type=normal", id);
	}
}
