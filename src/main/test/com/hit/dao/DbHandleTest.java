package com.hit.dao;

import static org.junit.Assert.assertNotNull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class DbHandleTest {

	private DbHandle dbHandle;
	private Connection c = null; 
	private Statement state = null;
	private ResultSet rs = null;
	private DbQueries queries;
	
	@Before
	public void beforeTests() {
		dbHandle = DbHandleImpl.getInstance();
		assertNotNull(dbHandle);
		queries = DbQueries.getInstance(); 
		assertNotNull(queries);
	}
	
	@Test
	void getConnectionTest() {
		c = dbHandle.getConnection();
		assertNotNull(c);
		try {
			state = c.createStatement();
			rs = state.executeQuery(queries.sqlGetAllUsers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void afterTests() {
		System.out.println(rs);
		try {
			rs.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
