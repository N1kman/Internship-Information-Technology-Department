<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIClientFlightImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIClientFlight" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.ClientFlight" %>	
<%@ page import="com.crud.app.entities.Flight" %>
<%@ page import="com.crud.app.entities.Client" %>	
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert Flight to Client</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIClientFlight api = new DatabaseAPIClientFlightImpl();
		ClientFlight clientFlight = null;
		
		Long idClient = Long.parseLong(request.getParameter("idClient"));
		Long idFlight = Long.parseLong(request.getParameter("idFlight"));
		
		Boolean flag = true;
		
		List<ClientFlight> list = api.findAll();
		for(ClientFlight buff: list) {
			if(buff.getClient().getId() == idClient && buff.getFlight().getId() == idFlight) {
				flag = false;
			}
		}
		
		if(flag) {
			clientFlight = new ClientFlight(idClient, idFlight);
			clientFlight = api.insert(clientFlight);
		}

	%>
	
	<div class="main">
		<div class="elem">
		<%
			if(clientFlight != null) {
				out.println("<p class=\"success\">!SUCCESS!<p>");
			} else {
				out.println("<p class=\"failed\">!FAILED! Already added<p>");
			}
		%>
	</div>
	<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

</body>
</html>