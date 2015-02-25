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
	
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" >
	<link rel="stylesheet" href="<c:url value="/resources/css/simple-sidebar.css"/>" >
	
	<sec:authorize var="loggedIn" access="isAuthenticated()" />
	<script src="<c:url value="/resources/js/trouble.js"/>" type="text/javascript"></script>
</head>

<body>

	<div id="wrapper">
		<!-- Sidebar -->
        <div id="sidebar-wrapper">
            <ul class="sidebar-nav">
                <li class="sidebar-brand">
                    <c:choose>
                    	<c:when test="${loggedIn}">
                        	<strong>Welcome	<sec:authentication property='principal.username'/></strong>
                    	</c:when>
                    	<c:otherwise>
                    		<strong>Please Login</strong>
                        </c:otherwise>
                    </c:choose>
                </li>
                
               	<sec:authorize access="hasRole('ROLE_CONTENT')">
               		<li><strong>Products</strong></li>
               		<li><a href="/finalprojectgleixner/product/list.html">List all Products</a></li>
               		<li><a href="/finalprojectgleixner/product/add.html">Add a Products</a></li>
               		<hr/>             	
               	</sec:authorize>
               	<sec:authorize access="hasRole('ROLE_USER')">
               		<li><strong>Cards</strong></li>
               		<li><a href="/finalprojectgleixner/card/list.html">List all Cards</a></li>
               		<li><a href="/finalprojectgleixner/card/add.html">Add a Card</a></li>
               		<hr/>         	
               	</sec:authorize>
               	<sec:authorize access="hasRole('ROLE_MAINT')">
               		<li><strong>Containers</strong></li>
               		<li><a href="/finalprojectgleixner/container/list.html">List all Containers</a></li>
               		<li><a href="/finalprojectgleixner/container/add.html">Add a Container</a></li>
               		<hr/>      	
               	</sec:authorize>
               	<sec:authorize access="hasRole('ROLE_CUSTOMER')">
               		<li><strong>Shipments</strong></li>
               		<li><a href="/finalprojectgleixner/shipment/list.html">List all Shipments</a></li>
               		<li><a href="/finalprojectgleixner/shipment/add.html">Add a Shipment</a></li>
               		<hr/>              	
               	</sec:authorize>
                
                <li>
                	<a href="http://localhost:8080/finalprojectgleixner/logout">Logout</a>
                </li>
            </ul>
        </div>
        <!-- /#sidebar-wrapper -->
        
        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
<!--                 <div id="inner-box"> -->
                    <div class="col-lg-12">
						<sitemesh:write property='body'/>
						<br/>
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Menu</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
        
	</div> <!-- End Wrapper -->
	    <!-- jQuery -->
	    
    <script src="<c:url value="/resources/js/jquery.js"/>" ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" ></script>

    <!-- Menu Toggle Script -->
    <script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
    </script>

</body>

</html>