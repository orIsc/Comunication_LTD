package com.hit.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.hit.dao.DbQueries;

public class DbHandle {
	 
	private Connection conn = null;
	private Statement state = null;
	private ResultSet rs = null;	
	private PreparedStatement prepStat = null;
	private DbQueries queries = DbQueries.getInstance();
	
	public Connection getConnection() throws Exception { //Getting connection
		//Connection conn = null;
		try {
		    // db parameters
		    String url       = "jdbc:mysql://localhost:3306/mysqljdbc";
		    String user      = "root";
		    String password  = "p6f76tre";
			
		    // create a connection to the database
		    conn = DriverManager.getConnection(url, user, password);
		   
		} catch(SQLException e) {
		   System.out.println(e.getMessage());
		} finally {
			try{
		           if(conn != null)
		             conn.close();
			}catch(SQLException ex){
		           System.out.println(ex.getMessage());
			}
		}
		return conn;
	}
	
	protected boolean executeQuery(String query, String whosQuery) { //Executing main create table queries
		try {
			conn = getConnection();
			state = conn.createStatement();
			state.executeUpdate(query);
			conn.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());		
			}
		return false;
	}

	public boolean registerUser(String userName, String pass, String salt, String email) { //Registring user to our system
		boolean status = false; 
		try {
			conn = getConnection();
			prepStat = conn.prepareStatement(queries.sqlUsersinsert);
			prepStat.setString(1, userName);
			prepStat.setString(2, pass);
			prepStat.setString(3, salt);
			prepStat.setString(4, email);
			status = isUserExists(email, conn);
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

	public boolean isUserExists(String email, Connection conn) { //checking if user already in db by phone
		Connection c = conn;
		String sqlUsersselect = "Select phone from Users where phone='" + email + "'";
		try {
			state = c.createStatement();
			ResultSet rs = state.executeQuery(sqlUsersselect);
			String phonenum = rs.getString("email");
			c.close();
			if (phonenum.equals(email)) {
				return true;
			}
		} catch (Exception e) {
			e.toString();
		}
		return false;
	}
	
	public void addSurfingPackage(String spName, String supplier, String infrastructure, int price) {
		
	}
}
