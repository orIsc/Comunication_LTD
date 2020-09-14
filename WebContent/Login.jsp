<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.hit.dao.DbHandle"
import="com.hit.dao.DbHandleImpl" errorPage="Error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>
h1 {text-align: center;}
h2 {text-align: center;}
h3 {text-align: center;}
h4 {text-align: center;}
h5 {text-align: center;}
</style>
</head>
<body>
<form action = "Login" method="POST">
<h1>
Login Page
</h1>
<h2>
Enter User Name
<input type="text" name="username"> <br>
Enter Password
<input type="password" name="password"><br>
<input type="submit" value="Login"><br>
</h2>
<div id="passMessage"></div> 
<h4>
<%
String login_msg=(String)request.getAttribute("passMessage");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
</h4>
</form>
<form action = "RegisterPage">
<h3>
Register new user
<input type="submit" value="Register"><br>
</h3>
</form>
<form action="ForgetPassword" method="get">
<h5>
<input type="submit" value="Forget Password"><br>
</h5>
</form>
</body>
</html>