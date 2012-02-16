/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IDMAPProjectionParamView.java
 *
 * Created on 19/06/2009, 13:55:47
 */
package fsmvis.viscomponents;

import java.io.IOException;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ComponentParamView extends AbstractParametersView {

    /** Creates new form IDMAPProjectionParamView */
    public ComponentParamView(AbstractComponent comp) {
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

        projectorButtonGroup = new javax.swing.ButtonGroup();
        parametersPanel = new javax.swing.JPanel();
        freenessLabel = new javax.swing.JLabel();
        freenessTextField = new javax.swing.JTextField();
        dampingfactorLabel = new javax.swing.JLabel();
        dampingfactorTextField = new javax.swing.JTextField();
        springforceLabel = new javax.swing.JLabel();
        springforceTextField = new javax.swing.JTextField();
        nriterationsLabel = new javax.swing.JLabel();
        nriterationsTextField = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("FSMVIS Parameters"));
        setLayout(new java.awt.GridBagLayout());

        parametersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));
        parametersPanel.setLayout(new java.awt.GridBagLayout());

        freenessLabel.setText("Freeness");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(freenessLabel, gridBagConstraints);

        freenessTextField.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(freenessTextField, gridBagConstraints);

        dampingfactorLabel.setText("Damping factor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(dampingfactorLabel, gridBagConstraints);

        dampingfactorTextField.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(dampingfactorTextField, gridBagConstraints);

        springforceLabel.setText("Spring force");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(springforceLabel, gridBagConstraints);

        springforceTextField.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(springforceTextField, gridBagConstraints);

        nriterationsLabel.setText("Number iterations");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(nriterationsLabel, gridBagConstraints);

        nriterationsTextField.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        parametersPanel.add(nriterationsTextField, gridBagConstraints);

        add(parametersPanel, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void reset() {
        if (comp instanceof HybridComp) {
            comp.setNumberIterations(Math.max((int) Math.sqrt(comp.getNumberInstances()), comp.getNumberIterations()));
        } else {
            comp.setNumberIterations(Math.max(comp.getNumberInstances(), comp.getNumberIterations()));
        }

        freenessTextField.setText(Float.toString(comp.getFreeness()));
        dampingfactorTextField.setText(Float.toString(comp.getDampingFactor()));
        springforceTextField.setText(Float.toString(comp.getSpringForce()));
        nriterationsTextField.setText(Integer.toString(comp.getNumberIterations()));
    }

    @Override
    public void finished() throws IOException {
        if (freenessTextField.getText().trim().length() > 0) {
            float freeness = Float.parseFloat(freenessTextField.getText());
            if (freeness > 0) {
                comp.setFreeness(freeness);
            } else {
                throw new IOException("The freeness should be positive.");
            }
        } else {
            throw new IOException("The freeness should be provided.");
        }

        if (dampingfactorTextField.getText().trim().length() > 0) {
            float dfactor = Float.parseFloat(dampingfactorTextField.getText());
            if (dfactor > 0) {
                comp.setDampingFactor(dfactor);
            } else {
                throw new IOException("The damping factor should be positive.");
            }
        } else {
            throw new IOException("The damping factor should be provided.");
        }

        if (springforceTextField.getText().trim().length() > 0) {
            float sforce = Float.parseFloat(springforceTextField.getText());
            if (sforce > 0) {
                comp.setSpringForce(sforce);
            } else {
                throw new IOException("The spring force should be positive.");
            }
        } else {
            throw new IOException("The spring force should be provided.");
        }

        if (nriterationsTextField.getText().trim().length() > 0) {
            int nit = Integer.parseInt(nriterationsTextField.getText());
            if (nit > 0) {
                comp.setNumberIterations(nit);
            } else {
                throw new IOException("The number of iterations should be positive.");
            }
        } else {
            throw new IOException("The number of iterations should be provided.");
        }
    }

    private AbstractComponent comp;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dampingfactorLabel;
    private javax.swing.JTextField dampingfactorTextField;
    private javax.swing.JLabel freenessLabel;
    private javax.swing.JTextField freenessTextField;
    private javax.swing.JLabel nriterationsLabel;
    private javax.swing.JTextField nriterationsTextField;
    private javax.swing.JPanel parametersPanel;
    private javax.swing.ButtonGroup projectorButtonGroup;
    private javax.swing.JLabel springforceLabel;
    private javax.swing.JTextField springforceTextField;
    // End of variables declaration//GEN-END:variables
}
