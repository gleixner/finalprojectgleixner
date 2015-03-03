<!DOCTYPE html>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
    <head>
        <meta charset="utf-8">
        <title>Containers Found</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Container Name</th>
                <th>Capacity</th>
                <th>Location</th>
                <th>Destination</th>
                <th>Status</th>
            </tr>
            <c:forEach var="container" items="${containers}">
                <tr>
                    <td>${container.name}</td>
                    <td>${container.capacity}</td>
                    <td>${container.location}</td>
                    <td>${container.destination}</td>
                    <td>${container.status}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>