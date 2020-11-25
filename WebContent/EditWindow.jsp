<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Management Utility</title>
</head>
<body>
<div align="center">
<h1><font face = "Times New Roman" size = "5">Change the name of the Image</font></h1>
</div>

<%
	String id = request.getParameter("id");
	String oldName = request.getParameter("oldName");
%>

<form method="post" action="edit">
<input type="hidden" name="imageId" value="<%= id %>">
<h3><font face = "Times New Roman" size = "4">Enter New Name of Image:</font></h3>
<br>

<input type="text" name="newName" size = "50" value="<%= oldName %>" style="overflow: auto">

</form>


</body>
</html>