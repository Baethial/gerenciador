package com.fis.rotonda.modelo;

import java.util.List;

public class Producto {
	
	//Atributos
	private long id;
	private String nombre;
	private long cantidad;
	private Categoria categoria;
	private double precio;
	private String descripcion;
	private String uriFoto;
	
	private static List<Producto> productosDisponibles;
	
	//Constructores
	public Producto(String nombre, long cantidad, Categoria categoria, double precio, String descripcion, String uriFoto) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
	}
	
	public Producto(long id, String nombre, long cantidad, Categoria categoria, double precio, String descripcion, String uriFoto) {
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.categoria = categoria;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
	}
	
	//Getters y Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUriFoto() {
		return uriFoto;
	}
	public void setUriFoto(String uriFoto) {
		this.uriFoto = uriFoto;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public static List<Producto> getProductosDisponibles() {
		return productosDisponibles;
	}
	public static void setProductosDisponibles(List<Producto> productosDisponibles) {
		Producto.productosDisponibles = productosDisponibles;
	}

	@Override
	public String toString() {
		return "Producto " + id + ":\n\t nombre=" + nombre + ",\n\t cantidad=" + cantidad + ",\n\t categoria=" + categoria
				+ ",\n\t precio=" + precio + ",\n\t descripcion=" + descripcion + ",\n\t uriFoto=" + uriFoto;
	}
	
	

}
