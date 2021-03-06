<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.hit.dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System</title>
<style>
@import url('https://fonts.googleapis.com/css?family=Encode+Sans+Condensed:400,600');

* {
  outline: none;
}

strong {
  font-weight: 600;
}

.page {
  width: 100%;
  height: 100vh;
  background: #fdfdfd;
  font-family: 'Encode Sans Condensed', sans-serif;
  font-weight: 600;
  letter-spacing: .03em;
  color: #212121;
}

header {
  display: flex;
  position: fixed;
  width: 100%;
  height: 70px;
  background: #212121;
  color: #fff;
  justify-content: center;
  align-items: center;
  -webkit-tap-highlight-color: rgba(0,0,0,0);
}

.button {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  z-index: 1;
  -webkit-appearance: none;
  border: 0;
  background: transparent;
  border-radius: 0;
  height: 70px;
  width: 30px;
  cursor: pointer;
  pointer-events: auto;
  margin-left: 25px;
  touch-action: manipulation;
  -webkit-tap-highlight-color: rgba(0,0,0,0);
}
.icon-bar {
  display: block;
  width: 100%;
  height: 3px;
  background: #aaa;
  transition: .3s;
}
.icon-bar + .icon-bar {
  margin-top: 5px;
}

#nav-container:focus-within .button {
  pointer-events: none;
}
#nav-container:focus-within .icon-bar:nth-of-type(1) {
  transform: translate3d(0,8px,0) rotate(45deg);
}
#nav-container:focus-within .icon-bar:nth-of-type(2) {
  opacity: 0;
}
#nav-container:focus-within .icon-bar:nth-of-type(3) {
  transform: translate3d(0,-8px,0) rotate(-45deg);
}

#nav-content {
  margin-top: 70px;
  padding: 20px;
  width: 90%;
  max-width: 300px;
  position: absolute;
  top: 0;
  left: 0;
  height: calc(100% - 70px);
  background: #ececec;
  pointer-events: auto;
  -webkit-tap-highlight-color: rgba(0,0,0,0);
  transform: translateX(-100%);
  transition: transform .3s;
  will-change: transform;
  contain: paint;
}

#nav-content ul {
  height: 100%;
  display: flex;
  flex-direction: column;
}

#nav-content li a {
  padding: 10px 5px;
  display: block;
  text-transform: uppercase;
  transition: color .1s;
}

#nav-content li a:hover {
  color: #BF7497;
}

#nav-content li:not(.small) + .small {
  margin-top: auto;
}

.small {
  display: flex;
  align-self: center;
}

.small a {
  font-size: 12px;
  font-weight: 400;
  color: #888;
}
.small a + a {
  margin-left: 15px;
}

#nav-container:focus-within #nav-content {
  transform: none;
}

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

html, body {
  height: 100%;
}

a,
a:visited,
a:focus,
a:active,
a:link {
  text-decoration: none;
  outline: 0;
}

a {
  color: currentColor;
  transition: .2s ease-in-out;
}

h1, h2, h3, h4 {
  margin: 0;
}

ul {
  padding: 0;
  list-style: none;
}

.img-responsive {
    height: auto;
    width: auto;
    max-height: 70px;
    max-width: 250px;
}

header {
  color:#3498db;
  font-size:30px;
}
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
<div class="page">
  <header tabindex="0"><img src="resources/comltdlogo-2.png" class="img-responsive" alt="">Comunication_LTD</header>
  <div id="nav-container">
    <div class="bg"></div>
    <div class="button" tabindex="0">
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </div>
    <div id="nav-content" tabindex="0">
      <ul>
        <li><a class="active" href="System.jsp">Home</a></li>
        <li><a href="ChangePassword">Change password</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="Logout">Logout</a></li>
        <li class="small"><a href="#0">Facebook</a><a href="#0">Instagram</a></li>
      </ul>
    </div>
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
