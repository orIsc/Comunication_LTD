package com.hit.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.hit.dm.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DbHandle dbHandle = DbHandleImpl.getInstance();
    private DbQueries queries = DbQueries.getInstance();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("username");
		String pass = request.getParameter("password");
		
		try {
			Connection c = dbHandle.getConnection();
			ResultSet rs = dbHandle.getUsers();

			if(dbHandle.validUser(uname, pass)) {
				HttpSession session = request.getSession();
				session.setAttribute("userName", uname);
				response.sendRedirect("System.jsp");
			}
			else {
				response.sendRedirect("Login.jsp");
			}
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}