<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.config.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Admin Dashboard</title>
<link rel="stylesheet" href="static/styles/supplier.css">
</head>

<body>
	<div id="app-bar">

		<div id="appbar-left">
			<div id="main-logo">
				<h1>Lipan Bara | Admin Dashboard</h1>
			</div>
		</div>

		<div id="appbar-right">
			<h3>${ username }</h3>
			|
			<div id="logout">
				<a href="adminLogin">Logout</a>
			</div>
		</div>

	</div>

	<div id="center">
		<div class="suppliers-table">

			<c:choose>
				<c:when test="${param[\"addSupplier\"] != null}">
					<h2>Add Supplier</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Supplier</h2>
				</c:otherwise>
			</c:choose>

			<form action="supplier" method="POST">
				<input type="hidden" name="id" value="${ supplier.id }">

				<c:choose>
					<c:when test="${param[\"addSupplier\"] != null}">
						<input type="hidden" name="supplierActivity" value="addSupplier">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="supplierActivity"
							value="updateSupplier">
					</c:otherwise>
				</c:choose>

				<div class="input-class">
					<label for="name">Name </label>
					<br>
					<input name="name" id="name" type="text" autocomplete="off"
						required value="${supplier.name }">
					<br>
				</div>

				<div class="input-class">
					<label for="quantity">Address</label>
					<br>
					<textarea name="address" id="address" autocomplete="off" required>${ supplier.address }</textarea>
					<br>
				</div>

				<div class="input-class">
					<label for="price">Phone</label>
					<br>
					<input name="phone" id="phone" type="text" autocomplete="off"
						required value="${ supplier.phone }">
					<br>
				</div>

				<input type="submit" id="hidden-submit" hidden="true">
			</form>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">

				<c:choose>
					<c:when test="${param[\"addSupplier\"] != null}">
						<button id="submit">Add</button>
					</c:when>
					<c:otherwise>
						<button id="submit">Update</button>
					</c:otherwise>
				</c:choose>

				<a href="supplier">
					<button style="background-color: #ff5a5f; color: white;">Cancel</button>
				</a>
			</div>
		</div>
	</div>

	<script>
	const submitButton = document.querySelector('#submit');
	submitButton.onclick = (e) => {
		document.querySelector('#hidden-submit').click();
	}
</script>
</body>

</html>