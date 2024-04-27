<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/nuevoProducto" var="linkServletNuevoProducto"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Enviado el formulario redirige a... -->
	<!-- <form action="/gerenciador/nuevoProducto" method="post"> -->
		<form action="${linkServletNuevoProducto}" method="post">
		Nombre Producto: <input type="text" name="nombre">
		<input type="submit">
	</form>
</body>
</html>