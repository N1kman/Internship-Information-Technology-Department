<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIFlightImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIFlight" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="com.crud.app.entities.Aircraft" %>
<%@ page import="com.crud.app.entities.Flight" %>
<%@ page import="java.util.List" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Flights</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/list.css">
</head>
<body>	
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIFlight api = new DatabaseAPIFlightImpl();
		List<Flight> flights = api.findAll();
	%>
	
	<div class="main">
		<div class="list">
			<%
				if(flights != null) {
					 for (Flight flight : flights) {
						String href = "href=\"" + request.getContextPath() + "/flights/" + flight.getId() + "\"";
						out.println("<a " + href + ">" + flight.getCode() + " " + flight.getPlaceDeparture() + "-" + flight.getPlaceArrival() + "</a>");
					};
				}
			%>
		</div>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>
	
</body>
</html>