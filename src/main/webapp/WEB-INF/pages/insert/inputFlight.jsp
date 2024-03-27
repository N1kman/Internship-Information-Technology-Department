<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIAircraftImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIAircraft" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Aircraft" %>	
<%@ page import="java.util.List" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Input Flight</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIAircraft api = new DatabaseAPIAircraftImpl();
		List<Aircraft> aircrafts = api.findAll();
	%>
	<div class="main">
		<form action="${pageContext.request.contextPath}/insertFlight">
			<p>
				<label for="code">Code of flight:</label>
				<input id="code" type="text" name="code" placeholder="AD-10" required>
			</p><br><br>
			
			<p>
				<label for="placeDeparture">Place departure:</label>
				<input id="placeDeparture" type="text" name="placeDeparture" placeholder="Pinsk" required>
			</p><br><br>
			
			<p>
				<label for="placeArrival">Place arrival:</label>
				<input id="placeArrival" type="text" name="placeArrival" placeholder="Borisov" required>
			</p><br><br>
			
			<p>
				<label for="dateDeparture">Date departure:</label>
				<input id="dateDeparture" type="date" name="dateDeparture" required>
			</p><br><br>
			
			<p>
				<label for="timeDeparture">Time departure:</label>
				<input id="timeDeparture" type="time" name="timeDeparture" required>
			</p><br><br>
			
			<p>
				<label for="dateArrival">Date arrival:</label>
				<input id="dateArrival" type="date" name="dateArrival" required>
			</p><br><br>
			
			<p>
				<label for="timeArrival">Time arrival:</label>
				<input id="timeArrival" type="time" name="timeArrival" required>
			</p><br><br>
			
			<label for="idAircraft">Select id of aircraft</label>
			<select name="idAircraft" id="idAircraft" required>
			<option value="0">Aircrafts are not available</option>
				<% 
					if(aircrafts != null){
						for(Aircraft aircraft: aircrafts){
							out.println("<option value=\"" + aircraft.getId() + "\">" + aircraft.getId() + "</option>");
						}
					}
				%>
			</select><br><br>
			
			<p>
				<input type="submit" value="Add">
			</p>
		</form>
		
		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>
</body>
</html>