<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System</title>
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
System page 

<form action="Logout">
<input type="submit" value="Logout">
</form>
</body>
</html>