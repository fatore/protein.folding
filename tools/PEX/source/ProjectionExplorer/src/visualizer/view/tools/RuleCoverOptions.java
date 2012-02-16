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
 * Contributor(s): Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

/*
 * RuleCoverOptions.java
 *
 * Created on 22 de Dezembro de 2007, 21:16
 */
package visualizer.view.tools;

/**
 *
 * @author  user
 */
public class RuleCoverOptions extends javax.swing.JDialog {

    /** Creates new form RuleCoverOptions */
    public RuleCoverOptions(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        resizeRadioButton = new javax.swing.JRadioButton();
        moveRadioButton = new javax.swing.JRadioButton();
        clusterRadioButton = new javax.swing.JRadioButton();
        topicRadioButton = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        minSpinner = new javax.swing.JSpinner();
        maxSpinner = new javax.swing.JSpinner();
        incSpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sliceMinSpinner = new javax.swing.JSpinner();
        sliceMaxSpinner = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        moveSliceSpinner = new javax.swing.JSpinner();
        subSlicesSpinner = new javax.swing.JSpinner();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cover Strategy"));

        resizeRadioButton.setText("Resize");
        resizeRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        resizeRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(resizeRadioButton);

        moveRadioButton.setText("Move");
        moveRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        moveRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        moveRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveRadioButtonActionPerformed(evt);
            }
        });
        jPanel1.add(moveRadioButton);

        clusterRadioButton.setText("Cluster");
        clusterRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        clusterRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(clusterRadioButton);

        topicRadioButton.setText("Class (\"TOPIC\" scalar)");
        topicRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        topicRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(topicRadioButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 12;
        mainPanel.add(jPanel1, gridBagConstraints);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cluster Options"));
        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        jLabel1.setText("Minimum");
        jPanel2.add(jLabel1);

        jLabel2.setText("Maximum");
        jPanel2.add(jLabel2);

        jLabel3.setText("Increment");
        jPanel2.add(jLabel3);

        minSpinner.setToolTipText("Minimum");
        jPanel2.add(minSpinner);

        maxSpinner.setToolTipText("Maximun");
        jPanel2.add(maxSpinner);

        incSpinner.setToolTipText("Increment");
        jPanel2.add(incSpinner);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jPanel2, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resize Options"));
        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jLabel4.setText("Minimum");
        jPanel3.add(jLabel4);

        jLabel5.setText("Maximum");
        jPanel3.add(jLabel5);

        sliceMinSpinner.setToolTipText("Initial");
        jPanel3.add(sliceMinSpinner);

        sliceMaxSpinner.setToolTipText("Final");
        jPanel3.add(sliceMaxSpinner);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(jPanel3, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Move Options"));
        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        jLabel6.setText("Slices");
        jPanel4.add(jLabel6);

        jLabel7.setText("Move Slices");
        jPanel4.add(jLabel7);

        moveSliceSpinner.setToolTipText("Slices");
        jPanel4.add(moveSliceSpinner);

        subSlicesSpinner.setToolTipText("SubSlices");
        jPanel4.add(subSlicesSpinner);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        mainPanel.add(jPanel4, gridBagConstraints);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        mainPanel.add(okButton, gridBagConstraints);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void moveRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveRadioButtonActionPerformed
    // TODO add your handling code here:
    }//GEN-LAST:event_moveRadioButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    public static RuleCoverOptions getInstance(javax.swing.JFrame parent) {
        if (instance == null || instance.getParent() != parent) {
            instance = new RuleCoverOptions(parent, true);
        }
        return instance;
    }

    public RuleCoverOptions display() {
        this.pack();
        this.setLocationRelativeTo(this.getParent());
        this.setVisible(true);

        return this;
    }

    public StrategyType getStrategy() {
        if (resizeRadioButton.isSelected()) {
            return StrategyType.RESIZE_ST;
        }
        if (moveRadioButton.isSelected()) {
            return StrategyType.MOVE_ST;
        }
        if (topicRadioButton.isSelected()) {
            return StrategyType.TOPIC_ST;
        }

        return StrategyType.CLUSTER_ST;

    }

    private int spinInt(javax.swing.JSpinner spin) {
        return new Double(((javax.swing.JSpinner.DefaultEditor) spin.getEditor()).getTextField().getText()).intValue();
    }

    public int getMin() {
        if (resizeRadioButton.isSelected()) {
            return spinInt(sliceMinSpinner);
        } else {
            return spinInt(minSpinner);
        }
    }

    public int getMax() {
        if (resizeRadioButton.isSelected()) {
            return spinInt(sliceMaxSpinner);
        } else {
            return spinInt(maxSpinner);
        }
    }

    public int getInc() {
        return spinInt(incSpinner);
    }

    public int getSlices() {
        return spinInt(moveSliceSpinner);

    }

    public int getSubSlices() {
        return spinInt(subSlicesSpinner);
    }

    public enum StrategyType {

        CLUSTER_ST, RESIZE_ST, MOVE_ST, TOPIC_ST
    }

    /**
     * @param args the command line arguments
     */
    public static void main(
            String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                RuleCoverOptions dialog = new RuleCoverOptions(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }

                });
                dialog.setVisible(true);
            }

        });
    }

    private static RuleCoverOptions instance;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton clusterRadioButton;
    private javax.swing.JSpinner incSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSpinner maxSpinner;
    private javax.swing.JSpinner minSpinner;
    private javax.swing.JRadioButton moveRadioButton;
    private javax.swing.JSpinner moveSliceSpinner;
    private javax.swing.JButton okButton;
    private javax.swing.JRadioButton resizeRadioButton;
    private javax.swing.JSpinner sliceMaxSpinner;
    private javax.swing.JSpinner sliceMinSpinner;
    private javax.swing.JSpinner subSlicesSpinner;
    private javax.swing.JRadioButton topicRadioButton;
    // End of variables declaration//GEN-END:variables
}
