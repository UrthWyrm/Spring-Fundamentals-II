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
<title>Add A Ninja</title>
</head>
<body>
	<div class="container">
		<h1>New Ninjas</h1>
		<a href="/">Home</a> | <a href="/dojo/new">Create Dojo</a>
		<form:form action="/ninja/new" method="post" modelAttribute="ninja">
			<form:label path="first_name">First Name:</form:label>
			<form:errors path="first_name"/>
			<form:input class="form-control" path="first_name"/>
			<form:label path="last_name">Last Name:</form:label>
			<form:errors path="last_name"/>
			<form:input class="form-control" path="last_name"/>
			<form:label path="age">Age:</form:label>
			<form:errors path="age"/>
			<form:input class="form-control" path="age" type="number"/>
			<form:label path="dojo">Dojo:</form:label>
			<form:select class="form-control" path="dojo">
				<c:forEach items="${dojos}" var="dojo">
        				<form:option value="${dojo.id}"><c:out value="${dojo.name}"/></form:option>
   				</c:forEach>
			</form:select>
			<input type="submit" value="Create Ninja" class="btn btn-primary">
		</form:form>
	</div>

</body>
</html>