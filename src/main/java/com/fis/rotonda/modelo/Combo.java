package com.fis.rotonda.modelo;

import java.util.Map;
import java.util.Objects;

public class Combo {

	//Atributos
	private long id;
	private String nombre;
	private double precio;
	private String descripcion;
	private String uriFoto;
	
	private Map<Producto, Long> productos;

	//Constructores
	public Combo(long id, String nombre, double precio, String descripcion, String uriFoto, Map<Producto, Long> productos) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
		this.productos = productos;
	}
	
	public Combo(long id, String nombre, double precio, String descripcion, String uriFoto) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
	}
	
	public Combo(String nombre, double precio, String descripcion, String uriFoto, Map<Producto, Long> productos) {
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

	public Map<Producto, Long> getProductos() {
		return productos;
	}

	public void setProductos(Map<Producto, Long> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Combo " + id + ":\n\t nombre=" + nombre + "\n\t precio=" + precio + "\n\t descripcion=" + descripcion
				+ "\n\t uriFoto=" + uriFoto + "\n\t productos=" + productos.entrySet() + "\n\t";
//		return "Producto " + id + ":\n\t nombre=" + nombre + ",\n\t cantidad=" + cantidad + ",\n\t categoria=" + categoria
//				+ ",\n\t precio=" + precio + ",\n\t descripcion=" + descripcion + ",\n\t uriFoto=" + uriFoto;
	}
	
	@Override
    public boolean equals(Object obj) {
		
		if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Combo objCombo = (Combo) obj;
        return this.id == objCombo.id;
        
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
	
	
	
	
}
