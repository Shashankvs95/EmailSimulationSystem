<%@page import="com.te.empwebapp.beans.mail_info"%>
<%@page import="com.te.empwebapp.beans.EmployeeInfoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
String name = (String) request.getAttribute("name");
List<mail_info> infoBeans = (List) request.getAttribute("infos");
%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search employee page</title>
</head>
<body>
	<fieldset>
		<h1 style="color: maroon; text-decoration: underline;">
			Welcome
			<%=name%></h1>
	</fieldset>

	<%
	if (infoBeans != null) {
	%>
	<fieldset>
		<legend>All Records</legend>
		<table border="1" style="width: 100%">
			<tr>
				<th>Message id</th>
				<th>From :</th>
				<th>To :</th>
				<th>Subject :</th>
				<th>Message :</th>
				<th>Status :</th>
			</tr>
			<%
			for (mail_info infoBean : infoBeans) {
			%>
			<tr>
				<td style="text-align: center;"><%=infoBean.getMid()%></td>
				<td style="text-align: center;"><%=infoBean.getFrom_email()%></td>
				<td style="text-align: center;"><%=infoBean.getTo_email()%></td>
				<td style="text-align: center;"><%=infoBean.getSubject()%></td>
				<td style="text-align: center;"><%=infoBean.getMessage()%></td>
				<td style="text-align: center;"><%=infoBean.getStatus()%></td>
			</tr>
			<%
			}
			%>
		</table>

	</fieldset>
	<%
	}
	%>
</body>
</html>