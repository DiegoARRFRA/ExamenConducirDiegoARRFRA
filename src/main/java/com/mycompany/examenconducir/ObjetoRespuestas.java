
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.DificultadExamen.url;
import static com.mycompany.examenconducir.ObjetoPreguntas.idPregunta;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author diego
 */
public class ObjetoRespuestas {
    public static ArrayList<String> respuestaCorrecta = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaUno = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaDos = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaTres = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaCuatro = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaCinco = new ArrayList<String>();
    public static ArrayList<Integer> respuestasRandomFalsas = new ArrayList<Integer>();
    

    
    public static void obtenerRespuestas() {
        obtenerRespuestaCorrecta();
        obtenerRespuestasIncorrectas();
    }
    
    public static String getRespuestaCorrecta (int id) {
        return respuestaCorrecta.get(id);
    }
    
    public static String getRespuestaIncorrectaUno (int id) {
        return obtenerRespuestaFalsaUno(id);
    }
    
    public static String getRespuestaIncorrectaDos (int id) {
        return obtenerRespuestaFalsaDos(id);
    }
    
    public static void vaciarElementos () {
        respuestaCorrecta.clear();
        respuestaIncorrectaUno.clear();
        respuestaIncorrectaDos.clear();
        respuestaIncorrectaTres.clear();
        respuestaIncorrectaCuatro.clear();
        respuestaIncorrectaCinco.clear();
        respuestasRandomFalsas.clear();


    }
    
    // Este método sirve para rellenar en nuestro arraylist todas las respuestas correctas extraidas de las preguntas que hemos sacado de la base de datos
    public static void obtenerRespuestaCorrecta () {       
        vaciarElementos();
       for (int k = 0; k < idPregunta.size(); k++) {
           String query = "Select enunciado_respuesta from respuestas where id_pregunta = " + idPregunta.get(k);
           try(var ObtenerRespuestaCorrecta = DriverManager.getConnection(url);
            var obtenerRespuesta = ObtenerRespuestaCorrecta.prepareStatement (query)){    
                try (var datosrs = obtenerRespuesta.executeQuery()) {
                    while (datosrs.next()) {
                        respuestaCorrecta.add(datosrs.getString(1));      
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
    
   // Este método sirve para rellenar en nuestros arraylists con todas las respuestas incorrectas relacionadas con cada pregunta, sacamos las 5 porque daremos la opción de modificar el test.
    public static void obtenerRespuestasIncorrectas () {
        for (int k = 0; k < idPregunta.size(); k++) {
            String query = "Select enunciado_respuesta_incorrecta_uno, enunciado_respuesta_incorrecta_dos, enunciado_respuesta_incorrecta_tres, enunciado_respuesta_incorrecta_cuatro, enunciado_respuesta_incorrecta_cinco"
                + " FROM respuestas where id_pregunta = " + idPregunta.get(k);
           try(var ObtenerRespuestasIncorrectas = DriverManager.getConnection(url);
                var obtenerRespuestas = ObtenerRespuestasIncorrectas.prepareStatement (query)){  
                    try (var datosrs = obtenerRespuestas.executeQuery()) {
                        while (datosrs.next()) {
                            respuestaIncorrectaUno.add(datosrs.getString(1));
                            respuestaIncorrectaDos.add(datosrs.getString(2));
                            respuestaIncorrectaTres.add(datosrs.getString(3));
                            respuestaIncorrectaCuatro.add(datosrs.getString(4));
                            respuestaIncorrectaCinco.add(datosrs.getString(5));
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
    
    
    public static void elegirRandomIdRespuestaIncorrecta () {
        respuestasRandomFalsas.clear();
        Random random = new Random();
       while (respuestasRandomFalsas.size() < 2) {
            //El rango de numeros que queremos en el array
            int randomNumber = random.nextInt((5 - 1) + 1) + 1;
            //Para evitar duplicados
            if (!respuestasRandomFalsas.contains(randomNumber)) {
                respuestasRandomFalsas.add(randomNumber);
            }
        } 
    }
    
    
    public static String obtenerRespuestaFalsaUno (int id) {
        
         if (null != respuestasRandomFalsas.get(0)) switch (respuestasRandomFalsas.get(0)) {
            case 1 -> {
                return respuestaIncorrectaUno.get(id);
             }
            case 2 -> {
                return respuestaIncorrectaDos.get(id);
             }
            case 3 -> {
                return respuestaIncorrectaTres.get(id);
             }
            case 4 -> {
                return respuestaIncorrectaCuatro.get(id);
             }
            case 5 -> {
                return respuestaIncorrectaCinco.get(id);
             }
            default -> {
             }
        }

      return "a";  

    }
    
    public static String obtenerRespuestaFalsaDos (int id) {
         if (null != respuestasRandomFalsas.get(1)) switch (respuestasRandomFalsas.get(1)) {
            case 1 -> {
                return respuestaIncorrectaUno.get(id);
             }
            case 2 -> {
                return respuestaIncorrectaDos.get(id);
             }
            case 3 -> {
                return respuestaIncorrectaTres.get(id);
             }
            case 4 -> {
                return respuestaIncorrectaCuatro.get(id);
             }
            case 5 -> {
                return respuestaIncorrectaCinco.get(id);
             }
            default -> {
             }
        }

        
      return "a";  

    }
    
    
    
    
    
}
