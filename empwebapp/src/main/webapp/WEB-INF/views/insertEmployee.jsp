<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String msg = (String) request.getAttribute("msg");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert Record</title>
</head>
<body>
	<fieldset>
		<legend>Add Details </legend>
		<form action="./add" method="post"  style="text-align: center">
			<!-- <table>
				<tr>
					<td>Employee ID</td>
					<td>:</td>
					<td><input type="number" name="id"></td>
				</tr>
				<tr>
					<td>Employee Name</td>
					<td>:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Employee Date of Birth</td>
					<td>:</td>
					<td><input type="date" name="dob"></td>
				</tr>
				<tr>
					<td>Employee Password</td>
					<td>:</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Register"></td>
				</tr>
			</table> -->
			<table  style="align-items: center">
			<tr>
					<td>Mid</td>
					<td>:</td>
					<td><input type="text" name="mid"></td>
				</tr>
			
			<tr>
					<td>To Email</td>
					<td>:</td>
					<td><input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Subject</td>
					<td>:</td>
					<td><input type="text" name="subject"></td>
				</tr>
				
				<tr>
					<td>Message</td>
					<td>:</td>
					<td><input type="text" name="message"></td>
				</tr>
				
				<tr>
					<td>Status</td>
					<td>:</td>
					<td><input type="text" name="status"></td>
				</tr>
				
				<tr>
					<td><input type="submit" value="send mail"></td>
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