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
<title>Events</title>
</head>
<body>
	<div class="container">
		<a href="/home">Back</a>
		<h1>${event.name}</h1>
		<div class="col-md-6">
			<h3>Edit Event</h3>
			<form:form method="POST" action="/events/${event.id}/edit"
				modelAttribute="emptyevent">
				<p>
					<form:label path="name">Name: </form:label>
					<form:input path="name" />
					(previously: ${event.name})
				</p>
				<p>
					<form:label path="date">Date: </form:label>
					<form:input type="date" path="date" />
					(previously:
					<fmt:formatDate pattern="MM/dd/yyyy" value="${event.date}"></fmt:formatDate>
					)
				</p>
				<p>
					<form:label path="location">Location: </form:label>
					<form:input path="location" />
					<form:select path="state">
						<c:forEach items="${usStates}" var="state">
							<form:option path="state" value="${state}"></form:option>
						</c:forEach>
					</form:select>
					(previously: ${event.location}, ${event.state})
				</p>
				<input type="submit" value="Edit" class="btn btn-primary" />
			</form:form>
		</div>
		<div class="col-md-6">
			<p>
				<form:errors path="emptyevent.*" />
			</p>
		</div>
	</div>
</body>
</html>