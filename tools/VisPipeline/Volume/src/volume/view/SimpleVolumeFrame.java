/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * VolumeFrame.java
 *
 * Created on 11/08/2009, 14:43:33
 */
package volume.view;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import visualizationbasics.coordination.AbstractCoordinator;
import visualizationbasics.coordination.IdentityCoordinator;
import visualizationbasics.model.AbstractModel;
import visualizationbasics.util.BasicsContants;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.SaveDialog;
import visualizationbasics.util.filter.BINFilter;
import visualizationbasics.util.filter.PNGFilter;
import visualizationbasics.util.filter.VTKFilter;
import visualizationbasics.view.ModelViewer;
import volume.model.VolumeModel;
import vtk.*;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class SimpleVolumeFrame extends ModelViewer {

    static {
        System.loadLibrary("vtkCommon");
        System.loadLibrary("vtkFiltering");
        System.loadLibrary("vtkGraphics");
        System.loadLibrary("vtkImaging");
        System.loadLibrary("vtksys");
        System.loadLibrary("vtkexpat");
        System.loadLibrary("vtkfreetype");
        System.loadLibrary("vtkftgl");
        System.loadLibrary("vtkjpeg");
        System.loadLibrary("vtkzlib");
        System.loadLibrary("vtktiff");
        System.loadLibrary("vtkpng");
        System.loadLibrary("vtkDICOMParser");
        System.loadLibrary("vtkIO");
        System.loadLibrary("vtkRendering");

        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
    }

    /** Creates new form VolumeFrame */
    public SimpleVolumeFrame() {
        initComponents();
        this.surfaceModel = (DefaultTableModel) this.surfacesTable.getModel();
        //this.volumePanel.add(this.vPanel);
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

        surfaceTabbedPane = new javax.swing.JTabbedPane();
        sufaceFittingPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        isovalueLabel = new javax.swing.JLabel();
        isoValueText = new javax.swing.JTextField();
        smoothText = new javax.swing.JTextField();
        smoothLabel = new javax.swing.JLabel();
        surfaceFittingButton = new javax.swing.JButton();
        storeButton = new javax.swing.JButton();
        saveImageButton = new javax.swing.JButton();
        surfacePanel = new javax.swing.JPanel();
        surfaceManipulationPanel = new javax.swing.JPanel();
        storeControlPanel = new javax.swing.JPanel();
        viewStoredButton = new javax.swing.JButton();
        scalarTextField = new javax.swing.JTextField();
        surfacesScrollPane = new javax.swing.JScrollPane();
        surfacesTable = new javax.swing.JTable();
        applyScalarButton = new javax.swing.JButton();
        scalarLabel = new javax.swing.JLabel();
        saveSurfaceButton = new javax.swing.JButton();
        storedPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Volume View Frame");
        setMinimumSize(new java.awt.Dimension(500, 500));

        sufaceFittingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        sufaceFittingPanel.setMinimumSize(new java.awt.Dimension(400, 200));
        sufaceFittingPanel.setPreferredSize(new java.awt.Dimension(598, 50));
        sufaceFittingPanel.setLayout(new java.awt.BorderLayout());

        controlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        controlPanel.setPreferredSize(new java.awt.Dimension(200, 100));
        controlPanel.setLayout(new java.awt.GridBagLayout());

        isovalueLabel.setText("Iso-Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(isovalueLabel, gridBagConstraints);

        isoValueText.setColumns(3);
        isoValueText.setText("255");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(isoValueText, gridBagConstraints);

        smoothText.setColumns(3);
        smoothText.setText("100");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(smoothText, gridBagConstraints);

        smoothLabel.setText("Smooth: # Interactions");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(smoothLabel, gridBagConstraints);

        surfaceFittingButton.setText("Surface Fitting");
        surfaceFittingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surfaceFittingButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(surfaceFittingButton, gridBagConstraints);

        storeButton.setText("Store Surface");
        storeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                storeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(storeButton, gridBagConstraints);

        saveImageButton.setText("Save Image");
        saveImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveImageButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        controlPanel.add(saveImageButton, gridBagConstraints);

        sufaceFittingPanel.add(controlPanel, java.awt.BorderLayout.PAGE_START);

        surfacePanel.setBackground(new java.awt.Color(255, 255, 255));
        surfacePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        surfacePanel.setMinimumSize(new java.awt.Dimension(400, 400));
        surfacePanel.setPreferredSize(new java.awt.Dimension(4, 300));
        surfacePanel.setLayout(new java.awt.BorderLayout());
        sufaceFittingPanel.add(surfacePanel, java.awt.BorderLayout.CENTER);

        surfaceTabbedPane.addTab("Surface", sufaceFittingPanel);

        surfaceManipulationPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        surfaceManipulationPanel.setMinimumSize(new java.awt.Dimension(400, 200));
        surfaceManipulationPanel.setPreferredSize(new java.awt.Dimension(598, 50));
        surfaceManipulationPanel.setLayout(new java.awt.BorderLayout());

        storeControlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        storeControlPanel.setLayout(new java.awt.GridBagLayout());

        viewStoredButton.setText("View Selected");
        viewStoredButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewStoredButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(viewStoredButton, gridBagConstraints);

        scalarTextField.setColumns(3);
        scalarTextField.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(scalarTextField, gridBagConstraints);

        surfacesScrollPane.setPreferredSize(new java.awt.Dimension(400, 200));

        surfacesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Surface", "Alpha", "R", "G", "B"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        surfacesScrollPane.setViewportView(surfacesTable);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(surfacesScrollPane, gridBagConstraints);

        applyScalarButton.setText("Apply Scalar");
        applyScalarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyScalarButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(applyScalarButton, gridBagConstraints);

        scalarLabel.setText("Scalar Index");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(scalarLabel, gridBagConstraints);

        saveSurfaceButton.setText("Save Selected");
        saveSurfaceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSurfaceButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        storeControlPanel.add(saveSurfaceButton, gridBagConstraints);

        surfaceManipulationPanel.add(storeControlPanel, java.awt.BorderLayout.NORTH);

        storedPanel.setBackground(new java.awt.Color(255, 255, 255));
        storedPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        storedPanel.setMinimumSize(new java.awt.Dimension(400, 400));
        storedPanel.setPreferredSize(new java.awt.Dimension(4, 300));
        storedPanel.setLayout(new java.awt.BorderLayout());
        surfaceManipulationPanel.add(storedPanel, java.awt.BorderLayout.CENTER);

        surfaceTabbedPane.addTab("Stored", surfaceManipulationPanel);

        getContentPane().add(surfaceTabbedPane, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void surfaceFittingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surfaceFittingButtonActionPerformed
        vtkContourFilter iso = new vtkContourFilter();
        iso.SetInput(imData);
        float isoV = Float.parseFloat(this.isoValueText.getText());
        iso.SetValue(0, isoV);

        int sM = Integer.parseInt(this.smoothText.getText());        

        vtkSmoothPolyDataFilter smoother = new vtkSmoothPolyDataFilter();
        smoother.SetInput(iso.GetOutput());
        smoother.SetNumberOfIterations(sM);
//        int n = iso.GetNumberOfContours();

        vtkPolyDataMapper isoMapper = new vtkPolyDataMapper();
        isoMapper.CreateDefaultLookupTable();
        isoMapper.SetInput(smoother.GetOutput());//iso.GetOutput());
        this.surface = smoother.GetOutput();
        double values[] = new double[iso.GetNumberOfContours()];
        for (int i = 0; i < iso.GetNumberOfContours(); i++) {
            values[i] = iso.GetValue(i);
        }
        isoMapper.SetScalarRange(minVal(values), maxVal(values));
        isoMapper.ScalarVisibilityOff();

        //Terminate the pipeline
        vtkActor isoActor = new vtkActor();
        isoActor.SetMapper(isoMapper);
        isoActor.GetProperty().SetColor(0, 1, 0); //surface color
        isoActor.GetProperty().SetInterpolationToFlat();

        if (vPanelSurface == null) {
            vPanelSurface = new vtkPanel();
        }

        //adding the outline
        vtkOutlineFilter outline = new vtkOutlineFilter();
        outline.SetInput(imData);
        vtkPolyDataMapper mapper2 = new vtkPolyDataMapper();
        mapper2.SetInput(outline.GetOutput());
        vtkActor outactor = new vtkActor();
        outactor.SetMapper(mapper2);
        outactor.GetProperty().SetColor(0, 0, 0);

        vPanelSurface.GetRenderer().RemoveAllViewProps();
        vPanelSurface.GetRenderer().SetBackground(1, 1, 1);
        vPanelSurface.GetRenderer().AddActor(isoActor);
        vPanelSurface.GetRenderer().AddActor(outactor);
        vPanelSurface.setSize(surfacePanel.getSize().width, surfacePanel.getSize().height);
//        vtkCamera aCamera = new vtkCamera();
//        aCamera.SetViewUp(0, 1, 0);
//        aCamera.SetPosition(0, 0, 500); //camera para distanciar e nao aparecer dentro do objeto
//        aCamera.SetFocalPoint(0, 0, 0);
        vPanelSurface.GetRenderer().SetActiveCamera(aCamera);

        this.vPanelSurface.GetRenderer().InteractiveOn();
        this.surfacePanel.add(vPanelSurface);
        this.surfacePanel.repaint();
        this.vPanelSurface.repaint();
}//GEN-LAST:event_surfaceFittingButtonActionPerformed

    private void storeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_storeButtonActionPerformed
        // TODO add your handling code here:
        if (this.surface != null) {
            String response = JOptionPane.showInputDialog(null, "Enter surface name", "Surface Name",
                    JOptionPane.QUESTION_MESSAGE);
            this.surfaces.add(this.surface);
            Object rowData[] = new Object[5];
            rowData[0] = response;
            rowData[1] = "1.0";
            rowData[2] = "1.0";
            rowData[3] = "0.0";
            rowData[4] = "0.0";
            this.surfaceModel.addRow(rowData);
        }
    }//GEN-LAST:event_storeButtonActionPerformed

    private void viewStoredButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewStoredButtonActionPerformed
        // TODO add your handling code here:
        if (vPanelStoredSurface == null) {
            vPanelStoredSurface = new vtkPanel();
        }
        //vPanelSurface.GetRenderer().GetActors().RemoveAllItems();
        vPanelStoredSurface.GetRenderer().RemoveAllViewProps();
        vPanelStoredSurface.GetRenderer().SetBackground(1, 1, 1);


        for (int i : this.surfacesTable.getSelectedRows()) {

            vtkPolyDataMapper isoMapper = new vtkPolyDataMapper();
            isoMapper.CreateDefaultLookupTable();
            isoMapper.SetInput(this.surfaces.get(i));
            //double values[] = new double[iso.GetNumberOfContours()];
            //isoMapper.SetScalarRange(minVal(values), maxVal(values));
            isoMapper.ScalarVisibilityOff();

            //Terminate the pipeline
            vtkActor isoActor = new vtkActor();
            isoActor.SetMapper(isoMapper);
            float alpha = Float.parseFloat((String) this.surfaceModel.getValueAt(i, 1));
            isoActor.GetProperty().SetOpacity(alpha);
            float R = Float.parseFloat((String) this.surfaceModel.getValueAt(i, 2));
            float G = Float.parseFloat((String) this.surfaceModel.getValueAt(i, 3));
            float B = Float.parseFloat((String) this.surfaceModel.getValueAt(i, 4));
            isoActor.GetProperty().SetColor(R, G, B);

            vPanelStoredSurface.GetRenderer().AddActor(isoActor);
        }

        vPanelStoredSurface.setSize(storedPanel.getSize().width, storedPanel.getSize().height);
//        vtkCamera aCamera = new vtkCamera();
//        aCamera.SetViewUp(0, 1, 0);
//        aCamera.SetPosition(0, 0, 500); //camera para distanciar e nao aparecer dentro do objeto
//        aCamera.SetFocalPoint(0, 0, 0);
        vPanelStoredSurface.GetRenderer().SetActiveCamera(aCamera);

        this.vPanelStoredSurface.GetRenderer().InteractiveOn();
        this.storedPanel.add(vPanelStoredSurface);
        this.storedPanel.repaint();
        this.vPanelStoredSurface.repaint();

}//GEN-LAST:event_viewStoredButtonActionPerformed

    private void applyScalarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyScalarButtonActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(BasicsContants.PROPFILENAME);
            int result = OpenDialog.showOpenDialog(spm, new BINFilter(), this);

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = OpenDialog.getFilename();
                float[] scalars = loadScalar(Integer.parseInt(scalarTextField.getText()), filename);
                if (vPanelStoredSurface == null) {
                    vPanelStoredSurface = new vtkPanel();
                }
                //vPanelSurface.GetRenderer().GetActors().RemoveAllItems();
                vPanelStoredSurface.GetRenderer().RemoveAllViewProps();
                vPanelStoredSurface.GetRenderer().SetBackground(1, 1,1);

                for (int i : this.surfacesTable.getSelectedRows()) {
                    vtkPolyDataMapper isoMapper = new vtkPolyDataMapper();
                    vtkPolyData surf = this.surfaces.get(i);
                    isoMapper.SetInput(this.surfaces.get(i));
                    //setting scalar values
                    for (int j = 0; j < surf.GetPoints().GetNumberOfPoints(); j++) {
                        int X = (int) surf.GetPoints().GetPoint(j)[0];
                        int Y = (int) surf.GetPoints().GetPoint(j)[1];
                        int Z = (int) surf.GetPoints().GetPoint(j)[2];
                        int W = this.imData.GetExtent()[1] + 1; //width
                        int H = this.imData.GetExtent()[3] + 1; //height
                        int index = X + Y * W + Z * W * H;
                        surf.GetPointData().GetScalars().SetTuple1(j, scalars[index]);
                    }
                    //isoMapper.SetScalarRange(minVal(values), maxVal(values));
                    //isoMapper.UseLookupTableScalarRangeOff();
                    isoMapper.SetScalarRange(this.min, this.max);
                    isoMapper.ScalarVisibilityOn();
                    isoMapper.CreateDefaultLookupTable();
                    vtkLookupTable lut = (vtkLookupTable) isoMapper.GetLookupTable();
                    lut.SetRange(this.min, this.max);

                    double hueMin = lut.GetHueRange()[0];
                    double hueMax = lut.GetHueRange()[1];
                    lut.SetHueRange(hueMax, hueMin);
                    double valMin = lut.GetValueRange()[0];
                    double valMax = lut.GetValueRange()[1];
                    lut.SetValueRange(valMax, valMin);
                    double satMin = lut.GetSaturationRange()[0];
                    double satMax = lut.GetSaturationRange()[1];
                    lut.SetSaturationRange(satMax, satMin);
                    lut.ForceBuild();
                    isoMapper.Update();
                    System.out.println("\n\nCOLOR INFORMATION");
                    System.out.println("Min: " + this.min);
                    System.out.println("Max: " + this.max);
                    System.out.println("Mapper Min: " + isoMapper.GetScalarRange()[0]);
                    System.out.println("Mapper Max: " + isoMapper.GetScalarRange()[1]);
                    System.out.println("LookUpTable Min: " + lut.GetTableRange()[0]);
                    System.out.println("LookUpTable Max: " + lut.GetTableRange()[1]);
                    //lut.

                    vtkActor isoActor = new vtkActor();
                    isoActor.SetMapper(isoMapper);
                    float alpha = Float.parseFloat((String) this.surfaceModel.getValueAt(i, 1));
                    isoActor.GetProperty().SetOpacity(alpha);

                    vPanelStoredSurface.GetRenderer().AddActor(isoActor);
                }

                vPanelStoredSurface.setSize(storedPanel.getSize().width, storedPanel.getSize().height);
//                vtkCamera aCamera = new vtkCamera();
//                aCamera.SetViewUp(0, 1, 0);
//                aCamera.SetPosition(0, 0, 500); //camera para distanciar e nao aparecer dentro do objeto
//                aCamera.SetFocalPoint(0, 0, 0);
                vPanelStoredSurface.GetRenderer().SetActiveCamera(aCamera);

                this.vPanelStoredSurface.GetRenderer().InteractiveOn();
                this.storedPanel.add(vPanelStoredSurface);
                this.storedPanel.repaint();
                this.vPanelStoredSurface.repaint();
            }
        } catch (IOException ex) {
            Logger.getLogger(SimpleVolumeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_applyScalarButtonActionPerformed

    private void saveSurfaceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSurfaceButtonActionPerformed
        for (int i : this.surfacesTable.getSelectedRows()) {
            try {
                PropertiesManager spm = PropertiesManager.getInstance("volume.properties");
                int result = SaveDialog.showSaveDialog(spm, new VTKFilter(), this,
                        ((String) this.surfaceModel.getValueAt(i, 0)) + ".vtk");
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = SaveDialog.getFilename();
                    vtkPolyDataWriter pdWriter = new vtkPolyDataWriter();
                    pdWriter.SetInput(this.surfaces.get(i));
                    pdWriter.SetFileName(filename);
                    pdWriter.Update();
                }
            } catch (IOException ex) {
                Logger.getLogger(SimpleVolumeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveSurfaceButtonActionPerformed

    private void saveImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveImageButtonActionPerformed
        try {
            if (surface != null) {
                PropertiesManager spm = PropertiesManager.getInstance("volume.properties");
                int result = SaveDialog.showSaveDialog(spm, new PNGFilter(), this, "surface.png");

                if (result == JFileChooser.APPROVE_OPTION) {
                    String filename = SaveDialog.getFilename();

                    vtkWindowToImageFilter w2i = new vtkWindowToImageFilter();
                    w2i.SetInput(vPanelSurface.GetRenderWindow());

                    vtkRenderLargeImage renderLarge = new vtkRenderLargeImage();
                    renderLarge.SetInput(vPanelSurface.GetRenderer());
                    renderLarge.SetMagnification(5);

                    vtkPNGWriter writer = new vtkPNGWriter();
                    //writer.SetInput(w2i.GetOutput());
                    writer.SetInput(renderLarge.GetOutput());
                    writer.SetFileName(filename);
                    writer.Write();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SimpleVolumeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveImageButtonActionPerformed

    public void setImageData(vtkImageData imageData) {
        imData = imageData;
        aCamera = new vtkCamera();
        aCamera.SetViewUp(0, 1, 0);
        aCamera.SetPosition(0, 0, 500); //camera para distanciar e nao aparecer dentro do objeto
        aCamera.SetFocalPoint(0, 0, 0);
    }

    @Override
    public void setModel(AbstractModel model) {
        if (model instanceof VolumeModel) {
            if (model != null) {
                super.setModel(model);
                System.out.println("#Instances in Model: " + this.model.getInstances().size());
            }
        }
    }

    public void setCoordinator(IdentityCoordinator coordinator) {
        this.coordinator = coordinator;
        if (this.coordinator != null) {
            this.coordinator.addModel(model);
        }
    }

    public IdentityCoordinator getCoordinator() {
        return coordinator;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (model != null) {
            ((VolumeModel) model).draw(this.imData);
            surfaceFittingButtonActionPerformed(null);
        } else {
            System.out.println("Volume Model is NULL");
        }
    }

    @Override
    public void addCoordinator(AbstractCoordinator coordinator) {
        super.addCoordinator(coordinator);
    }

    private double minVal(double a[]) {
        int i;
        double min_aux = 0;
        if (a.length > 0) {
            min_aux = a[0];
        }
        for (i = 1; i < a.length; i++) {
            if (a[i] < min_aux) {
                min_aux = a[i];
            }
        }
        return min_aux;
    }

    private double maxVal(double a[]) {
        int i;
        double max_aux = 0;
        if (a.length > 0) {
            max_aux = a[0];
        }
        for (i = 1; i < a.length; i++) {
            if (a[i] > max_aux) {
                max_aux = a[i];
            }
        }
        return max_aux;
    }

    private float[] loadScalar(int index, String filename) {
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        float[] scalars = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(filename));
            dis = new DataInputStream(bis);
            int nP = dis.readInt();
            int nA = dis.readInt() + 1;
            scalars = new float[nP];
            this.max = Float.MIN_VALUE;
            this.min = Float.MAX_VALUE;
            for (int p = 0; p < nP; p++) {
                for (int a = 0; a < nA; a++) {
                    float v = dis.readFloat();
                    if (a == index) {
                        scalars[p] = v;
                        if (v > max) {
                            max = v;
                        }
                        if (v < min) {
                            min = v;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                dis.close();
                bis.close();
            } catch (IOException ex) {
                Logger.getLogger(SimpleVolumeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return scalars;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new SimpleVolumeFrame().setVisible(true);
            }

        });
    }

    private vtkCamera aCamera;
    private IdentityCoordinator coordinator;
    private vtkImageData imData;
    private vtkPanel vPanelSurface = null;
    private vtkPanel vPanelStoredSurface = null;
    private vtkPolyData surface = null;
    private ArrayList<vtkPolyData> surfaces = new ArrayList<vtkPolyData>();
    private DefaultTableModel surfaceModel = null;
    private float max = 0.0f;
    private float min = 0.0f;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyScalarButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JTextField isoValueText;
    private javax.swing.JLabel isovalueLabel;
    private javax.swing.JButton saveImageButton;
    private javax.swing.JButton saveSurfaceButton;
    private javax.swing.JLabel scalarLabel;
    private javax.swing.JTextField scalarTextField;
    private javax.swing.JLabel smoothLabel;
    private javax.swing.JTextField smoothText;
    private javax.swing.JButton storeButton;
    private javax.swing.JPanel storeControlPanel;
    private javax.swing.JPanel storedPanel;
    private javax.swing.JPanel sufaceFittingPanel;
    private javax.swing.JButton surfaceFittingButton;
    private javax.swing.JPanel surfaceManipulationPanel;
    private javax.swing.JPanel surfacePanel;
    private javax.swing.JTabbedPane surfaceTabbedPane;
    private javax.swing.JScrollPane surfacesScrollPane;
    private javax.swing.JTable surfacesTable;
    private javax.swing.JButton viewStoredButton;
    // End of variables declaration//GEN-END:variables
}
