<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.config.*"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Cart</title>
<link rel="stylesheet" href="static/styles/cart.css">
</head>

<body>
	<div id="app-bar">

		<div id="appbar-left">
			<div id="main-logo">
				<h1>
					<a href="home">Lipan Bara</a>
					| Cart
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
		<div class="products-table">

			<c:set var="totalPrice" value="0.0" />

			<%-- Loop all products in cart here --%>
			<c:forEach items="${cartList}" var="cart">
				<form class="product" action="cart" method="POST">
					<input type="hidden" name="cartActivity" value="removeFromCart">
					<input type="hidden" name="cartId" value="${ cart.id }">

					<img src="${ cart.product.imageLocation }" alt="product-image">

					<div class="product-desc">
						<span class="product-name">${ cart.product.name }</span>
						<span class="product-description">${ cart.product.description }
						</span>
						<span class="product-price">RM${ CurrencyFormatter.format(cart.product.price) }</span>
						<span class="product-buttons">
							<button>Remove From Cart</button>
						</span>
					</div>
				</form>

				<c:set var="totalPrice"
					value="${ totalPrice + cart.product.price }" />

			</c:forEach>

		</div>
	</div>

	<div id="cart-footer">
		<div class="grid-item">
			Name: ${ name }
			<br>
			Phone: ${ phone }
			<br>
			Address: ${ address }
			<br>
			Shipping Method: Lipan Express
			<br>
			Total: RM${ CurrencyFormatter.format(totalPrice) }
		</div>
		<div class="grid-item">

			<%-- Check if there is item in cart --%>
			<c:choose>
				<c:when test="${cartList.size() != 0}">
					<div class="pay-div">
						<a href="receipt">
							<button>Pay</button>
						</a>
					</div>
				</c:when>
				<c:otherwise>
					<%-- NOTHING --%>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>

</html>