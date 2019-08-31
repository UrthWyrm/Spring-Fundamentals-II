<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Products And Categories</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</head>
	<body>
<div class="container">
		<h1>Products and Categories</h1>
		<a href="/product/new">Add a Product</a> | <a href="/category/new">Add a Category</a>
		<h3>Categories</h3>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>Category Name</th>
					<th>Product Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${category}" var="category">
					<tr>
						<td><a href="/category/addRelationship/${category.id}"><c:out value="${category.name}"/></a></td>
						<td><c:out value="${category.getProducts().size()}"></c:out></td>
					</tr>				
				</c:forEach>
			</tbody>		
		</table>
		<h3>Products</h3>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>Product Name</th>
					<th>Product Description</th>
					<th>Category Count</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${product}" var="product">
					<tr>
						<td><a href="/product/${product.id}"><c:out value="${product.name}"/></a></td>
						<td><c:out value="${product.description}"/></td>
						<td><c:out value="${product.getCategories().size()}"></c:out></td>
					</tr>				
				</c:forEach>
			</tbody>		
		</table>
	</div>
	</body>
</html>