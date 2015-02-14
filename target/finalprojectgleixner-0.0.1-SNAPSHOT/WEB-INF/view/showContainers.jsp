<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Containers</title>
</head>
<body>

	<h1>Containers</h1>
	<hr/>
	<p>Name: Capacity: Location: Destination: Status</p>
	<c:forEach items="${containers}" var="container">
		<p>${container.name}: ${container.capacity}: ${container.location}: ${container.destination}: ${container.status}
	</c:forEach>

</body>
</html>