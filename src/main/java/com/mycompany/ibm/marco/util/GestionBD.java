/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ibm.marco.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Marco Maestre
 */
public class GestionBD {

    // database URL
    private static final String DB_URL = "jdbc:mysql://";
    
    //  Database credentials
    private static final String HOST= AppConfig.CURRENTSERVER;
    private String DB;
    private static final String PORT = AppConfig.PORT;
    private static final String USER = AppConfig.USER;
    private static final String PASS = AppConfig.PASS;
    public Connection con;
    
    public GestionBD() {

    }

    public Connection connectDatabase(String database) throws SQLException {
        String url = null;
        Connection connection=null;
        DB = database;
       
            // We register the MySQL driver
            // Registramos el driver de MySQL
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                System.err.println("Error al registrar el driver de MySQL: " + ex);
            }
            
            url = DB_URL + HOST + ":" + PORT + "/" + DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            // Database connect
            // Conectamos con la base de datos
            try{
                connection = DriverManager.getConnection(
                        url,
                        USER, PASS);
            }catch (SQLException e) {
                System.out.println(e.toString());
            }
            boolean valid = connection.isValid(50000);
            //System.out.println(valid ? "Connection OK" : "Connection FAIL");
      
		return connection;
    }
    
    public void closeConection(Connection connection) {
        
        try {
            if (!connection.isClosed())
                connection.close();
	} catch (SQLException e) {
            e.printStackTrace();
	}
    }
}
