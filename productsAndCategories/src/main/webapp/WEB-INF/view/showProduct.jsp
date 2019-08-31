<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Products and Categories</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
			<a href="/">Home</a> | <a href="/product/new">Add a Product</a> | <a
				href="/category/new">Add a Category</a>
		<div class="row">
			<div class="col-6">
				<h1>
					<c:out value="${product.name}" />
				</h1>
				<h3>Categories:</h3>
				<ul>
					<c:forEach items="${product.categories}" var="category">
						<li><c:out value="${category.name}" /></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-6">
					<h3>Add a Category</h3>
					<form action="/product/addRelationship/${product.id}" method="POST">
						<div class="form-group">
							<label for="category">Category:</label> <select name="category">
								<c:forEach items="${category}" var="category">
									<option value="${category.id}"><c:out
											value="${category.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" value="Add Category" class="btn btn-primary">

					</form>

			</div>
		</div>
	</div>
</body>
</html>