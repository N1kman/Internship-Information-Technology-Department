<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIAircraftImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIAircraft" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="com.crud.app.entities.Aircraft" %>
<%@ page import="java.util.List" %>	
	
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Aircrafts</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/list.css">
</head>
<body>	
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIAircraft api = new DatabaseAPIAircraftImpl();
		List<Aircraft> aircrafts = api.findAll();
	%>
	
	<div class="main">
		<div class="list">
			<%
				if(aircrafts != null) {
					 for (Aircraft aircraft : aircrafts) {
						String href = "href=\"/aircrafts/" + aircraft.getId() + "\"";
						out.println("<a " + href + ">" + aircraft.getRegistrationNumber() + " " + aircraft.getModel() + "</a>");
					};
				}
			%>
		</div>
		<div class="back">
			<a href="/">Back</a>
		</div>
	</div>

</body>
</html>