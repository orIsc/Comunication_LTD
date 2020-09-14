package com.hit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private DbHandle dbHandle = DbHandleImpl.getInstance();
	 private DbQueries queries = DbQueries.getInstance();
	 private Password_utils passUtils = Password_utils.getInstance();
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		String salt = passUtils.getSalt(16);
		RequestDispatcher rd = null;
		Connection c = dbHandle.getConnection(); 
	    
		if(!dbHandle.isUserExists(uname) && passUtils.validPassword(pass)) {
			dbHandle.registerUser(uname, pass, salt, email);
			response.sendRedirect("Login.jsp");
		}
		else {
			request.setAttribute("regMessage","Invalid Username or Password");
			rd = request.getRequestDispatcher("Register.jsp");            
			rd.include(request, response);
		}
	}

}
