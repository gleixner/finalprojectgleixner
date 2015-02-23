<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<style>
	table, th, td {
	    border: 1px solid black;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Containers</title>
</head>
<body>

	<h1>Containers</h1>
	<hr/>

	<table>
		<tr>
			<th> Name </th>
			<th> Capacity </th>
			<th> Location </th>
			<th> Destination </th>
			<th> Status </th>
		<tr>
		<c:forEach items="${containers}" var="container">
			<tr>
				<td>${container.name}</td>
				<td>${container.capacity}</td>
				<td>${container.location}</td>
				<td>${container.destination}</td>
				<td>${container.status}</td>
			</tr>
		</c:forEach>	
	</table>

</body>
</html>