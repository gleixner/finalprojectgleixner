<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Container</title>
</head>
<h2>Add a Container</h2>
<body>
	<form:form method="POST" commandName="container">
	    <table>
	    <tr>
	        <td>Name:</td>
	        <td><form:input path="name" placeholder="Enter name"/></td> 
	        <td><form:errors path="name" cssclass="error" /></td>        
	    </tr>
	    <tr>
	        <td>Capacity:</td>
	        <td><form:input path="capacity" placeholder="Enter a number"/></td>
	        <td><form:errors path="capacity" cssclass="error" /></td>  
	    </tr>
	    <tr>
	        <td>Rate:</td>
	        <td><form:input path="rate" placeholder="Enter a rate"/></td>
	        <td><form:errors path="rate" cssclass="error" /></td>  
	    </tr>
	    <tr>
	        <td>Location:</td>
	        <td ><form:input path="location" placeholder="Enter 3 Letter Airport Code"/></td>
	        <td><form:errors path="location" cssclass="error" /></td>  	        
	    </tr>
	    <tr>
	        <td>Destination:</td>
	        <td><form:input path="destination" placeholder="Enter 3 Letter Airport Code"/></td>
	        <td><form:errors path="destination" cssclass="error" /></td>  
	    </tr>
	    <tr>
	        <td>Status:</td>
			<td>
				<form:select path="status">
					<form:option value="  ----Choose One----  "/>
					<form:options />
				</form:select>
			</td>
			<td><form:errors path="status" cssclass="error" /></td> 
	    </tr>	    
	    <tr>
	        <td colspan="2">
	        	<c:choose>
	        		<c:when test='${container.name != null}'>
	        			<input type="submit" value="Edit Container"/>
	        		</c:when>
	        		<c:otherwise>
	        			<input type="submit" value="Add Container"/>
	        		</c:otherwise>
	        	</c:choose>
	        </td>
	    </tr>
		</table>
	</form:form>
</body>
</html>