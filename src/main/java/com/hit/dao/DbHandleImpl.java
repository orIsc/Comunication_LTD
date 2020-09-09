package com.hit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hit.dao.DbQueries;

public class DbHandleImpl implements DbHandle {
	 
	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;	
	private PreparedStatement prepStat = null;
	private DbQueries queries = DbQueries.getInstance();
	private static DbHandleImpl instance;
	
	private DbHandleImpl() {
		
	}
	
	public static DbHandle getInstance() {
    	if(instance == null) {
    		instance = new DbHandleImpl();
    	}
    	return instance;
    }
	
	@Override
	public Connection getConnection(){ //Getting connection to db
		String path = "jdbc:mysql://localhost:3306/cyberProject";
		String user = "root";
		String pass = "Kill-Z0ne";
		try {
		    // create a connection to the db
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		    conn = DriverManager.getConnection(path, user, pass);   
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		}
		return conn;
	}
	
	@Override
	public void executeQuery(String query) { //Executing main create table queries
		try {
			conn = getConnection();
			state = conn.createStatement();
			state.executeUpdate(query);
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());		
			}
	}

	@Override
	public boolean registerUser(String userName, String pass, String salt, String email) { //Registring user to db
		boolean status = false; 
		try {
			conn = getConnection();
			prepStat = conn.prepareStatement(queries.sqlUsersinsert);
			prepStat.setString(1, userName);
			prepStat.setString(2, pass);
			prepStat.setString(3, salt);
			prepStat.setString(4, email);
			status = isUserExists(userName, conn);
			if (status == false) {
				prepStat.executeUpdate();
				System.out.println("User inserted successfully");			
			} else {
				return false;
			}
			prepStat.close();
			conn.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return true;
	}

	@Override
	public boolean isUserExists(String userName, Connection conn) { //checking if user already in db by userName
		Connection c = conn;
		String sqlUsersByUserName = "Select userName from Users where userName='" + userName + "'";
		try {
			state = c.createStatement();
			ResultSet rs = state.executeQuery(sqlUsersByUserName);
			String uname = rs.getString("userName");
			c.close();
			if (uname.equals(userName)) {
				return true;
			}
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}
	
	@Override
	public void addSurfingPackage(String spName, String supplier, String infrastructure, int price) { //adding surfing package to db
		try {
			conn = getConnection();
			prepStat = conn.prepareStatement(queries.sqlSurfingPackageinsert); 
			prepStat.setString(1, spName);
			prepStat.setString(2, supplier);
			prepStat.setString(3, infrastructure);
			prepStat.setInt(4, price);
			prepStat.executeUpdate();
			prepStat.close();
			conn.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}
	
	@Override
	public void addPurchase(String userName, String spName) { //adding purchase to db
		try {
			conn = getConnection();
			prepStat = conn.prepareStatement(queries.sqlPurchasesinsert);
			prepStat.setString(1, userName);
			prepStat.setString(2, spName);
			prepStat.executeUpdate();
			prepStat.close();
			conn.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}

	@Override
	public ResultSet getUsers() { //get all users from db
		try {
			conn= getConnection();
			state = conn.createStatement();
			rs = state.executeQuery(queries.sqlGetAllUsers);
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return rs;
	}

	@Override
	public ResultSet getSurfingPackages() { //get all surfing packages from db
		try {
			conn= getConnection();
			state = conn.createStatement();
			rs = state.executeQuery(queries.sqlSurffingPackageGetall);
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return rs;
	}

	@Override
	public ResultSet getPurchases() { //get all purchases from db
		try {
			conn= getConnection();
			state = conn.createStatement();
			rs = state.executeQuery(queries.sqlPurchasesGetall);
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return rs;
	}

	@Override
	public boolean validUser(String userName, String password) {
		try {
			conn= getConnection();
			prepStat = conn.prepareStatement(queries.sqlVerifyUser);
			prepStat.setString(1, userName);
			prepStat.setString(2, password);
			rs = prepStat.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return false;
	}


}


