<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formato (fechas) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- para errores de renderizado en rutas PUT -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${category.name}</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row mt-5">
			<div class="col-12">
				<h1 class="text-info">Category: ${category.name}</h1>
			</div>
		</div>
		<div class="row mt-3">
			<div class="col-6 p-5">
				<h2 class="mb-5">Products</h2>
				<c:forEach var="product" items="${product_list}">
					<h4>${product.name}</h4>
				</c:forEach>
			</div>
			<div class="col-6 p-5">
				<h2>Add Products</h2>
				<form action="/categories/addtocategory" method="POST">
					<div class="form-group mt-5">
						<select class="form-control" name="option">
							<c:forEach var="product" items="${no_product_list}">
								<option value="${product.id}">${product.name}</option>
							</c:forEach>
						 </select>
					 </div>
					 <input type="hidden" value="${category.id}" name="id"/>
					 <button type="submit" class="btn btn-primary mt-5">Add</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>