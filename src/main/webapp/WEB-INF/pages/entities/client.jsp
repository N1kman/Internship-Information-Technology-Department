<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIClientImpl"%>
<%@ page import="com.crud.app.services.api.DatabaseAPIClient"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIFlightImpl"%>
<%@ page import="com.crud.app.services.api.DatabaseAPIFlight"%>
<%@ page import="com.crud.app.utils.ServiceConnectionFactory"%>
<%@ page import="com.crud.app.entities.Flight"%>
<%@ page import="com.crud.app.entities.Client"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Client</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/elem.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>

	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIClient api = new DatabaseAPIClientImpl();
		String id = request.getAttribute("id").toString();
		Client client = api.findById(Long.parseLong(id));
	%>

	<div class="main">
		<div class="elem">
			<%
				out.println("<h2>" + client.getSurname() + " " + client.getFirstname() + " " + client.getPatronymic() + "</h2>");
				out.println("<p>" + "Passport id: " + client.getPassportId() + "</p>");
				out.println("<h4>Flights:</h4>");
				for (Flight flight : client.getFlights()) {
					out.println("<p>" + flight.getPlaceDeparture() + "-" + flight.getPlaceArrival() + "(" + flight.getDateDeparture() + ")" + "</p>");
				}
			%>
		</div>

		<%
		DatabaseAPIFlight apiFl = new DatabaseAPIFlightImpl();
		List<Flight> flights = apiFl.findAll();
		%>

		<form action="${pageContext.request.contextPath}/InsertFlightToClient">
			<input type="hidden" name="idClient" value=<%=id%> /> 
			
			<label for="idFlight">Add flight:</label>
			<select id="idFlight" name="idFlight">
				<%
					for (Flight flight : flights) {
						out.println("<option value=\"" + flight.getId() + "\">" + flight.getPlaceDeparture() + "-" + flight.getPlaceArrival() + "(" + flight.getDateDeparture() + ")" + "</option>");
					}
				%>
			</select>
			
			<input type="submit" value="Add"/> 
		</form>

		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

</body>
</html>