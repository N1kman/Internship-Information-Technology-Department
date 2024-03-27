<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIClientImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIClient" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Client" %>		

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Insert Client</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPIClient api = new DatabaseAPIClientImpl();
		Client client = new Client();
		
		client.setPassportId(request.getParameter("passportId"));
		client.setFirstname(request.getParameter("firstname"));
		client.setSurname(request.getParameter("surname"));
		client.setPatronymic(request.getParameter("patronymic"));
		
		client = api.insert(client);

	%>
	
	<div class="main">
		<div class="elem">
		<%
			if(client != null) {
				out.println("<p class=\"success\">!SUCCESS!<p>");
				out.println("<h2>" + client.getSurname() +  " " + client.getFirstname() +  " " + client.getPatronymic() + "</h2>");
				out.println("<p>" + "Passport id: " + client.getPassportId() + "</p>");
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