<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html  >
<html>
<head>
<style>
body {
	text-align: center;
}
</style>
<link rel="stylesheet" href="<c:url value="resources/myStyle.css" />">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADMIN PAGE</title>
</head>
<body background="<c:url value="resources/images/index11.jpg" />">

	<c:import url="headerAdmin.jsp" />

	<marquee>
		<h1 class="ems2">
			<font color="black" size="8px"> Welcome Admin ${name } </font>
		</h1>
	</marquee>

		<center>
			<h3>
				<a href="add.obj">ADD EMPLOYEE </a>
			</h3>
			<h3>
				<a href="modify.obj">UPDATE EMPLOYEE</a>
			</h3>
			<h3>
				<a href="display.obj">DISPLAY ALL EMPLOYEE DETAILS</a>
			</h3>
			<h3>
				<a href="delete.obj">DELETE EMPLOYEE DETAILS</a>
			</h3>
			<h3>
				<a href="search.obj">SEARCH EMPLOYEE DETAILS</a>
			</h3>
		</center>
	
</body>
</html>