/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.DificultadExamen.numeroPreguntas;
import static com.mycompany.examenconducir.DificultadExamen.url;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ObjetoPreguntas {

    public static ArrayList<String> enunciadoPregunta = new ArrayList<String>();
    public static ArrayList<Integer> idPregunta = new ArrayList<Integer>();



    public static int getIdPregunta(int k) {
        return idPregunta.get(k);
    }

    
    public static String getEnunciado_pregunta(int k) { 
        return enunciadoPregunta.get(k);
    }

    // Este método sirve para obtener las preguntas de la base de datos, las obtenemos de forma random en función del numero de preguntas que haya querido seleccionar el Usuario.
    public static void obtenerPreguntas () {
        enunciadoPregunta.clear();
        idPregunta.clear();
        String query = "Select id_pregunta, enunciado_pregunta from preguntas order by random() limit " + numeroPreguntas; 
        try(var preguntas = DriverManager.getConnection(url);
            var obtenerPregunta = preguntas.prepareStatement (query)){
                try (var datosrs = obtenerPregunta.executeQuery()) {
                    while (datosrs.next()) {
                        idPregunta.add(datosrs.getInt(1)); // Rellenamos el IDpregunta
                        enunciadoPregunta.add(datosrs.getString(2));// Rellenamos el contenido de esa pregunta, su enunciado.
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
