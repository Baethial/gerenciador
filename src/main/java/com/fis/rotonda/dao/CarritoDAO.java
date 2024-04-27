package com.fis.rotonda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fis.rotonda.modelo.Carrito;
import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Producto;
import com.fis.rotonda.modelo.Restaurante;
import com.fis.rotonda.modelo.Usuario;

public class CarritoDAO {
	
	private Connection con;

	public CarritoDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Carrito carrito) {
       
    }
	
	public Carrito obtenerRestaurante(long id) {
        return null;
    }
	
	public List<Usuario> listar() {
        return null;
    }

    public int eliminar(long id) {
        return -1;
    }

    public boolean modificar() {
    	return false;
    }
    
}
