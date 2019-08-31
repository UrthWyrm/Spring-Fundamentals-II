<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>Licenses</title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	</head>
	<body>
	<div class="container">
		<h1><c:out value="${user.first_name} ${user.last_name}"/></h1>
		<ul>
			<li>License Number: <c:out value="${user.driversLicense.number}"/></li>
			<li>State: <c:out value="${user.driversLicense.state}"/></li>
			<li>Expiration Date: <fmt:formatDate value="${user.driversLicense.expiration_date}" pattern="MM-dd-yyyy"/></li>
			<li><a href="/person/${user.id}/delete">Delete</a></li>
		</ul>
	</div>
</html>