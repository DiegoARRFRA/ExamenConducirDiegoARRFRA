
package com.mycompany.examenconducir;


import static com.mycompany.examenconducir.Conexion.ConexionBaseCamion;
import static com.mycompany.examenconducir.Conexion.ConexionBaseCoche;
import static com.mycompany.examenconducir.Conexion.ConexionBaseMoto;
import static com.mycompany.examenconducir.Conexion.leerProperties;
import static com.mycompany.examenconducir.InterfazPrincipalAutoescuela.baseElegida;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public final class CreadorExamen extends javax.swing.JFrame {
    boolean permitido = false; // Una variable de control para evitar errores.
    public static int numeroPreguntas; // La variable que va a determinar cuantas preguntas tiene nuestro examen.
    public static  String url; // La url determinada en función de lo elegido


    public CreadorExamen() {
        initComponents();
        // Leemos los properties, para rellenar los elementos de URL
        leerProperties();   
        // Llamamos al método de conexión base, el encargado de mediante un switch elegir que conexión establecemos
        conexionBase ();
        
        // Parametros de configuración del layout.
        
        
        ImageIcon logoAutoescuela = new ImageIcon ("src/main/resources/imagenes/logoAutoescuela.png");
        this.setIconImage(logoAutoescuela.getImage());
        
        ImageIcon dgt = new ImageIcon ("src/main/resources/imagenes/dgt.png");
        Icon dgtIcon = new ImageIcon (dgt.getImage().getScaledInstance(dgt_label.getWidth(), dgt_label.getHeight(), Image.SCALE_DEFAULT));
        dgt_label.setIcon(dgtIcon);
        setLocationRelativeTo(null);
        setResizable(false);

    }
    
    
    // Para establecer una conexión a una base de datos en función de lo seleccionado por el usuario
    // Importamos el baseElegida y en función de su valor establecemos una conexión con una u otra base de datos
    // gracias a los getter que creamos en la clase Conexión
    public void conexionBase () {
        switch (baseElegida) {
            case "coche" -> url = ConexionBaseCoche();
            case "moto" -> url = ConexionBaseMoto();
            case "camion" -> url =  ConexionBaseCamion();
            default -> {
            }
        }
    }
    
    // Este método es el encargado de delimitar el numero de preguntas que tendrá nuestro examen
    // Asignando un valor fijo a nuestra variable en función del radioButton que este seleccionado, el radioButton no cambia con el isSelected
    // ya que en el momento que este método entra en acción es cuando el usuario ha dado al botón de avanzar, por lo que la decisión va a ser siempre definitiva.
    public void numeroPreguntas() {
        
        if (radioBoton_diezPreguntas.isSelected()) {
            numeroPreguntas = 10;
            permitido = true; // Si el boton es seleccionado convertimos nuestra variable de control a true.
        } 
        
        if (radioBoton_quincePreguntas.isSelected()) {
            numeroPreguntas = 15;
            permitido = true;
        
        } if (radioBoton_veintePreguntas.isSelected()) {
            numeroPreguntas = 20;
            permitido = true;
        }
        
        // Establecemos una doble comprobación de que está todo marcado.
        if (!radioBoton_diezPreguntas.isSelected() && !radioBoton_quincePreguntas.isSelected()&& !radioBoton_veintePreguntas.isSelected()) { 
            permitido = false;
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_numeroPreguntas = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        radioBoton_veintePreguntas = new javax.swing.JRadioButton();
        radioBoton_diezPreguntas = new javax.swing.JRadioButton();
        radioBoton_quincePreguntas = new javax.swing.JRadioButton();
        Boton_confirmar = new javax.swing.JButton();
        Boton_InsertarPreguntas = new javax.swing.JButton();
        dgt_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("SELECCIONE EL NÚMERO DE PREGUNTAS QUE DESEA TENER");

        radioBoton_veintePreguntas.setBackground(new java.awt.Color(51, 51, 255));
        buttonGroup_numeroPreguntas.add(radioBoton_veintePreguntas);
        radioBoton_veintePreguntas.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        radioBoton_veintePreguntas.setForeground(new java.awt.Color(0, 0, 0));
        radioBoton_veintePreguntas.setText("20 preguntas");

        radioBoton_diezPreguntas.setBackground(new java.awt.Color(0, 0, 255));
        buttonGroup_numeroPreguntas.add(radioBoton_diezPreguntas);
        radioBoton_diezPreguntas.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        radioBoton_diezPreguntas.setForeground(new java.awt.Color(0, 0, 0));
        radioBoton_diezPreguntas.setText("10 preguntas");

        radioBoton_quincePreguntas.setBackground(new java.awt.Color(51, 51, 255));
        buttonGroup_numeroPreguntas.add(radioBoton_quincePreguntas);
        radioBoton_quincePreguntas.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        radioBoton_quincePreguntas.setForeground(new java.awt.Color(0, 0, 0));
        radioBoton_quincePreguntas.setText("15 preguntas");

        Boton_confirmar.setBackground(new java.awt.Color(0, 0, 255));
        Boton_confirmar.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        Boton_confirmar.setForeground(new java.awt.Color(255, 255, 255));
        Boton_confirmar.setText("Confirmar");
        Boton_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_confirmarActionPerformed(evt);
            }
        });

        Boton_InsertarPreguntas.setBackground(new java.awt.Color(0, 0, 255));
        Boton_InsertarPreguntas.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        Boton_InsertarPreguntas.setForeground(new java.awt.Color(255, 255, 255));
        Boton_InsertarPreguntas.setText("Insertar preguntas");
        Boton_InsertarPreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_InsertarPreguntasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Boton_InsertarPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Boton_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioBoton_diezPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioBoton_quincePreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(radioBoton_veintePreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 96, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dgt_label, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioBoton_diezPreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Boton_InsertarPreguntas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(radioBoton_quincePreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBoton_veintePreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Boton_confirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
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

    private void Boton_InsertarPreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InsertarPreguntasActionPerformed
        
            // Nos envia directos a la activity de Insertar Preguntas en nuestra base de datos.
            InsertarPreguntas  newframe = new InsertarPreguntas ();
            newframe.setVisible(true);
            this.dispose();    
    }//GEN-LAST:event_Boton_InsertarPreguntasActionPerformed

    private void Boton_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_confirmarActionPerformed
        // Llamamos a numero de preguntas para ver cuantas vamos a establecer para el examen
        numeroPreguntas();
        // Si nuestra variable de control es falsa, quiere decir que no se ha marcado ninguna opción en el radioButton, por lo que no dejamos avanzar.
        if (permitido == false) {
            JOptionPane.showMessageDialog(this,"Se debe de seleccionar un número de preguntas para crear el examen");          
        } else {
            // En caso de que sea verdadera, nos dirigimos a nuestra clase EsquemaExamen que es en la que tratamos con los objetos relacionados.
            EsquemaExamen  newframe = new EsquemaExamen ();
            newframe.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_Boton_confirmarActionPerformed

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
            java.util.logging.Logger.getLogger(CreadorExamen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreadorExamen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreadorExamen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreadorExamen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreadorExamen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_InsertarPreguntas;
    private javax.swing.JButton Boton_confirmar;
    private javax.swing.ButtonGroup buttonGroup_numeroPreguntas;
    private javax.swing.JLabel dgt_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton radioBoton_diezPreguntas;
    private javax.swing.JRadioButton radioBoton_quincePreguntas;
    private javax.swing.JRadioButton radioBoton_veintePreguntas;
    // End of variables declaration//GEN-END:variables

}
  
