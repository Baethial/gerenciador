package com.fis.rotonda.modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.fis.rotonda.dao.ComboDAO;
import com.fis.rotonda.dao.ProductoDAO;
import com.fis.rotonda.factory.ConnectionFactory;

public class Carrito {

	private Map<Producto, Long> listaProductos;
	private Map<Combo, Long> listaCombos;
	
	private ConnectionFactory factory;
	private Connection con;
	private ProductoDAO productoDAO;
	private ComboDAO comboDAO;
	
	private Usuario usuario;
	
	public Carrito(Usuario usuario) {
		this.listaProductos = new HashMap<>();
		this.listaCombos = new HashMap<>();
		
		this.factory = new ConnectionFactory();
		this.con = factory.recuperaConexion();
		this.productoDAO = new ProductoDAO(con);
		this.comboDAO = new ComboDAO(con);
		
		this.usuario = usuario;
	}
	
	public boolean agregarProducto(Producto producto, Long cantidad) {
		
		long existencias = producto.getCantidad();
		
		if ((cantidad <= 0) || (cantidad > existencias) || (producto.equals(null))) {
			return false;
		}
		
		try {
			con.setAutoCommit(false);
			
			productoDAO.modificarCantidad(producto.getId(), -cantidad);
			listaProductos.put(producto, cantidad);
			
			con.commit();
			return true;
		} catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public boolean agregarProductoSinCommit(Producto producto, Long cantidad) {
	
	long existencias = producto.getCantidad();
	
	if ((cantidad <= 0) || (cantidad > existencias) || (producto.equals(null))) {
		return false;
	}
	
	try {
		productoDAO.modificarCantidad(producto.getId(), -cantidad);
		listaProductos.put(producto, cantidad);
		
		return true;
	} catch(Exception e) {
		throw new RuntimeException();
	}
}
	
	public boolean agregarCombo(Combo combo, Long cantidad) {
		
		if ((cantidad <= 0) || (combo.equals(null))) {
			return false;
		}
		
		try {
			con.setAutoCommit(false);
			
			Map<Producto, Long> listaProductos = combo.getProductos();
			
			listaProductos.forEach((key, value) -> {
				agregarProductoSinCommit(key, value*cantidad);
			});
			listaCombos.put(combo, cantidad);
			
			con.commit();
			return true;
		} catch (Exception e) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			throw new RuntimeException();
		}finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public boolean eliminarProducto(Producto producto) {
		
		if (producto.equals(null)) {
			return false;
		}
		
		System.out.println(listaProductos.containsKey(producto));
		
		long cantidadEnCarrito = listaProductos.get(producto);
		
		try {
			con.setAutoCommit(false);
			
			productoDAO.modificarCantidad(producto.getId(), cantidadEnCarrito);
			listaProductos.remove(producto);
			
			con.commit();
			return true;
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public boolean eliminarCombo(Combo combo) {
		
		if (combo.equals(null)) {
			return false;
		}
		
		System.out.println(listaCombos.containsKey(combo));
		
		long cantidadEnCarrito = listaCombos.get(combo);
		
		try {
			con.setAutoCommit(false);
			
			comboDAO.modificarCantidad(combo.getId(), cantidadEnCarrito);
			listaCombos.remove(combo);
			
			con.commit();
			return true;
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Recibo realizarPago() {
		
		double fondos = usuario.getFondos();
		double valorCompra = obtenerValorCompra();
		
		if (fondos < valorCompra) {
			return null;
		}
		
		Recibo recibo;
		
		try {
			usuario.setFondos(fondos - valorCompra);
			recibo = new Recibo(usuario.getId(), usuario.getNombre(), valorCompra);
			//Crear reciboDAO
			//reciboDAO.guardar()
			getListaProductos().clear();
			getListaCombos().clear();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return recibo;
		
	}
	
	public double obtenerValorCompra() {
		
		double valorPago = 0;
		
		for (Map.Entry<Producto, Long> entry : listaProductos.entrySet()) {
		    Double valorProducto = entry.getKey().getPrecio();
		    Long cantidadProducto = entry.getValue();
		    
		    valorPago += valorProducto * cantidadProducto;
		}
		
		for (Map.Entry<Combo, Long> entry : listaCombos.entrySet()) {
		    Double valorCombo = entry.getKey().getPrecio();
		    Long cantidadCombo = entry.getValue();
		    
		    valorPago += valorCombo * cantidadCombo;
		}
		
		//Adicion del IVA reducido
		valorPago = valorPago + (valorPago*0.1);
		
		return valorPago;
	}
	
	//Getters y Setters
	
	public Map<Producto, Long> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(Map<Producto, Long> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Map<Combo, Long> getListaCombos() {
		return listaCombos;
	}

	public void setListaCombos(Map<Combo, Long> listaCombos) {
		this.listaCombos = listaCombos;
	}
	
}
