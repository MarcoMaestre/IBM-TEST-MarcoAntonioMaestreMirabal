/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ibm.marco.controller;

import com.mycompany.ibm.marco.model.Proveedor;
import java.sql.SQLException;
import com.mycompany.ibm.marco.util.GestionBD;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author maest
 */
public class IBMTest {
        public static void main( String[] args ) throws SQLException{
            ArrayList<Proveedor> proveedorList = null; //Listado de proveedores 
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //Cambiar el formato de la fecha para imprimir en el fichero.
            GestionBD bbdd = new GestionBD();
            Connection connectionIBM = null;
            try{     
                connectionIBM =bbdd.connectDatabase("testIBM");
                proveedorList = FunctionsSQL.getProveedor(connectionIBM, args);
                if (proveedorList.isEmpty())
                     System.out.println("Lo siento, este cliente con id " + args[0] + " no tiene proveedores asignados.");
                
            }catch ( ArrayIndexOutOfBoundsException e )
            {            
                //si no existen parametros muestra error y termina programa
                JOptionPane.showMessageDialog(null,"Error: No se pasaron argumentos. Necesita un id_cliente.");
                System.exit(0);
            }
            //Si hay un listado de proveedores entonces si pinta el resultado en un texto plano
            if (!proveedorList.isEmpty()){
                try {
                    String ruta = "IBM-TEST-MarcoAntonioMaestreMirabal";
                    String contenido = "Proveedores:";
                    File file = new File(ruta);
                    // Si el archivo no existe es creado
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(contenido);
                    for (int i = 0; i < proveedorList.size(); i++){
                        bw.write("\n"+proveedorList.get(i).getIdProveedor());
                        bw.write(","+proveedorList.get(i).getNombre());
                        bw.write(","+formatter.format(proveedorList.get(i).getFechaAlta()));                    
                        bw.write(","+proveedorList.get(i).getIdCliente());
                    }
                    bw.close();
                    System.out.println("Se ha generado correctamente el fichero plano con los proveedores del cliente con id " + args[0] + ".");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            bbdd.closeConection(connectionIBM);
    }
}
