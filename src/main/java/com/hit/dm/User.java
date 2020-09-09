package com.hit.dm;

public class User {

	private String userName;
	private String password;
	private String salt;
	private String email;
	
	public User(String userName, String password, String salt, String email) {
		super();
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", salt=" + salt + ", email=" + email + "]";
	}
	
	
}
