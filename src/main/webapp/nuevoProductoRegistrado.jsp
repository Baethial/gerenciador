<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
   <body>
   	  <c:if test="${not empty nombre}">
   	  	Nuevo Producto Registrado: ${nombre}<br>
   	  </c:if>
   	  <c:if test="${empty nombre}">
   	  	Ningun Producto Registrado: ${nombre}<br>
   	  </c:if>
      Lista Productos:<br>
      <c:forEach items="${productos}" var="producto">
         <li>${producto.nombre}</li>
      </c:forEach>
   </body>
</html>