<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Input Client</title>
	<link rel="stylesheet" type="text/css" href="/static/css/main.css">
	<link rel="stylesheet" type="text/css" href="/static/css/form.css">
</head>
<body>
	
	<div class="main">
		<form action="${pageContext.request.contextPath}/insertClient">
			<p>
				<label for="passportId">Passport id:</label>
				<input id="passportId" type="text" name="passportId" placeholder="50505A123" required>
			</p><br><br>
			
			<p>
				<label for="firstname">Firstname:</label>
				<input id="firstname" type="text" name="firstname" placeholder="Ivan" required>
			</p><br><br>
			
			<p>
				<label for="surname">Surname:</label>
				<input id="surname" type="text" name="surname" placeholder="Glets" required>
			</p><br><br>
			
			<p>
				<label for="patronymic">Patronymic:</label>
				<input id="patronymic" type="text" name="patronymic" placeholder="Ivanovich" required>
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