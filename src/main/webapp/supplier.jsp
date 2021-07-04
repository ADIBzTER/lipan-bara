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
				<h1>Lipan Bara | Admin Dashboard</h1>
			</div>
		</div>

		<div id="appbar-right">
			<h3>${ username }</h3>
			|
			<div id="logout">
				<a href="logout">Logout</a>
			</div>
		</div>

	</div>

	<div id="center">
		<div class="suppliers-table">

			<h2>Suppliers</h2>
			<a href="supplier?addSupplier=true">
				<button>Add Supplier</button>
			</a>
			<table>
				<tr>
					<th></th>
					<th>Supplier Name</th>
					<th>Address</th>
					<th>Phone</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>

				<c:set var="counter" value="0" />
				<%-- Loop all suppliers here --%>
				<c:forEach items="${supplierList}" var="supplier">
					<c:set var="counter" value="${ counter + 1}" />

					<tr>
						<td>${ counter }</td>
						<td>${ supplier.name }</td>
						<td>${ supplier.address }</td>
						<td>${ supplier.phone }</td>
						<td><a
							href="supplier?updateSupplier=true&supplierId=${ supplier.id }">
								<button id="${ supplier.id }" class="update-button">Update</button>
						</a></td>
						<td>
							<button id="${ supplier.id }" class="delete-button"
								data-supplier-name="${ supplier.name }">Delete</button>
						</td>
					</tr>
				</c:forEach>

				<c:set var="totalPrice" value="${ totalPrice + cart.product.price }" />

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
				<a href="purchase">
					<button>Sales</button>
				</a>
			</div>
		</div>
	</div>

	<script src="./static/scripts/supplier.js"></script>
</body>

</html>