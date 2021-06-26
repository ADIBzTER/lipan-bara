<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					<a href="home"><a href="home">Lipan Bara</a> | Resit
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
			<div class="resit-top">Order Resit #898</div>
			<table id="user-details">
				<tr>
					<th>Name</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Shipping</th>
				</tr>

				<tr>
					<td>Saiful Samsul</td>
					<td>+60 11-222 3333</td>
					<td>No 99, Jalan Mahsuri 3, Taman Mahsuri, 79999, Nilai,
						Negeri Selangor</td>
					<td>JnT</td>
				</tr>
			</table>

			<table id="order-details">
				<tr>
					<th>Name</th>
					<th>Price(RM)</th>
					<th>Quantity</th>
				</tr>

				<br>

				<tr>
					<td>[144hz] Xiaomi Curved Gaming Monitor Mi Surface</td>
					<td>1,649.00</td>
					<td>1</td>
				</tr>
			</table>

		</div>
	</div>

</body>

</html>