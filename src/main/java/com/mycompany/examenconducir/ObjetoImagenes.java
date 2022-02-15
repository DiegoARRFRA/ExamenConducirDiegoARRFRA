/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.DificultadExamen.url;
import static com.mycompany.examenconducir.ObjetoPreguntas.idPregunta;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ObjetoImagenes {
    public static ArrayList<String> rutaImagenes= new ArrayList<String>();
        
    
    
    public static void rellenarArrayImagenes () {
       rutaImagenes.clear();
    
        for (int k = 0; k < idPregunta.size(); k++) {
             String query = "Select imagenes from preguntas where id_pregunta = " + idPregunta.get(k);
         try(var insImg = DriverManager.getConnection(url); 
            var modImg = insImg.prepareStatement(query);){ 
                try(var rs =  modImg.executeQuery()){
                    while (rs.next()) {
                       rutaImagenes.add(rs.getString("imagenes")); 
                    } 
                }catch (SQLException ex) {
                    System.err.println("Error de ejecucion de consulta");
                    System.err.println(ex.toString());  
                } 
        }catch(SQLException ex){
                System.err.println("Error al establecer la consulta");
                System.err.println(ex.toString());
        }
        }
       
      
    }
    
}
