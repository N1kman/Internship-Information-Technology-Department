<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Input Aircraft</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<div class="main">
		<form action="${pageContext.request.contextPath}/insertAircraft">
			<p>
				<label for="registrationNumber">Registration number:</label>
				<input id="registrationNumber" type="text" name="registrationNumber" placeholder="AAA-707" required>
			</p><br><br>
			
			<p>
				<label for="model">Model:</label>
				<input id="model" type="text" name="model" placeholder="Airbus" required>
			</p><br><br>
			
			<p>
				<label for="company">Company:</label>
				<input id="company" type="text" name="company" placeholder="Belavia" required>
			</p><br><br>
			
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