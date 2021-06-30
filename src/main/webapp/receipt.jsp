<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.config.*"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Receipt</title>
<link rel="stylesheet" href="static/styles/resit.css">
</head>

<body>
	<div id="app-bar">

		<div id="appbar-left">
			<div id="main-logo">
				<h1>
					<a href="home">Lipan Bara</a> | Resit
				</h1>
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
		<div class="resit">
			<div class="resit-top">
				Order Resit #<%=(new Date()).getTime()%></div>
			<table id="user-details">
				<tr>
					<th>Name</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Shipping</th>
				</tr>

				<tr>
					<td>${ name }</td>
					<td>${phone }</td>
					<td>${ address }</td>
					<td>Lipan Express</td>
				</tr>
			</table>

			<br>

			<table id="order-details">
				<tr>
					<th>Name</th>
					<th>Quantity</th>
					<th>Price(RM)</th>

				</tr>

				<c:set var="totalPrice" value="0.0" />

				<c:forEach items="${purchaseList}" var="purchase">
					<tr>
						<td>${ purchase.product.name }</td>
						<td>${ purchase.quantity }</td>
						<td>${ CurrencyFormatter.format(purchase.product.price) }</td>
					</tr>

					<c:set var="totalPrice" value="${ totalPrice + purchase.price }" />
				</c:forEach>

				<tr>
					<td colspan="2"><b>Total Price</b></td>
					<td><b>${ CurrencyFormatter.format(totalPrice) }</b></td>
				</tr>
			</table>
			<br>
			<button onclick="location.href = 'home';">Go To Home</button>
		</div>
	</div>

</body>

</html>