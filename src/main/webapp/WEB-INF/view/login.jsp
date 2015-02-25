<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link href=<c:url value="/resources/css/addProduct.css" /> rel="stylesheet" type="text/css">
</head>
<body>

	<c:if test="${param.error != null}">
		<strong>There was an error, please try again</strong>
	</c:if>
	
	<c:if test="${param.logout != null}">
		<strong>You have successfully logged out</strong>
	</c:if>

	<form name="f" action="/finalprojectgleixner/login" method="POST">
		<table>
		   <tbody><tr><td>User:</td><td><input type="text" name="username" value=""></td></tr>
		   <tr><td>Password:</td><td><input type="password" name="password"></td></tr>
		   <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		   <tr><td colspan="2"><input name="submit" type="submit" value="Login"></td></tr>
		 </tbody></table>
	</form>
	
</body>
</html>