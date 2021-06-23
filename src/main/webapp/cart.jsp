<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lipan Bara Tech | Cart</title>
<link rel="stylesheet" href="static/styles/cart.css">
</head>

<body>
	<div id="app-bar">

		<div id="appbar-left">
			<div id="main-logo">
				<h1>
					<a href="home">Lipan Bara</a> | Cart
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

			<%-- Loop all products here --%>
			<c:forEach items="${productList}" var="product">
				<div class="product">
					<img src="${ product.product.imageLocation }" alt="product-image">
					<div class="product-desc">
						<span class="product-name">${ product.product.name }</span> <span
							class="product-description">${ product.product.description }
						</span> <span class="product-price">RM${ product.product.price }</span> <span
							class="product-buttons">
							<button>Remove From Cart</button>
						</span>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

	<div id="cart-footer">
		<div class="grid-item">
			Name: ${ name }<br> Phone: ${ phone } <br> Address: ${ address }
			<br> Shipping Method: Lipan Express
		</div>
		<div class="grid-item">
			<div class="pay-div">
				<a href="receipt">
					<button>Pay</button>
				</a>
			</div>
		</div>
	</div>
</body>

</html>