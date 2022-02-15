
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.DificultadExamen.url;
import static com.mycompany.examenconducir.ObjetoImagenes.rutaImagenes;
import static com.mycompany.examenconducir.ObjetoPreguntas.idPregunta;
import static com.mycompany.examenconducir.ResultadoFinal.idItem;
import java.awt.Color;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author diego
 */
public class MostrarPreguntaErronea extends javax.swing.JFrame {
    // El funcionamiento de esta clase se basa en recrear la pregunta exactamente en el estado en que el usuario la vio, y mostrar las opciones falsas y correctas
   
    // Estas variables son las que van a recoger los datos que leemos de la tabla del examen del usuario.
    String enunciado_pregunta, enunciado_boton_uno, enunciado_boton_dos, enunciado_boton_tres;
    int posicion_correcta;
    int seleccion;
    
    public MostrarPreguntaErronea() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        ImageIcon logoAutoescuela = new ImageIcon ("src/main/resources/imagenes/logoAutoescuela.png");
        this.setIconImage(logoAutoescuela.getImage());
        
        ImageIcon dgt = new ImageIcon ("src/main/resources/imagenes/dgt.png");
        Icon dgtIcon = new ImageIcon (dgt.getImage().getScaledInstance(dgt_label.getWidth(), dgt_label.getHeight(), Image.SCALE_DEFAULT));
        dgt_label.setIcon(dgtIcon);
        // El método rellenar contenido es el que va a operar con todos los submetodos
        rellenarContenido();
    }
    
    
    public void rellenarContenido () {
        // La mision de este método es
        // 1. Asignar a la etiqueta numero_pregunta su numero, para informar al usuario
        label_numero_pregunta.setText(idItem);
        
        
        // 2. Leer la tabla del examen, recogiendo todos los datos relativos a las preguntas aparecidas para mostrarlas
        leerTablaExamen();
        // 3. Rellenar los elementos objetos y foto en función de los parametros leidos
        mostrar_enunciado_pregunta.setText(enunciado_pregunta);
        opcion_usuario_label.setText(seleccion + "");
        rellenarPreguntas();
        rellenarFoto();
        
    }
    
      public void leerTablaExamen () {
          // Hacemos una SELECT de la tabla y recogemos los parametros obtenidos, los almacenamos en variables
          // Estos parametros definiran el como estructuramos el frame
         
        String operacion = "Select enunciado_pregunta, enunciado_respuesta_uno, enunciado_respuesta_dos, enunciado_respuesta_tres, posicion_correcta, posicion_usuario  "
                 + "from examen_usuario where id_pregunta =  ?";
        try(var consultaDatosOperacion = DriverManager.getConnection(url); 
                var pstMod = consultaDatosOperacion.prepareStatement(operacion);){ 
                pstMod.setString (1,idItem);
                try(var rs =  pstMod.executeQuery()){
                    if (rs.next()) {
                        enunciado_pregunta = rs.getString(1);
                        enunciado_boton_uno = rs.getString(2);
                        enunciado_boton_dos = rs.getString(3);
                        enunciado_boton_tres = rs.getString(4);
                        posicion_correcta = rs.getInt(5);
                        seleccion = rs.getInt(6);
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

    
   
    
    public void rellenarPreguntas () {
     
            // Si se ha acertado la pregunta entonces lo primero es asignar el texto que habia en la pregunta cuando se formuló a la recreación actual
            pregunta_uno.setText(enunciado_boton_uno);
            pregunta_dos.setText(enunciado_boton_dos);
            pregunta_tres.setText(enunciado_boton_tres);
            
            // Despues comprobamos cual es la marcada por el usuario
            switch (seleccion) {
                case 1:
                    pregunta_uno.setSelected(true);
                    break;
                case 2:
                    pregunta_dos.setSelected(true);
                    break;
                default:
                    pregunta_tres.setSelected(true);
                    break;
            }
            
            // Deshabilitamos los botones, ya que esta interfaz no esta diseñada para operar, solo para mostrar contenido
            pregunta_uno.setEnabled(false);
            pregunta_dos.setEnabled(false);
            pregunta_tres.setEnabled(false);

            
            // Despues comprobamos cual es la correcta, la asignamos un color verde a la correcta y un rojo a las erroneas
            switch (posicion_correcta) {
                case 1:
                    pregunta_uno.setForeground(Color.GREEN);
                    pregunta_dos.setForeground(Color.RED);
                    pregunta_tres.setForeground(Color.RED);
                    break;
                case 2:
                    pregunta_uno.setForeground(Color.RED);
                    pregunta_dos.setForeground(Color.GREEN);
                    pregunta_tres.setForeground(Color.RED);
                    break;
                default:
                    pregunta_uno.setForeground(Color.RED);
                    pregunta_dos.setForeground(Color.RED);
                    pregunta_tres.setForeground(Color.GREEN);
                    break;
            }   
        }
       
    public void rellenarFoto () {     
       // Obtenemos la foto que había en el momento.
 
        ImageIcon imagenRevision = new ImageIcon (rutaImagenes.get(Integer.parseInt(idItem) - 1));
        Icon imagenExamenIc = new ImageIcon (imagenRevision.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
        foto.setIcon(imagenExamenIc);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        pregunta_uno = new javax.swing.JRadioButton();
        pregunta_tres = new javax.swing.JRadioButton();
        pregunta_dos = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        label_numero_pregunta = new javax.swing.JLabel();
        foto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostrar_enunciado_pregunta = new javax.swing.JTextArea();
        dgt_label = new javax.swing.JLabel();
        boton_volver = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        opcion_usuario_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonGroup1.add(pregunta_uno);
        pregunta_uno.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pregunta_uno.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_uno.setText("jRadioButton1");

        buttonGroup1.add(pregunta_tres);
        pregunta_tres.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pregunta_tres.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_tres.setText("jRadioButton1");

        buttonGroup1.add(pregunta_dos);
        pregunta_dos.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        pregunta_dos.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_dos.setText("jRadioButton1");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Usted seleccionó la opción número: ");

        label_numero_pregunta.setBackground(new java.awt.Color(0, 0, 0));
        label_numero_pregunta.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        label_numero_pregunta.setForeground(new java.awt.Color(0, 0, 0));
        label_numero_pregunta.setText("Numero pregunta");

        foto.setText("FOTO");

        mostrar_enunciado_pregunta.setEditable(false);
        mostrar_enunciado_pregunta.setColumns(20);
        mostrar_enunciado_pregunta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        mostrar_enunciado_pregunta.setRows(5);
        jScrollPane1.setViewportView(mostrar_enunciado_pregunta);

        boton_volver.setBackground(new java.awt.Color(51, 51, 255));
        boton_volver.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        boton_volver.setForeground(new java.awt.Color(255, 255, 255));
        boton_volver.setText("Volver");
        boton_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_volverActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("PREGUNTA:");

        opcion_usuario_label.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        opcion_usuario_label.setForeground(new java.awt.Color(0, 0, 0));
        opcion_usuario_label.setText("NUMERO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pregunta_dos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pregunta_uno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pregunta_tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label_numero_pregunta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(0, 22, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(boton_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(opcion_usuario_label)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(label_numero_pregunta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(foto, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(opcion_usuario_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pregunta_uno, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(pregunta_dos, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(pregunta_tres, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boton_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_volverActionPerformed
        ResultadoFinal  newframe = new ResultadoFinal ();
        newframe.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_boton_volverActionPerformed

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
            java.util.logging.Logger.getLogger(MostrarPreguntaErronea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MostrarPreguntaErronea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MostrarPreguntaErronea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MostrarPreguntaErronea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MostrarPreguntaErronea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_volver;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel dgt_label;
    private javax.swing.JLabel foto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_numero_pregunta;
    private javax.swing.JTextArea mostrar_enunciado_pregunta;
    private javax.swing.JLabel opcion_usuario_label;
    private javax.swing.JRadioButton pregunta_dos;
    private javax.swing.JRadioButton pregunta_tres;
    private javax.swing.JRadioButton pregunta_uno;
    // End of variables declaration//GEN-END:variables
}
