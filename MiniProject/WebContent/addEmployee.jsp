<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="resources/myStyle.css">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD DETAILS</title>
</head>
<body background="resources/images/index11.jpg">

	<c:import url="headerAdmin.jsp"></c:import>
	<center>
		<h2 style="color: red;">
			<c:if test="${message ne null}">
				<span>${message}</span>
			</c:if>
		</h2>
		<c:if test="${temp==0}">
			<center>
				<h1 style="color: white; background-color: black" class="ems">Add
					Employee Details</h1>
			</center>
			<table class="trans">
				<s:form action="addEmployee.obj" modelAttribute="employee"
					method="post">
					<tr>
						<td>First Name : <span style="color: red;">*</span></td>
						<td><s:input path="firstName" /></td>
						<td style="color: red"><s:errors path="firstName"></s:errors></td>
					</tr>
					<tr>
						<td>Last Name : <span style="color: red;">*</span></td>
						<td><s:input path="lastName" /></td>
						<td style="color: red"><s:errors path="lastName"></s:errors></td>
					</tr>
					<tr>
						<td>Date of Birth : "yyyy-mm-dd" <span style="color: red;">*</span></td>
						<td><s:input path="dateOfBirth" required="true" type="date" /></td>
						<td style="color: red"><s:errors path="dateOfBirth"></s:errors></td>
					</tr>
					<tr>
						<td>Date of Joining : "yyyy-mm-dd" <span style="color: red;">*</span></td>
						<td><s:input path="dateOfJoining" required="true" type="date" /></td>
						<td style="color: red"><s:errors path="dateOfJoining"></s:errors></td>
					</tr>
					<tr>
						<td>Department Id :</td>
						<td><s:input path="departmentId" /></td>
						<td style="color: red"><s:errors path="departmentId"></s:errors></td>
					</tr>
					<tr>
						<td>Grade : <span style="color: red;">*</span></td>
						<td><s:select path="grade">
								<s:option value="Select Grade">
								</s:option>
								<s:options items="${grade}"></s:options>
							</s:select></td>
						<td style="color: red"><s:errors path="grade"></s:errors></td>
					</tr>
					<tr>
						<td>Designation :</td>
						<td><s:input path="designation" /></td>
						<td style="color: red"><s:errors path="designation"></s:errors></td>
					</tr>


					<tr>
						<td>MaritalStatus : <span style="color: red;">*</span></td>
						<td><s:select path="maritalStatus">
								<s:option value="select Marital Status"></s:option>
								<s:option value="Single">Single</s:option>
								<s:option value="Married">Married</s:option>
								<s:option value="Divorced">Divorced</s:option>
								<s:option value="Widowed">Widowed</s:option>
								<s:option value="Separated">Separated</s:option>
							</s:select></td>
						<td style="color: red"><s:errors path="maritalStatus"></s:errors></td>
					</tr>
					<tr>
						<td>Gender : <span style="color: red;">*</span></td>
						<td><s:radiobutton path="gender" value="M" />Male <s:radiobutton
								path="gender" value="F" />Female</td>
						<td style="color: red"><s:errors path="gender"></s:errors></td>
					</tr>
					<tr>
						<td>Salary : <span style="color: red;">*</span></td>
						<td><s:input path="salary" /></td>
						<td style="color: red"><s:errors path="salary"></s:errors></td>
					</tr>
					<tr>
						<td>Address :</td>
						<td><s:input path="address" /></td>
						<td style="color: red"><s:errors path="address"></s:errors></td>
					</tr>
					<tr>
						<td>PhoneNumber :</td>
						<td><s:input path="phoneNumber" /></td>
						<td style="color: red"><s:errors path="phoneNumber"></s:errors></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;"><input
							type="submit" value="ADD EMPLOYEE" /></td>
					</tr>
				</s:form>
			</table>
		</c:if>
		<c:if test="${temp==1 }">
			<h1 style="color: green; background-color: white" class="ems">
				Employee Details Added Successfully EMPLOYEE ID : ${ id } and PASSWORD :${ pwd }</h1>

		</c:if>
	</center>
</body>
</html>