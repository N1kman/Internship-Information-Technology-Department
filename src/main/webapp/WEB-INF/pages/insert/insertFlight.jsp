<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIFlightImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIFlight" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Aircraft" %>	
<%@ page import="com.crud.app.entities.Flight" %>	
<%@ page import="com.crud.app.entities.Seat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalTime" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert Flight</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIFlight api = new DatabaseAPIFlightImpl();
		Flight flight = new Flight();
		Aircraft aircraft = new Aircraft();
		
		aircraft.setId(Long.valueOf(request.getParameter("idAircraft")));
		flight.setAircraft(aircraft);
		flight.setCode(request.getParameter("code"));
		flight.setPlaceDeparture(request.getParameter("placeDeparture"));
		flight.setPlaceArrival(request.getParameter("placeArrival"));
		flight.setDateDeparture(LocalDateTime.of(LocalDate.parse(request.getParameter("dateDeparture")), LocalTime.parse(request.getParameter("timeDeparture"))));
		flight.setDateArrival(LocalDateTime.of(LocalDate.parse(request.getParameter("dateArrival")), LocalTime.parse(request.getParameter("timeArrival"))));
		
		if(flight.getAircraft().getId() != 0) {
			flight = api.insert(flight);
			if(flight != null) {
				flight = api.findById(flight.getId());
			}
		}
	%>
	
	<div class="main">
		<div class="elem">
		<%
			if(flight != null && flight.getAircraft().getId() != 0) {
				out.println("<p class=\"success\">!SUCCESS!<p>");
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
			} else {
				out.println("<p class=\"failed\">!FAILED! ADD AIRCRAFT<p>");
			}
		%>
	</div>
	<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

</body>
</html>