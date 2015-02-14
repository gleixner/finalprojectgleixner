<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add a Card</title>
	<link href=<c:url value="/resources/css/addProduct.css" /> rel="stylesheet" type="text/css">
</head>
<body>
	<h2>Add a Card for <sec:authentication property='principal.username'/></h2>
	<hr/>
	
		<form:form method="POST" commandName="card">
		<table>
			<tr>
				<td>Name:</td> 
				<td><form:input path="name" /> </td>
			</tr>
			<tr>
				<td>Number: </td>
				<td> <form:input path="number"/> </td>
			</tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Add a Card"/>
		        </td>
		    </tr>
		</table>
	</form:form>
	
</body>
</html>