package com.hit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DbHandle dbHandle = DbHandleImpl.getInstance();
    private DbQueries queries = DbQueries.getInstance();
    private Password_utils passUtils = Password_utils.getInstance();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		Connection c = dbHandle.getConnection();
		ResultSet rs = dbHandle.getUser(uname);

		if(dbHandle.validUser(uname, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", uname);
			response.sendRedirect("System.jsp");
		}
		else {
			response.sendRedirect("Login.jsp");
		}
	}

	public static String getTimeStamp() {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy_HH:mm:ss");
		Calendar calobj = Calendar.getInstance();
		String dp = df.format(calobj.getTime());
		return dp;
	}
}