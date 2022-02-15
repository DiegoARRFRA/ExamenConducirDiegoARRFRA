
package com.mycompany.examenconducir;


import static com.mycompany.examenconducir.DificultadExamen.numeroPreguntas;
import static com.mycompany.examenconducir.DificultadExamen.url;
import static com.mycompany.examenconducir.ObjetoImagenes.rellenarArrayImagenes;
import static com.mycompany.examenconducir.ObjetoImagenes.rutaImagenes;
import static com.mycompany.examenconducir.ObjetoPreguntas.getEnunciado_pregunta;
import static com.mycompany.examenconducir.ObjetoPreguntas.obtenerPreguntas;
import static com.mycompany.examenconducir.ObjetoRespuestas.elegirRandomIdRespuestaIncorrecta;
import static com.mycompany.examenconducir.ObjetoRespuestas.getRespuestaCorrecta;
import static com.mycompany.examenconducir.ObjetoRespuestas.getRespuestaIncorrectaDos;
import static com.mycompany.examenconducir.ObjetoRespuestas.getRespuestaIncorrectaUno;
import static com.mycompany.examenconducir.ObjetoRespuestas.obtenerRespuestas;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
 

/**
 *
 * @author diego
 */
public final class esquemaExamenCoche extends javax.swing.JFrame {
   

    ArrayList<Integer> opcionesBotones= new ArrayList<>();
    public boolean control = false; // Para comprobar que se han marcado todas las opciones
    public int respuestaCorrecta; // Para saber que se ha seleccionado la opción correcta en el boton marcado por el usuario
    public int numeroPreguntaActual = 1; // Numero de la pregunta actual, empezando por 1
    public int contador = 0; // Parecido a lo de arriba, pero pensado para mandar datos a los array (empienzan en 0)
    
    String variableRecogidaBotonUno,variableRecogidaBotonDos,variableRecogidaBotonTres;
    String variableRecogidaAcierto;
    int numeroBotonSeleccionado;


    public esquemaExamenCoche() {
        vaciarTabla();       
        obtenerExamen();
        initComponents();
        establecerElementos();
        setLocationRelativeTo(null);
        setResizable(false);
        num_Mostrado.setText(numeroPreguntaActual + "");
        
        ImageIcon dgt = new ImageIcon ("src/main/resources/imagenes/dgt.png");
        Icon dgtIcon = new ImageIcon (dgt.getImage().getScaledInstance(dgt_label.getWidth(), dgt_label.getHeight(), Image.SCALE_DEFAULT));
        dgt_label.setIcon(dgtIcon);

        ImageIcon logoAutoescuela = new ImageIcon ("src/main/resources/imagenes/logoAutoescuela.png");
        Icon logoDiego = new ImageIcon (logoAutoescuela.getImage().getScaledInstance(autoescuela_diego_label.getWidth(), autoescuela_diego_label.getHeight(), Image.SCALE_DEFAULT));
        autoescuela_diego_label.setIcon (logoDiego);
        this.setIconImage(logoAutoescuela.getImage());
    }
    
    
    // Para establecer una conexión a una base de datos en función de lo seleccionado por el usuario

    
    
    public void vaciarTabla() {
        String query = "Delete from examen_usuario";
        try(var delete = DriverManager.getConnection(url); 
            var deleteContenido = delete.prepareStatement(query);){ 
                deleteContenido.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Error al establecer la consulta");
            System.err.println(ex.toString());
        }
        
        
    }
  
    
    public void obtenerExamen () {
        // Rellenamos todos los recipientes de nuestros preguntas y respuestas
        obtenerPreguntas();     
        obtenerRespuestas();   
        rellenarArrayImagenes ();  
    }
    
    


    
    public void establecerElementos () {
        // Cosas que hace este método
        // 1- Poner la imagen de la foto
            ponerImagenes();
        // 2- Poner el texto de la pregunta
        texto_Pregunta.setText(getEnunciado_pregunta(contador));
        // 3- Hace una llamada a establecer
        establecer();
       
    }
    

