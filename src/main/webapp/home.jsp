<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Lipan Bara Tech | Home</title>
<link rel="stylesheet" href="./static/styles/home.css">
</head>

<body>
	<div id="app-bar">
		<div id="main-logo">
			<h1>Lipan Bara</h1>
		</div>
		<input type="text" name="search" id="search-bar"
			placeholder="Lampu Kedai Tomyam">
		<div id="sigin-signup">
			<a href="sign-up.jsp">Sign Up</a> | <a href="login.jsp">Login</a>
		</div>
	</div>

	<div id="center">
		<div class="products-table">

			<%-- Loop all products here --%>
			<c:forEach items="${productList}" var="product">

				<div class="product">
					<img src="${product.prodImageLocation}" alt="product-image">
					<div class="product-desc">
						<span class="product-name">${product.prodName}</span> <span
							class="product-description">${product.prodDescription} </span> <span
							class="product-price">RM${product.prodPrice}</span> <span
							class="product-buttons">
							<button>Buy</button>
						</span>
					</div>
				</div>

			</c:forEach>

		</div>

	</div>
</body>

</html>