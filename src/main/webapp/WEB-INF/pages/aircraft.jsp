<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIAircraftImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIAircraft" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="com.crud.app.entities.Aircraft" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Aircraft</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIAircraft api = new DatabaseAPIAircraftImpl();
		String id = request.getAttribute("id").toString();
		Aircraft aircraft = api.findById(Long.parseLong(id));
	%>
	
	<div class="main">
		<div class="seat">
		<%
			out.println("<h2>" + aircraft.getRegistrationNumber() +  " " + aircraft.getModel() + "</h2>");
			out.println("<p>" + "Company: " + aircraft.getCompany() + "</p>");
			out.println("<h4>Seats:</h4>");
			for(Seat seat: aircraft.getSeats()){
				out.println("<p>" + seat.getSeatNumber() + " " + seat.getSeatType() + "</p>");
			}
		%>
		</div>
		<div class="back">
			<a href="/aircrafts">Back</a>
		</div>
	</div>

</body>
</html>