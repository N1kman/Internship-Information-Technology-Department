<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIFlightImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIFlight" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="com.crud.app.entities.Aircraft" %>	
<%@ page import="com.crud.app.entities.Flight" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Flight</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/elem.css">
</head>
<body>	
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIFlight api = new DatabaseAPIFlightImpl();
		String id = request.getAttribute("id").toString();
		Flight flight = api.findById(Long.parseLong(id));
	%>
	
	<div class="main">
		<div class="elem">
		<%
			out.println("<h2>" + flight.getCode() + " " + flight.getPlaceDeparture() + "-" + flight.getPlaceArrival() + "</h2>");
			out.println("<p>" + "Date departure: " + flight.getDateDeparture() + "</p>");
			out.println("<p>" + "Date arrival: " + flight.getDateArrival() + "</p>");
			out.println("<h4>Aircraft:</h4>");
			out.println("<p>" + "Registration Number: " + flight.getAircraft().getRegistrationNumber() + "</p>");
			out.println("<p>" + "Model: " + flight.getAircraft().getModel() + "</p>");
			out.println("<p>" + "Company: " + flight.getAircraft().getCompany() + "</p>");
			out.println("<h4>Seats:</h4>");
			for(Seat seat: flight.getAircraft().getSeats()){
				out.println("<p>" + seat.getSeatNumber() + " " + seat.getSeatType() + "</p>");
			}
		%>
		</div>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

</body>
</html>