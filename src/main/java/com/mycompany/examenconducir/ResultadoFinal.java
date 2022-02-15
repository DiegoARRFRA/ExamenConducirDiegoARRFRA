
package com.mycompany.examenconducir;

import static com.mycompany.examenconducir.CreadorExamen.numeroPreguntas;
import static com.mycompany.examenconducir.CreadorExamen.url;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author diego
 */
public final class ResultadoFinal extends javax.swing.JFrame {
    
    // Esta es la clase ResultadoFinal, es la clase encargada de mostrar los resultados del examen y de mostrar de manera detallada que respuestas hicimos bien y cual no.
    // Implementa dos listas como las que vimos en clase personalizadas, cada una de ellas está asociado a un campo de nuestra tabla de examen_usuario.
    // Por un lado esta las respuetas acertadas y por otro las falladas.
    
    // Esta clase se encarga principalmente de rellenar esas listas, calcular con contadores los fallos y establecer mediante una operación el resultado si es aprobado o no.
    // También hace de puente a nuestra clase de vista personalizada, que con el click permite al usuario recrear la pregunta y nos muestra toda la información sobre la misma
    

    int contadorAciertos, contadorFallos; // Los contadores, que van a delimitar si hemos acertado o fallado.
    public static String idItem; // Este es el ID del item que se selecciona en la lista para ver a fondo
    static DefaultListModel listaAciertos,listaFallos ; // Las dos listas.
    public ResultadoFinal() {
        initComponents();
        listaAciertos = new DefaultListModel();
        listaFallos = new DefaultListModel();
        
        // Rellenar las listas y comprobar si esta aprobado.
        rellenarListaAciertos();
        rellenarListaFallos();
        comprobarExamenAprobado();
        
        ImageIcon dgt = new ImageIcon ("src/main/resources/imagenes/dgt.png");
        Icon dgtIcon = new ImageIcon (dgt.getImage().getScaledInstance(dgt_label.getWidth(), dgt_label.getHeight(), Image.SCALE_DEFAULT));
        dgt_label.setIcon(dgtIcon);
        setLocationRelativeTo(null);
        setResizable(false); 
    }


   // Rellenamos la lista mediante una consulta, y en el bucle del cursor añadimos los elementos que obtenemos de dicha consulta a la lista.
    // Establecemos un contador, cada vez que ese contador se suma es que se ha añadido un item a la lista.
   public void rellenarListaAciertos () {
        contadorAciertos = 0;
        String rellenarLista = "select id_pregunta from examen_usuario where aciertos is not null"; 
        try(var procesoRelleno = DriverManager.getConnection(url); 
                var pstMod = procesoRelleno.prepareStatement(rellenarLista);){ 
               try(var rs =  pstMod.executeQuery()){
                    while (rs.next()) {
                     int id = rs.getInt(1); 
                     String elemento = id + "";
                     listaAciertos.addElement(elemento);
                     contadorAciertos++;
                    } 
                }catch (SQLException ex) {
                    System.err.println("Error de ejecucion de consulta");
                    System.err.println(ex.toString());  
                }   
            
            }catch(SQLException ex){
                System.err.println("Error al establecer la consulta");
                System.err.println(ex.toString());
            } 
        
        // Añadimos el modelo y mostramos en la etiqueta cual han sido los aciertos gracias al contador.
        jListaAciertos.setModel(listaAciertos);   
        mostrar_Aciertos.setText("NUMERO DE ACIERTOS :  " + contadorAciertos);

    }   
   
    // Rellenamos la lista mediante una consulta, y en el bucle del cursor añadimos los elementos que obtenemos de dicha consulta a la lista.
    // Establecemos un contador, cada vez que ese contador se suma es que se ha añadido un item a la lista.
    public void rellenarListaFallos () {
         contadorFallos = 0;
        String rellenarLista = "select id_pregunta from examen_usuario where fallos is not null"; 
        try(var procesoRelleno = DriverManager.getConnection(url); 
                var pstMod = procesoRelleno.prepareStatement(rellenarLista);){ 
               try(var rs =  pstMod.executeQuery()){
                    while (rs.next()) {
                     int id = rs.getInt(1); 
                     String elemento = id + "";
                     listaFallos.addElement(elemento);
                     contadorFallos++;
                    } 
                }catch (SQLException ex) {
                    System.err.println("Error de ejecucion de consulta");
                    System.err.println(ex.toString());  
                }   
            
            }catch(SQLException ex){
                System.err.println("Error al establecer la consulta");
                System.err.println(ex.toString());
            } 
        
        // Añadimos el modelo y mostramos en la etiqueta cual han sido los aciertos fallos al contador.
        jListaFallos.setModel(listaFallos);      
        mostrar_fallos.setText("NUMERO DE FALLOS :  " + contadorFallos);
    }
    
