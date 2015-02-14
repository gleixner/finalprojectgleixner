<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="currentPage" content="About Me"/>
<title>About Me</title>

<link rel="stylesheet" href="<c:url value="/resources/css/central_styles.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" >
<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.cs"/>" >

<script src="<c:url value="/resources/js/tabs.js"/>" type="text/javascript"></script>
</head>
<body>

	<main role="main">
	<article role="article" style="height: 0px;">
	
		<div id="spacer1" class="col-lg-3">
		<div id="linkBox" class="languages col-lg-2">
			<h3>Welcome</h3>
			<div id="tabs">
				<ol>
					<li id="tab1" class="tabButton"><a href="#tabPanel1">About Me</a></li>
					<li id="tab2" class="tabButton"><a href="#tabPanel2">Contact Me</a></li>
					<li id="tab3" class="tabButton"><a href="#tabPanel3">Enemies</a></li>
					<li id="tab4" class="tabButton"><a href="#tabPanel3">Enemies</a></li>
				</ol>
			</div>
		</div>
		
		</div>
		
		<div id="mainBox" class="col-lg-6">
			<div id="panel">
				<div id="tabPanel1" class="panelBag">
					<h4>About Me</h4>
					<img class="mugshot" src="<c:url value="/resources/img/chq-jamesgl.jpg"/>"/>
					<h1>James Gleixner</h1>
					<p>Here's a picture of a suave gentleman with a piercing stare</p>
				</div>
				
				<div id="tabPanel2" class="panelBag ">
					<h4>Contact me</h4>
					<form method="POST" action="submitContact">
						<div id="email">
							<label>Email address</label> 
							<input name="email" type="email" class="form-control" placeholder="Enter email">
						</div>
						<div id="message">
							<label>Message</label> 
							<input name="message" type="text" class="form-control" placeholder="Talk to me!"><br>
							<input id="submit" type="submit" value="Send it to me!!!" />
						</div>
					</form>
				</div>
				
				<div id="tabPanel3" class="panelBag">
					<h4>Enemies</h4>
						<h1>Scorpion</h1>
						<img class="enemies" src="<c:url value="/resources/img/scorpion.jpg"/>"/>
						<p>These are bad news!</p>
					<hr noshade="noshade"/>
						<h1>Asian Giant Hornet</h1>
						<img class="enemies" src="<c:url value="/resources/img/hornet.jpg"/>"/>
						<p>Why nature oh why!?</p>
					<hr noshade="noshade"/>
						<h1>Giant Centipede</h1>
						<img class="enemies" src="<c:url value="/resources/img/centipede.jpeg"/>"/>
						<p>Kill it with fire!</p>
					<hr noshade="noshade"/>
						<h1>Fluffy Bunny</h1>
						<img class="enemies" src="<c:url value="/resources/img/bunny.jpg"/>"/>
						<p>I've seen Monty Python.  I know what's up.</p>
				</div>
				
				<div id="tabPanel4" class="panelBag">
					<h4>About Me</h4>
					<img class="mugshot" src="<c:url value="/resources/img/chq-jamesgl.jpg"/>"/>
					<h1>James Gleixner</h1>
					<p>Here's a picture of a suave gentleman with a piercing stare</p>
				</div>
			</div>
		</div>
	</article>
	</main>
<!-- 	<div class="clearfix"></div> -->
</body>
</html>
