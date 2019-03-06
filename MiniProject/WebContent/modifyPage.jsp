<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="<c:url value="resources/myStyle.css" />">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Employee Details</title>
</head>
<body background="<c:url value="resources/images/index11.jpg" />">
	<c:import url="headerAdmin.jsp"></c:import>
	<center>
		<h1 style="color: white; background-color: black" class="ems">Modify
			Employee Details</h1>
	</center>
	<c:if test="${temp==0 }">
		<s:form action="modifyDetails.obj" modelAttribute="bean" method="post">
			<center>
				<h4 style="color: red;">
				<c:if test="${message ne null}">
					<span>${message}</span>
				</c:if>
				</h4>
				<table class="trans">
					<tr>
						<td>Enter Employee ID :</td>
						<td><s:input path="employeeId" /></td>
						<td style="color: red"><s:errors path="employeeId"></s:errors></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="MODIFY" /></td>
					</tr>

				</table>
			</center>
		</s:form>

	</c:if>
	<c:if test="${temp>0}">

		<s:form action="update.obj" modelAttribute="employee" method="post">
			<center>
				<h2 style="color: red;">
					<c:if test="${message ne null}">
						<div>${message}</div>
					</c:if>
				</h2>
				<table class="trans">
					<tr>
						<td>Employee Id :</td>
						<td><s:input path="employeeId" value="${employee.employeeId}"
								readonly="true" /></td>
						<td><s:errors path="employeeId"></s:errors></td>
					</tr>
					<tr>
						<td>Employee First Name :</td>
						<td><s:input path="firstName" value="${employee.firstName}" />
						</td>
						<td><s:errors path="firstName"></s:errors></td>
					</tr>
					<tr>
						<td>Employee Last Name :</td>
						<td><s:input path="lastName" value="${employee.lastName}" />
						</td>
						<td><s:errors path="lastName"></s:errors></td>
					</tr>
					<tr>
						<td>Date of Birth : "yyyy-mm-dd"</td>
						<td><s:input path="dateOfBirth"
								value="${employee.dateOfBirth}" type="date" /></td>
						<td><s:errors path="dateOfBirth"></s:errors></td>
					</tr>
					<tr>
						<td>Date of Joining : "yyyy-mm-dd"</td>
						<td><s:input path="dateOfJoining"
								value="${employee.dateOfJoining}" type="date" /></td>
						<td><s:errors path="dateOfJoining"></s:errors></td>
					</tr>
					<tr>
						<td>Employee Department Id :</td>
						<td><s:input path="departmentId"
								value="${employee.departmentId}" /></td>
						<td><s:errors path="departmentId"></s:errors></td>
					</tr>
					<tr>
						<td>Grade :</td>
						<td><s:input path="grade" value="${employee.grade}" /></td>
						<td><s:errors path="grade"></s:errors></td>
					</tr>
					<tr>
						<td>Employee Designation :</td>
						<td><s:input path="designation"
								value="${employee.designation}" /></td>
						<td><s:errors path="designation"></s:errors></td>
					</tr>
					<tr>
						<td>MaritalStatus :</td>
						<td><s:input path="maritalStatus"
								value="${employee.maritalStatus}" /></td>
						<td><s:errors path="maritalStatus"></s:errors></td>
					</tr>
					<tr>
						<td>Gender :</td>
						<td><s:input path="gender" value="${employee.gender}" /></td>
						<td><s:errors path="gender"></s:errors></td>
					</tr>
					<tr>
						<td>Salary :</td>
						<td><s:input path="salary" value="${employee.salary}" /></td>
						<td><s:errors path="salary"></s:errors></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><s:input path="address" value="${employee.address}" /></td>
						<td><s:errors path="address"></s:errors></td>
					</tr>
					<tr>
						<td>PhoneNumber :</td>
						<td><s:input path="phoneNumber"
								value="${employee.phoneNumber}" /></td>
						<td><s:errors path="phoneNumber"></s:errors></td>
					</tr>
					<tr>
						<td><input type="submit" value="UPDATE EMPLOYEE" /></td>
					</tr>
				</table>
			</center>
		</s:form>

	</c:if>

</body>
</html>