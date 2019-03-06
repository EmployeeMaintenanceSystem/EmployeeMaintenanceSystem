<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/myStyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index Page</title>
</head>
<body background="<c:url value='resources/images/index11.jpg' />">
	
	<center>
		<h1 style="color: white; background-color: black">Employee
			Maintenance System</h1>
	</center>
	<a href="login.obj"><img class="logout"
		src="resources/images/login-button-png-4.jpg" align="top" height="40"
		width="150"></a>
	<c:import url="footer.jsp"/>
</body>
</html>