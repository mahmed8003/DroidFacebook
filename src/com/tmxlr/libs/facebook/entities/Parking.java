package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Parking {
	
	@SerializedName("lot")
	private Integer lot;
	
	@SerializedName("street")
	private Integer street;
	
	@SerializedName("valet")
	private Integer valet;

	public Integer getLot() {
		return lot;
	}

	public Integer getStreet() {
		return street;
	}

	public Integer getValet() {
		return valet;
	}
	
	
}
