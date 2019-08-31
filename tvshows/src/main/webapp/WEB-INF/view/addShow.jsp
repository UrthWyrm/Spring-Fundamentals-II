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

<meta charset="UTF-8">
<title>Shows</title>
</head>
<body>
	<div class="container">
		<a href="/home">Back</a>
		<h1>${show.name}</h1>
		<div class="col-md-6">
			<h3>Edit Show</h3>
			<form:form method="POST" action="/shows/${show.id}/edit"
				modelAttribute="emptyshow">
				<p>
					<form:label path="name">Name: </form:label>
					<form:input path="name" />
					(previously: ${show.name})
				</p>
				<p>
					<form:label path="network">Network: </form:label>
					<form:input path="network" />
					(previously: ${show.name})
				</p>
				<p>
					<form:label path="ratings">Rate This Show:</form:label>
					<form:select path="ratings" class="select is-one-third">
						<form:option value=""></form:option>
						<form:option value="1">1</form:option>
						<form:option value="2">2</form:option>
						<form:option value="3">3</form:option>
						<form:option value="4">4</form:option>
						<form:option value="5">5</form:option>

					</form:select>
					(previously: ${show.ratings})
				</p>

				<input type="submit" value="Edit" class="btn btn-primary" />
			</form:form>
		</div>
		<div class="col-md-6">
			<p>
				<form:errors path="emptyshow.*" />
			</p>
		</div>
	</div>
</body>
</html>