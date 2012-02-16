/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PersistentPLSPBinaryReaderParamView.java
 *
 * Created on 28/05/2009, 17:09:39
 */
package plsp.persistent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import plsp.filter.PLSPBinFilter;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.util.BasicsContants;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PersistentPLSPBinaryReaderParamView extends AbstractParametersView {

    /** Creates new form PersistentPLSPBinaryReaderParamView */
    public PersistentPLSPBinaryReaderParamView(PersistentPLSPBinaryReaderComp comp) {
        initComponents();

        this.comp = comp;
        reset();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        matrixLabel = new javax.swing.JLabel();
        matrixTextField = new javax.swing.JTextField();
        matrixButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Persistent P-LSP Binary File Reader"));
        setLayout(new java.awt.GridBagLayout());

        matrixLabel.setText("File name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(matrixLabel, gridBagConstraints);

        matrixTextField.setColumns(35);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(matrixTextField, gridBagConstraints);

        matrixButton.setText("Search...");
        matrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(matrixButton, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void matrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matrixButtonActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(BasicsContants.PROPFILENAME);
            int result = OpenDialog.showOpenDialog(spm, new PLSPBinFilter(), this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = OpenDialog.getFilename();
                matrixTextField.setText(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(PersistentPLSPBinaryReaderParamView.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_matrixButtonActionPerformed

    @Override
    public void reset() {
        matrixTextField.setText(comp.getFilename());
    }

    @Override
    public void finished() throws IOException {
        if (matrixTextField.getText().trim().length() > 0) {
            comp.setFilename(matrixTextField.getText());
        } else {
            throw new IOException("A Persistent P-LSP file name must be provided.");
        }
    }

    private PersistentPLSPBinaryReaderComp comp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton matrixButton;
    private javax.swing.JLabel matrixLabel;
    private javax.swing.JTextField matrixTextField;
    // End of variables declaration//GEN-END:variables
}
