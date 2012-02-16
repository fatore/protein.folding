/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): 
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package textprocessing.processing.view;

import textprocessing.processing.*;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import textprocessing.corpus.Corpus;
import textprocessing.processing.stemmer.StemmerFactory.StemmerType;

/**
 *
 * @author  Fernando Vieira Paulovich
 */
public class LuhnCutAnalizer extends javax.swing.JDialog {

    /** Creates new form LuhnCutAnalizer
     * @param parent 
     */
    private LuhnCutAnalizer(JFrame parent) {
        super(parent);
        initModels();
        initComponents();

        this.setPreferredSize(new Dimension(700, 550));
        this.setSize(700, 550);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        luhnPanel = new javax.swing.JPanel();
        zipfPanel = new javax.swing.JPanel();
        zipfCurvePanel = new ZipfCurve();
        cutConfigurationPanel = new javax.swing.JPanel();
        upperCutPanel = new javax.swing.JPanel();
        upperCutSlider = new javax.swing.JSlider();
        upperCutLabel = new javax.swing.JLabel();
        upperCutButtonPanel = new javax.swing.JPanel();
        upperCutTextField = new javax.swing.JTextField();
        upperCutPlusButton = new javax.swing.JButton();
        upperCutMinusButton = new javax.swing.JButton();
        lowerCutPanel = new javax.swing.JPanel();
        lowerCutSlider = new javax.swing.JSlider();
        lowerCutButtonPanel = new javax.swing.JPanel();
        lowerCutTextField = new javax.swing.JTextField();
        lowerCutPlusButton = new javax.swing.JButton();
        loerCutMinusButton = new javax.swing.JButton();
        lowerCutLabel = new javax.swing.JLabel();
        ngramsPanel = new javax.swing.JPanel();
        numberGramsPanel = new javax.swing.JPanel();
        ngramsLabel = new javax.swing.JLabel();
        ngramsTextField = new javax.swing.JTextField();
        ngramsScrollPane = new javax.swing.JScrollPane();
        ngramsTable = new javax.swing.JTable();
        changeStopwordsButton = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        analyzeButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Luhn's cut-off Analysis");
        setModal(true);

        luhnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        luhnPanel.setLayout(new java.awt.BorderLayout());

        zipfPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Zipf's Curve"));
        zipfPanel.setLayout(new java.awt.BorderLayout());
        zipfPanel.add(zipfCurvePanel, java.awt.BorderLayout.CENTER);

        luhnPanel.add(zipfPanel, java.awt.BorderLayout.CENTER);

        cutConfigurationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Cut-off Configuration"));
        cutConfigurationPanel.setLayout(new java.awt.BorderLayout());

        upperCutPanel.setLayout(new java.awt.BorderLayout());

        upperCutSlider.setMajorTickSpacing(1);
        upperCutSlider.setSnapToTicks(true);
        upperCutSlider.setValue(0);
        upperCutSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                upperCutSliderStateChanged(evt);
            }
        });
        upperCutPanel.add(upperCutSlider, java.awt.BorderLayout.CENTER);

        upperCutLabel.setText("Upper Cut");
        upperCutPanel.add(upperCutLabel, java.awt.BorderLayout.WEST);

        upperCutButtonPanel.setLayout(new java.awt.GridBagLayout());

        upperCutTextField.setColumns(5);
        upperCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        upperCutButtonPanel.add(upperCutTextField, gridBagConstraints);

        upperCutPlusButton.setText("+");
        upperCutPlusButton.setPreferredSize(new java.awt.Dimension(43, 15));
        upperCutPlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutPlusButton, gridBagConstraints);

        upperCutMinusButton.setText("-");
        upperCutMinusButton.setPreferredSize(new java.awt.Dimension(39, 15));
        upperCutMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upperCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        upperCutButtonPanel.add(upperCutMinusButton, gridBagConstraints);

        upperCutPanel.add(upperCutButtonPanel, java.awt.BorderLayout.EAST);

        cutConfigurationPanel.add(upperCutPanel, java.awt.BorderLayout.SOUTH);

        lowerCutPanel.setLayout(new java.awt.BorderLayout());

        lowerCutSlider.setMajorTickSpacing(1);
        lowerCutSlider.setSnapToTicks(true);
        lowerCutSlider.setValue(0);
        lowerCutSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                lowerCutSliderStateChanged(evt);
            }
        });
        lowerCutPanel.add(lowerCutSlider, java.awt.BorderLayout.CENTER);

        lowerCutButtonPanel.setLayout(new java.awt.GridBagLayout());

        lowerCutTextField.setColumns(5);
        lowerCutTextField.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        lowerCutButtonPanel.add(lowerCutTextField, gridBagConstraints);

        lowerCutPlusButton.setText("+");
        lowerCutPlusButton.setPreferredSize(new java.awt.Dimension(43, 15));
        lowerCutPlusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowerCutPlusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        lowerCutButtonPanel.add(lowerCutPlusButton, gridBagConstraints);

        loerCutMinusButton.setText("-");
        loerCutMinusButton.setPreferredSize(new java.awt.Dimension(39, 15));
        loerCutMinusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loerCutMinusButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        lowerCutButtonPanel.add(loerCutMinusButton, gridBagConstraints);

        lowerCutPanel.add(lowerCutButtonPanel, java.awt.BorderLayout.EAST);

        lowerCutLabel.setText("Lower Cut");
        lowerCutPanel.add(lowerCutLabel, java.awt.BorderLayout.WEST);

        cutConfigurationPanel.add(lowerCutPanel, java.awt.BorderLayout.NORTH);

        luhnPanel.add(cutConfigurationPanel, java.awt.BorderLayout.SOUTH);

        ngramsPanel.setLayout(new java.awt.GridBagLayout());

        ngramsLabel.setText("Number ngrams");
        numberGramsPanel.add(ngramsLabel);

        ngramsTextField.setColumns(5);
        ngramsTextField.setEditable(false);
        numberGramsPanel.add(ngramsTextField);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        ngramsPanel.add(numberGramsPanel, gridBagConstraints);

        ngramsScrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Ngrams and Frequency"));
        ngramsScrollPane.setPreferredSize(new java.awt.Dimension(230, 275));

        ngramsTable.setModel(this.tableModel);
        ngramsTable.setEnabled(false);
        ngramsScrollPane.setViewportView(ngramsTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        ngramsPanel.add(ngramsScrollPane, gridBagConstraints);

        changeStopwordsButton.setText("Change Stopwords");
        changeStopwordsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeStopwordsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        ngramsPanel.add(changeStopwordsButton, gridBagConstraints);

        luhnPanel.add(ngramsPanel, java.awt.BorderLayout.EAST);

        getContentPane().add(luhnPanel, java.awt.BorderLayout.CENTER);

        analyzeButton.setText("Analyze");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(analyzeButton);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void upperCutMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperCutMinusButtonActionPerformed
        int upperCut = this.upperCutSlider.getValue();
        this.upperCutSlider.setValue(upperCut + 1);
    }//GEN-LAST:event_upperCutMinusButtonActionPerformed

    private void upperCutPlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upperCutPlusButtonActionPerformed
        int upperCut = this.upperCutSlider.getValue();
        this.upperCutSlider.setValue(upperCut - 1);
    }//GEN-LAST:event_upperCutPlusButtonActionPerformed

    private void loerCutMinusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loerCutMinusButtonActionPerformed
        int lowerCut = this.lowerCutSlider.getValue();
        this.lowerCutSlider.setValue(lowerCut + 1);
    }//GEN-LAST:event_loerCutMinusButtonActionPerformed

    private void lowerCutPlusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowerCutPlusButtonActionPerformed
        int lowerCut = this.lowerCutSlider.getValue();
        this.lowerCutSlider.setValue(lowerCut - 1);
    }//GEN-LAST:event_lowerCutPlusButtonActionPerformed

    private void changeStopwordsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeStopwordsButtonActionPerformed
        StopwordsManager.getInstance(this).display();
        analyze();
    }//GEN-LAST:event_changeStopwordsButtonActionPerformed

    private void lowerCutSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_lowerCutSliderStateChanged
        int upperCut = this.upperCutSlider.getValue();
        int lowerCut = this.lowerCutSlider.getValue();

        int[] freqs = ((ZipfCurve) this.zipfCurvePanel).setCutLines(lowerCut, upperCut);
        this.lowerCutTextField.setText(Integer.toString(freqs[0]));
        this.upperCutTextField.setText(Integer.toString(freqs[1]));
        this.ngramsTable.setRowSelectionInterval(upperCut, lowerCut);
        this.ngramsTextField.setText(Integer.toString(lowerCut - upperCut + 1));

        if (this.lowerCutSlider.getValue() < this.upperCutSlider.getValue()) {
            this.upperCutSlider.setValue(this.lowerCutSlider.getValue());
        }
    }//GEN-LAST:event_lowerCutSliderStateChanged

    private void upperCutSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_upperCutSliderStateChanged
        int upperCut = this.upperCutSlider.getValue();
        int lowerCut = this.lowerCutSlider.getValue();

        int[] freqs = ((ZipfCurve) this.zipfCurvePanel).setCutLines(lowerCut, upperCut);
        this.lowerCutTextField.setText(Integer.toString(freqs[0]));
        this.upperCutTextField.setText(Integer.toString(freqs[1]));
        this.ngramsTable.setRowSelectionInterval(upperCut, lowerCut);
        this.ngramsTextField.setText(Integer.toString(lowerCut - upperCut + 1));

        if (this.upperCutSlider.getValue() > this.lowerCutSlider.getValue()) {
            this.lowerCutSlider.setValue(this.upperCutSlider.getValue());
        }
    }//GEN-LAST:event_upperCutSliderStateChanged

    private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
        analyze();
    }//GEN-LAST:event_analyzeButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_closeButtonActionPerformed

    public static LuhnCutAnalizer getInstance(JFrame parent) {
        return new LuhnCutAnalizer(parent);
    }

    public void display(Corpus corpus, StemmerType stemmertype, int nrgrams) {
        this.corpus = corpus;
        this.stemmertype = stemmertype;
        this.nrgrams = nrgrams;
        this.upperCutTextField.setText("");
        this.lowerCutTextField.setText("");
        this.ngramsTextField.setText("");

        this.lowerCutSlider.setEnabled(false);
        this.lowerCutSlider.setValue(0);
        this.upperCutSlider.setEnabled(false);
        this.upperCutSlider.setValue(0);

        ((ZipfCurve) this.zipfCurvePanel).setNgrams(null);

        this.initModels();
        this.ngramsTable.setModel(this.tableModel);

        this.analyze();

        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);
    }

    public int getLowerCut() {
        if (this.lowerCutTextField.getText().trim().length() > 0) {
            return Integer.parseInt(this.lowerCutTextField.getText());
        } else {
            return 0;
        }
    }

    public int getUpperCut() {
        if (this.upperCutTextField.getText().trim().length() > 0) {
            return Integer.parseInt(this.upperCutTextField.getText());
        } else {
            return 0;
        }
    }

    protected void countWordsFrequency(HashMap<String, Ngram> corporaNgrams) throws IOException {
        Preprocessor pre = new Preprocessor(corpus);
        ArrayList<Ngram> ngrams = pre.getNgramsAccordingTo(1, -1, nrgrams, stemmertype);

        for (Ngram n : ngrams) {
            corporaNgrams.put(n.ngram, n);
        }
    }

    protected void initModels() {
        String[] titulos = new String[]{"Ngram", "Frequency"};
        this.tableModel = new DefaultTableModel(null, titulos);
    }

    protected void analyze() {
        try {
            HashMap<String, Ngram> corpusngrams = new HashMap<String, Ngram>();
            this.countWordsFrequency(corpusngrams);

            ArrayList<Ngram> ngrams = new ArrayList<Ngram>();
            for (String key : corpusngrams.keySet()) {
                ngrams.add(corpusngrams.get(key));
            }

            //Sorting the ngrams by its frequency in decreasing order
            Collections.sort(ngrams);

            this.initModels();
            this.ngramsTable.setModel(this.tableModel);

            for (int i = 0; i < ngrams.size(); i++) {
                String[] label = new String[2];
                label[0] = ngrams.get(i).ngram;
                label[1] = Integer.toString(ngrams.get(i).frequency);
                this.tableModel.addRow(label);
            }

            ((ZipfCurve) this.zipfCurvePanel).setNgrams(ngrams);

            this.lowerCutSlider.setMaximum(ngrams.size() - 1);
            //this.lowerCutSlider.setMajorTickSpacing(ngrams.size()/100);
            this.lowerCutSlider.setEnabled(true);

            this.upperCutSlider.setMaximum(ngrams.size() - 1);
            //this.upperCutSlider.setMajorTickSpacing(ngrams.size()/100);
            this.upperCutSlider.setEnabled(true);

        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private StemmerType stemmertype;
    private int nrgrams;
    private DefaultTableModel tableModel;
    private Corpus corpus;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton changeStopwordsButton;
    protected javax.swing.JButton closeButton;
    private javax.swing.JPanel cutConfigurationPanel;
    private javax.swing.JButton loerCutMinusButton;
    private javax.swing.JPanel lowerCutButtonPanel;
    private javax.swing.JLabel lowerCutLabel;
    private javax.swing.JPanel lowerCutPanel;
    private javax.swing.JButton lowerCutPlusButton;
    protected javax.swing.JSlider lowerCutSlider;
    protected javax.swing.JTextField lowerCutTextField;
    private javax.swing.JPanel luhnPanel;
    private javax.swing.JLabel ngramsLabel;
    private javax.swing.JPanel ngramsPanel;
    private javax.swing.JScrollPane ngramsScrollPane;
    protected javax.swing.JTable ngramsTable;
    protected javax.swing.JTextField ngramsTextField;
    private javax.swing.JPanel numberGramsPanel;
    private javax.swing.JPanel upperCutButtonPanel;
    private javax.swing.JLabel upperCutLabel;
    private javax.swing.JButton upperCutMinusButton;
    private javax.swing.JPanel upperCutPanel;
    private javax.swing.JButton upperCutPlusButton;
    protected javax.swing.JSlider upperCutSlider;
    protected javax.swing.JTextField upperCutTextField;
    protected javax.swing.JPanel zipfCurvePanel;
    private javax.swing.JPanel zipfPanel;
    // End of variables declaration//GEN-END:variables
}
