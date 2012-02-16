/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LandmarksISOMAPProjectionParamView.java
 *
 * Created on 14/06/2010, 10:11:29
 */
package projection.technique.lisomap;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando
 */
public class LandmarksISOMAPProjectionParamView extends AbstractParametersView {

    /** Creates new form LandmarksISOMAPProjectionParamView */
    public LandmarksISOMAPProjectionParamView(LandmarksISOMAPProjectionComp comp) {
        initComponents();

        this.comp = comp;

        for (DissimilarityType disstype : DissimilarityType.values()) {
            this.dissimilarityComboBox.addItem(disstype);
        }

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

        parametersPanel = new javax.swing.JPanel();
        nrNeighborsLabel = new javax.swing.JLabel();
        nrNeighborsTextField = new javax.swing.JTextField();
        dissimilarityPanel = new javax.swing.JPanel();
        dissimilarityComboBox = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Landmarks ISOMAP Parameters"));
        setLayout(new java.awt.GridBagLayout());

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        nrNeighborsLabel.setText("Number of Neighbors");
        parametersPanel.add(nrNeighborsLabel);

        nrNeighborsTextField.setColumns(10);
        parametersPanel.add(nrNeighborsTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(parametersPanel, gridBagConstraints);

        dissimilarityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dissimilarity"));
        dissimilarityPanel.setLayout(new java.awt.BorderLayout());
        dissimilarityPanel.add(dissimilarityComboBox, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(dissimilarityPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void reset() {
        nrNeighborsTextField.setText(Integer.toString(comp.getNumberNeighbors()));
        dissimilarityComboBox.setSelectedItem(comp.getDissimilarityType());
    }

    @Override
    public void finished() throws IOException {
        if (nrNeighborsTextField.getText().trim().length() > 0) {
            int nneighbors = Integer.parseInt(nrNeighborsTextField.getText());
            if (nneighbors > 0) {
                comp.setNumberNeighbors(nneighbors);
            } else {
                throw new IOException("The number of neighbors should be positive.");
            }
        } else {
            throw new IOException("The number of neighbors should be provided.");
        }

        comp.setDissimilarityType((DissimilarityType) dissimilarityComboBox.getSelectedItem());
    }

    private LandmarksISOMAPProjectionComp comp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox dissimilarityComboBox;
    private javax.swing.JPanel dissimilarityPanel;
    private javax.swing.JLabel nrNeighborsLabel;
    private javax.swing.JTextField nrNeighborsTextField;
    private javax.swing.JPanel parametersPanel;
    // End of variables declaration//GEN-END:variables
}
