<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update successful!</title>
</head>
<body>
	<c:choose>
		<c:when test="${isUpdated }">
			<c:choose>
				<c:when test="${not empty film }">
					<h1>${film.title} updated successfully!</h1>
					<h2>${film.title }</h2>
					<h3>${film}</h3>
					<h3>Cast:</h3>
					<ul>
						<c:forEach items="${actorList}" var="actor">
							<li>${actor.getFirstName() } ${actor.getLastName() }</li>
						</c:forEach>
					</ul>
					<h3>Category: ${category }</h3>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
			<h2>Update failed!</h2>
		</c:otherwise>
	</c:choose>
	<form action="index.html">
		<input type="submit" value="Return to homepage">
	</form>
</body>
</html>