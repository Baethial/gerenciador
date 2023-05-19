package com.fis.rotonda.modelo;

import java.util.List;

public class Combo {

	//Atributos
	private long id;
	private String nombre;
	private double precio;
	private String descripcion;
	private String uriFoto;
	
	private List<Producto> productos;

	//Constructores
	public Combo(long id, String nombre, double precio, String descripcion, String uriFoto, List<Producto> productos) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
		this.productos = productos;
	}
	
	public Combo(String nombre, double precio, String descripcion, String uriFoto, List<Producto> productos) {
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
		this.productos = productos;
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
	
	
	
	
}
