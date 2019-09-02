<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<form:form action="add.do" method="POST" modelAttribute="film">
		<form:label path="title">Title:</form:label>
		<form:input path="title" />
		<br />
		<form:label path="description">Description:</form:label>
		<form:input path="description" />
		<br />
		<form:label path="releaseYear">Release year:</form:label>
		<form:input path="releaseYear" />
		<form:errors path="releaseYear"/>
		<br />
		<form:label path="language_id">Language id:</form:label>
		<form:input path="language_id" />
		<form:errors path="language_id"/>
		<br />
		<form:label path="rentalDuration">Rental duration:</form:label>
		<form:input path="rentalDuration" />
		<br />
		<form:label path="rentalRate">Rental rate:</form:label>
		<form:input path="rentalRate" />
		<br />
		<form:label path="length">Length:</form:label>
		<form:input path="length" />
		<br />
		<form:label path="length">Length:</form:label>
		<form:input path="length" />
		<br />
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost" />
		<br />
		<form:label path="rating">Rating:</form:label>
		<form:input path="rating" />
		<br />
		<form:label path="special_features">Special features:</form:label>
		<form:input path="special_features" />
		<br />
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>