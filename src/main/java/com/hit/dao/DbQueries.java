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
	
	public String sqlUsersinsert = "INSERT INTO users" +
			"(userName,password,salt,email) VALUES (?,?,?,?)";	
	
	public String sqlSurfingPackageinsert = "INSERT INTO surfingPackages" +
			"(supplier,infrastructure,price,spId) VALUES (?,?,?,?)";	
	
	public String sqlPurchasesinsert = "INSERT INTO purchases" +
			"(userName,spName) VALUES (?,?)";	
	
}
