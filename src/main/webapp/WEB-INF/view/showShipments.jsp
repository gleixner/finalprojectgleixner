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
<title>All Current Shipments </title>
</head>
<body>

	<c:if test='${param.name!=null}'>
		<h3>Shipment name ${param.name} has been successfully added</h3>
	</c:if>

	<table>
		<tr>
			<th>Name</th>
			<th>Origin</th>
			<th>Destination</th>
			<th>Volume</th>
		<tr>
		<c:forEach items="${shipments}" var="shipment">
			<tr>
				<td>${shipment.name}</td>
				<td>${shipment.origin}</td>
				<td>${shipment.destination}</td>
				<td>${shipment.volume}</td>
			</tr>
		</c:forEach>	
	</table>

</body>
</html>