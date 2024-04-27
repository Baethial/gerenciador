package com.fis.rotonda.modelo;

public class Usuario {

	private long id;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	
	private double fondos;
	
	private Carrito carrito;

	public Usuario(long id, String nombre, String direccion, String telefono, 
				   String email, double fondos) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.fondos = fondos;
		this.carrito = new Carrito(this);
	}
	
	public Usuario(String nombre, String direccion, String telefono, String email, double fondos) {
		
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.fondos = fondos;
		this.carrito = new Carrito(this);
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getFondos() {
		return fondos;
	}

	public void setFondos(double fondos) {
		this.fondos = fondos;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	

}
