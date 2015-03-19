<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Current Shipments </title>
</head>
<body>

	<c:if test='${shipment !=null}'>
	<c:choose>
		<c:when test="${fn:length(dataView.assignedRoutes) > 0}">
			<h3>Shipment ${shipment.name} has been routed on the following containers</h3>
		
			<c:forEach items="${dataView.assignedRoutes}" var="leg">
			<h3>Booking from ${leg.origin} to ${leg.destination}</h3>
			<table class="table table-striped">
				<tr>
					<thead>
					<th> Name </th>
					<th> Assigned Volume </th>
					<th> Location </th>
					<th> Destination </th>
					<th> Status </th>
					</thead>
				<tr>
				<tbody>
				<c:forEach items="${leg.assignedScas}" var="sca">
					<tr>
						<td>${sca.container.name}</td>
						<td>${sca.shipmentVolume}</td>
						<td>${sca.container.location}</td>
						<td>${sca.container.destination}</td>
						<td>${sca.container.status}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:forEach>
			
			<c:if test="${shipment.editable}">
				<td><a href='<c:url value="/shipment/route.html?id=${shipment.id}"/>'>Reroute Shipment</a> </td>
				<br/>
				<td><a href='<c:url value="/shipment/route.html?id=${shipment.id}&delete=true"/>'>Delete routing</a> </td>
			</c:if>
		</c:when>
		<c:otherwise>
			<h3> Shipment ${shipment.name} has not been routed </h3>
			<td><a href='<c:url value="/shipment/route.html?id=${shipment.id}"/>'>Route Shipment</a> </td>
			<br/>
			<td><a href='<c:url value="/shipment/routeDijkstra.html?id=${shipment.id}"/>'>Route Shipment Like a Boss!</a> </td>
		</c:otherwise>
	</c:choose>
	</c:if>
		

	
	<h1>Shipment</h1>
	<hr/>
	
	<table class="table table-striped">
		<tr>
			<th> Name </th>
			<th> Origin </th>
			<th> Destination </th>
			<th> Volume </th>
			<th> Info </th>
			<th> Edit </th>
			<th> Remove </th>
		<tr>
		<c:forEach items="${shipments}" var="shipment">
			<tr>
				<td>${shipment.name}</td>
				<td>${shipment.origin}</td>
				<td>${shipment.destination}</td>
				<td>${shipment.volume}</td>
				<td><a href='<c:url value="/shipment/list.html?id=${shipment.id}"/>'>Info</a> </td>
				<c:if test="${shipment.editable}">
					<td><a href='<c:url value="/shipment/edit.html?id=${shipment.id}"/>'>Edit</a> </td>
					<td><a href='<c:url value="/shipment/remove.html?id=${shipment.id}"/>'>Remove</a></td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td>
				<c:if test="${page>0}">
					<a href="<c:url value="/shipment/list.html?page=${page-1}"/>">Previous</a>
				</c:if>
			</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>		
			<td>
				<c:if test="${more}">
					<a href="<c:url value="/shipment/list.html?page=${page+1}"/>">Next</a>
				</c:if>
			<td>
		</tr>	
	</table>

</body>
</html>