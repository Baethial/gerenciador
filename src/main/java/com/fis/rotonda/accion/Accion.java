package com.fis.rotonda.accion;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Accion {
	String ejecutar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
