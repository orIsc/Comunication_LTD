package com.hit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hit.dm.Password_utils;
import com.hit.dao.DbHandle;
import com.hit.dao.DbHandleImpl;

/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Password_utils passUtil = Password_utils.getInstance();
    private DbHandle dbHandle = DbHandleImpl.getInstance();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ForgotPassword.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String randomPassword = passUtil.getSalt(10);
		
		try {
			Connection c = dbHandle.getConnection();
			ResultSet rs = dbHandle.getUsers();
			RequestDispatcher rd = request.getRequestDispatcher("ForgotPassword.jsp"); 
			
			while(rs.next()) {
				if(!email.equals(rs.getString("email"))) {
					request.setAttribute("fpMessage","Invalid email");
				}
				else {
					dbHandle.updatePassword(rs.getString("userName"), randomPassword);
					request.setAttribute("fpMessage","Password sent successfuly to " + email + 
							"random pass is:" + randomPassword);
				}
			}
			rd = request.getRequestDispatcher("ForgotPassword.jsp");            
			rd.include(request, response);
		}catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		
	}

}
