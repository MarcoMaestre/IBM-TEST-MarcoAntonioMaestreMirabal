/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ibm.marco.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maest
 */
public class GestionBD {
      // JDBC driver name
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    // database URL
    private static final String DB_URL = "jdbc:mysql://";
    
    //  Database credentials
    //static final String USER = "houtelecom_db_user";
    private static final String HOST= AppConfig.CURRENTSERVER;
    private String DB;
    private static final String PORT = AppConfig.PORT;
    private static final String USER = AppConfig.USER;
    private static final String PASS = AppConfig.PASS;
    public Connection con;
    private static GestionBD gestionBDTestIBM;
    //private static GestionBD gestionBDHoutelecom;
    
    public GestionBD() {
//        DB=db;
//        
//        try {
//            Class.forName(JDBC_DRIVER).newInstance();
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//            System.out.println("Error al cargar el Driver de Mysql");
//        }
//        
//        try {
//            String url = DB_URL + HOST + ":" + PORT + "/" + DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//            con = DriverManager.getConnection( url, USER, PASS );
//            
//        } catch (SQLException ex) {
//            // handle any errors
//            System.out.println("SQLException: " + ex.getMessage());
//            System.out.println("SQLState: " + ex.getSQLState());
//            System.out.println("VendorError: " + ex.getErrorCode());
//        }
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
