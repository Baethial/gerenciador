package com.fis.rotonda.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fis.rotonda.dao.ProductoDAO;
import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Producto;

public class NuevoProductoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("nuevo producto registrado");
		
		//Para obtener parametro nombre pasado a traves del navegador
		String nombreProducto = request.getParameter("nombre");
		
		//PrintWriter out = response.getWriter();
		//out.println("<html><body>Nuevo Producto Registrado: " + nombreProducto + "</body></html>");
		
		Producto producto1 = new Producto(51, "Tako", 94, Categoria.PLATO_FUERTE, 4.26, "Bolita de pulpo", "ubic/fot.png");
		Producto producto2 = new Producto(51, "Sushi", 94, Categoria.PLATO_FUERTE, 4.26, "Bolita de pulpo", "ubic/fot.png");
		Producto producto3 = new Producto(51, "Ramen", 94, Categoria.PLATO_FUERTE, 4.26, "Bolita de pulpo", "ubic/fot.png");
		List<Producto> listaProductos = new ArrayList<>();
		listaProductos.add(producto1);
		listaProductos.add(producto2);
		listaProductos.add(producto3);
		
		//Agregar atributo a la request
		request.setAttribute("productos", listaProductos);
		request.setAttribute("nombre", nombreProducto);
		//Llamar al JSP
		RequestDispatcher rd = request.getRequestDispatcher("/nuevoProductoRegistrado.jsp");
		rd.forward(request, response);
		
	}

}