    public void establecer () {
        // Establecer hace lo siguiente
        // 1- Elige un número random del 1 al 3 sin que se repitan para poner los diferentes enunciados posibles de la respuesta
       numeroRandom();
       // 2- Elige las respuestas incorrectas posibles relacionadas al id_pregunta que está actualmente
       elegirRandomIdRespuestaIncorrecta();
       // 3- Elige de forma random el tipo de respuesta que tendrá cada botón en función de lo marcado en el array random que operamos arriba.
       elegirRandomOpcionUno();
       elegirRandomOpcionDos();
       elegirRandomOpcionTres(); 
       // 4- Vacia de nuevo el array para operar con el en la siguiente pregunta
       opcionesBotones.clear();

    }
    
        public void ponerImagenes () {
         ImageIcon imagenExamen = new ImageIcon (rutaImagenes.get(contador));
        Icon imagenExamenIc = new ImageIcon (imagenExamen.getImage().getScaledInstance(foto_preguntas.getWidth(), foto_preguntas.getHeight(), Image.SCALE_DEFAULT));
        foto_preguntas.setIcon(imagenExamenIc);
    }
         
    

    
    
    public void elegirRandomOpcionUno () {
            if(null != opcionesBotones.get(0)) switch (opcionesBotones.get(0)) {
            case 1 -> {
                pregunta_uno.setText(getRespuestaCorrecta(contador));
                variableRecogidaAcierto = getRespuestaCorrecta(contador);
                respuestaCorrecta = 1;
                }
            case 2 ->  {
                pregunta_uno.setText(getRespuestaIncorrectaUno(contador));

                }

            case 3 -> {
                pregunta_uno.setText(getRespuestaIncorrectaDos(contador));
            }
            default -> {
                }
        } 
       
    }
    
    public void elegirRandomOpcionDos () {
          if(null != opcionesBotones.get(1)) switch (opcionesBotones.get(1)) {
            case 1 -> {
                pregunta_dos.setText(getRespuestaCorrecta(contador));
                variableRecogidaAcierto = getRespuestaCorrecta(contador);
                respuestaCorrecta = 2;
              }
             case 2 ->  {
                pregunta_dos.setText(getRespuestaIncorrectaUno(contador));
                }

            case 3 -> {
                pregunta_dos.setText(getRespuestaIncorrectaDos(contador));
            }
            default -> {
              }
        } 
       
    }
    
    public void elegirRandomOpcionTres () {
         if(null != opcionesBotones.get(2)) switch (opcionesBotones.get(2)) {
            case 1 -> {
                pregunta_tres.setText(getRespuestaCorrecta(contador));
                variableRecogidaAcierto = getRespuestaCorrecta(contador);
                respuestaCorrecta = 3;
             }
               case 2 ->  {
                pregunta_tres.setText(getRespuestaIncorrectaUno(contador));
                }

            case 3 -> {
                pregunta_tres.setText(getRespuestaIncorrectaDos(contador));
            }
            default -> {
             }
        } 
       
    }
    
    public void numeroRandom () {    
       Random random = new Random();
       while (opcionesBotones.size() < 3) {
            //El rango de numeros que queremos en el array
            int randomNumber = random.nextInt((3 - 1) + 1) + 1;
            //Para evitar duplicados
            if (!opcionesBotones.contains(randomNumber)) {
                opcionesBotones.add(randomNumber);
            }
        } 
    }
    
    public void botonMarcado () {
        if (pregunta_uno.isSelected()) {
            numeroBotonSeleccionado = 1;
        } 
        
        if (pregunta_dos.isSelected()) {
            numeroBotonSeleccionado = 2;
        }
        
        if (pregunta_tres.isSelected()) {
            numeroBotonSeleccionado = 3;
        }
    }
    
    public void todoMarcado () {
        control = !(!pregunta_uno.isSelected() && !pregunta_dos.isSelected() && !pregunta_tres.isSelected());
    }
    
