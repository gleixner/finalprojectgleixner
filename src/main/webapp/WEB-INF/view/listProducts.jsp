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
<title>Products</title>
</head>
<body>

	<h2> Products Available </h2>

	<table>
		<tr>
			<th> Name </th>
			<th> Description </th>
			<th> Price </th>
		<tr>
		<c:forEach items="${products}" var="product">
			<tr>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
		    	<td><a href='<c:url value="/product/edit.html?id=${product.id}"/>'>Edit</a> </td>
		    	<td><a href='<c:url value="/product/remove.html?id=${product.id}"/>'>Remove</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<c:if test="${page>0}">
					<a href="<c:url value="/product/list.html?page=${page-1}"/>">Previous</a>
				</c:if>
			</td>
			<td></td>
			<td>
				<a href="<c:url value="/product/list.html?page=${page+1}"/>">Next</a>
			<td>
		</tr>
	</table>

</body>
</html>