<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
		<meta http-equiv="refresh" content="0; url=shipment/list.html" />
	</head> 
	<body>
		<c:url value="/showMessage.html" var="messageUrl" />
		<a href="product/list.html">Click to enter</a>
	</body>
</html>
