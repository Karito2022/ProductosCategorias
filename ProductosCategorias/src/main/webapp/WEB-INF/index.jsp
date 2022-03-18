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
	<title>New Product</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row mt-3">
			<div class="col-6 p-5">
				<h2>New product</h2>
				<form:form action="/products/create" method="post" modelAttribute="newProduct" class="mt-4">
					<div class="mb-4 row">
						<div class="col-6">
							<h5><form:label path="name">Name:</form:label></h5>
						</div>
						<div class="col-6">
							<form:input path="name" class="form-control"/>
						</div>
						<div class="col-12">
							<p class="text-danger"><form:errors path="name"/></p>
						</div>
					</div>
					<div class="mb-4 row">
						<div class="col-6">
							<h5><form:label path="description">Description:</form:label></h5>
						</div>
						<div class="col-6">
							<form:textarea path="description" class="form-control"/>
						</div>
						<div class="col-12">
							<p class="text-danger"><form:errors path="description"/></p>
						</div>
					</div>
					<div class="mb-4 row">
						<div class="col-6">
							<h5><form:label path="price">Price:</form:label></h5>
						</div>
						<div class="col-6">
							<form:input type="number" path="price" class="form-control" step=".01"/>
						</div>
						<div class="col-12">
							<p class="text-danger"><form:errors path="price"/></p>
						</div>
					</div>
					<div class="d-flex flex-row-reverse">
					  <input type="submit" value="Create" class="btn btn-primary btn-lg"/>
					</div>
				</form:form>    
			</div>
		</div>
	</div>
</body>
</html>