    public void insertarRespuestaUsuario () {
        botonMarcado ();
        variableRecogidaBotonUno = pregunta_uno.getText();
        variableRecogidaBotonDos = pregunta_dos.getText();
        variableRecogidaBotonTres = pregunta_tres.getText();
        if (pregunta_uno.isSelected() && respuestaCorrecta == 1  ||
            pregunta_dos.isSelected() && respuestaCorrecta == 2  ||
            pregunta_tres.isSelected() && respuestaCorrecta == 3 ) {
            
            insertarEnTablaAcierto ();
        } else {
            insertarEnTablaFallo();
        }
             
    }
    
    public void insertarEnTablaAcierto () {
        String query = "Insert into examen_usuario (id_pregunta,aciertos,enunciado_pregunta,enunciado_respuesta_uno, enunciado_respuesta_dos, enunciado_respuesta_tres, posicion_correcta, posicion_usuario) values (?,?,?,?,?,?,?,?)";
        try(var modInsertarAcierto = DriverManager.getConnection(url); 
            var pstAcierto = modInsertarAcierto.prepareStatement(query);){ 
                pstAcierto.setDouble   (1,numeroPreguntaActual);
                pstAcierto.setString   (2,"CORRECTA");
                pstAcierto.setString   (3, getEnunciado_pregunta(contador) );
                pstAcierto.setString   (4,variableRecogidaBotonUno );
                pstAcierto.setString   (5, variableRecogidaBotonDos);
                pstAcierto.setString   (6, variableRecogidaBotonTres);
                pstAcierto.setInt      (7,respuestaCorrecta );
                pstAcierto.setInt      (8, numeroBotonSeleccionado);
                pstAcierto.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Error al establecer la consulta");
            System.err.println(ex.toString());
        }  
    }
    
     public void insertarEnTablaFallo() {
        String query = "Insert into examen_usuario (id_pregunta,fallos,enunciado_pregunta,enunciado_respuesta_uno, enunciado_respuesta_dos, enunciado_respuesta_tres, posicion_correcta, posicion_usuario) values (?,?,?,?,?,?,?,?)";
        try(var modInsertarFallo = DriverManager.getConnection(url); 
            var pstFallo = modInsertarFallo.prepareStatement(query);){ 
                pstFallo.setDouble   (1,numeroPreguntaActual);
                pstFallo.setString   (2,"FALLADA");
                pstFallo.setString   (3, getEnunciado_pregunta(contador) );
                pstFallo.setString   (4,variableRecogidaBotonUno );
                pstFallo.setString   (5, variableRecogidaBotonDos);
                pstFallo.setString   (6, variableRecogidaBotonTres);
                pstFallo.setInt      (7,respuestaCorrecta );
                pstFallo.setInt      (8, numeroBotonSeleccionado);
                pstFallo.executeUpdate();
        }catch(SQLException ex){
            System.err.println("Error al establecer la consulta");
            System.err.println(ex.toString());
        }  
    }
     
     public void aumentarMarcadores () {
        contador++;
        numeroPreguntaActual++;
        num_Mostrado.setText(numeroPreguntaActual + "");

     }
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotonesPreguntas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto_Pregunta = new javax.swing.JTextArea();
        pregunta_tres = new javax.swing.JRadioButton();
        pregunta_uno = new javax.swing.JRadioButton();
        pregunta_dos = new javax.swing.JRadioButton();
        foto_preguntas = new javax.swing.JLabel();
        boton_avanzar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        num_Mostrado = new javax.swing.JLabel();
        boton_finalizar = new javax.swing.JButton();
        dgt_label = new javax.swing.JLabel();
        autoescuela_diego_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        texto_Pregunta.setEditable(false);
        texto_Pregunta.setColumns(20);
        texto_Pregunta.setFont(new java.awt.Font("Book Antiqua", 1, 18)); // NOI18N
        texto_Pregunta.setRows(5);
        texto_Pregunta.setToolTipText("");
        jScrollPane1.setViewportView(texto_Pregunta);

