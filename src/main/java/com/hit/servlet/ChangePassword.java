package com.hit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
	 private String name = "";
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.sendRedirect("ChangePassword.jsp");
	 }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currPass = request.getParameter("currentPassword");
		String newPass = request.getParameter("newPassword");
		
		try {
			Connection c = dbHandle.getConnection();
			HttpSession session = request.getSession();
			RequestDispatcher rd = null;
			name = (String) session.getAttribute("userName");
			
			if(dbHandle.validUser(name, currPass)) {
				if(passUtils.validPassword(newPass)) {
					dbHandle.updatePassword(name, newPass);
					request.setAttribute("changePasswordMessage", "Password accepted");
				}
				else {
					request.setAttribute("changePasswordMessage", "Invalid new password");
				}
			}
			else {
				request.setAttribute("changePasswordMessage", "Invalid  current password");
			}	
			rd=request.getRequestDispatcher("ChangePassword.jsp");            
			rd.include(request, response);
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
