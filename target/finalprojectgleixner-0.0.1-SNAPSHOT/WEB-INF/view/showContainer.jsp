<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>Name: ${container.name}</h2>
		
		<p>Remaining Capacity: ${container.capacity}</p>
		
		<p>Current Location: ${container.location}</p>
		
		<p>Destination: ${container.destination}</p>
		
		<p>Container Status: ${container.status}</p>
	</body>
</html>