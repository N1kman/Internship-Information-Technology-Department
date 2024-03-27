<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPIClientImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPIClient" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Client" %>	
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
		DatabaseAPIClient api = new DatabaseAPIClientImpl();
		List<Client> clients = api.findAll();
	%>
	
	<div class="main">
		<div class="list">
			<%
				if(clients != null) {
					 for (Client client : clients) {
						String href = "href=\"" + request.getContextPath() + "/clients/" + client.getId() + "\"";
						out.println("<a " + href + ">" + client.getSurname() + " " + client.getPassportId() + "</a>");
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