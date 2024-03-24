<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPISeatImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPISeat" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="com.crud.app.entities.Aircraft" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Seat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPISeat api = new DatabaseAPISeatImpl();
		String id = request.getAttribute("id").toString();
		Seat seat = api.findById(Long.parseLong(id));
	%>
	
	<div class="main">
		<div class="seat">
		<%
			out.println("<h2>" + seat.getSeatNumber() +  " " + seat.getSeatType() + "</h2>");
			out.println("<h4>Aircraft infortmation:</h4>");
			out.println("<p>" + "Redistration number: " + seat.getAircraft().getRegistrationNumber() + "</p>");
			out.println("<p>" + "Model: " + seat.getAircraft().getModel() + "</p>");
			out.println("<p>" + "Company: " + seat.getAircraft().getCompany() + "</p>");
		%>
		</div>
		<div class="back">
			<a href="/seats">Back</a>
		</div>
	</div>

</body>
</html>