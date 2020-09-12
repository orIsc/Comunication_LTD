<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>
<style>
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

<form action="ChangePassword" method="POST">
<h1>
Change password<br>
Enter Current password
<input type="password" name="currentPassword"> <br>
Enter New Password
<input type="password" name="newPassword"><br>
<input type="submit" value="submit"><br>
${changePasswordMessage}
</h1>
</form>
</body>
</html>