    // Comprobamos si el examen está acertado, en función del número de preguntas tendrá unas carácterísticas para aprobar.
    
    public void comprobarExamenAprobado () {
        if (numeroPreguntas == 10 && contadorFallos > 1) {
            mostrarResultado.setText("Su examen está suspendido");
        } else if (numeroPreguntas == 10 && contadorFallos <= 0){
          mostrarResultado.setText("Su examen está aprobado");
        }
        
        if (numeroPreguntas == 15 && contadorFallos > 1) {
            mostrarResultado.setText("Su examen está suspendido");
        } else if (numeroPreguntas == 10 && contadorFallos <= 1){
          mostrarResultado.setText("Su examen está aprobado");
        }  
        
          if (numeroPreguntas == 20 && contadorFallos > 2) {
            mostrarResultado.setText("Su examen está suspendido");
        } else if (numeroPreguntas == 10 && contadorFallos <= 2){
          mostrarResultado.setText("Su examen está aprobado");
        }
      
    }
    
  
    

    

        
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListaAciertos = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListaFallos = new javax.swing.JList<>();
        mostrar_Aciertos = new javax.swing.JLabel();
        mostrar_fallos = new javax.swing.JLabel();
        mostrarResultado = new javax.swing.JLabel();
        boton_MenuPrincipal = new javax.swing.JButton();
        dgt_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ha acertado las preguntas:");

        jListaAciertos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListaAciertos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListaAciertosValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jListaAciertos);

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("RESULTADO DE SU EXAMEN");

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ha fallado las preguntas:");

        jScrollPane2.setBackground(new java.awt.Color(153, 153, 255));

        jListaFallos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jListaFallos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListaFallosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListaFallos);

        mostrar_Aciertos.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        mostrar_Aciertos.setForeground(new java.awt.Color(0, 0, 0));
        mostrar_Aciertos.setText("NUMERO DE ACIERTOS:      NUMERO");

        mostrar_fallos.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        mostrar_fallos.setForeground(new java.awt.Color(0, 0, 0));
        mostrar_fallos.setText("NUMERO DE FALLOS:      NUMERO");

        mostrarResultado.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        mostrarResultado.setForeground(new java.awt.Color(0, 0, 0));
        mostrarResultado.setText("Su examen está aprobado/suspendido");

        boton_MenuPrincipal.setBackground(new java.awt.Color(0, 51, 204));
        boton_MenuPrincipal.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        boton_MenuPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        boton_MenuPrincipal.setText("Ir al menú principal");
        boton_MenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_MenuPrincipalActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Click, para ver pregunta en detalle");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Click, para ver pregunta en detalle");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(76, 76, 76))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mostrar_Aciertos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boton_MenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mostrar_fallos, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mostrarResultado))
                                .addGap(0, 58, Short.MAX_VALUE)))
                        .addGap(417, 417, 417))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(mostrar_fallos)
                                .addGap(50, 50, 50)
                                .addComponent(mostrarResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(boton_MenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mostrar_Aciertos)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_MenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_MenuPrincipalActionPerformed
       
        InterfazPrincipalAutoescuela  newframe = new InterfazPrincipalAutoescuela ();
        newframe.setVisible(true);
        this.dispose();
        
        
    }//GEN-LAST:event_boton_MenuPrincipalActionPerformed

    private void jListaAciertosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListaAciertosValueChanged
        
       idItem =  jListaAciertos.getSelectedValue();
       
       
       MostrarPreguntaContestada  newframe = new MostrarPreguntaContestada ();
       newframe.setVisible(true);
       this.dispose();
        
        
    }//GEN-LAST:event_jListaAciertosValueChanged

    private void jListaFallosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListaFallosValueChanged
        
       idItem =  jListaFallos.getSelectedValue();
      
       MostrarPreguntaContestada  newframe = new MostrarPreguntaContestada ();
       newframe.setVisible(true);
       this.dispose();
    }//GEN-LAST:event_jListaFallosValueChanged

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
            java.util.logging.Logger.getLogger(ResultadoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResultadoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResultadoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultadoFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResultadoFinal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_MenuPrincipal;
    private javax.swing.JLabel dgt_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jListaAciertos;
    private javax.swing.JList<String> jListaFallos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel mostrarResultado;
    private javax.swing.JLabel mostrar_Aciertos;
    private javax.swing.JLabel mostrar_fallos;
    // End of variables declaration//GEN-END:variables
}
