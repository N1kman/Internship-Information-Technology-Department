<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.crud.app.services.api.impl.DatabaseAPISeatImpl" %>
<%@ page import="com.crud.app.services.api.DatabaseAPISeat" %>	
<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
<%@ page import="com.crud.app.entities.Seat" %>	
<%@ page import="java.util.List" %>	 
    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Seats</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
</head>
<body>	
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPISeat api = new DatabaseAPISeatImpl();
		List<Seat> seats = api.findAll();
	%>
	
	<div class="main">
		<div class="seat-list">
			<%
				if(seats != null) {
					 for (Seat seat : seats) {
						String href = "href=\"/seats/" + seat.getId() + "\"";
						out.println("<a " + href + ">" + seat.getSeatNumber() + " " + seat.getSeatType() + "</a>");
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