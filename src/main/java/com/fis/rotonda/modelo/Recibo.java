package com.fis.rotonda.modelo;

import java.time.LocalDateTime;

public class Recibo {
	
	private long id;
	private long idUsuario;
	private String nombreUsuario;
	private double valorCompra;
	private LocalDateTime fecha;
	
	
	public Recibo(long id, long idUsuario, String nombreUsuario, double valorCompra) {
		this.id = id;
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.valorCompra = valorCompra;
		this.fecha = LocalDateTime.now();
	}
	
	public Recibo(long idUsuario, String nombreUsuario, double valorCompra) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.valorCompra = valorCompra;
		this.fecha = LocalDateTime.now();
	}

	//Getters y Setters
	public long getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public double getValorCompra() {
		return valorCompra;
	}


	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}


	public LocalDateTime getFecha() {
		return fecha;
	}


	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
}
