<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Completed Action</title>
</head>
<body>
	<a href="index.html">Home</a><br>
	
	<c:choose>

    	<c:when test="${isDeleted == true }">
  			Deleted film ${filmID }
    	</c:when>    

    	<c:otherwise>
    	    Could not delete film 
    	</c:otherwise>

	</c:choose>

</body>
</html>