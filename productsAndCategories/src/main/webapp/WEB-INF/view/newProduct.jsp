<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1>Products and Categories</h1>
		<a href="/">Home</a> | <a href="/category/new">Add a Category</a>
		<form:form action="/product/new" method="POST" modelAttribute="product">
			<div class="form-group">
				<form:label for="name" path="name">Name: 
				<form:input type="text" path="name" class="form-control" id="name"/>
				</form:label>
			</div>
			<div class="form-group">
				<form:label for="description" path="description">Description: 
				<form:input type="text" path="description" class="form-control" id="description"/>
				</form:label>
			</div>
			<div class="form-group">
				<form:label for="price" path="price">Price: 
				<form:input type="number" min="0.00" max="10000.00" step="0.01" path="price" class="form-control" id="price"/>
				</form:label>
			</div>
				<input type="submit" value="Add Product" class="btn btn-primary">
				
		</form:form>
		<% if(request.getAttribute("errors") != null){ %>
		<fieldset>
		<legend>Errors</legend>
		<c:forEach items="${errors}" var="error">
			<p><c:out value="${error.getDefaultMessage()}"/></p>
		</c:forEach>
		</fieldset>
		<% } %>
		</div>

</body>
</html>