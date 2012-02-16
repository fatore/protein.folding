/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PreprocessorParamView.java
 *
 * Created on 27/05/2009, 09:58:31
 */
package textprocessing.processing;

import textprocessing.processing.view.LuhnCutAnalizer;
import java.io.IOException;
import textprocessing.processing.stemmer.StemmerFactory.StemmerType;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class PreprocessorParamView extends AbstractParametersView {

    /** Creates new form PreprocessorParamView */
    public PreprocessorParamView(PreprocessorComp comp) {
        initComponents();

        this.comp = comp;

        for (StemmerType st : StemmerType.values()) {
            this.stemmerComboBox.addItem(st);
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

        wordsButtonGroup = new javax.swing.ButtonGroup();
        preProcessingPanel = new javax.swing.JPanel();
        luhnLabel = new javax.swing.JLabel();
        gramsComboBox = new javax.swing.JComboBox();
        gramsLabel = new javax.swing.JLabel();
        luhnLowerTextField = new javax.swing.JTextField();
        analyzeButton = new javax.swing.JButton();
        luhnUpperLabel = new javax.swing.JLabel();
        luhnUpperTextField = new javax.swing.JTextField();
        stemmerComboBox = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Corpus Pre-processing"));
        setLayout(new java.awt.GridBagLayout());

        preProcessingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));
        preProcessingPanel.setLayout(new java.awt.GridBagLayout());

        luhnLabel.setText("Luhn's lower cut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnLabel, gridBagConstraints);

        gramsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));
        gramsComboBox.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(gramsComboBox, gridBagConstraints);

        gramsLabel.setText("Number of grams");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(gramsLabel, gridBagConstraints);

        luhnLowerTextField.setColumns(5);
        luhnLowerTextField.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnLowerTextField, gridBagConstraints);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(analyzeButton, gridBagConstraints);

        luhnUpperLabel.setText("Luhn's upper cut");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnUpperLabel, gridBagConstraints);

        luhnUpperTextField.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(luhnUpperTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        preProcessingPanel.add(stemmerComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(preProcessingPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        if (comp.getCorpus() != null) {
            LuhnCutAnalizer analizer = LuhnCutAnalizer.getInstance(null);
            analizer.display(comp.getCorpus(), (StemmerType) stemmerComboBox.getSelectedItem(),
                    gramsComboBox.getSelectedIndex() + 1);

            luhnLowerTextField.setText(Integer.toString(analizer.getLowerCut()));
            luhnUpperTextField.setText(Integer.toString(analizer.getUpperCut()));
        }
}//GEN-LAST:event_analyzeButtonActionPerformed

    @Override
    public void reset() {
        stemmerComboBox.setSelectedItem(comp.getStemmer());
        luhnLowerTextField.setText(Integer.toString(comp.getLowerCut()));

        if (comp.getUpperCut() > -1) {
            luhnUpperTextField.setText(Integer.toString(comp.getUpperCut()));
        } else {
            luhnUpperTextField.setText("");
        }

        gramsComboBox.setSelectedIndex(comp.getNumberGrams() - 1);
    }

    @Override
    public void finished() throws IOException {
        comp.setStemmer((StemmerType) stemmerComboBox.getSelectedItem());

        if (luhnLowerTextField.getText().trim().length() > 0) {
            if (Integer.parseInt(luhnLowerTextField.getText()) > -1) {
                comp.setLowerCut(Integer.parseInt(luhnLowerTextField.getText()));
            } else {
                throw new IOException("The Luhn's lower cut should be positive.");
            }
        } else {
            comp.setLowerCut(1);
        }

        if (luhnUpperTextField.getText().trim().length() > 0) {
            if (Integer.parseInt(luhnUpperTextField.getText()) > -1) {
                comp.setUpperCut(Integer.parseInt(luhnUpperTextField.getText()));
            } else {
                throw new IOException("The Luhn's upper cut should be positive.");
            }
        } else {
            comp.setUpperCut(-1);
        }
    }

    private PreprocessorComp comp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JComboBox gramsComboBox;
    private javax.swing.JLabel gramsLabel;
    private javax.swing.JLabel luhnLabel;
    private javax.swing.JTextField luhnLowerTextField;
    private javax.swing.JLabel luhnUpperLabel;
    private javax.swing.JTextField luhnUpperTextField;
    private javax.swing.JPanel preProcessingPanel;
    private javax.swing.JComboBox stemmerComboBox;
    private javax.swing.ButtonGroup wordsButtonGroup;
    // End of variables declaration//GEN-END:variables
}
