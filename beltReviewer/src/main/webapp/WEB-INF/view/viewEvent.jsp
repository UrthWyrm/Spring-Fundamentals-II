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
            <h4>Host: ${event.host.first_name} ${event.host.last_name}</h4>
            <h4>Date: ${event.date}</h4>
            <h4>Location: ${event.location}, ${event.state}</h4>
            <h4># People attending: ${event.usersAttending.size()}</h4><br>
            <table class="table table-striped table-ruled">
                <tr>
                    <th>Name</th>
                    <th>Location</th>
                </tr>
                <c:forEach items="${event.usersAttending}" var="usersAttending">
                    <tr>
                        <td>${usersAttending.first_name} ${usersAttending.last_name}</td>
                        <td>${usersAttending.location}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>  
        <div class="col-md-6">
        <div class = "form-group">
            <h3>Message Wall</h3>
            <div style="height: 150px; width: 500px; overflow: auto; outline: solid 1px black;">
                <c:forEach items="${messages}" var="comment">
                    <p>${comment.user.first_name} says: ${comment.message}</p>
                </c:forEach>
            </div>
            <br>
            <form:form method="POST" action="/events/${event.id}/addmsg" modelAttribute="msg">
                <form:label path="message">Say Something: </form:label>
                <form:input path="message" type="textarea" class = "form-control"/>
                <br>
                <input type="submit" value="Submit" class="btn btn-primary"/>
            </form:form>
            <br><p><form:errors path="msg.*"/></p>
            </div>
        </div>
    </div>
</body>
</html>