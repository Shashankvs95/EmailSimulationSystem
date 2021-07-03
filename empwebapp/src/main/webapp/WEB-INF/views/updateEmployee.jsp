	<%@page import="org.jboss.jandex.TypeTarget.Usage"%>
<%@page import="com.te.empwebapp.beans.EmployeeInfoBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String msg = (String) request.getAttribute("msg");
EmployeeInfoBean infoBean = (EmployeeInfoBean) request.getAttribute("id");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Data</title>
</head>
<body>
	<fieldset style="text-align: center">
		<legend>Add Details </legend>
		<form action="./update" method="post">
			<table>
				<tr>
					<td>Employee ID</td>
					<td>:</td>
					<td><%=infoBean.getId()	%></td>
					<td><input type="hidden" name="id" value=<%=infoBean.getId()%>></td>
				</tr>
				<tr>
					<td>Employee Name</td>
					<td>:</td>
					<td><input type="text" name="name"
						value=<%=infoBean.getName()%>></td>
				</tr>
				<tr>
					<td>Employee Date of Birth</td>
					<td>:</td>
					<td><input type="date" name="dob"
						value=<%=infoBean.getDob()%>></td>
				</tr>
				<tr>
					<td>Employee Password</td>
					<td>:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Register"></td>
				</tr>
			</table>
		</form>
	</fieldset>

	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h1 style="color: magenta;"><%=msg%>
	</h1>
	<%
		}
	%>

</body>
</html>