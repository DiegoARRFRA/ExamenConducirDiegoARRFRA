
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.CreadorExamen.url;
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
    
    // Este objeto respuestas es el objeto que tiene todo lo relacionado con las respuestas de las preguntas que hemos obtenido anteriormente con el objeto Preguntas.
    // Está conformado en su esencia por seis arrayList, uno contiene las respuestas correctas y los otros 5 respuestas incorrectas, todas relacionadas con los ID_Preguntas.
    // Este objeto tiene muchas misiones y funcionalidades, para empezar podemos definir entre sus funciones la de obtener las respuestas de la tabla de respuestas de la base
    // Obtener de manera random las dos respuestas incorrectas y pasar los datos.
    public static ArrayList<String> respuestaCorrecta = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaUno = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaDos = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaTres = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaCuatro = new ArrayList<String>();
    public static ArrayList<String> respuestaIncorrectaCinco = new ArrayList<String>();
    public static ArrayList<Integer> respuestasRandomFalsas = new ArrayList<Integer>();
    
    
    // Este es el método que llamamos desde el EsquemaExamen, es el que va a activar todo el funcionamiento de nuestras clases de objeto respuestas.
    public static void obtenerRespuestas() {
        obtenerRespuestaCorrecta();
        obtenerRespuestasIncorrectas();
    }
    
    // Los getter, en este caso serán solamente tres a pesar de tener 6 arraylist. Esto ocurre porque siempre mandaremos solo 3 datos, una respuesta correcta y 2 incorrectas.
    public static String getRespuestaCorrecta (int id) {
        return respuestaCorrecta.get(id);
    }
    
    public static String getRespuestaIncorrectaUno (int id) {
        return obtenerRespuestaFalsaUno(id);
    }
    
    public static String getRespuestaIncorrectaDos (int id) {
        return obtenerRespuestaFalsaDos(id);
    }
    
    // Método de control de vaciado, para evitar residuos de operaciones anteriores y trabajar con arraylists "nuevos".
    // Lo llamaremos siempre antes de extraer los datos de la base de datos y rellenar el contenido.
    
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
       // Establecemos todas las respuestas que vamos a obtener apartir de los arraylist IDpreguntas que ya obtuvimos antes, así guardamos la relación.
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
    
   // Este método sirve para rellenar en nuestros arraylists con todas las respuestas incorrectas relacionadas con cada pregunta, sacamos 5 para ir alternando entre posibles erroneas.
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
    
    
    // Este es el método que nos va a elegir de manera random 2 numeros diferentes del 1 al 5, en función de los números seleccionados elegiremos un arraylist y su contenido 
    // erroneo para mostrarlo, de esta forma nos aseguramos de que pueda salir literalmente cualquier respuesta incorrecta de las 5 sin importar el orden, es un proceso similar
    // Al que hicimos en nuestra EsquemaExamen para delimitar que botones tendran las respuestas y cual  tendran la correcta y cual no de las opciones.
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
    
    // En función del numero obtenido en la posicion 0 del 1 al 5 seleccionaremos la respuesta falsa random de ese arraylist.
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

      return "Control Erronea 1";  

    }
    // En función del numero obtenido en la posicion 0 del 1 al 5 seleccionaremos la respuesta falsa random de ese arraylist.
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
      return "Control Erronea 2";  

    }
    
    
    
    
    
}
