package com.fis.rotonda.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.fis.rotonda.dao.ComboDAO;
import com.fis.rotonda.dao.ProductoDAO;
import com.fis.rotonda.factory.ConnectionFactory;
import com.fis.rotonda.modelo.Carrito;
import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Combo;
import com.fis.rotonda.modelo.Producto;
import com.fis.rotonda.modelo.Usuario;

public class ProductosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var factory = new ConnectionFactory();
		ProductoDAO productoDao = new ProductoDAO(factory.recuperaConexion());
		ComboDAO comboDao = new ComboDAO(factory.recuperaConexion());
		Usuario usuario = new Usuario("Andres", "direccion", "3203692468", "123@falso.com", 1150000);
		Carrito carrito = usuario.getCarrito();
		carrito.agregarProducto(productoDao.obtenerProducto(2), 5L);
		carrito.agregarProducto(productoDao.obtenerProducto(5), 3L);
		
		List<Producto> listaProductos = productoDao.listar();
		List<Combo> listaCombos = comboDao.listar();
		
		//Agregar atributo a la request
		request.setAttribute("usuario", usuario);
//		request.setAttribute("carrito", carrito);
		request.setAttribute("listaProductos", listaProductos);
		request.setAttribute("listaCombos", listaCombos);
		
		//Llamar al JSP
		RequestDispatcher rd = request.getRequestDispatcher("/productos.jsp");
		rd.forward(request, response);
		
	}

}
