/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.CreadorExamen.numeroPreguntas;
import static com.mycompany.examenconducir.CreadorExamen.url;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author diego
 */
public class ObjetoPreguntas {

    // El objeto Preguntas es el objeto que trata todo lo relacionado con la tabla preguntas de nuestras bases de datos
    // Está conformado principlamente por dos arraylist que representan el ID y el Contenido de las preguntas
    public static ArrayList<String> enunciadoPregunta = new ArrayList<String>();
    public static ArrayList<Integer> idPregunta = new ArrayList<Integer>();


    // Se conforma de los getter de los arrayList y su "Setter" que está fundamentado en una consulta de nuestra base de datos y obtención de manera random de las preguntas
    // para el examen actual.
    public static int getIdPregunta(int k) {
        return idPregunta.get(k);
    }

    
    public static String getEnunciado_pregunta(int k) { 
        return enunciadoPregunta.get(k);
    }

    // Este método sirve para obtener las preguntas de la base de datos, las obtenemos de forma random en función del numero de preguntas que haya querido seleccionar el Usuario.
    // Lo primero que hacemos es un borrado de memoria de los arrayList, sirve para deshechar residuos que pudieran quedar de ejecuciones pasadas de estos métodos. Al borrarlo
    // Nos aseguramos que todo el contenido de nuestras sentencias va a entrar en arraysLists nuevos.
   // Hacemos la Select en la que establecemos como limit el parámetro numeroPreguntas, que es aquel que definimos en la clase CreadorExamen y nos marcaba numero de preguntas que seleccionamos
    // Con el raddio button.
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
