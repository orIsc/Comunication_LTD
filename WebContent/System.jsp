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
  #container{
                margin:0 auto;
                width:80%;
                overflow:auto;
            }
            table.gridtable {
                margin:0 auto;
                width:95%;
                overflow:auto;
                font-family: helvetica,arial,sans-serif;
                font-size:14px;
                color:#333333;
                border-width: 1px;
                border-color: #666666;
                border-collapse: collapse;
                text-align: center;
            } 
            table.gridtable th {
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #666666;
                background-color: #F6B4A5;
            }
            table.gridtable td {
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #666666;
            }
            tr:hover {background-color: #D8DA5C}
h2 {text-align: center;}
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
<h3>
Welcome ${userName}
</h3>
<h1>
Comunication LTD
</h1>
<form action="AddPurchase" method="POST">
<div class="container" id="container">
<table class="gridtable" id="tableMain" width="59%" border="1" align="center" >
	 <thead>
    <tr class="tableheader">
        <td>Limit</td>
        <td>Supplier</td>
        <td>Infrastructure</td>
        <td>Price</td>
    </tr>
    </thead>
    <tbody>
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
</tbody>
</table>
</div>
<h2>
<button id="Add to purchases" type="button">Add to purchases</button>		
</h2>	
</form>	
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<script>		
            $(document).ready(function () {	
          
				$('#tableMain tbody').on('click', 'tr', function() {
                    var tableData = $(this).children("td").map(function() {
                        return $(this).text();
                    }).get();
                    var td=tableData[0];
                    alert(td);
				});
	
			});	
		</script>
		
</body>
</html>
