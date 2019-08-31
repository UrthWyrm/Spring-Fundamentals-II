<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Dojo And Ninjas</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</head>
	<body>
<div class="container">
		<h1>Dojos and Ninjas</h1>
		<a href="/dojo/new">Create Dojo</a> | <a href="/ninja/new">Create Ninja</a>
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>Dojo Name</th>
					<th>Ninja Count</th>
					<th>Date Created</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${dojo}" var="dojo">
				<tr>
					<td><a href="/dojo/${dojo.id}"><c:out value="${dojo.name}"/></a></td>
					<td><c:out value="${dojo.getNinja().size()}"/> ninjas</td>
					<td><c:out value="${dojo.created_at}"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	</body>
</html>