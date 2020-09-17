package com.hit.dm;

public class UsersHistory {

	private String userName;
	private String timeStamp;
	
	
	public UsersHistory(String userName, String timeStamp) {
		super();
		this.userName = userName;
		this.timeStamp = timeStamp;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "UsersHistory [userName=" + userName + ", timeStamp=" + timeStamp + "]";
	}
	
}
