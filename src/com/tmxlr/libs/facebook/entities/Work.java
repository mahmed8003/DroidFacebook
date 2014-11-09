package com.tmxlr.libs.facebook.entities;

import com.google.gson.annotations.SerializedName;

public class Work {

	@SerializedName("end_date")
	String endDate;

	@SerializedName("employer")
	BasicEntity employer;

	@SerializedName("location")
	BasicEntity location;

	@SerializedName("position")
	BasicEntity position;

	@SerializedName("start_date")
	String startDate;

	public String getEndDate() {
		return endDate;
	}

	public BasicEntity getEmployer() {
		return employer;
	}

	public BasicEntity getLocation() {
		return location;
	}

	public BasicEntity getPosition() {
		return position;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setEmployer(BasicEntity employer) {
		this.employer = employer;
	}

	public void setLocation(BasicEntity location) {
		this.location = location;
	}

	public void setPosition(BasicEntity position) {
		this.position = position;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	

}
