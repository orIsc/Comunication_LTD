<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Forget password</title>
<style>
h1 {text-align: center;}
</style>
</head>
<body>

<form action="ChangePassword" method="POST">
<h1>
Forget password<br>
Enter Current password
<input type="password" name="currentPassword"> <br>
Enter New Password
<input type="password" name="newPassword"><br>
<input type="submit" value="submit"><br>
</h1>
</form>
</body>
</html>