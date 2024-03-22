<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page import="com.crud.app.services.API.Impl.DatabaseAPISeatImpl" %>
	<%@ page import="com.crud.app.services.API.DatabaseAPISeat" %>	
	<%@ page import="com.crud.app.utils.ServiceConnectionFactory" %>
	<%@ page import="com.crud.app.entities.Seat" %>	
	
	<%
		ServiceConnectionFactory.setLocalDriverFromProperties();
		DatabaseAPISeat api = new DatabaseAPISeatImpl();
		Seat seat = api.findById(4);
		if(seat != null) {
			out.println(seat);
		}
	%>


</body>
</html>