<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href=<c:url value="/resources/css/addProduct.css" /> rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" commandName="product">
		<table>
		    <tr>
		        <td>Name:</td>
		        <td><form:input path="name" /></td> 
		        <td><form:errors path="name" class="error" /></td>        
		    </tr>
		    <tr>
		        <td>Description:</td>
		        <td><form:input path="description" /></td>
		    </tr>
		    <tr>
		        <td>Price:</td>
		        <td><form:input path="price" /></td>
		    </tr>
		    <tr>
		        <td colspan="2">
		            <input type="submit" value="Add Product"/>
		        </td>
		    </tr>
		</table>  
	</form:form>

</body>
</html>