<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forget password</title>
<style>
h1 {text-align: center;}
h2 {text-align: center;}
</style>
</head>
<body>
<form action="ForgetPassword" method="POST">
<h1>
Forget password<br>
Enter Email
<input type="text" name="email"> <br>
<input type="submit" value="confirm"><br>
</h1>
</form>
<div id="fpMessage"></div> 
<h2>
<%
String login_msg=(String)request.getAttribute("fpMessage");  
if(login_msg!=null)
out.println("<font color=red size=4px>"+login_msg+"</font>");
%>
</h2>
</body>
</html>