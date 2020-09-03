package com.hit.dm;

public class Purchases {
	
	private String userName;
	private String spName;
	
	public Purchases(String userName, String spName) {
		super();
		this.userName = userName;
		this.spName = spName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public String getSpName() {
		return spName;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}
	
	
}
