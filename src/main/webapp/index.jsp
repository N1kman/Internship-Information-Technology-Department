<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Entities</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css?v=1.0">
</head>
<body>

	<div class="main">
		<a class="choose" href="${pageContext.request.contextPath}/seats">Get seats</a> 
		<a class="choose" href="${pageContext.request.contextPath}/aircrafts">Get aircrafts</a> 
		<a class="choose" href="${pageContext.request.contextPath}/flights">Get flights</a>
		<a class="choose" href="${pageContext.request.contextPath}/clients">Get clients</a>
		
		<a class="choose" href="${pageContext.request.contextPath}/inputSeat">Insert seat</a>
		<a class="choose" href="${pageContext.request.contextPath}/inputAircraft">Insert aircraft</a>
		<a class="choose" href="${pageContext.request.contextPath}/inputFlight">Insert flight</a>
		<a class="choose" href="${pageContext.request.contextPath}/inputClient">Insert client</a>
	</div>

</body>
</html>