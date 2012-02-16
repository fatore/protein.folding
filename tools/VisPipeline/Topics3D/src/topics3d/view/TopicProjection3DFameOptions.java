/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ProjectionFameOptions.java
 *
 * Created on 23/06/2009, 11:12:03
 */
package topics3d.view;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projection3d.model.Projection3DModel;
import visualizationbasics.color.ColorScalePanel;
import projection.model.ProjectionModel;
import visualizationbasics.color.ColorScaleFactory.ColorScaleType;

/**
 *
 * @author jpocom
 */
public class TopicProjection3DFameOptions extends javax.swing.JDialog {

    /** Creates new form ProjectionFameOptions */
    private TopicProjection3DFameOptions(java.awt.Frame parent) {
        super(parent);
        initComponents();

        for (ColorScaleType cst : ColorScaleType.values()) {
            this.scaleComboBox.addItem(cst);
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

        adjustsPanel = new javax.swing.JPanel();
        paremetersPanel = new javax.swing.JPanel();
        backGroundButton = new javax.swing.JButton();
        qualityPanel = new javax.swing.JPanel();
        radiusSlider = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        alphaSelectedSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        alphaNotSelectedSlider = new javax.swing.JSlider();
        jLabel4 = new javax.swing.JLabel();
        radiusTextField = new javax.swing.JTextField();
        complementPanel = new javax.swing.JPanel();
        outlineCheckBox = new javax.swing.JCheckBox();
        axesCheckBox = new javax.swing.JCheckBox();
        colorTableCheckBox = new javax.swing.JCheckBox();
        colorPanel = new javax.swing.JPanel();
        scaleRangePanel = new javax.swing.JPanel();
        minSlider = new javax.swing.JSlider();
        maxSlider = new javax.swing.JSlider();
        coloScalePanel = new ColorScalePanel(null);
        reverseScaleCheckBox = new javax.swing.JCheckBox();
        colorChosePanel = new javax.swing.JPanel();
        scaleComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Projection Options");
        setModal(true);

        adjustsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Adjustments "));
        adjustsPanel.setLayout(new java.awt.GridBagLayout());

        paremetersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Graphics Parameters"));
        paremetersPanel.setLayout(new java.awt.GridBagLayout());

        backGroundButton.setText("BackGround Color");
        backGroundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backGroundButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        paremetersPanel.add(backGroundButton, gridBagConstraints);

        qualityPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sphere Quality"));
        qualityPanel.setLayout(new java.awt.GridBagLayout());

        radiusSlider.setPreferredSize(new java.awt.Dimension(100, 33));
        radiusSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                radiusSliderMouseReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        qualityPanel.add(radiusSlider, gridBagConstraints);

        jLabel2.setText("Radius:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        qualityPanel.add(jLabel2, gridBagConstraints);

        alphaSelectedSlider.setPreferredSize(new java.awt.Dimension(100, 33));
        alphaSelectedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                alphaSelectedSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        qualityPanel.add(alphaSelectedSlider, gridBagConstraints);

        jLabel3.setText("Alpha Selected:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        qualityPanel.add(jLabel3, gridBagConstraints);

        alphaNotSelectedSlider.setPreferredSize(new java.awt.Dimension(100, 33));
        alphaNotSelectedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                alphaNotSelectedSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        qualityPanel.add(alphaNotSelectedSlider, gridBagConstraints);

        jLabel4.setText("Alpha Not Selected:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        qualityPanel.add(jLabel4, gridBagConstraints);

        radiusTextField.setPreferredSize(new java.awt.Dimension(40, 27));
        radiusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radiusTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        qualityPanel.add(radiusTextField, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(qualityPanel, gridBagConstraints);
        qualityPanel.getAccessibleContext().setAccessibleName("Sphere Properties");

        complementPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Complements"));
        complementPanel.setPreferredSize(new java.awt.Dimension(200, 96));
        complementPanel.setLayout(new java.awt.GridBagLayout());

        outlineCheckBox.setText("Outline");
        outlineCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outlineCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        complementPanel.add(outlineCheckBox, gridBagConstraints);

        axesCheckBox.setText("Axes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        complementPanel.add(axesCheckBox, gridBagConstraints);

        colorTableCheckBox.setText("Color Table");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        complementPanel.add(colorTableCheckBox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        paremetersPanel.add(complementPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(paremetersPanel, gridBagConstraints);

        colorPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Color Scale"));
        colorPanel.setLayout(new java.awt.BorderLayout(3, 3));

        scaleRangePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Scale Range"));
        scaleRangePanel.setLayout(new java.awt.GridBagLayout());

        minSlider.setMaximum(50);
        minSlider.setPaintLabels(true);
        minSlider.setPaintTicks(true);
        minSlider.setInverted(true);
        minSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        scaleRangePanel.add(minSlider, gridBagConstraints);

        maxSlider.setMaximum(50);
        maxSlider.setPaintLabels(true);
        maxSlider.setPaintTicks(true);
        maxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                maxSliderStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        scaleRangePanel.add(maxSlider, gridBagConstraints);

        coloScalePanel.setMinimumSize(new java.awt.Dimension(20, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        scaleRangePanel.add(coloScalePanel, gridBagConstraints);

        reverseScaleCheckBox.setText("Reverse Scale");
        reverseScaleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reverseScaleCheckBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        scaleRangePanel.add(reverseScaleCheckBox, gridBagConstraints);

        colorPanel.add(scaleRangePanel, java.awt.BorderLayout.SOUTH);

        colorChosePanel.setLayout(new java.awt.GridBagLayout());

        scaleComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                scaleComboBoxItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        colorChosePanel.add(scaleComboBox, gridBagConstraints);

        jLabel1.setText("Scale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        colorChosePanel.add(jLabel1, gridBagConstraints);

        colorPanel.add(colorChosePanel, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        adjustsPanel.add(colorPanel, gridBagConstraints);

        getContentPane().add(adjustsPanel, java.awt.BorderLayout.CENTER);
        adjustsPanel.getAccessibleContext().setAccessibleName("Adjustments");

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

    private void backGroundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backGroundButtonActionPerformed
        if (viewer != null) {
            java.awt.Color color = javax.swing.JColorChooser.showDialog(this,
                    "Choose the Backgroud Color", java.awt.Color.BLACK);
            if (color != null)
                viewer.setViewerBackground(color);
        }
}//GEN-LAST:event_backGroundButtonActionPerformed

    private void alphaSelectedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_alphaSelectedSliderStateChanged
        if (viewer != null) {
            ProjectionModel model = (ProjectionModel) viewer.getModel();

            if (model != null) {
                javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
                model.setAlpha((float) Math.pow(slider.getValue() / 100.0f, 2));
                model.notifyObservers();
            }
        }
}//GEN-LAST:event_alphaSelectedSliderStateChanged

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.viewer = null;
        this.setVisible(false);
}//GEN-LAST:event_closeButtonActionPerformed

    private void minSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minSliderStateChanged
        if (viewer != null) {
            try {
                if (viewer.getModel() != null) {
                    float min = ((float) minSlider.getValue() / (float) minSlider.getMaximum()) / 2.0f;
                    float max = ((float) maxSlider.getValue() / (float) maxSlider.getMaximum()) / 2.0f;
                    ((ProjectionModel) viewer.getModel()).getColorTable().getColorScale().setMinMax(0.5f - min, max + 0.5f);
                }

                if (viewer.getView() != null) {
                    viewer.getView().colorAs(viewer.getCurrentScalar());
                }

                coloScalePanel.repaint();

            } catch (IOException ex) {
                Logger.getLogger(TopicProjection3DFameOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_minSliderStateChanged

    private void maxSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_maxSliderStateChanged
        if (viewer != null) {
            try {
                if (viewer.getModel() != null) {
                    float min = ((float) minSlider.getValue() / (float) minSlider.getMaximum()) / 2.0f;
                    float max = ((float) maxSlider.getValue() / (float) maxSlider.getMaximum()) / 2.0f;
                    ((ProjectionModel) viewer.getModel()).getColorTable().getColorScale().setMinMax(0.5f - min, max + 0.5f);
                }

                if (viewer.getView() != null) {
                    viewer.getView().colorAs(viewer.getCurrentScalar());
                }

                coloScalePanel.repaint();
            } catch (IOException ex) {
                Logger.getLogger(TopicProjection3DFameOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_maxSliderStateChanged

    private void reverseScaleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reverseScaleCheckBoxActionPerformed
        if (viewer != null) {
            ProjectionModel model = (ProjectionModel) viewer.getModel();

            if (model != null) {
                model.getColorTable().getColorScale().setReverse(reverseScaleCheckBox.isSelected());
            }

            if (viewer.getView() != null) {
                viewer.getView().colorAs(viewer.getCurrentScalar());
            }

            coloScalePanel.repaint();
        }
    }//GEN-LAST:event_reverseScaleCheckBoxActionPerformed

    private void scaleComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_scaleComboBoxItemStateChanged
        if (viewer != null && evt.getStateChange() == ItemEvent.DESELECTED) {
            ColorScaleType type = (ColorScaleType) scaleComboBox.getSelectedItem();

            if (type != null) {
                try {
                    changeColorScale(type);
                } catch (IOException ex) {
                    Logger.getLogger(TopicProjection3DFameOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_scaleComboBoxItemStateChanged

    private void radiusSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radiusSliderMouseReleased
        if (viewer != null) {
            Projection3DModel model = (Projection3DModel) viewer.getModel();

            if (model != null) {
                javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
                model.setSphereRadius(slider.getValue() / 10000.0f);
                model.notifyObservers();
            }
        }
    }//GEN-LAST:event_radiusSliderMouseReleased

    private void outlineCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outlineCheckBoxActionPerformed
        if (viewer != null) {
            viewer.showOutline(outlineCheckBox.isSelected());
        }
    }//GEN-LAST:event_outlineCheckBoxActionPerformed

    private void alphaNotSelectedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_alphaNotSelectedSliderStateChanged
        if (viewer != null) {
            Projection3DModel model = (Projection3DModel) viewer.getModel();

            if (model != null) {
                javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
                model.setAlphaNS((float) Math.pow(slider.getValue() / 100.0f, 2));
                model.notifyObservers();
            }
        }
    }//GEN-LAST:event_alphaNotSelectedSliderStateChanged

    private void radiusTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radiusTextFieldActionPerformed
        if (viewer != null) {
            Projection3DModel model = (Projection3DModel) viewer.getModel();

            if (model != null) {
                float radius = Float.parseFloat(radiusTextField.getText());
                model.setSphereRadius(radius);
                model.notifyObservers();
            }
        }
    }//GEN-LAST:event_radiusTextFieldActionPerformed

    public static TopicProjection3DFameOptions getInstance(javax.swing.JFrame parent) {
        return new TopicProjection3DFameOptions(parent);
    }

    public void display(TopicProjection3DFrame viewer) {
        if (viewer != null) {

            Projection3DModel model = (Projection3DModel) viewer.getModel();

            if (model != null) {
                radiusSlider.setValue((int) (model.getSphereRadius() * 10000));
                radiusTextField.setText(""+model.getSphereRadius());
                alphaSelectedSlider.setValue((int) (Math.sqrt(model.getAlpha()) * 100));
                alphaNotSelectedSlider.setValue((int) (Math.sqrt(model.getAlphaNS()) * 100));
                outlineCheckBox.setSelected(viewer.getView().getOutlineActor().GetVisibility()==1?true:false );

                scaleComboBox.setSelectedItem(model.getColorTable().getColorScaleType());
                ((ColorScalePanel) coloScalePanel).setColorTable(model.getColorTable());
                reverseScaleCheckBox.setSelected(model.getColorTable().getColorScale().isReverse());
                maxSlider.setValue((int) (model.getColorTable().getColorScale().getMax() * maxSlider.getMaximum()));
                minSlider.setValue((int) ((1.0f - model.getColorTable().getColorScale().getMin() * 2) * minSlider.getMaximum()));
            }
        }

        this.viewer = viewer;
        pack();
        setLocationRelativeTo(this.getParent());
        setVisible(true);
    }

    private void changeColorScale(ColorScaleType scaletype) throws IOException {
        if (viewer != null && viewer.getModel() != null) {
            ProjectionModel model = (ProjectionModel) viewer.getModel();

            model.getColorTable().setColorScaleType(scaletype);
            float min = ((float) minSlider.getValue() / (float) minSlider.getMaximum()) / 2.0f;
            float max = ((float) maxSlider.getValue() / (float) maxSlider.getMaximum()) / 2.0f;
            model.getColorTable().getColorScale().setMinMax(0.5f - min, max + 0.5f);
            model.getColorTable().getColorScale().setReverse(reverseScaleCheckBox.isSelected());

            if (viewer != null && viewer.getView() != null) {
                viewer.getView().colorAs(viewer.getCurrentScalar());
            }

            coloScalePanel.repaint();
        }

    }

    private TopicProjection3DFrame viewer;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel adjustsPanel;
    private javax.swing.JSlider alphaNotSelectedSlider;
    private javax.swing.JSlider alphaSelectedSlider;
    private javax.swing.JCheckBox axesCheckBox;
    private javax.swing.JButton backGroundButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel coloScalePanel;
    private javax.swing.JPanel colorChosePanel;
    private javax.swing.JPanel colorPanel;
    private javax.swing.JCheckBox colorTableCheckBox;
    private javax.swing.JPanel complementPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSlider maxSlider;
    private javax.swing.JSlider minSlider;
    private javax.swing.JCheckBox outlineCheckBox;
    private javax.swing.JPanel paremetersPanel;
    private javax.swing.JPanel qualityPanel;
    private javax.swing.JSlider radiusSlider;
    private javax.swing.JTextField radiusTextField;
    private javax.swing.JCheckBox reverseScaleCheckBox;
    private javax.swing.JComboBox scaleComboBox;
    private javax.swing.JPanel scaleRangePanel;
    // End of variables declaration//GEN-END:variables
}
