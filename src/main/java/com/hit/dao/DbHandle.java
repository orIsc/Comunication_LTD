package com.hit.dao;

import java.sql.Connection;
import java.sql.ResultSet;

public interface DbHandle {
	public Connection getConnection();
	public void executeQuery(String query);
	public boolean registerUser(String userName, String pass, String salt, String email);
	public boolean isUserExists(String email, Connection conn);
	public void addSurfingPackage(String spName, String supplier, String infrastructure, int price);
	public void addPurchase(String userName, String spName);
	public ResultSet getUsers();
	public ResultSet getSurfingPackages();
	public ResultSet getPurchases();
	public boolean validUser(String userName, String password);
}
