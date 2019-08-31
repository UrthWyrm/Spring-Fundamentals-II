<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="/">Home</a> | <a href="/product/new">Add a Product</a> | <a
			href="/category/new">Add a Category</a>
		<div class="row">
			<div class="col-6">
				<h1>
					<c:out value="${category.name}" />
				</h1>
				<h3>Products:</h3>
				<ul>
					<c:forEach items="${category.products}" var="product">
						<li><c:out value="${product.name}" /></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-6">
			
					<h3>Add a Product</h3>
					<form action="/category/addRelationship/${category.id}"
						method="POST">
						<div class="form-group">
							<label for="product">Product:</label> <select name="product">
								<c:forEach items="${product}" var="product">
									<option value="${product.id}"><c:out
											value="${product.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" value="Add Product" class="btn btn-primary">
					</form>
				
			</div>
		</div>
	</div>
</body>
</html>