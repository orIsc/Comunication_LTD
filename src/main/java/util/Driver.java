package util;

import com.hit.dao.DbHandle;
import com.hit.dao.DbHandleImpl;
import com.hit.dao.DbQueries;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		DbHandle dbHandle = DbHandleImpl.getInstance();
		DbQueries queries = DbQueries.getInstance();
		
		try {
			Connection c = dbHandle.getConnection();
			//dbHandle.registerUser("roi", "1234498797", "khgjhgs", "sdasdsd");
//			dbHandle.addSurfingPackage("up to 40gb", "bezeq", "bezeq-international", 80);
//			dbHandle.addSurfingPackage("up to 100gb", "bezeq", "bezeq-international", 120);
//			dbHandle.addPurchase("or", "up to 100gb");
//			dbHandle.addPurchase("roi", "up to 40gb");
			Statement stat = c.createStatement();
			ResultSet rs = dbHandle.getUsers();
			while (rs.next()) {
				System.out.println(rs.getString("userName") + "," + rs.getString("password") +
						","  + rs.getString("salt") + ","  + rs.getString("email"));
			}
			rs = dbHandle.getSurfingPackages();
			while (rs.next()) {
				System.out.println(rs.getString("spName") + "," + rs.getString("supplier") +
						","  + rs.getString("infrastructure") + ","  + rs.getString("price"));
			}
			rs = dbHandle.getPurchases();
			while (rs.next()) {
				System.out.println(rs.getString("userName") + "," + rs.getString("spName"));
			}
			rs.close();
			c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
