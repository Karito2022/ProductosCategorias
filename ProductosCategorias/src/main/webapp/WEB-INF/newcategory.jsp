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
	<title>New Category</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		
		<div class="row mt-3">
			<div class="col-6 p-5">
				<h2>New Category</h2>
				<form:form action="/categories/create" method="post" modelAttribute="newCategory" class="mt-4">
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
					<div class="d-flex flex-row-reverse">
					  <input type="submit" value="Create" class="btn btn-primary btn-lg"/>
					</div>
				</form:form>    
			</div>
		</div>
	</div>
</body>
</html>