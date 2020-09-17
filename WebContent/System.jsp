<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.hit.dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System</title>
<style>
body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.topnav {
  overflow: hidden;
  background-color: #333;
}

.topnav a {
  float: left;
  color: #f2f2f2;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a:hover {
  background-color: #ddd;
  color: black;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
h1 {text-align: center;}
</style>
</head>
<body>

<% 

response.setHeader("Cache-Control", "no-cache, no-store, revalidate");
response.setHeader("pragma", "no-cache");
response.setHeader("Expires", "0");

if(session.getAttribute("userName") == null) {
		response.sendRedirect("Login.jsp");
	}  
%>
<div class="topnav">
  <a class="active" href="System.jsp">Home</a>
  <a href="ChangePassword">Change password</a>
  <a href="Logout">Logout</a>
  <a href="#about">About</a>
</div>
<h1>
Comunication LTD
</h1>
<table width="59%" border="1" align="center">
	 <thead>
    <tr>
        <td>Limit</td>
        <td>Supplier</td>
        <td>Infrastructure</td>
        <td>Price</td>
    </tr>
    </thead>
    <%
      DbHandle dbHandle = DbHandleImpl.getInstance();
      ResultSet rs = dbHandle.getSurfingPackages();
      ResultSetMetaData metaData = rs.getMetaData();
        while(rs.next())
        {
            %>
                <tr>
                 <%
                 for(int i = 1; i<=metaData.getColumnCount();i++)
                    { %>
                     <td>
                     <%= rs.getString(i)%>
                     </td>
                <% 
                    }
                %>                   
                </tr>
            <% 
        }
    %>
</table>
</body>
</html>