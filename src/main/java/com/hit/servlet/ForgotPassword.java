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
import com.hit.email.EmailUtility;
import com.hit.dao.DbHandle;
import com.hit.dao.DbHandleImpl;
import javax.servlet.ServletContext;


/**
 * Servlet implementation class ForgetPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Password_utils passUtil = Password_utils.getInstance();
    private DbHandle dbHandle = DbHandleImpl.getInstance();
    private String host;
    private String port;
    private String user;
    private String pass;
    

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = "smtp.gmail.com";
        port = "587";
        user = "comltd17@gmail.com";
        pass = "comunication";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ForgotPassword.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String randomPassword = passUtil.getSalt(10);
		String userName = "";
		boolean flag = false;
		
		try {
			Connection c = dbHandle.getConnection();
			ResultSet rs = dbHandle.getUsers();
			RequestDispatcher rd = request.getRequestDispatcher("ForgotPassword.jsp"); 
			
			while(rs.next()) {
				if(rs.getString("email").equals(email) ) {
					userName = rs.getString("userName");
				}
			}
			if(dbHandle.isEmailExist(email) ) {
				dbHandle.updatePassword(userName, randomPassword);
				request.setAttribute("fpMessage","Password sent successfuly");
				String subject = "Forgot Password";
		        String content = "Authentication succeeded your random password is: " + randomPassword; 
		 
		        try {
		            EmailUtility.sendEmail(host, port, user, pass, email, subject,
		                    content);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        } 
			}
			else {
				request.setAttribute("fpMessage","Invalid email");
			}
			getServletContext().getRequestDispatcher("/ForgotPassword.jsp").forward(
                    request, response);
			rd.include(request, response);
		}catch(Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		
	}

}
