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
<link rel="stylesheet" href="static/styles/product.css">
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

			<c:choose>
				<c:when test="${param[\"addProduct\"] != null}">
					<h2>Add Product</h2>
				</c:when>
				<c:otherwise>
					<h2>Update Product</h2>
				</c:otherwise>
			</c:choose>

			<form action="product" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="id" value="${ product.id }"> <input
					type="hidden" name="imageLocation"
					value="${ product.imageLocation }">

				<c:choose>
					<c:when test="${param[\"addProduct\"] != null}">
						<input type="hidden" name="productActivity" value="addProduct">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="productActivity" value="updateProduct">
					</c:otherwise>
				</c:choose>

				<div class="input-class">
					<label for="name">Name </label> <br> <input name="name"
						id="name" type="text" autocomplete="off" required
						value="${product.name }"> <br>
				</div>

				<div class="input-class">
					<label for="quantity">Quantity </label> <br> <input
						name="quantity" id="quantity" type="number" min="1"
						autocomplete="off" required value="${ product.quantity }">
					<br>
				</div>

				<div class="input-class">
					<label for="price">Price(RM) </label> <br> <input name="price"
						id="price" type="number" min="1" autocomplete="off" required
						value="${ product.price }"> <br>
				</div>

				<div class="input-class">
					<label for="description">Description </label> <br>
					<textarea name="description" id="description" required>${ product.description }</textarea>
					<br>
				</div>

				<div class="input-class">
					<label for="image">Upload Product Image</label> <br>

					<c:choose>
						<c:when test="${param[\"addProduct\"] != null}">
							<input type="file" name="image" id="image"
								accept=".png,.jpg,.jpeg" required>
						</c:when>
						<c:otherwise>
							<input type="file" name="image" id="image"
								accept=".png,.jpg,.jpeg">
						</c:otherwise>
					</c:choose>

					<br>
				</div>

				<div class="input-class">
					<label for="supplier">Choose Supplier</label> <br> <select
						name="supplierId" id="supplier">

						<%-- Loop all suppliers here --%>
						<c:forEach items="${supplierList}" var="supplier">
							<c:choose>
								<c:when test="${ product.suppId == supplier.id }">
									<option value="${ supplier.id }" selected>${ supplier.name }</option>
								</c:when>
								<c:otherwise>
									<option value="${ supplier.id }">${ supplier.name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>

					</select>

				</div>
			</form>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
				<c:choose>
					<c:when test="${param[\"addProduct\"] != null}">
						<button id="submit">Add</button>
					</c:when>
					<c:otherwise>
						<button id="submit">Update</button>
					</c:otherwise>
				</c:choose>
				<a href="product">
					<button style="background-color: #ff5a5f; color: white;">Cancel</button>
				</a>
			</div>
		</div>
	</div>

	<script>
	const addButton = document.querySelector('#submit');
	addButton.onclick = (e) => {
		document.querySelector('form').submit();
	}
</script>
</body>

</html>