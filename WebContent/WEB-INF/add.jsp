<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add film</title>
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
		<form:errors path="releaseYear" />
		<br />
		<form:label path="language_id">Language id: </form:label>
		<form:input path="language_id" />
		<form:errors path="language_id" />
		<br>
		<span>(1: English, 2: Italian, 3: Japanese, 4: Mandarin, 5: French, 6: German)</span>
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
		<form:label path="replacementCost">Replacement Cost:</form:label>
		<form:input path="replacementCost" />
		<br />
		<span>Rating:</span>
		<form:select path="rating" items="${ratings}" />

		<br />
		<form:checkboxes path="special_features" items="${specialFeatures}" />
		<br />
		<span> <input type="submit" value="Submit" />
		</span>
	</form:form>
</body>
</html>