/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.examenconducir;

/**
 *
 * @author diego
 */
public class componente_Preguntas extends javax.swing.JPanel {

    /**
     * Creates new form componente_Preguntas
     */
    public componente_Preguntas() {
        initComponents();
    }

      public void setTextBotonUno(String texto) {
        pregunta_uno.setText(texto);
    }
    
    public String getTextBotonUno () {
        return pregunta_uno.getText();
    }
    
     
    
       public void setTextBotonDos(String texto) {
        pregunta_dos.setText(texto);
    }
    
    public String getTextBotonDos () {
        return pregunta_dos.getText();
    }
    
       public void setTextBotonTres(String texto) {
        pregunta_tres.setText(texto);
    }
    
    public String getTextBotonTres () {
        return pregunta_tres.getText();
    }
    
   
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pregunta_tres = new javax.swing.JRadioButton();
        pregunta_dos = new javax.swing.JRadioButton();
        pregunta_uno = new javax.swing.JRadioButton();

        buttonGroup1.add(pregunta_tres);
        pregunta_tres.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        pregunta_tres.setText("jRadioButton1");

        buttonGroup1.add(pregunta_dos);
        pregunta_dos.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        pregunta_dos.setText("jRadioButton1");

        buttonGroup1.add(pregunta_uno);
        pregunta_uno.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        pregunta_uno.setText("jRadioButton1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pregunta_dos, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
            .addComponent(pregunta_tres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pregunta_uno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pregunta_uno, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pregunta_dos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pregunta_tres, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton pregunta_dos;
    private javax.swing.JRadioButton pregunta_tres;
    private javax.swing.JRadioButton pregunta_uno;
    // End of variables declaration//GEN-END:variables
}
