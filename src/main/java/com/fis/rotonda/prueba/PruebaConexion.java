package com.fis.rotonda.prueba;

import java.sql.SQLException;
import java.util.List;

import com.fis.rotonda.dao.ProductoDAO;
import com.fis.rotonda.factory.ConnectionFactory;
import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Producto;

public class PruebaConexion {

	public static void main(String[] args) throws SQLException {
		
		var factory = new ConnectionFactory();
		ProductoDAO productoDao = new ProductoDAO(factory.recuperaConexion());
		
		// Prueba Insercion
//		Producto producto = new Producto("California Rol", 16, Categoria.PLATO_FUERTE, 25000, "Maki con pepino, palmmito ...", "/ubicacion");
//        productoDao.guardar(producto);
		
		// Prueba Eliminacion
//		productoDao.eliminar(6);
		
		// Prueba Consulta
//		List<Producto> listaProductos = productoDao.listar();
//		for (Producto producto : listaProductos) {
//			System.out.println(producto);
//		}
		
		// Prueba Actualizacion
//		productoDao.modificar("California Roll", 20, 6.12, "Maki", "Ubicacion", 6);
		
		// Prueba Consulta por Categoria
		List<Producto> listaProductos = productoDao.listar(Categoria.PLATO_FUERTE);
		for (Producto producto : listaProductos) {
			System.out.println(producto);
		}
		
    }

}
