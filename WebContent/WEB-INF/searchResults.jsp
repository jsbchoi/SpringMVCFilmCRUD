<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Detail</title>
</head>
<body>
	<a href="index.html">Home</a><br><br>

	<c:if test = "${empty filmList && not empty film}" >

		<form action="update.do" method="GET">
			<input type="hidden" value=${film.id } name="filmID"/>
			<input type="submit" value="Update Film" />
		</form>

		<form action="delete.do" method="GET">
			<input type="hidden" value=${film.id } name="filmID"/>
			<input type="submit" value="Delete" />
		</form>

	</c:if>
	
	<c:if test = "${empty film && empty filmList}" >
		No films found
	</c:if>

	<c:choose>
		<c:when test="${not empty film }">
			<h2>${film.title }</h2>
			<h3>${film}</h3>
		<h3>Cast:</h3>
		<ul>
		<c:forEach items="${actorList}" var="actor">
			<li>${actor.getFirstName() } ${actor.getLastName() }</li>
		</c:forEach>
		</ul>
		<h3>Category: ${category }</h3>
		<c:forEach items="${inventory }" var="item">
			<p>${item.toString() }</p>
		</c:forEach>
		</c:when>	

		<c:when test="${not empty filmList }">
			<c:forEach items="${filmList }" var="f">
				<a href="search.do?filmID=${f.id }">${f.title}</a><br>
				${f }
				<br><br>
			</c:forEach>
		</c:when>	

	</c:choose>
	
</body>
</html>