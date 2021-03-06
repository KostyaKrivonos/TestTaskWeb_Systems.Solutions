/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.QuadraticEquationController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import model.QuadraticEquation;

/**
 *
 * @author Алёшечка
 */
public class ResultWindow extends javax.swing.JInternalFrame {
    private static int count; // лічильник відкривання вікна.
    private DefaultListModel<QuadraticEquation> listModel;
    ArrayList<QuadraticEquation> equationList;
    private QuadraticEquationController equationController;
    /**
     * Creates new form ResultWindow
     */
    public ResultWindow() {
        try {
            count++;
            initComponents();
            this.equationController = new QuadraticEquationController();
            listModel = new DefaultListModel<>();
            jList1.setModel(listModel);

            equationList = equationController.getAllEquation();
            for (QuadraticEquation i:equationList){
                listModel.addElement(i);
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(ResultWindow.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("ResultWindow");
        setPreferredSize(new java.awt.Dimension(250, 250));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        getContentPane().add(jScrollPane2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameClosing
        count--;
    }//GEN-LAST:event_formInternalFrameClosing

    public static int getCount() {
        return count;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<QuadraticEquation> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
