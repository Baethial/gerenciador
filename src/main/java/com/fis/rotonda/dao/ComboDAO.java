package com.fis.rotonda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fis.rotonda.modelo.Categoria;
import com.fis.rotonda.modelo.Combo;
import com.fis.rotonda.modelo.Producto;

public class ComboDAO {
	
	private Connection con;

	public ComboDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Combo combo) {
		
		Map<Producto, Long> productos = combo.getProductos();
		
        try {
            PreparedStatement statement1;
            statement1 = con.prepareStatement(
                        "INSERT INTO combos "
                        + "(combo_nombre, combo_precio,"
                        + "combo_descripcion, combo_uri_foto)"
                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
    
            PreparedStatement statement2;
            statement2 = con.prepareStatement(
                        "INSERT INTO combos_productos "
                        + "(combo_id, producto_id, producto_cantidad)"
                        + " VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                
            try (statement1) {
                statement1.setString(1, combo.getNombre());
                statement1.setDouble(2, combo.getPrecio());
                statement1.setString(3, combo.getDescripcion());
                statement1.setString(4, combo.getUriFoto());
                
                statement1.execute();
                
                try (statement2) {
                	
                	// Obtencion de el ID del combo insertado
                	final ResultSet resultSet = statement1.getGeneratedKeys();
                	resultSet.next(); // bug fix
                    long comboID = resultSet.getLong(1);
                	                    
                    // Insercion de los productos en la tabla de rompimiento
                    for (Producto producto : productos.keySet()) {
        				statement2.setLong(1, comboID);
        				statement2.setLong(2, producto.getId());
        				statement2.setLong(3, productos.get(producto));
        				
        				statement2.execute();
        			}
                    
                    //con.commit();
                }
				 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Combo> listar() {
	    List<Combo> resultado = new ArrayList<>();

	    try {
	        final PreparedStatement statement1 = con.prepareStatement("SELECT * FROM combos");

	        try (statement1) {
	            statement1.execute();

	            ResultSet resultSet1 = statement1.getResultSet();

	            try (resultSet1) {
	                while (resultSet1.next()) {
	                    Combo newCombo = new Combo(resultSet1.getLong("combo_id"),
	                            resultSet1.getString("combo_nombre"),
	                            resultSet1.getDouble("combo_precio"),
	                            resultSet1.getString("combo_descripcion"),
	                            resultSet1.getString("combo_uri_foto")
	                    );

	                    Map<Producto, Long> listaProductos = new HashMap<>();

	                    PreparedStatement statement2 = con.prepareStatement("SELECT producto_id, producto_cantidad FROM combos_productos "
	                            + "WHERE combo_id = ?");
	                    
	                    statement2.setLong(1, newCombo.getId());

	                    try (statement2) {
	                        statement2.execute();

	                        ResultSet resultSet2 = statement2.getResultSet();
	                        ProductoDAO productoDao = new ProductoDAO(con);

	                        try (resultSet2) {
	                            while (resultSet2.next()) {
	                                listaProductos.put(productoDao.obtenerProducto(resultSet2.getLong("producto_id")),
	                                        resultSet2.getLong("producto_cantidad"));
	                            }
	                        }
	                    }

	                    newCombo.setProductos(new HashMap<>(listaProductos));
	                    resultado.add(newCombo);
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
        	
        	final PreparedStatement statement1 = con.prepareStatement("DELETE FROM combos_productos WHERE combo_id = ?");
        	final PreparedStatement statement2 = con.prepareStatement("DELETE FROM combos WHERE combo_id = ?");
        	

            try (statement1; statement2) {
                statement1.setInt(1, id);
                statement1.execute();
                
                statement2.setInt(1, id);
                statement2.execute();

                //con.commit();
                
                int updateCount = statement2.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modificar(String nombre, Double precio, String descripcion, String URI, long id) {
    	
    	if (id <= 0 || obtenerCombo(id) == null) {
    		return false;
    	}
    	
        try {
            final PreparedStatement statement = con.prepareStatement(
                    "UPDATE combos SET "
                    + "combo_nombre = ?, "
                    + "combo_precio = ?, "
                    + "combo_descripcion = ?, "
                    + "combo_uri_foto = ? "
                    + "WHERE combo_id = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setDouble(2, precio);
                statement.setString(3, descripcion);
                statement.setString(4, URI);
                statement.setLong(5, id);
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
    	
    	ProductoDAO productoDAO = new ProductoDAO(con);
    	
    	if (id <=0 || obtenerCombo(id) == null) {
    		return false;
    	}
    	
    	try {
    		Map<Producto, Long> listaProductos = obtenerCombo(id).getProductos();
        	
        	listaProductos.forEach((key, value)-> {
        		productoDAO.modificarCantidad(key.getId(), cantidad*value);
        	});
        	
        	//con.commit();
        	
        	return true;
    	} catch (Exception e) {
			throw new RuntimeException();
		}
    	
    	
    }

    public Combo obtenerCombo(long id) {
    	Combo resultado = null;

	    try {
	        final PreparedStatement statement1 = con.prepareStatement("SELECT * FROM combos WHERE combo_id = ?");
	        
	        statement1.setLong(1, id);

	        try (statement1) {
	            statement1.execute();

	            ResultSet resultSet1 = statement1.getResultSet();

	            try (resultSet1) {
	            	
	                resultSet1.next();
	                
	                resultado = new Combo(resultSet1.getLong("combo_id"),
                            resultSet1.getString("combo_nombre"),
                            resultSet1.getDouble("combo_precio"),
                            resultSet1.getString("combo_descripcion"),
                            resultSet1.getString("combo_uri_foto")
                    );

                    Map<Producto, Long> listaProductos = new HashMap<>();

                    PreparedStatement statement2 = con.prepareStatement("SELECT producto_id, producto_cantidad FROM combos_productos "
                            + "WHERE combo_id = ?");
                    
                    statement2.setLong(1, resultado.getId());

                    try (statement2) {
                        statement2.execute();

                        ResultSet resultSet2 = statement2.getResultSet();
                        ProductoDAO productoDao = new ProductoDAO(con);

                        try (resultSet2) {
                        	
                        	while(resultSet2.next()) {
                        		listaProductos.put(productoDao.obtenerProducto(
                                		resultSet2.getLong("producto_id")),
                                        resultSet2.getLong("producto_cantidad")
                                        );
                        	}
                            
                        }
                    }

                    resultado.setProductos(new HashMap<>(listaProductos));
	              
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }

	    return resultado;
    }
	
}
