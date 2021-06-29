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

			<h2>Sales Report</h2>
			<table>
				<tr>
					<th>Purchase Id</th>
					<th>Date</th>
					<th>Shipping</th>
					<th>Quantity</th>
					<th>Customer Name</th>
					<th>Product Name</th>
					<th>Price(RM)</th>
				</tr>

				<c:set var="totalSales" value="0.0" />

				<%-- Loop all purchases here --%>
				<c:forEach items="${purchaseList}" var="purchase">
					<tr>
						<td>${ purchase.id }</td>
						<td>${ purchase.date }</td>
						<td>${ purchase.shipping }</td>
						<td>${ purchase.quantity }</td>
						<td>${ purchase.customer.name }</td>
						<td>${ purchase.product.name }</td>
						<td>${ CurrencyFormatter.format(purchase.price) }</td>
					</tr>

					<c:set var="totalSales" value="${ totalSales + purchase.price }" />

				</c:forEach>
				<tr>
					<td colspan="6">
						<b>Total Sales</b>
					</td>
					<td>
						<b>${ CurrencyFormatter.format(totalSales) }</b>
					</td>
				</tr>

			</table>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
				<a href="product">
					<button>Product</button>
				</a>
				<a href="supplier">
					<button>Supplier</button>
				</a>
				<button style="background-color: #a8dadc;">Sales</button>
			</div>
		</div>
	</div>

</body>

</html>