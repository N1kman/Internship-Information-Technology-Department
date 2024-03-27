<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPISeatImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPISeat" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Aircraft" %>	
<%@ page import="com.crud.app.entities.Seat" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert Seat</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPISeat api = new DatabaseAPISeatImpl();
		Seat seat = new Seat();
		Aircraft aircraft = new Aircraft();
		
		aircraft.setId(Long.valueOf(request.getParameter("idAircraft")));
		seat.setSeatNumber(request.getParameter("seatNumber"));
		seat.setSeatType(request.getParameter("seatType"));
		seat.setAircraft(aircraft);
		
		if(seat.getAircraft().getId() != 0) {
			seat = api.insert(seat);
			if(seat != null) {
				seat = api.findById(seat.getId());
			}
		}
	%>
	
	<div class="main">
		<div class="elem">
		<%
			if(seat != null && seat.getAircraft().getId() != 0) {
				out.println("<p class=\"success\">!SUCCESS!<p>");
				out.println("<h2>" + seat.getSeatNumber() +  " " + seat.getSeatType() + "</h2>");
				out.println("<h4>Aircraft infortmation:</h4>");
				out.println("<p>" + "Redistration number: " + seat.getAircraft().getRegistrationNumber() + "</p>");
				out.println("<p>" + "Model: " + seat.getAircraft().getModel() + "</p>");
				out.println("<p>" + "Company: " + seat.getAircraft().getCompany() + "</p>");
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