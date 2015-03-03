<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <meta charset="utf-8">
        <title>Card</title>
    </head>
    <body>
            <p>Card Number ${card.number}</p>
            <p>Registered to ${card.owner}</p>
            <p>Amount remaining: <fmt:formatNumber value="${card.amount}" type="currency"/></p>
            <p>Address:</p>
            <p>${card.address.street}</p>
            <p>${card.address.city}, ${card.address.state}</p>
            <c:forEach var="number" items="${card.phoneNumbers}">
            	<p>${number.number}</p>
            </c:forEach>
    </body>
</html>