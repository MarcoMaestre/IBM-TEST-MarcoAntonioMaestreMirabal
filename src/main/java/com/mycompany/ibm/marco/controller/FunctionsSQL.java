/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ibm.marco.controller;

import com.mycompany.ibm.marco.model.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marco Maestre
 */
public class FunctionsSQL {
    
    public static ArrayList<Proveedor> getProveedor(Connection connection, String[] args) throws SQLException {
		
	ArrayList<Proveedor> elementos = new ArrayList<>();

	//Creamos el Statement para poder hacer consultas
        Statement st;
	st = connection.createStatement();
	// La consulta es un String con código SQL y se recuperan los proveedores según el id_cliente especificado al ejecutar el jar			
	String sqlProveedor = "SELECT * FROM proveedores WHERE id_cliente = "+ args[0] + ";";
	        // Ejecuta una consulta que devuelve resultados                
	    ResultSet rs;		
            rs = st.executeQuery(sqlProveedor);	
            while (rs.next()) {

		Proveedor proveedor= new Proveedor();
		proveedor.setIdProveedor(rs.getInt("id_proveedor"));
		proveedor.setNombre(rs.getString("nombre"));
		proveedor.setFechaAlta(rs.getDate("fecha_alta"));			    
		proveedor.setIdCliente(rs.getInt("id_cliente"));			    
		elementos.add(proveedor);	
			}
            rs.close(); // Cierra el resulset
	st.close(); // Cierra el statement
	
	return elementos;
	}
}
