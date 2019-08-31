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
<title>Television Shows</title>
</head>
<body>
	<div class="container">
		<form id="logoutForm" method="POST" action="/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit" value="Logout"
				class="btn btn-xs pull-right" />
		</form>
		<h1>
			Welcome,
			<c:out value="${user.first_name}" />
		</h1>

		<br>
		<h4>Here is a list of some of the most popular shows:</h4>

		<table class="table table-striped">
			<tr>
				<th>Name</th>
				<th>Network</th>
				<th>Rating</th>
				<th>Action / Status</th>
			</tr>
			<c:forEach items="${outofstate}" var="show">
				<tr>
					<td><a href="/shows/${show.id}">${show.name}</a></td>
					<td>${show.network}</td>
					<td>${show.ratings}</td>
					<td><a href="/shows/${show.id}/edit">Edit</a> | <a
						href="shows/${show.id}/delete">Delete</a></td>
					<td><c:out value="${category.getShows().size()}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<div class="col-md-6">
			<div class="group-group">
				<h3>Add A Show</h3>

				<form:form method="POST" action="/show/create" modelAttribute="show">
					<p>
						<form:label path="name">Name:</form:label>
						<form:input path="name" class="form-control" />
					</p>
					<p>
						<form:label path="ratings">Rate This Show:</form:label>
								<form:select path="ratings"
										class="select is-one-third">
										<form:option value=""></form:option>
										<form:option value="1">1</form:option>
										<form:option value="2">2</form:option>
										<form:option value="3">3</form:option>
										<form:option value="4">4</form:option>
										<form:option value="5">5</form:option>
										
									</form:select>
					</p>
					<p>
						<form:label path="network">Network:</form:label>
						<form:input type="network" path="network" class="form-control" />
					</p>
					
					<input type="submit" value="Create" class="btn btn-primary" />
				</form:form>
			</div>
			<div class="col-md-6">
				<p>
					<form:errors path="course.*" />
				</p>
			</div>
		</div>
	</div>
</body>
</html>