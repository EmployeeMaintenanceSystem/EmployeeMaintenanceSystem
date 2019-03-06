<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body background="<c:url value="resources/images/index11.jpg" />">
		<c:import url="headerAdmin.jsp"></c:import>
		<c:if test="${bean ne null}">
		<center>
			<h1 style="color: white; background-color: black" class="ems">Employee
				Information</h1>
		</center>
		<table border="1" class="trans">
			<tr>
				<td bgcolor="bisque">Employee Id</td>
				<td bgcolor="bisque">Employee First Name</td>
				<td bgcolor="bisque">Employee Last Name</td>
				<td bgcolor="bisque">Date of Birth</td>
				<td bgcolor="bisque">Date of Joining</td>
				<td bgcolor="bisque">Employee Department Id</td>
				<td bgcolor="bisque">Grade</td>
				<td bgcolor="bisque">Employee Designation</td>
				<td bgcolor="bisque">MaritalStatus</td>
				<td bgcolor="bisque">Gender</td>
				<td bgcolor="bisque">Salary</td>
				<td bgcolor="bisque">Address</td>
				<td bgcolor="bisque">PhoneNumber</td>
				<td bgcolor="bisque">Action</td>
				
			</tr>
				<tr>
					<td>${bean.employeeId}</td>
					<td>${bean.firstName}</td>
					<td>${bean.lastName }</td>
					<td>${bean.dateOfBirth }</td>
					<td>${bean.dateOfJoining }</td>
					<td>${bean.departmentId }</td>
					<td>${bean.grade }</td>
					<td>${bean.designation }</td>
					<td>${bean.maritalStatus }</td>
					<td>${bean.gender }</td>
					<td>${bean.salary }</td>
					<td>${bean.address }</td>
					<td>${bean.phoneNumber }</td>
					<td><a href="deleteEmp.obj?empId=${bean.employeeId}"><b style="color: red;">Delete</b></a>
				</tr>
		</table>
		</c:if>
</body>
</html>