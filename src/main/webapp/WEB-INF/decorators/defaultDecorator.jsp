<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
	<title><sitemesh:write property='title'/></title>
	<sitemesh:write property='head'/>
	</head>

	<body>
	<h2> <sitemesh:write property='title'/> </h2>
	<br/>
	<strong>Welcome	<sec:authentication property='principal.username'/></strong>
	<br/>
	<a href="http://localhost:8080/finalprojectgleixner/logout">Logout</a>
	<hr/>
	
	<sitemesh:write property='body'/>
	</body>
</html>