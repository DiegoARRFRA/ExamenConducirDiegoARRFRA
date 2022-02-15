/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.CreadorExamen.url;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class InsertarPreguntas extends javax.swing.JFrame {

    boolean control = false;
    public InsertarPreguntas() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        
        ImageIcon logoAutoescuela = new ImageIcon ("src/main/resources/imagenes/logoAutoescuela.png");
        this.setIconImage(logoAutoescuela.getImage());
    }
    
    public boolean camposMarcados () {
          if (id_pregunta.getText().length() == 0 || enunciado_pregunta.getText().length() == 0 || enunciado_respuesta.getText().length()== 0||
              enunciado_respuesta_incorrecta_uno.getText().length() == 0 ||enunciado_respuesta_incorrecta_dos.getText().length() == 0 || enunciado_respuesta_incorrecta_tres.getText().length() == 0 ||
              enunciado_respuesta_incorrecta_cuatro.getText().length() == 0 ||enunciado_respuesta_incorrecta_cinco.getText().length() == 0   ) {
              control= false;
          } else {
              control = true;
          }
              
         return control;
          
    } 
    
    public void insertarPregunta ()  {
        String insertarPregunta = "Insert into preguntas (id_pregunta, enunciado_pregunta, imagenes) values (?,?,?)";
        try(var modInsertarPregunta = DriverManager.getConnection(url); 
           var pstMod = modInsertarPregunta.prepareStatement(insertarPregunta);){ 
                pstMod.setInt     (1,Integer.parseInt(id_pregunta.getText()));
                pstMod.setString  (2,enunciado_pregunta.getText().toString());
                pstMod.setString  (3, ruta_imagen.getText().toString());
                pstMod.executeUpdate();
                JOptionPane.showMessageDialog(this, "Se ha insertado en preguntas");
        }catch(SQLException ex){
                System.err.println("Error al establecer la consulta");
                System.err.println(ex.toString());
        }
            
   }
    
    
    public void insertarRespuesta () {
        String insertarRespuesta = "Insert into respuestas (id_pregunta, id_respuesta, enunciado_respuesta, enunciado_respuesta_incorrecta_uno, enunciado_respuesta_incorrecta_dos, enunciado_respuesta_incorrecta_tres, enunciado_respuesta_incorrecta_cuatro, enunciado_respuesta_incorrecta_cinco)"
                + " values (?,?,?,?,?,?,?,?)";
            try(var modInsertarRespuestas = DriverManager.getConnection(url); 
                var pstMod = modInsertarRespuestas.prepareStatement(insertarRespuesta);){ 
                    pstMod.setInt     (1,Integer.parseInt(id_pregunta.getText()));
                    pstMod.setInt     (2,Integer.parseInt(id_pregunta.getText()));
                    pstMod.setString  (3,enunciado_respuesta.getText().toString());
                    pstMod.setString  (4, enunciado_respuesta_incorrecta_uno.getText().toString());
                    pstMod.setString  (5, enunciado_respuesta_incorrecta_dos.getText().toString());
                    pstMod.setString  (6, enunciado_respuesta_incorrecta_tres.getText().toString());
                    pstMod.setString  (7, enunciado_respuesta_incorrecta_cuatro.getText().toString());
                    pstMod.setString  (8, enunciado_respuesta_incorrecta_cinco.getText().toString());
                    pstMod.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Se ha insertado en respuestas");
                }catch(SQLException ex){
                System.err.println("Error al establecer la consulta");
                System.err.println(ex.toString());
            }        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        id_pregunta = new com.mycompany.deincomponentes.LabeledTextField();
        ruta_imagen = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_pregunta = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta_incorrecta_uno = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta_incorrecta_dos = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta_incorrecta_tres = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta_incorrecta_cuatro = new com.mycompany.deincomponentes.LabeledTextField();
        enunciado_respuesta_incorrecta_cinco = new com.mycompany.deincomponentes.LabeledTextField();
        boton_crear = new javax.swing.JButton();
        boton_retroceder = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel5.setText("INSERTAR UNA PREGUNTA EN LA TABLA SELECCIONADA");

        id_pregunta.setLabel("ID DE LA PREGUNTA");

        ruta_imagen.setLabel("Ruta de la imagen de la pregunta");

        enunciado_pregunta.setLabel("Enunciado Pregunta");

        enunciado_respuesta.setLabel("Enunciado de la respuesta correcta");

        enunciado_respuesta_incorrecta_uno.setLabel("Enunciado de respuesta incorrecta uno");

        enunciado_respuesta_incorrecta_dos.setLabel("Enunciado de respuesta incorrecta dos");

        enunciado_respuesta_incorrecta_tres.setLabel("Enunciado de respuesta incorrecta tres");

        enunciado_respuesta_incorrecta_cuatro.setLabel("Enunciado de respuesta incorrecta cuatro");

        enunciado_respuesta_incorrecta_cinco.setLabel("Enunciado de respuesta incorrecta cinco");

        boton_crear.setBackground(new java.awt.Color(0, 0, 204));
        boton_crear.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        boton_crear.setForeground(new java.awt.Color(255, 255, 255));
        boton_crear.setText("CREAR PREGUNTA");
        boton_crear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_crearActionPerformed(evt);
            }
        });

        boton_retroceder.setBackground(new java.awt.Color(0, 0, 204));
        boton_retroceder.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        boton_retroceder.setForeground(new java.awt.Color(255, 255, 255));
        boton_retroceder.setText("RETROCEDER");
        boton_retroceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_retrocederActionPerformed(evt);
            }
        });

        jLabel1.setText("La ruta para las fotos sigue este patron");

        jLabel2.setText("src/main/resources/imagenes/vehiculo/nombre.jpg");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(enunciado_pregunta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta_incorrecta_uno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta_incorrecta_dos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta_incorrecta_cinco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta_incorrecta_cuatro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(enunciado_respuesta_incorrecta_tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(boton_retroceder, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(boton_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(84, 84, 84))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(id_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ruta_imagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(id_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ruta_imagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(enunciado_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta_incorrecta_uno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta_incorrecta_dos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta_incorrecta_tres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta_incorrecta_cuatro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enunciado_respuesta_incorrecta_cinco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_retroceder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_crearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_crearActionPerformed

        if (camposMarcados()) { 
            if (ruta_imagen.getText().length() == 0) {
                ruta_imagen.setText("src/main/resources/imagenes/logoAutoescuela.png");
            }       
            insertarPregunta();
            insertarRespuesta();
            
  
        } else {
            JOptionPane.showMessageDialog(this, "Se deben de rellenar todos los campos (salvo imagen que es opcional) para poder ingresar las preguntas");
        }
        
        
      
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_boton_crearActionPerformed

    private void boton_retrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_retrocederActionPerformed
        JOptionPane.showMessageDialog(this, "No se han realizado cambios");
          CreadorExamen  newframe = new CreadorExamen ();
          newframe.setVisible(true);
          this.dispose();
            
        
    }//GEN-LAST:event_boton_retrocederActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InsertarPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InsertarPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InsertarPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InsertarPreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InsertarPreguntas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_crear;
    private javax.swing.JButton boton_retroceder;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_pregunta;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta_incorrecta_cinco;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta_incorrecta_cuatro;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta_incorrecta_dos;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta_incorrecta_tres;
    private com.mycompany.deincomponentes.LabeledTextField enunciado_respuesta_incorrecta_uno;
    private com.mycompany.deincomponentes.LabeledTextField id_pregunta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private com.mycompany.deincomponentes.LabeledTextField ruta_imagen;
    // End of variables declaration//GEN-END:variables
}
