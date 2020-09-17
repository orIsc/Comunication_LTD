package com.hit.dao;


public class DbQueries {
	private static DbQueries querySingleton = null;
	
	//FULL QUERIES FOR OUR SYSTEM
	private DbQueries() {
	}

	public static DbQueries getInstance() {
		if (querySingleton == null) {
			querySingleton = new DbQueries();
		}
		return querySingleton;
	}
	
	public String sqlVerifyUser = "SELECT * FROM users where userName=? and password=?;";
	public String sqlGetAllUsers = "SELECT * FROM users";
	public String sqlSurffingPackageGetall = "Select * from surfingPackages;";
	public String sqlPurchasesGetall = "Select * from purchases;";
	public String sqlUsersinsert = "INSERT INTO users" +
			"(userName,password,salt,email) VALUES (?,?,?,?)";	
	public String sqlSurfingPackageinsert = "INSERT INTO surfingPackages" +
			"(spName,supplier,infrastructure,price) VALUES (?,?,?,?)";	
	public String sqlPurchasesinsert = "INSERT INTO purchases" +
			"(userName,spName) VALUES (?,?)";	
	public String sqlInsertTimeStamp = "INSERT INTO userHistory" + "(userName,timeStamp) VALUES (?,?)";
	
}
