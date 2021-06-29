<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.config.*"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="./images/favicon.ico" />
<title>Lipan Bara Tech | Home</title>
<link rel="stylesheet" href="./static/styles/home.css">
</head>

<body>
	<div id="app-bar">
		<div id="main-logo" class="grid-element">
			<h1>
				<a href="home">Lipan Bara</a>
			</h1>
		</div>
		<input type="text" name="search" id="search-bar" class="grid-element"
			placeholder="Lampu Kedai Tomyam">
		<div id="sigin-signup" class="grid-element">

			<c:choose>
				<c:when test="${loggedIn != null}">
					<h3>${ username }
						|
						<a href="cart">Cart</a>
						|
						<a href="logout">Logout</a>
					</h3>
				</c:when>
				<c:otherwise>
					<h3>
						<a href="register">Sign Up</a>
						|
						<a href="login">Login</a>
					</h3>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

	<div id="center">
		<div class="products-table">

			<%-- Loop all products here --%>
			<c:forEach items="${productList}" var="product">
				<form class="product" action="cart" method="GET"
					onsubmit="return false">

					<input type="hidden" class="cart-activity" name="cartActivity"
						value="addToCart">

					<input type="hidden" class="product-id" name="productId"
						value="${ product.id }">

					<img src="${product.imageLocation}" alt="product-image">

					<div class="product-desc">
						<span class="product-name">${product.name}</span>
						<span class="product-description">${product.description} </span>
						<span class="product-price">RM${CurrencyFormatter.format(product.price)}
							| Available: ${ product.quantity }</span>
						<span class="product-buttons">
							<button id="buy">Buy</button>

							<c:choose>
								<c:when test="${loggedIn != null}">
									<button id="add-cart">Add to Cart</button>
								</c:when>
								<c:otherwise>
									<button onclick="location.href='login'">Add to Cart</button>
								</c:otherwise>
							</c:choose>

						</span>
					</div>
				</form>
			</c:forEach>

		</div>

	</div>
	<script type="text/javascript" src="./static/scripts/home.js"></script>
</body>

</html>