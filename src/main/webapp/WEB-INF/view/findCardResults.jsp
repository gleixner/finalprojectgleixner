<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <meta charset="utf-8">
        <title>Cards Found</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Card Number</th>
                <th>Owner</th>
                <th>Amount</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Alt Phone</th>
            </tr>
            <c:forEach var="card" items="${cards}">
                <tr>
                    <td>${card.number}</td>
                    <td>${card.owner}</td>
                    <td><fmt:formatNumber value="${card.amount}" type="currency"/></td>
                    <td>${card.address.street}, ${card.address.city}, ${card.address.state}</td>
                    <c:forEach var="number" items="${card.phoneNumbers}">
                        <td>${number.number}</td>
                    </c:forEach>
			    	<td>
			    		<a href='<c:url value="/card/buy.html?card=${card.id}"/>'>Buy</a>
			    	</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>