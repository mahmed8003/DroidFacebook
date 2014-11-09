package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class BasicEntity extends Entity {

	@SerializedName("id")
	String id;
	
	@SerializedName("name")
	String name;
	
	
	public BasicEntity() {
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
