<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>You inquired about the following product:</h2>
		<h2>${product.name}</h2>
		<p>${product.description}</p>
<%-- 		<h2>${product.name} - <fmt:formatNumber value="${product.price}" type="currency"/></h2> --%>
		<h2>${product.price}</h2>
	</body>
</html>
