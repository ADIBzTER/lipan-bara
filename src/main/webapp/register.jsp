<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Register</title>
<link rel="stylesheet" href="./static/styles/sign-up.css">
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
				<h2>Sign Up</h2>
				<br>
				<form action="register" method="POST" autocomplete="off" required>
					<div class="form-group">
						<label for="name">Your Name</label> <input type="text"
							class="form-control" name="name" placeholder="Lipan Bara"
							required>
					</div>
					<div class="form-group">
						<label for="username">Username</label> <input type="text"
							class="form-control" name="username" placeholder="lipanBara"
							required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" name="password" required>
					</div>
					<div class="form-group">
						<label for="address">Address</label>
						<textarea type="text" class="form-control" name="address" required></textarea>
					</div>
					<div class="form-group">
						<label for="phone">Phone</label> <input type="tel"
							class="form-control" name="phone" placeholder="+60123456789"
							required>
					</div>
					<p class="warning-message">${ errorMessage }</p>
					<button type="submit" class="btn btn-primary">Register</button>
					<div class="bottom-message">
						Already register? <a href="login">Login here</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script src="./static/scripts/login.js"></script>
</body>

</html>