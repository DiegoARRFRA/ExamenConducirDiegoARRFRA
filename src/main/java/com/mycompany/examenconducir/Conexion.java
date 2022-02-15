
package com.mycompany.examenconducir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


/**
 *
 * @author diego
 */
public class Conexion {
    // Las variables que representan un puntero a la base de datos.
    public static String urlCoche;
    public static String urlMoto;
    public static String urlCamion;
    
    public static void leerProperties() {
     
        // Mediante un tryCatch extraemos el contenido del properties y lo asignamos a unas variables.
        // Creamos un objeto prop sobre el que extraer todo.
        Properties prop = new Properties();
            try { 
                prop.load(Files.newInputStream(Path.of("FicheroProperties.properties"))); 
                urlCoche = prop.getProperty("urlCoche"); // Mediante el getProperty extraemos el contenido asociado a la key y lo asignamos a esta variable.
                urlMoto= prop.getProperty("urlMoto");  
                urlCamion = prop.getProperty("urlCamion");  
            }catch (NullPointerException ex) { 
                System.err.println (" Se esta intentando acceder a un miembro de un objeto para el que todavía no hemos reservado memoria.");
            }catch (FileNotFoundException ex) {
                System.err.println (" El fichero no se encuentra.");
            }catch (IOException ex) {
                System.err.println (" Existe un error que no permite esta operación, revise el codigo");
            }                 
   } 
        // Devuelve la variable coche, cuando es llamado este método es porque el usuario ha seleccionado el coche en la primera interfaz
      
        public static String ConexionBaseCoche () {
            return urlCoche;
       }             
    
        
        // Devuelve la variable moto, cuando es llamado este método es porque el usuario ha seleccionado el coche en la primera interfaz

       public static String ConexionBaseMoto () {
            return urlMoto;
        
       }     
    
        
        // Devuelve la variable camion, cuando es llamado este método es porque el usuario ha seleccionado el coche en la primera interfaz
        
       public static String ConexionBaseCamion () {
            return urlCamion;
        
       }     

    }
    
    
    
    
    

