<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<!-- 	<link rel="stylesheet" href="//cdn.datatables.net/1.10.5/css/jquery.dataTables.min.css"> -->
<!-- 	<script src="//cdn.datatables.net/1.10.5/js/jquery.dataTables.min.js" type="text/javascript"></script> -->
	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Containers</title>
</head>
<body>
	<c:choose>
		<c:when test="${(criteria.name == null || criteria.name == '') && (criteria.location == null || criteria.location == '')}">
			<h1>Containers</h1>
		</c:when>
		<c:otherwise>
			<h1>Search Results</h1>
		</c:otherwise>
	</c:choose>
			<hr/>

	<table id="myTable"> 
		<thead>
			<tr>
				<th> Name </th>
				<th> Capacity </th>
				<th> Rate </th>
				<th> Location </th>
				<th> Destination </th>
				<th> Status </th>
				<th> Edit </th>
				<th> Remove </th>
			</tr>
		</thead>
		<c:forEach items="${containers}" var="container">
			<tr>
				<td>${container.name}</td>
				<td>${container.capacity}</td>
				<td>${container.rate}</td>
				<td>${container.location}</td>
				<td>${container.destination}</td>
				<td>${container.status}</td>
				<td><a href='<c:url value="/container/edit.html?id=${container.id}"/>'>Edit</a> </td>
		    	<td><a href='<c:url value="/container/remove.html?id=${container.id}"/>'>Remove</a></td>
			</tr>
		</c:forEach>
<!-- 		</tbody> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<%-- 				<c:if test="${page>0}"> --%>
<%-- 					<a href="<c:url value="/container/list.html?page=${page-1}"/>">Previous</a> --%>
<%-- 				</c:if> --%>
<!-- 			</td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td> -->
<!-- 			<td></td>		 -->
<!-- 			<td> -->
<%-- 				<c:if test="${more}"> --%>
<%-- 					<a href="<c:url value="/container/list.html?page=${page+1}"/>">Next</a> --%>
<%-- 				</c:if> --%>
<!-- 			<td> -->
<!-- 		</tr>	 -->
	</table>
	
	<c:if test="${criteria !=null}">
		<br/>
		<h2>Search</h2>
		<hr/>
		<form:form method="post" commandName="criteria">
			<table>
			<tr>
	        	<td>Container Name:</td>
	        	<td><form:input path="name" /></td>
	     	</tr>
		    <tr>
		    	<td>Current Location:</td>
		    	<td><form:input path="location" /></td>
		    </tr>
	        <tr>
	        	<td colspan="2">
	            <input type="submit" value="Find Container"/>
	        	</td>
	    	</tr>
			</table>
		</form:form>
	</c:if>
</body>
</html>