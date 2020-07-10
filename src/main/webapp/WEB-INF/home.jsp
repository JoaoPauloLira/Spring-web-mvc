<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<c:url value="resources/css" var="cssPath" />

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css">

<title>NewTec - Sistema de Vendas</title>
</head>
<body>
	<div class="container">
 		<jsp:include page = "menu.jsp" />
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>