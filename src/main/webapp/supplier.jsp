<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
				<h1>
					<a href="home">Lipan Bara</a> | Admin Dashboard
				</h1>
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
		<div class="products-table">

			<h2>Suppliers</h2>
			<table>
				<tr>
					<th>Supplier Id</th>
					<th>Supplier Name</th>
					<th>Address</th>
					<th>Contact Number</th>
				</tr>

				<%-- Loop all products here --%>
				<c:forEach items="${supplierList}" var="supplier">
					<tr>
						<td>${ supplier.id }</td>
						<td>${ supplier.name }</td>
						<td>${ supplier.address }</td>
						<td>${ supplier.phone }</td>
					</tr>
				</c:forEach>

			</table>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
				<a href="product">
					<button>Product</button>
				</a>
				<button style="background-color: #a8dadc;">Supplier</button>
			</div>
		</div>
	</div>

</body>

</html>