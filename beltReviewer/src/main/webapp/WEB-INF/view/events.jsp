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
		   <form id="logoutForm" method="POST" action="/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Logout" class="btn btn-xs pull-right"/>
        </form> 
        <h1>Welcome, <c:out value = "${user.first_name}"/></h1>
        <br>
        <h4>Here are some of the events in your state:</h4>
        <c:if test="${instate.size() == 0}"><h5>There are currently no events in your area at this time.</h5></c:if>
        <c:if test="${instate.size() > 0}">
            <table class="table table-striped table-ruled">
                <tr>
                    <th>Name</th>
                    <th>Date</th>
                    <th>Location</th>
                    <th>Host</th>
                    <th>Action / Status</th>
                </tr>
                <c:forEach items="${instate}" var="event">
                        <tr>
                            <td><a href="/events/${event.id}">${event.name}</a></td>
                            <td><fmt:formatDate pattern = "MM/dd/yyyy" value="${event.date}"></fmt:formatDate></td>
                            <td>${event.location}, ${event.state}</td>
                            <td>${event.host.first_name} ${event.host.last_name}</td>
                            <c:choose>
                                <c:when test="${event.host == user}">
                                    <td><a href="/events/${event.id}/edit">Edit</a> | <a href="events/${event.id}/delete">Delete</a></td>
                                </c:when>
                                <c:otherwise>
                                    <c:set var = "attending" value= "${false}"/>
                                    <c:forEach items="${event.getUsersAttending()}" var="partier">
                                        <c:if test="${partier == user}">
                                            <c:set var = "attending" value= "${true}"/>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${attending == false}">
                                            <td><a href="/events/${event.id}/join">Join</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Joining | <a href="events/${event.id}/leave">Cancel</a></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>        
                        </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>
        <h4>Here are some of the events in other states:</h4>
        <table class="table table-striped table-ruled">
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>Location</th>
                <th>State</th>
                <th>Host</th>
                <th>Action / Status</th>
            </tr>
            <c:forEach items="${outofstate}" var="event">
                <tr>
                    <td><a href="/events/${event.id}">${event.name}</a></td>
                    <td><fmt:formatDate pattern = "MM/dd/yyyy" value="${event.date}"></fmt:formatDate></td>
                    <td>${event.location}</td>
                    <td>${event.state}</td>
                    <td>${event.host.first_name} ${event.host.last_name}</td>
                    <c:choose>
                        <c:when test="${event.host == user}">
                            <td>Joining | <a href="/events/${event.id}/edit">Edit</a> | <a href="events/${event.id}/delete">Delete</a></td>
                        </c:when>
                        <c:otherwise>
                            <c:set var = "attending" value= "${false}"/>
                            <c:forEach items="${event.getUsersAttending()}" var="partier">
                                <c:if test="${partier == user}">
                                    <c:set var = "attending" value= "${true}"/>
                                </c:if>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${attending == false}">
                                    <td><a href="/events/${event.id}/join">Join</a></td>
                                </c:when>
                                <c:otherwise>
                                    <td>Joining | <a href="events/${event.id}/leave">Cancel</a></td>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div class = "col-md-6">
        <div class = "group-group">
            <h3>Create an Event</h3>
            
            <form:form method="POST" action="/event/create" modelAttribute="event">
                <p><form:label path="name">Name:</form:label>
                <form:input path="name" class = "form-control"/></p>
                <p><form:label path="date">Date:</form:label>
                <form:input type="date" path="date" class = "form-control" min="2019-08-22"/></p>
                <p>City: 
                    <form:input path="location" class="form-control"/>
                    <p>State:
                    <form:select path="state" class="form-control">
                        <c:forEach items="${usStates}" var="state">
                            <form:option value="${state}"></form:option>
                        </c:forEach>
                    </form:select>
                </p>  
                <input type="submit" value="Create" class="btn btn-primary"/>
            </form:form>   
        </div>  
        <div class="col-md-6"><p><form:errors path="course.*"/></p></div>
    </div>
</div>
</body>
</html>