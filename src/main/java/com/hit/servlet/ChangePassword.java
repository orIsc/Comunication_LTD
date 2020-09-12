package com.hit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hit.dao.DbHandle;
import com.hit.dao.DbHandleImpl;
import com.hit.dao.DbQueries;
import com.hit.dm.Password_utils;
import com.hit.dm.User;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DbHandle dbHandle = DbHandleImpl.getInstance();
	 private DbQueries queries = DbQueries.getInstance();
	 private Password_utils passUtils = Password_utils.getInstance();
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("ChangePassword.jsp");
	 }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currPass = request.getParameter("currentPassword");
		String newPass = request.getParameter("newPassword");
		String name;
		
		try {
			Connection c = dbHandle.getConnection();
			ResultSet rs = dbHandle.getUsers();
			HttpSession session = request.getSession();
			name = (String) request.getAttribute("userName");
			rs = dbHandle.getUser(name);
			
			if(passUtils.verifyUserPassword(currPass, rs.getString("password"), rs.getString("salt"))) {
				System.out.println("valid curr");
				if(passUtils.validPassword(newPass)) {
					System.out.println("valid new");
					request.setAttribute("changePasswordMessage", "Password accepted");
					response.sendRedirect("System.jsp");
				}
				else {
					System.out.println("invalid");
					request.setAttribute("changePasswordMessage", "Invalid password");
					response.sendRedirect("ChangePassword.jsp");
				}
			}
			else {
				request.setAttribute("changePasswordMessage", "Invalid  current password");
				response.sendRedirect("ChangePassword.jsp");
			}	
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
