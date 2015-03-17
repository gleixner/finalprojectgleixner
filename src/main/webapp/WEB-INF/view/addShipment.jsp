<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add a Shipment</title>
</head>
<h2>Add a Shipment</h2>
<body>
	<form:form method="POST" commandName="shipment">
	    <table>
	    <tr>
	        <td>Name:</td>
	        <td><form:input path="name" placeholder="Enter name"/></td> 
	        <td><form:errors path="name" cssclass="error" /></td>  
	        <c:if test='${error!=null && error=="true"}' >
	        	<td>Shipment name already exists.  Please choose a different name</td>
	        </c:if>      
	    </tr>
	    <tr>
	        <td>Origin:</td>
	        <td><form:input path="origin" placeholder="Enter 3 Letter Airport Code"/></td>
	        <td><form:errors path="origin" cssclass="error" /></td>  
	    </tr>
	    <tr>
	        <td>Destination:</td>
	        <td ><form:input path="destination" placeholder="Enter 3 Letter Airport Code"/></td>
	        <td><form:errors path="destination" cssclass="error" /></td>  	        
	    </tr>
	    <tr>
	        <td>Volume:</td>
	        <td><form:input path="volume" placeholder="Enter volume of shipment"/></td>
	        <td><form:errors path="volume" cssclass="error" /></td>  
	    </tr>
	    <tr>
	        <td colspan="2">
	        	<c:choose>
	        		<c:when test='${shipment.name != null}'>
		        		<h3>Editing a shipment will cause its routing to be deleted</h3>
	        			<input type="submit" value="Edit Shipment"/>
	        		</c:when>
	        		<c:otherwise>
	        			<input type="submit" value="Add Shipment"/>
	        		</c:otherwise>
	        	</c:choose>
	        </td>
	    </tr>
		</table>
	</form:form>
</body>
</html>