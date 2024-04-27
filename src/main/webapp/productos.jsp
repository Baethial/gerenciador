<%@page import="com.mchange.v2.sql.filter.SynchronizedFilterDataSource"%>
<%@page import="com.fis.rotonda.modelo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<title>Productos rotonda de comidas</title>
	<link rel="stylesheet" type="text/css" href="styles.css" />
	<script>
        window.onload = function() {
            const btnCart = document.querySelector('.container-icon');
            const containerCartProducts = document.querySelector('.container-cart-products');

            btnCart.addEventListener('click', function() {
                containerCartProducts.classList.toggle('hidden-cart');
            });
        };
    </script>
</head>

<body>
		<div class="stripe">
			<h1>ROTONDA DE COMIDAS CCD</h1>
		</div>

		<header>
			
			<!-- Esta seccion es la encargada del carrito -->
			<div class="container-icon">
				<!-- Imagen del carrito -->
				<svg
					xmlns="http://www.w3.org/2000/svg"
					fill="none"
					viewBox="0 0 24 24"
					stroke-width="1.5"
					stroke="currentColor"
					class="icon-cart"
				>
					<path
						stroke-linecap="round"
						stroke-linejoin="round"
					/>
				</svg>
				<!-- Contador de productos del carrito (Numero que aparece antes de abrir el carrito) -->
				<div class="count-products">
					<span id="contador-productos">0</span>
				</div>
				
				<!-- Contenedor de los items del carrito) -->
				<div class="container-cart-products hidden-cart">
					<div class="cart-product">
						<!-- Aqui se muestran los items que se van guardando en el carrito) -->					
						<c:forEach items="${usuario.getCarrito().getListaProductos().entrySet()}" var="pareja" >
							<div class="info-cart-product">
                            	<span class="cantidad-producto-carrito">${pareja.getValue()}</span>
                            	<p class="titulo-producto-carrito">${pareja.getKey().getNombre()}</p>
                            	<span class="precio-producto-carrito">${pareja.getKey().getPrecio()}</span>
                        	</div>
						</c:forEach>
						
					</div>
					<!-- Valor total de los productos en el carrito -->
                    <div class="cart-total">
                        <h3>Total:</h3>
                        <span class="total-pagar">$200</span>
						<a href="pagoRealizado.HTML"><button>Realizar pago</button></a>
                    </div>
				</div>
			</div>
		</header>
		<div class="subtituloSeccion"><h3 >PRODUCTOS</h3></div>
		<!-- Contenedor de los proudctos  -->
		<div class="container-items">
				
			<c:forEach items="${listaProductos}" var="producto" >
				<!-- Producto individual  -->	
				<div class="item">
					<figure>
						<img
							src="${producto.uriFoto}"
							alt="producto"
						/>
					</figure>
					<div class="info-product">
						<h2>${producto.nombre}</h2>
						<p class="price">${producto.precio}</p>
						<p>${producto.descripcion}</p>
						<button>Añadir al carrito</button>
					</div>
				</div>
			
			</c:forEach>
			
		</div>
		<div class="subtituloSeccion"><h3 >COMBOS</h3></div>
		
		<!-- Contenedor de los combos  -->
		<div class="container-items">
			
			<c:forEach items="${listaCombos}" var="combo" >
				<!-- Combo individual  -->
				<div class="item">
					<figure>
						<img
							src="${combo.uriFoto}"
							alt="producto"
						/>
					</figure>
					<div class="info-product">
						<h2>${combo.nombre}</h2>
						<p class="price">${combo.precio}</p>
						<p>${combo.descripcion}</p>
						<button>Añadir al carrito</button>
					</div>
				</div>
			
			</c:forEach>
			
		</div>

        <!-- <script src="index.js"></script> -->
	</body>
</html>