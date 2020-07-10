<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="resources/css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body>

	<div>${sucesso}</div>

	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">Codigo Produto</th>
				<th scope="col">Descrição</th>
				<th scope="col">Custo</th>
				<th scope="col">Valor</th>
				<th scope="col">Ação</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${produtos }" var="produto">
				<tr>
					<th>${produto.codigo }</th>
					<td>${produto.descricao}</td>
					<td>${produto.precos[0].valor}</td>
					<td>${produto.precos[1].valor}</td>
					<td><a
						href="/LojaWeb/produtos/editar?codigo=${produto.codigo }">Editar</a></td>

				</tr>
			</c:forEach>

		</tbody>
	</table>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>