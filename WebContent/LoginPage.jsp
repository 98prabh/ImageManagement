<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<Title>Image Management Utility </Title>
<link rel="stylesheet"
 href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>

<style>.form-group:before {
    content:" *";
    color: red;
    }</style>
    
<body>
<div class="container col-md-8 col-md-offset-3" style="overflow: auto">
<hr>
 <div align="center">
 <h1><font face = "Times New Roman" size = "6">Login Form</font></h1>
 </div>
<hr>

  <form action="<%=request.getContextPath()%>/login" method="post">
 
   <div class="form-group" >
    <label for="uname">Enter Username:</label>
    <input type="text"
     class="form-control" id="username" placeholder="Username"
     name="username" required>
   </div>
 
   
   <div class="form-group">
    <label for="uname">Enter Password:</label>
    <input type="password"
     class="form-control" id="password" placeholder="Password"
     name="password" required>
   </div>
   
   
   <hr>
   
   	<div>
   		<span style="color: red"><%=(request.getAttribute("LoginResult") == null) ? "" : request.getAttribute("LoginResult")%></span>
   	</div>
   	
   <div align="center">
   <button type="submit" class="btn btn-primary" >Login</button>
   </div>
   
  </form>
 </div>
</body>
</html>