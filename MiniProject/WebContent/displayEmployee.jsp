<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="<c:url value="resources/myStyle.css" />">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DISPLAY</title>
</head>
<body background="<c:url value="resources/images/index11.jpg" />">
	<c:import url="headerAdmin.jsp"></c:import>
	<center>
		<h1 style="color: white; background-color: black" class="ems">Employee
			Records</h1>
	</center>
	<br>
	<table border="1" align="center" class="trans">
		<tr>
			<th bgcolor="bisque">Employee Id</th>
			<th bgcolor="bisque">Employee First Name</th>
			<th bgcolor="bisque">Employee Last Name</th>
			<th bgcolor="bisque">Date of Birth</th>
			<th bgcolor="bisque">Date of Joining</th>
			<th bgcolor="bisque">Department Id</th>
			<th bgcolor="bisque">Grade</th>
			<th bgcolor="bisque">Designation</th>
			<th bgcolor="bisque">Basic salary</th>
			<th bgcolor="bisque">Gender</th>
			<th bgcolor="bisque">MaritalStatus</th>
			<th bgcolor="bisque">Address</th>
			<th bgcolor="bisque">Phone Number</th>
			<th bgcolor="bisque">Action</th>
			<c:forEach var="employeeList" items="${list}">
				<tr>
					<td>${employeeList.employeeId }</td>
					<td>${employeeList.firstName}</td>
					<td>${employeeList.lastName}</td>
					<td>${employeeList.dateOfBirth }</td>
					<td>${employeeList.dateOfJoining }</td>
					<td>${employeeList.departmentId }</td>
					<td>${employeeList.grade }</td>
					<td>${employeeList.designation }</td>
					<td>${employeeList.salary }</td>
					<td>${employeeList.gender}</td>
					<td>${employeeList.maritalStatus }</td>
					<td>${employeeList.address }</td>
					<td>${employeeList.phoneNumber}</td>
					<td><a
						href="deleteEmployee.obj?empId=${employeeList.employeeId }"><b
							style="color: red;">Delete</b></a></td>
				</tr>
			</c:forEach>
	</table>
</body>
</html>