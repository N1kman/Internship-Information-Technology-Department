<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIAircraftImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIAircraft" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Aircraft" %>	
<%@ page import="com.crud.app.entities.Seat" %>	

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert Aircraft</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIAircraft api = new DatabaseAPIAircraftImpl();
		Aircraft aircraft = new Aircraft();
		
		aircraft.setRegistrationNumber(request.getParameter("registrationNumber"));
		aircraft.setModel(request.getParameter("model"));
		aircraft.setCompany(request.getParameter("company"));
		
		aircraft = api.insert(aircraft);

	%>
	
	<div class="main">
		<div class="elem">
		<%
			if(aircraft != null) {
				out.println("<p class=\"success\">!SUCCESS!<p>");
				out.println("<h2>" + aircraft.getRegistrationNumber() +  " " + aircraft.getModel() + "</h2>");
				out.println("<p>" + "Company: " + aircraft.getCompany() + "</p>");
			} else {
				out.println("<p class=\"failed\">!FAILED!<p>");
			}
		%>
	</div>
	<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

</body>
</html>