package com.fis.rotonda.servlet;

import java.io.IOException;

import com.fis.rotonda.accion.Accion;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Servlet: Unico punto de entrada de la aplicacion 
public class UnicaEntradaServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtencion de la accion a ejecutar
		String paramAccion = request.getParameter("accion");
		
		String nombreDeClase = "com.fis.rotonda.accion." + paramAccion;
		String nombre;
		
		try {
			//Llamado de la clase accion 
			//y ejecucion de su metodo
			Class clase = Class.forName(nombreDeClase);
			Accion accion = (Accion) clase.newInstance();
			nombre = accion.ejecutar(request, response);
			
		} catch (ClassNotFoundException | InstantiationException | 
				IllegalAccessException | ServletException | IOException e) {
			throw new ServletException(e);
		}
		
		String[] tipoYDireccion = nombre.split(":");
		
		if(tipoYDireccion[0].equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+tipoYDireccion[1]);
			rd.forward(request, response);
		}else {
			response.sendRedirect(tipoYDireccion[1]);
		}
		
	}

}
