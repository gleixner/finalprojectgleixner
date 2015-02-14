<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<title><sitemesh:write property='title'/></title>
	<sitemesh:write property='head'/>
	</head>
 
	<body>
	This is the decorator body in user:
	<sitemesh:write property='body'/>
	</body>
</html>