package com.hit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hit.dao.DbQueries;
import com.hit.dm.Password_utils;
import com.hit.dm.User;

public class DbHandleImpl implements DbHandle {
	 
	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;	
	private PreparedStatement prepStat = null;
	private DbQueries queries = DbQueries.getInstance();
	private static DbHandleImpl instance;
	private Password_utils passUtil = Password_utils.getInstance();
	
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
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(path, user, pass);   
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
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
	public void registerUser(String userName, String pass, String salt, String email) { //Registring user to db
		String secPass;
		
		try {
			conn = getConnection();
			if (!isUserExists(userName)) {
				secPass = passUtil.generateSecurePassword(pass, salt);
				prepStat = conn.prepareStatement(queries.sqlUsersinsert);
				prepStat.setString(1, userName);
				prepStat.setString(2, secPass);
				prepStat.setString(3, salt);
				prepStat.setString(4, email);
				prepStat.executeUpdate();
				System.out.println("User inserted successfully");			
			}
			else {
				System.out.println("User name is allready in use");
			}
			prepStat.close();
			conn.close();	
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
	}

	@Override
	public boolean isUserExists(String userName) { //checking if user already in db by userName
		String sqlGetUserByName =  "SELECT * FROM users where userName=?";
		String uname = "";
		try {
			conn = getConnection();
			rs = getUser(userName);
			while(rs.next()) {
				uname = rs.getString("userName");
			}
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
		String salt = "";
		String secPass;
		
		try {
			conn= getConnection();
			rs = getUser(userName);
			while(rs.next()) {
				salt = rs.getString("salt");
			}
			secPass = passUtil.generateSecurePassword(password, salt);
			prepStat = conn.prepareStatement(queries.sqlVerifyUser);
			prepStat.setString(1, userName);
			prepStat.setString(2, secPass);
			rs = prepStat.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return false;
	}

	@Override
	public boolean updatePassword(String userName, String password) {
		String sqlChangePassword = "UPDATE users SET password=? where userName=?";
		String secPass;
		String salt = "";
		
		try {
			conn = getConnection();
			rs = getUser(userName);
			while(rs.next()) {
				salt = rs.getString("salt");
			}
			secPass = passUtil.generateSecurePassword(password, salt);
			prepStat = conn.prepareStatement(sqlChangePassword);
			prepStat.setString(1, secPass);
			prepStat.setString(2, userName);
			prepStat.executeUpdate();
			prepStat.close();
			conn.close();			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return false;
	}

	@Override
	public ResultSet getUser(String userName) {
		String sqlGetUserByName =  "SELECT * FROM users where userName=?";
		try {
			conn= getConnection();	
			prepStat = conn.prepareStatement(sqlGetUserByName);
			prepStat.setString(1, userName);
			rs = prepStat.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());	
		}
		return rs;
	}


}


