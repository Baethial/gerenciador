package com.fis.rotonda.Launcher;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fis.rotonda.dao.ComboDAO;
import com.fis.rotonda.dao.ProductoDAO;
import com.fis.rotonda.factory.ConnectionFactory;
import com.fis.rotonda.modelo.Carrito;
import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Combo;
import com.fis.rotonda.modelo.Producto;
import com.fis.rotonda.modelo.Usuario;

public class RotondaApp {

	public static void main(String[] args) throws SQLException {
		
		var factory = new ConnectionFactory();
		ProductoDAO productoDao = new ProductoDAO(factory.recuperaConexion());
		ComboDAO comboDao = new ComboDAO(factory.recuperaConexion());
		Usuario usuario = new Usuario("Andres", "dire", "tel", "123@false.com", 150000);
		
		// Prueba Insercion
//		Producto producto = new Producto("California Rol", 16, Categoria.PLATO_FUERTE, 25000, "Maki con pepino, palmmito ...", "/ubicacion");
//        productoDao.guardar(producto);
		
		// Prueba Eliminacion
//		productoDao.eliminar(7);
		
		// Prueba Consulta
//		List<Producto> listaProductos = productoDao.listar();
//		for (Producto producto : listaProductos) {
//			System.out.println(producto);
//		}
		
		// Prueba Actualizacion
//		productoDao.modificar("California Roll", 20, 6.12, "Maki", "Ubicacion", 6);
		
		//Prueba modificar cantidad
//		productoDao.modificarCantidad(3, 100);
//		productoDao.modificarCantidad(5, 100);
//		productoDao.modificarCantidad(9, 100);
		
		// Prueba Consulta por Categoria
//		List<Producto> listaProductos = productoDao.listar(Categoria.PLATO_FUERTE);
//		for (Producto producto : listaProductos) {
//			System.out.println(producto);
//		}
		
		// Prueba Consulta por ID
//		Producto producto = productoDao.obtenerProducto(2L);
//		System.out.println(producto);
		
		//Prueba Insercion Combo
//		Map<Producto, Long> listaProductos = new HashMap<>();
//		listaProductos.put(productoDao.obtenerProducto(1), 1L); //los pruductos ya deben existir en la DB (tener id)
//		listaProductos.put(productoDao.obtenerProducto(2), 2L);
//		listaProductos.put(productoDao.obtenerProducto(3), 1L);
//		Combo newCombo = new Combo("Combo Prueba", 12.4, "descripcion com...", "/ubicacion/combo", listaProductos);
//		comboDao.guardar(newCombo);
		
		//Prueba Listado de Combos
//		List<Combo> listaCombos = comboDao.listar();
//		System.out.println(listaCombos);
		
		//Prueba Eliminar Combo
//		comboDao.eliminar(10);
		
		//Prueba Modificar Combo
//		comboDao.modificar("Combo Super", 20.4, "Combo con todo", "/ubicacion/comboSuper.jpg", 4);
		
		//Prueba Obtener Combo
//		Combo combo = comboDao.obtenerCombo(3);
//		System.out.println(combo);
		
		//Prueba Modificar Cantidad Combo
		//comboDao.modificarCantidad(1, -2);
		
		//Prueba Agregar producto al carrito
//		carrito.agregarProducto(productoDao.obtenerProducto(2), 2L);
//		carrito.agregarProducto(productoDao.obtenerProducto(4), 1L);
//		Map<Producto, Long> listaProductos = carrito.getListaProductos();
//		System.out.println(listaProductos.keySet());
		
		//Prueba Eliminar producto de carrito
//		carrito.eliminarProducto(productoDao.obtenerProducto(4));
//		System.out.println("Producto eliminado");
//		System.out.println(carrito.getListaProductos());
		
		//Prueba Agregar combo al carrito
//		carrito.agregarCombo(comboDao.obtenerCombo(2), 2L);
//		carrito.agregarCombo(comboDao.obtenerCombo(5), 1L);
//		carrito.agregarCombo(comboDao.obtenerCombo(3), 3L);
//		System.out.println(carrito.getListaCombos());
		
		//Prueba eliminar combo del carrito
//		carrito.eliminarCombo(comboDao.obtenerCombo(3));
//		System.out.println(carrito.getListaCombos());

    }

}
