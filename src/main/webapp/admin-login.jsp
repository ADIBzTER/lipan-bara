<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Admin Login</title>
<link rel="stylesheet" href="./static/styles/login.css">
<link rel="stylesheet" href="./static/styles/bootstrap.min.css">
</head>

<body>

	<div class="top"></div>

	<div class="grid">
		<div id="title">
			<h2>Lipan Bara Tech</h2>
			<h5>Build your custom PC with our accessories</h5>
		</div>
		<div id="outer">
			<div id="inner">
				<h2>Admin Login</h2>
				<br>
				<form action="adminLogin" method="POST" autocomplete="off">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" name="username" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" required>
					</div>
					<p class="warning-message">${ errorMessage }</p>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>
	</div>
	<script src="./static/scripts/login.js"></script>
</body>

</html>