        pregunta_tres.setBackground(new java.awt.Color(255, 255, 255));
        grupoBotonesPreguntas.add(pregunta_tres);
        pregunta_tres.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        pregunta_tres.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_tres.setText("PREGUNTA 3");

        pregunta_uno.setBackground(new java.awt.Color(255, 255, 255));
        grupoBotonesPreguntas.add(pregunta_uno);
        pregunta_uno.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        pregunta_uno.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_uno.setText("PREGUNTA 1");
        pregunta_uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pregunta_unoActionPerformed(evt);
            }
        });

        pregunta_dos.setBackground(new java.awt.Color(255, 255, 255));
        grupoBotonesPreguntas.add(pregunta_dos);
        pregunta_dos.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        pregunta_dos.setForeground(new java.awt.Color(0, 0, 0));
        pregunta_dos.setText("PREGUNTA 2");

        boton_avanzar.setBackground(new java.awt.Color(0, 0, 255));
        boton_avanzar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        boton_avanzar.setForeground(new java.awt.Color(255, 255, 255));
        boton_avanzar.setText("SIGUIENTE PREGUNTA");
        boton_avanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_avanzarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Número de pregunta: ");

        num_Mostrado.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        num_Mostrado.setForeground(new java.awt.Color(0, 0, 0));
        num_Mostrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        num_Mostrado.setText("Num Mostrado");

        boton_finalizar.setBackground(new java.awt.Color(0, 0, 255));
        boton_finalizar.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        boton_finalizar.setForeground(new java.awt.Color(255, 255, 255));
        boton_finalizar.setText("FINALIZAR TEST");
        boton_finalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_finalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(boton_avanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(boton_finalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106)
                                .addComponent(foto_preguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(autoescuela_diego_label, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel1)
                                        .addGap(47, 47, 47)
                                        .addComponent(num_Mostrado)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(pregunta_tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pregunta_dos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pregunta_uno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(autoescuela_diego_label, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(num_Mostrado, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foto_preguntas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pregunta_uno, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pregunta_dos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pregunta_tres, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton_avanzar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_finalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_avanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_avanzarActionPerformed
       
        
        if (numeroPreguntaActual == numeroPreguntas ) {
            boton_avanzar.setText("No hay mas preguntas ");
            JOptionPane.showMessageDialog(this, "Examen terminado ");
        } else if ( numeroPreguntaActual > numeroPreguntas ) {
            boton_avanzar.setEnabled(false);

        } else {
            todoMarcado ();    
            if (control == false) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione una opción antes de continuar");
            } else {
                    insertarRespuestaUsuario();
                    aumentarMarcadores(); 
                    establecerElementos(); 
         }
     
       
       
            
        }
      
    }//GEN-LAST:event_boton_avanzarActionPerformed

    private void boton_finalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_finalizarActionPerformed
         insertarRespuestaUsuario();
        if (numeroPreguntaActual < numeroPreguntas ) {
            JOptionPane.showMessageDialog(this, "El examen no ha terminado ");
        } else {
            ResultadoFinal  newframe = new ResultadoFinal ();
            newframe.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_boton_finalizarActionPerformed

    private void pregunta_unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pregunta_unoActionPerformed

    }//GEN-LAST:event_pregunta_unoActionPerformed

    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(esquemaExamenCoche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new esquemaExamenCoche().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel autoescuela_diego_label;
    private javax.swing.JButton boton_avanzar;
    private javax.swing.JButton boton_finalizar;
    private javax.swing.JLabel dgt_label;
    private javax.swing.JLabel foto_preguntas;
    private javax.swing.ButtonGroup grupoBotonesPreguntas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel num_Mostrado;
    private javax.swing.JRadioButton pregunta_dos;
    private javax.swing.JRadioButton pregunta_tres;
    private javax.swing.JRadioButton pregunta_uno;
    private javax.swing.JTextArea texto_Pregunta;
    // End of variables declaration//GEN-END:variables
}
