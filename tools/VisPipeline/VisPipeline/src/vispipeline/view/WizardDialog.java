/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * WizardDialog.java
 *
 * Created on 29/05/2009, 10:54:57
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
public class WizardDialog extends javax.swing.JDialog {

    public static final int SUCESS = 0;
    public static final int FAIL = 1;

    /** Creates new form WizardDialog */
    private WizardDialog(JFrame parent, AbstractParametersView parameditor) {
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
        nextButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        parametersPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(parametersPanel, java.awt.BorderLayout.CENTER);

        buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        nextButton.setText("Next >>");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(nextButton);

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

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        try {
            parameditor.finished();
            this.result = WizardDialog.SUCESS;
            this.setVisible(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
}//GEN-LAST:event_nextButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_cancelButtonActionPerformed

    public static WizardDialog getInstance(JFrame parent, AbstractParametersView parameditor) {
        return new WizardDialog(parent, parameditor);
    }

    public int display() {
        this.result = WizardDialog.FAIL;
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
        return result;
    }

    private int result;
    private AbstractParametersView parameditor;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel parametersPanel;
    // End of variables declaration//GEN-END:variables
}
