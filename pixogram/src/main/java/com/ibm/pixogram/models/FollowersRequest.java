package com.ibm.pixogram.models;

public class FollowersRequest {
  private String username;
  private String followerUsername;
  private String email;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getFollowerUsername() {
	return followerUsername;
}
public void setFollowerUsername(String followerUsername) {
	this.followerUsername = followerUsername;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
  
}
