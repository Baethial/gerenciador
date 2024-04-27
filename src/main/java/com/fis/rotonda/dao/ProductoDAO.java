package com.fis.rotonda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Producto;

public class ProductoDAO {
	
	private Connection con;

	public ProductoDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Producto producto) {
        try {
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO productos "
                        + "(producto_nombre, producto_cantidad, producto_categoria, producto_precio,"
                        + "producto_descripcion, producto_uri_foto)"
                        + " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
                statement.setString(1, producto.getNombre());
                statement.setLong(2, producto.getCantidad());
                statement.setString(3, producto.getCategoria().toString().toLowerCase());
                statement.setDouble(4, producto.getPrecio());
                statement.setString(5, producto.getDescripcion());
                statement.setString(6, producto.getUriFoto());
    
                statement.execute();
                //con.commit();
    
                final ResultSet resultSet = statement.getGeneratedKeys();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Producto obtenerProducto(long id) {
        Producto resultado = null;

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT * FROM productos WHERE producto_id = ?");
    
            try (statement) {
            	statement.setLong(1, id);
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    
                	resultSet.next();
                	resultado = new Producto(
                            resultSet.getLong("producto_id"),
                            resultSet.getString("producto_nombre"),
                            resultSet.getLong("producto_cantidad"),
                            Categoria.valueOf(resultSet.getString("producto_categoria").toUpperCase()),
                            resultSet.getDouble("producto_precio"),
                            resultSet.getString("producto_descripcion"),
                            resultSet.getString("producto_uri_foto")
                            );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultado;
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

    public int eliminar(long id) {
        try {
            final PreparedStatement statement = con.prepareStatement("DELETE FROM productos WHERE producto_id = ?");

            try (statement) {
                statement.setLong(1, id);
                statement.execute();
                
                //con.commit();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modificar(String nombre, long cantidad, Double precio, String descripcion, String URI, long id) {
    	
    	if (id <=0 || obtenerProducto(id) == null) {
    		return false;
    	}
    	
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

                //con.commit();
                
                int updateCount = statement.getUpdateCount();

                return true;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public boolean modificarCantidad(long id, long cantidad) {
    	
    	long existencias = obtenerProducto(id).getCantidad();
    	long nuevaCantidad = existencias + cantidad;
    	
    	if (nuevaCantidad < 0) {
    		return false;
    	}
    	
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE productos SET "
                    + "producto_cantidad = ? "
                    + "WHERE producto_id = ?");

            try (statement) {
                statement.setLong(1, nuevaCantidad);
                statement.setLong(2, id);
                statement.execute();

                //con.commit();
                
                return true;
            }
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
