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

package tensor.view.tools;

import datamining.clustering.BKmeans;
import datamining.clustering.Clustering;
import datamining.clustering.Kmedoids;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;
import projection.model.Scalar;
import tensor.model.FiberInstance;
import tensor.model.FiberModel;
import tensor.util.TensorConstants;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.filter.DATAandBINMultipleFilter;


/**
 *
 * @author Fernando Vieira Paulovich
 */
public class MultidimensionalClusteringView extends javax.swing.JDialog {

    /** Creates new form MultidimensionalClusteringView */
    private MultidimensionalClusteringView(java.awt.Frame parent) {
        super(parent);
        initComponents();

        for (DissimilarityType disstype : DissimilarityType.values()) {
            this.distanceComboBox.addItem(disstype);
        }

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

        surfaceTypePanel = new javax.swing.JPanel();
        noneRadioButton = new javax.swing.JRadioButton();
        convexHullRadioButton = new javax.swing.JRadioButton();
        densityRadioButton = new javax.swing.JRadioButton();
        clusteringButtonGroup = new javax.swing.ButtonGroup();
        surfaceButtonGroup = new javax.swing.ButtonGroup();
        dataPanel = new javax.swing.JPanel();
        pointsPanel = new javax.swing.JPanel();
        pointsTextField = new javax.swing.JTextField();
        pointsButton = new javax.swing.JButton();
        chooseDistanceTypePanel = new javax.swing.JPanel();
        distanceComboBox = new javax.swing.JComboBox();
        clusteringPanel = new javax.swing.JPanel();
        clusteringTypePanel = new javax.swing.JPanel();
        kmedoidsRadioButton = new javax.swing.JRadioButton();
        bkmeansRadioButton = new javax.swing.JRadioButton();
        nrclustersLabel = new javax.swing.JLabel();
        nrclustersTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        generateButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        surfaceTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Surface Type"));
        surfaceTypePanel.setLayout(new java.awt.GridBagLayout());

        surfaceButtonGroup.add(noneRadioButton);
        noneRadioButton.setSelected(true);
        noneRadioButton.setText("None");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        surfaceTypePanel.add(noneRadioButton, gridBagConstraints);

        surfaceButtonGroup.add(convexHullRadioButton);
        convexHullRadioButton.setText("Convex Hull");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        surfaceTypePanel.add(convexHullRadioButton, gridBagConstraints);

        surfaceButtonGroup.add(densityRadioButton);
        densityRadioButton.setText("Density");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        surfaceTypePanel.add(densityRadioButton, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Clustering the Multidimensional Data");
        setModal(true);

        dataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        dataPanel.setLayout(new java.awt.GridBagLayout());

        pointsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Points File"));
        pointsPanel.setLayout(new java.awt.GridBagLayout());

        pointsTextField.setColumns(35);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        pointsPanel.add(pointsTextField, gridBagConstraints);

        pointsButton.setText("Search...");
        pointsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pointsButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        pointsPanel.add(pointsButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        dataPanel.add(pointsPanel, gridBagConstraints);

        chooseDistanceTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose the Distance Type"));
        chooseDistanceTypePanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 3);
        chooseDistanceTypePanel.add(distanceComboBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        dataPanel.add(chooseDistanceTypePanel, gridBagConstraints);

        clusteringPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Clustering Parameters"));
        clusteringPanel.setLayout(new java.awt.GridBagLayout());

        clusteringTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Clustering Type"));
        clusteringTypePanel.setLayout(new java.awt.GridBagLayout());

        clusteringButtonGroup.add(kmedoidsRadioButton);
        kmedoidsRadioButton.setText("K-medoids");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        clusteringTypePanel.add(kmedoidsRadioButton, gridBagConstraints);

        clusteringButtonGroup.add(bkmeansRadioButton);
        bkmeansRadioButton.setSelected(true);
        bkmeansRadioButton.setText("Bisecting K-means");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        clusteringTypePanel.add(bkmeansRadioButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        clusteringPanel.add(clusteringTypePanel, gridBagConstraints);

        nrclustersLabel.setText("Number Clusters");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 13, 3);
        clusteringPanel.add(nrclustersLabel, gridBagConstraints);

        nrclustersTextField.setColumns(5);
        nrclustersTextField.setText("5");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 13, 3);
        clusteringPanel.add(nrclustersTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        dataPanel.add(clusteringPanel, gridBagConstraints);

        getContentPane().add(dataPanel, java.awt.BorderLayout.CENTER);

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(generateButton);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(closeButton);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void pointsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pointsButtonActionPerformed
    try {
        PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
        int result = OpenDialog.showOpenDialog(spm, new DATAandBINMultipleFilter(), this);

        if (result == JFileChooser.APPROVE_OPTION) {
            String filename = OpenDialog.getFilename();
            pointsTextField.setText(filename);
        }
    } catch (IOException ex) {
        Logger.getLogger(MultidimensionalClusteringView.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_pointsButtonActionPerformed

private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
    try {
        AbstractMatrix matrix = MatrixFactory.getInstance(pointsTextField.getText());
        DissimilarityType mtype = (DissimilarityType) distanceComboBox.getSelectedItem();
        AbstractDissimilarity diss = DissimilarityFactory.getInstance(mtype);

        Clustering clustering = null;
        if (kmedoidsRadioButton.isSelected()) {
            scalar = model.addScalar(TensorConstants.KMEDOIDS + nrclustersTextField.getText());
            clustering = new Kmedoids(Integer.parseInt(nrclustersTextField.getText()));
        } else if (bkmeansRadioButton.isSelected()) {
            scalar = model.addScalar(TensorConstants.BKMEANS + nrclustersTextField.getText());
            clustering = new BKmeans(Integer.parseInt(nrclustersTextField.getText()));
        }

        ArrayList<ArrayList<Integer>> clusters = clustering.execute(diss, matrix);

        //creating an index to accelerate the scalar assingment
        HashMap<Integer, Integer> cindex = new HashMap<Integer, Integer>();
        for (int i = 0; i < clusters.size(); i++) {
            for (int j = 0; j < clusters.get(i).size(); j++) {
                cindex.put(matrix.getRow(clusters.get(i).get(j)).getId(), i);
            }
        }

        for (AbstractInstance p : model.getInstances()) {
            FiberInstance pi = (FiberInstance)p;
            if (cindex.containsKey(pi.getId())) {
                pi.setScalarValue(scalar, cindex.get(pi.getId()));
            } else {
                pi.setScalarValue(scalar, 0);

                Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Probably the points file is not the one used to create the projection!");
            }
        }
        
        this.setVisible(false);
    } catch (IOException ex) {
        Logger.getLogger(MultidimensionalClusteringView.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_generateButtonActionPerformed

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    this.setVisible(false);
}//GEN-LAST:event_closeButtonActionPerformed

    public static MultidimensionalClusteringView getInstance(javax.swing.JFrame parent) {
        return new MultidimensionalClusteringView(parent);
    }

    public Scalar display(FiberModel model) {
        this.model = model;
        this.scalar = null;

        pack();
        setLocationRelativeTo(getParent());
        setVisible(true);

        return scalar;
    }

    private Scalar scalar;
    private FiberModel model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bkmeansRadioButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JPanel chooseDistanceTypePanel;
    private javax.swing.JButton closeButton;
    private javax.swing.ButtonGroup clusteringButtonGroup;
    private javax.swing.JPanel clusteringPanel;
    private javax.swing.JPanel clusteringTypePanel;
    private javax.swing.JRadioButton convexHullRadioButton;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JRadioButton densityRadioButton;
    private javax.swing.JComboBox distanceComboBox;
    private javax.swing.JButton generateButton;
    private javax.swing.JRadioButton kmedoidsRadioButton;
    private javax.swing.JRadioButton noneRadioButton;
    private javax.swing.JLabel nrclustersLabel;
    private javax.swing.JTextField nrclustersTextField;
    private javax.swing.JButton pointsButton;
    private javax.swing.JPanel pointsPanel;
    private javax.swing.JTextField pointsTextField;
    private javax.swing.ButtonGroup surfaceButtonGroup;
    private javax.swing.JPanel surfaceTypePanel;
    // End of variables declaration//GEN-END:variables
}
