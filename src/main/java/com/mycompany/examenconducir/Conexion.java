
package com.mycompany.examenconducir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Conexion {
    public static String urlCoche;
    public static String urlMoto;
    public static String urlCamion;
    
      public static void leerProperties() {
        
            Properties prop = new Properties();
            try { 
                prop.load(Files.newInputStream(Path.of("FicheroProperties.properties"))); 
                urlCoche = prop.getProperty("urlCoche");
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
        // Establecemos la conexión con la base de Coches
      
        public static String ConexionBaseCoche () {
            return urlCoche;
        
       }     
    
        
        // Establecemos la conexión con la base de Motos

       public static String ConexionBaseMoto () {
            return urlMoto;
        
       }     
    
        
        // Establecemos la conexión con la base de Camiones
        
       public static String ConexionBaseCamion () {
            return urlCamion;
        
       }     
    
        
         

    }
    
    
    
    
    

