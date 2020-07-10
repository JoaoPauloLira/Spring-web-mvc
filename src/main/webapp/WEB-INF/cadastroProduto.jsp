<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="../resources/css/bootstrap.min.css">
<title>Cadastro de Produtos</title>
</head>
<body>
	<div class="container">

		<jsp:include page="menu.jsp" />

		<hr>

		<h3>Cadastro de Produtos</h3>

		<hr>

		<form:form action="/LojaWeb/produtos" method="post"
			enctype="multipart/form-data">

			<div class="form-group">

				<div class="row">
					<div class="col-auto">
						<label for="dataCadastro">Data Cadastro</label> <input
							type="datetime" class="form-control" name="dataCadastro"
							id="dataCadastro" placeholder="">
					</div>
					<div class="col-auto">
						<label for="dataUltimaAlteracao">Data Ultima Alteração</label> <input
							type="datetime" class="form-control" name="dataUltimaAlteracao"
							id="dataUltimaAlteracao" placeholder="">
					</div>
				</div>

			</div>


			<div class="form-group">
				<div class="row">
					<div class="col-md-2">
						<label for="codigo">Código</label> <input type="number"
							class="form-control" id="codigo" readonly="readonly"
							placeholder="">
					</div>
					<div class="col-md-4">
						<label for="descricao">Descrição</label> <input type="text"
							class="form-control" id="descricao" name=descricao
							placeholder="descrição do produto">
						<form:errors path="descricao" />
					</div>
					<div class="col-md-2">
						<label for="UnidadeMedida">Unidade</label> <select
							class="form-control" id="UnidadeMedida" name="UnidadeMedida">
							<option>Unidade 1</option>
							<option>Unidade 2</option>
							<option>Unidade 3</option>
							<option>Unidade 4</option>
							<option>Unidade 5</option>
						</select>
					</div>
					<div class="col-md-4">
						<label for="codigobarras">Código de Barras</label> <input
							type="text" class="form-control" id="codigobarras"
							name="codigobarras" placeholder="xxxxxxxxxxxxxx">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="row">
					<div class="col-md-4">
						<label for="sessaoProduto">Sessão do Produto</label> <select
							class="form-control" id="sessaoProduto" name="sessaoProduto">
							<option>Sessao 1</option>
						</select>
					</div>
					<div class="col-md-4">
						<label for="grupo">Grupo</label> <select class="form-control"
							id="grupo" name="grupo">
							<option>Grupo 1</option>
						</select>
					</div>
					<div class="col-md-4">
						<label for="subGrupo">Sub-Grupo</label> <select
							class="form-control" id="subGrupo" name="subGrupo">
							<option>SubGrupo 1</option>
						</select>
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="row">
					<div class="col-md-4">
						<label for="situacao">Situação</label> <select
							class="form-control" id="situacao" name="situacao">
							<option value="ATIVO">Ativo</option>
							<option value="INATIVO">Inativo</option>
						</select>
					</div>
					<!--  <div class="col-md-4">
						<label for="margemLucro">Margem de Lucro</label> 
						<input type="number" class="form-control" id="margemLucro" name="margemLucro" placeholder=""> 
					</div> -->
					<div class="col-md-4">
						<label for="referencia">Refêrencia</label> <input type="text"
							class="form-control" id="referencia" name="referencia"
							placeholder="">
					</div>
				</div>
			</div>

			<div class="form-group">
				<div class="row">
					<!-- <div class="col-md-2">
						<label for="estoqueMinimo">Estoque Mínimo</label> 
						<input type="text" class="form-control" id="estoqueMinimo" name="estoqueMinimo" placeholder=""> 
					</div>
				    <div class="col-md-2">
						<label for="estoqueMaximo">Estoque Máximo</label> 
						<input type="text" class="form-control" id="estoqueMaximo" name="estoqueMaximo" placeholder=""> 
					</div>
					<div class="col-md-2">
						<label for="estoqueAtual">Estoque Atual</label> 
						<input type="text" class="form-control" id="estoqueAtual" name="estoqueAtual" placeholder=""> 
					</div>-->

					<c:forEach items="${tiposPreco}" var="tipo" varStatus="status">
						<div class="col-md-3">
							<label for="preco">${tipo}</label> <input type="text"
								class="form-control" id="preco"
								name="precos[${status.index }].valor"> <input
								type="hidden" class="form-control" id="preco"
								name="precos[${status.index }].tipo" value="${tipo}">

						</div>
					</c:forEach>
					<div class="col-md-2">
						<label for="imgPath">Foto</label> <input type="file"
							class="form-control" id="imgPath" name="imgPath">
					</div>
				</div>
			</div>

			<div class="form-group">
				<label for="observacao">Observação</label>
				<textarea class="form-control" id="observacao" name="observacao"
					rows="3"></textarea>
				<form:errors path="observacao" />
			</div>

			<button type="submit" class="btn btn-primary">Salvar</button>

		</form:form>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>