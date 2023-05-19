package com.fis.rotonda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Combo;
import com.fis.rotonda.modelo.Producto;

public class ComboDAO {
	
	private Connection con;

	public ComboDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Combo combo) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO combos "
                        + "(combo_nombre, combo_precio,"
                        + "combo_descripcion, combo_uri_foto)"
                        + " VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, combo.getNombre());
                statement.setDouble(2, combo.getPrecio());
                statement.setString(3, combo.getDescripcion());
                statement.setString(4, combo.getUriFoto());
    
                // Hay que insertar los productos en la tabla de rompimiento
                
                
                
                
                
                statement.execute();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        combo.setId(resultSet.getInt(1));
                        
                        System.out.println(String.format("Fue insertado el producto: %s", combo));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM productos");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getLong("producto_id"),
                                resultSet.getString("producto_nombre"),
                                resultSet.getLong("producto_cantidad"),
                                Categoria.valueOf(resultSet.getString("producto_categoria").toUpperCase()),
                                resultSet.getDouble("producto_precio"),
                                resultSet.getString("producto_descripcion"),
                                resultSet.getString("producto_uri_foto")
                                )); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    public int eliminar(Integer id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM productos WHERE producto_id = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, long cantidad, Double precio, String descripcion, String URI, long id) {
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE productos SET "
                    + "producto_nombre = ?, "
                    + "producto_cantidad = ?, "
                    + "producto_precio = ?, "
                    + "producto_descripcion = ?, "
                    + "producto_uri_foto = ? "
                    + "WHERE producto_id = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setLong(2, cantidad);
                statement.setDouble(3, precio);
                statement.setString(4, descripcion);
                statement.setString(5, URI);
                statement.setLong(6, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Producto> listar(Categoria categoria) {
    	List<Producto> resultado = new ArrayList<>();

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM productos "
                    		+ "WHERE producto_categoria = ?");
    
            try (statement) {
            	statement.setString(1, categoria.toString());
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getLong("producto_id"),
                                resultSet.getString("producto_nombre"),
                                resultSet.getLong("producto_cantidad"),
                                Categoria.valueOf(resultSet.getString("producto_categoria").toUpperCase()),
                                resultSet.getDouble("producto_precio"),
                                resultSet.getString("producto_descripcion"),
                                resultSet.getString("producto_uri_foto")
                                )); 
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }
	

}
