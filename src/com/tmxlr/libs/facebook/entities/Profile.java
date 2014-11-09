package com.tmxlr.libs.facebook.entities;

import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Profile extends Entity {

	@SerializedName("id")
	String id;

	@SerializedName("name")
	String name;

	@SerializedName("about")
	String about;

	@SerializedName("bio")
	String bio;

	@SerializedName("birthday")
	String birthday;

	@SerializedName("email")
	String email;

	@SerializedName("favorite_athletes")
	List<BasicEntity> favoriteAthletes;

	@SerializedName("favorite_teams")
	List<BasicEntity> favoriteTeams;

	@SerializedName("first_name")
	String firstName;

	@SerializedName("gender")
	String gender;

	@SerializedName("hometown")
	BasicEntity hometown;

	@SerializedName("inspirational_people")
	List<BasicEntity> inspirationalPeople;

	@SerializedName("installed")
	boolean installed;

	@SerializedName("is_verified")
	boolean isVrified;

	@SerializedName("languages")
	List<BasicEntity> languages;

	@SerializedName("last_name")
	String lastName;

	@SerializedName("link")
	String link;

	@SerializedName("locale")
	String locale;

	@SerializedName("location")
	BasicEntity location;

	@SerializedName("middle_name")
	String middleName;

	@SerializedName("name_format")
	String nameFormat;

	@SerializedName("political")
	String political;

	@SerializedName("quotes")
	String quotes;

	@SerializedName("relationship_status")
	String relationshipStatus;

	@SerializedName("religion")
	String religion;

	@SerializedName("timezone")
	int timezone;

	@SerializedName("third_party_id")
	String thirdPartyId;

	@SerializedName("verified")
	boolean verified;

	@SerializedName("website")
	String website;

	@SerializedName("work")
	Work work;

	@SerializedName("picture")
	JsonObject picture;

	public Profile() {
	}

	@Override
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAbout() {
		return about;
	}

	public String getBio() {
		return bio;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getEmail() {
		return email;
	}

	public List<BasicEntity> getFavoriteAthletes() {
		return favoriteAthletes;
	}

	public List<BasicEntity> getFavoriteTeams() {
		return favoriteTeams;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}

	public BasicEntity getHometown() {
		return hometown;
	}

	public List<BasicEntity> getInspirationalPeople() {
		return inspirationalPeople;
	}

	public boolean isInstalled() {
		return installed;
	}

	public boolean isVrified() {
		return isVrified;
	}

	public List<BasicEntity> getLanguages() {
		return languages;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLink() {
		return link;
	}

	public String getLocale() {
		return locale;
	}

	public BasicEntity getLocation() {
		return location;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getNameFormat() {
		return nameFormat;
	}

	public String getPolitical() {
		return political;
	}

	public String getQuotes() {
		return quotes;
	}

	public String getRelationshipStatus() {
		return relationshipStatus;
	}

	public String getReligion() {
		return religion;
	}

	public int getTimezone() {
		return timezone;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public boolean isVerified() {
		return verified;
	}

	public String getWebsite() {
		return website;
	}

	public Work getWork() {
		return work;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFavoriteAthletes(List<BasicEntity> favoriteAthletes) {
		this.favoriteAthletes = favoriteAthletes;
	}

	public void setFavoriteTeams(List<BasicEntity> favoriteTeams) {
		this.favoriteTeams = favoriteTeams;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHometown(BasicEntity hometown) {
		this.hometown = hometown;
	}

	public void setInspirationalPeople(List<BasicEntity> inspirationalPeople) {
		this.inspirationalPeople = inspirationalPeople;
	}

	public void setInstalled(boolean installed) {
		this.installed = installed;
	}

	public void setVrified(boolean isVrified) {
		this.isVrified = isVrified;
	}

	public void setLanguages(List<BasicEntity> languages) {
		this.languages = languages;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public void setLocation(BasicEntity location) {
		this.location = location;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setNameFormat(String nameFormat) {
		this.nameFormat = nameFormat;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public void setQuotes(String quotes) {
		this.quotes = quotes;
	}

	public void setRelationshipStatus(String relationshipStatus) {
		this.relationshipStatus = relationshipStatus;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public String getPicture() {
		if (picture != null) {
			if (picture.has("data")) {
				JsonObject dataObj = picture.getAsJsonObject("data");
				if (dataObj.has("url")) {
					String url = dataObj.get("url").getAsString();
					return url;
				}
			}
		}
		return null;
	}
}
