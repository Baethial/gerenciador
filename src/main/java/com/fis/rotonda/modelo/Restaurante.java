package com.fis.rotonda.modelo;

import java.util.List;

public class Restaurante {
	
	private long id;
	private String nombre;
	private String descripcion;
	private String uriFoto;
	
	private List<Producto> listaProductos;
	private List<Combo> listaCombos;
	
	public Restaurante(long id, String nombre, String descripcion, String uriFoto, List<Producto> listaProductos,
			List<Combo> listaCombos) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
		this.listaProductos = listaProductos;
		this.listaCombos = listaCombos;
	}
	
	public Restaurante(String nombre, String descripcion, String uriFoto, List<Producto> listaProductos,
			List<Combo> listaCombos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.uriFoto = uriFoto;
		this.listaProductos = listaProductos;
		this.listaCombos = listaCombos;
	}
	
	public boolean agregarProducto(Producto producto) {
		//Implementar
		return false;
	}
	
	public boolean eliminarProducto(Producto producto) {
		//Implementar
		return false;
	}
	
	public boolean agregarCombo(Combo combo) {
		//Implementar
		return false;
	}
	
	public boolean eliminarCombo(Combo combo) {
		//Implementar
		return false;
	}
	
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

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public List<Combo> getListaCombos() {
		return listaCombos;
	}

	public void setListaCombos(List<Combo> listaCombos) {
		this.listaCombos = listaCombos;
	}
	
	

}
