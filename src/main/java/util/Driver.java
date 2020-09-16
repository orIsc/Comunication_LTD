package util;

import com.hit.dao.DbHandle;
import com.hit.dao.DbHandleImpl;
import com.hit.dao.DbQueries;
import com.hit.dm.Configuration;
import com.hit.dm.Password_utils;

import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		DbHandle dbHandle = DbHandleImpl.getInstance();
		DbQueries queries = DbQueries.getInstance();
		Password_utils passUtil  = Password_utils.getInstance();
		ResultSet rs = null;
		
		try {
			Connection c = dbHandle.getConnection();
//			dbHandle.registerUser("gili", "Ddddd-1234", passUtil.getSalt(16), "sgha@walla.com");
//			dbHandle.addSurfingPackage("up to 40gb", "bezeq", "bezeq-international", 80);
//			dbHandle.addSurfingPackage("up to 100gb", "bezeq", "bezeq-international", 120);
//			dbHandle.addPurchase("or", "up to 100gb");
//			dbHandle.addPurchase("roi", "up to 40gb");
//			Statement stat = c.createStatement();
//			dbHandle.updatePassword("adi", "Aaaaa-1234");
//			rs = dbHandle.getUsers();
//			while (rs.next()) {
//				System.out.println(rs.getString("userName") + "," + rs.getString("password") +
//						","  + rs.getString("salt") + ","  + rs.getString("email"));
//			}
//			rs = dbHandle.getSurfingPackages();
//			while (rs.next()) {
//				System.out.println(rs.getString("spName") + "," + rs.getString("supplier") +
//						","  + rs.getString("infrastructure") + ","  + rs.getString("price"));
//			}
//			rs = dbHandle.getPurchases();
//			while (rs.next()) {
//				System.out.println(rs.getString("userName") + "," + rs.getString("spName"));
//			}
//			rs = dbHandle.getUser("roi");
//			while (rs.next()) {
//				System.out.println(rs.getString("salt"));
//			}
//			String secPass = passUtil.generateSecurePassword("Bbbbb1234-", "Vo474wXlOCG7eXIm");
//			if(passUtil.verifyUserPassword("Bbbbb1234-", secPass, "Vo474wXlOCG7eXIm")) {
//				System.out.println("Valid");
//				System.out.println(secPass);
//			}else {
//				System.out.println("InValid");
//			}
//			if(dbHandle.isUserExists("dana")) {
//				System.out.println("yes");
//			}else {
//				System.out.println("no");
//			}
//			if(dbHandle.validUser("adi", "Ccccc-1234")) {
//				System.out.println("valid");
//			}else {
//				System.out.println("invalid");
//			}
			System.out.println("test="+Thread.currentThread().getContextClassLoader().getResource("configuration.json"));
			Configuration conf = passUtil.getConfigurations();
			System.out.println(conf);
			//rs.close();
			c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
