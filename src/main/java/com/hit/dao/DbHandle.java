package com.hit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import com.hit.dm.Configuration;
import com.hit.dm.User;
import com.hit.dm.UsersHistory;

public interface DbHandle {
	public Connection getConnection();
	public void executeQuery(String query);
	public void registerUser(String userName, String pass, String salt, String email, String timeStamp);
	public boolean isUserExists(String userName);
	public void addSurfingPackage(String spName, String supplier, String infrastructure, int price);
	public void addPurchase(String userName, String spName);
	public ResultSet getUsers();
	public ResultSet getSurfingPackages();
	public ResultSet getPurchases();
	public boolean validUser(String userName, String password);
	public boolean updatePassword(String userName, String password);
	public ResultSet getUser(String userName);
	public void addUserVisit(String userName, String timeStamp);
	public List<UsersHistory> getAllVisits(String userName);
	public void removeUserVisit(String userName, String timeStamp);
	public boolean isEmailExist(String email);
	public void setUserTimeStamp(String userName, String timeStamp);
	public void setUserFailedAttempts(String userName, int faildAttempts);
}
