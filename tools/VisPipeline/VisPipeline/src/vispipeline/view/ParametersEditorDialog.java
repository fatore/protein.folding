/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParametersEditorDialog.java
 *
 * Created on 27/05/2009, 13:43:19
 */
package vispipeline.view;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ParametersEditorDialog extends javax.swing.JDialog {

    /** Creates new form ParametersEditorDialog */
    private ParametersEditorDialog(JFrame parent, AbstractParametersView parameditor) {
        super(parent);
        initComponents();

        this.parameditor = parameditor;

        this.setPreferredSize(new Dimension(700, 600));
        this.setSize(700, 600);

        //adding the
        parametersPanel.removeAll();
        parametersPanel.add(parameditor);
        parametersPanel.validate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parametersPanel = new javax.swing.JPanel();
        buttonPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Parameters Editor");
        setModal(true);

        parametersPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(parametersPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(cancelButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        try {
            parameditor.finished();
            this.setVisible(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    public static ParametersEditorDialog getInstance(JFrame parent, AbstractParametersView parameditor) {
        return new ParametersEditorDialog(parent, parameditor);
    }

    public void display() {
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    private AbstractParametersView parameditor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel parametersPanel;
    // End of variables declaration//GEN-END:variables
}
