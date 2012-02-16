/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DistanceMatrixReaderParamView.java
 *
 * Created on 19/06/2009, 17:09:44
 */
package distance;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.util.BasicsContants;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.filter.DATAFilter;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class DistanceMatrixReaderParamView extends AbstractParametersView {

    /** Creates new form DistanceMatrixReaderParamView */
    public DistanceMatrixReaderParamView(DistanceMatrixReaderComp comp) {
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

        filePanel = new javax.swing.JPanel();
        distmatrixButton = new javax.swing.JButton();
        distmatrixTextField = new javax.swing.JTextField();
        distmatrixLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Distance Matrix Reader"));
        setLayout(new java.awt.GridBagLayout());

        filePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Distance Matrix File"));
        filePanel.setLayout(new java.awt.GridBagLayout());

        distmatrixButton.setText("Search...");
        distmatrixButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distmatrixButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(distmatrixButton, gridBagConstraints);

        distmatrixTextField.setColumns(35);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(distmatrixTextField, gridBagConstraints);

        distmatrixLabel.setText("File name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        filePanel.add(distmatrixLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        add(filePanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void distmatrixButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distmatrixButtonActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(BasicsContants.PROPFILENAME);
            int result = OpenDialog.showOpenDialog(spm, new DATAFilter(), this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = OpenDialog.getFilename();
                distmatrixTextField.setText(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(DistanceMatrixReaderParamView.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_distmatrixButtonActionPerformed

    @Override
    public void reset() {
        distmatrixTextField.setText(comp.getFilename());
    }

    @Override
    public void finished() throws IOException {
        if (distmatrixTextField.getText().trim().length() > 0) {
            comp.setFilename(distmatrixTextField.getText());
        } else {
            throw new IOException("A distance matrix file name must be provided.");
        }
    }

    private DistanceMatrixReaderComp comp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton distmatrixButton;
    private javax.swing.JLabel distmatrixLabel;
    private javax.swing.JTextField distmatrixTextField;
    private javax.swing.JPanel filePanel;
    // End of variables declaration//GEN-END:variables
}
