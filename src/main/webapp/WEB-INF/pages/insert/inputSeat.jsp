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
	<title>Input Seat</title>
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
		<form action="${pageContext.request.contextPath}/insertSeat">
			<p>
				<label for="seatNumber">Seat number:</label>
				<input id="seatNumber" type="text" name="seatNumber" placeholder="AA-01" required>
			</p><br><br>
	
			<fieldset>
				<legend>Seat type</legend>
				<input id="business" type="radio" name="seatType" value="business" required>
				<label for="business">business</label>
				<input id="standart" type="radio" name="seatType" value="standart" required>
				<label for="standart">standart</label>
			</fieldset><br><br>
			
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