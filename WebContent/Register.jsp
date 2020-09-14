<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<style>
h1 {text-align: center;}
h2 {text-align: center;}
h3 {text-align: center;}
</style>
</head>
<body>
<form action = "Register" method="POST">
<h1>
Register Page
</h1>
<h2>
Enter User Name
<input type="text" name="username"> <br>
Enter Password
<input type="password" name="password"><br>
Enter Email
<input type="text" name="email"><br>
<input type="submit" value="Register"><br>
</h2>
</form>
<div id="regMessage"></div> 
<h3>
<%
String login_msg=(String)request.getAttribute("regMessage");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
</h3>
</body>
</html>