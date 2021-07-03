<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String msg = (String) request.getAttribute("msg");
String cookieVal = (String) request.getAttribute("cookie");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cookies</title>
</head>
<body>
	<table>
		<tr>
			<th><a href="./createCookie">Create A Cookie</a></th>
			<th><a href="./readCookie">Read Cookie</a></th>
		</tr>
	</table>

	<%
		if (msg != null && !msg.isEmpty()) {
	%>
	<h1 style="color: green;"><%=msg%>
	</h1>
	<%
		}
	%>
	<%
		if (cookieVal != null && !cookieVal.isEmpty()) {
	%>
	<h1 style="color: green;">Cookie Value  is   <%=cookieVal%>
	</h1>
	<%
		}
	%>
</body>
</html>