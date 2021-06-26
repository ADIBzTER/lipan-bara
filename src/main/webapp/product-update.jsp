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

			<h2>Add Or Update Product</h2>
			<form action="product" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="addProduct">
				<div class="input-class">
					<label for="name">Name </label> <br> <input name="name"
						id="name" type="text" required> <br>
				</div>

				<div class="input-class">
					<label for="quantity">Quantity </label> <br> <input
						name="quantity" id="quantity" type="number" required> <br>
				</div>

				<div class="input-class">
					<label for="price">Price </label> <br> <input name="price"
						id="price" type="number" required> <br>
				</div>

				<div class="input-class">
					<label for="description">Description </label> <br>
					<textarea name="description" id="description" type="text" required></textarea>
					<br>
				</div>

				<div class="input-class">
					<label for="image">Upload Product Image</label> <br> <input
						type="file" name="image" id="image" accept=".png,.jpg,.jpeg"
						required> <br>
				</div>

				<div class="input-class">
					<label for="supplier">Choose Supplier</label> <br> <select
						name="supplierId" id="supplier">

						<%-- Loop all suppliers here --%>
						<c:forEach items="${supplierList}" var="supplier">
							<option value="${ supplier.id }">${ supplier.name }</option>
						</c:forEach>

					</select>
				</div>
			</form>

		</div>
	</div>

	<div class="navbar">
		<div class="navbar-flex">
			<div class="navbar-grid">
				<button id="add">Add</button>
				<a href="product">
					<button style="background-color: #ff5a5f;">Cancel</button>
				</a>
			</div>
		</div>
	</div>

	<script>
	const addButton = document.querySelector('#add');
	addButton.onclick = (e) => {
		let files = document.querySelector('#image').files;
		let file = files[0];

		let fileName = file.name;
		fileName = fileName.split('\.');
		fileName = Date.now() + fileName[fileName.length - 1];
		file.name = fileName;
		
		files[0] = file;
		
		document.querySelector('form').submit();
	}
</script>
</body>

</html>