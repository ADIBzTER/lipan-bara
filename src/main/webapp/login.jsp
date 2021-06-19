<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lipan Bara Tech | Login</title>
<link rel="stylesheet" href="./static/styles/login.css">
<link rel="stylesheet" href="./static/styles/bootstrap.min.css">
</head>

<body>

	<div class="top"></div>

	<div class="grid">
		<div id="title">
			<h2>Lipan Bara Tech</h2>
			<h5>Buy full set gaming PC with the lowest price</h5>
		</div>
		<div id="outer">
			<div id="inner">
				<h2>Login</h2>
				<br>
				<form action="login" method="POST" autocomplete="off">
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" name="username" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" required>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
					<div class="bottom-message">
						No Account Yet? <a href="sign-up.jsp">Sign up here</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="./static/scripts/login.js"></script>
</body>

</html>