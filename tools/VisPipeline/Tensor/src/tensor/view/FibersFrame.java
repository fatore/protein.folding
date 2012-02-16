/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FibersFrame.java
 *
 * Created on Oct 27, 2009, 5:35:33 PM
 */
package tensor.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import projection.model.Scalar;
import tensor.model.FiberInstance;
import tensor.model.FiberModel;
import tensor.model.TRKFiberReaderComp;
import tensor.util.TensorConstants;
import tensor.util.Util;
import tensor.view.selectedview.LineSelectedFiber;
import tensor.view.selectedview.SelectedFiberRepresentation;
import tensor.view.selectedview.SurfaceDensitySelectedFiber;
import tensor.view.selectedview.SurfaceSplatSelectedFiber;
import tensor.view.selectedview.TubeSelectedFiber;
import tensor.view.tools.MultidimensionalClusteringView;
import visualizationbasics.coordination.AbstractCoordinator;
import visualizationbasics.coordination.IdentityCoordinator;
import visualizationbasics.model.AbstractModel;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.SaveDialog;
import visualizationbasics.util.filter.PNGFilter;
import visualizationbasics.util.filter.SCALARFilter;
import visualizationbasics.util.filter.VTKFilter;
import visualizationbasics.view.MemoryCheck;
import visualizationbasics.view.MessageDialog;
import visualizationbasics.view.ModelViewer;
import visualizationbasics.view.selection.AbstractSelection;
import visualizationbasics.view.selection.coordination.IdentityCoordinationSelection;
import vtk.vtkActor;
import vtk.vtkCamera;
import vtk.vtkCellPicker;
import vtk.vtkOutlineFilter;
import vtk.vtkPNGWriter;
import vtk.vtkPanel;
import vtk.vtkPolyDataMapper;
import vtk.vtkPolyDataWriter;
import vtk.vtkRenderLargeImage;
import vtk.vtkRenderer;
import vtk.vtkSurfaceActor;
import vtk.vtkWindowToImageFilter;

/**
 *
 * @author jpocom
 */
public class FibersFrame extends ModelViewer {

    static {
        System.loadLibrary("vtkWidgetsJava");
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkGenericFilteringJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkHybridJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
        System.loadLibrary("vtkVolumeRenderingJava");
    }

    /** Creates new form FibersFrame */
    public FibersFrame() {
        initComponents();

        this.viewerPanel.add(view);

        addSelectedView(new LineSelectedFiber(this), true);
        addSelectedView(new TubeSelectedFiber(this), false);
        addSelectedView(new SurfaceDensitySelectedFiber(this), false);
        addSelectedView(new SurfaceSplatSelectedFiber(this), false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectionButtonGroup = new javax.swing.ButtonGroup();
        selectedViewButtonGroup = new javax.swing.ButtonGroup();
        surfacesPopupMenu = new javax.swing.JPopupMenu();
        deleteMenuItem = new javax.swing.JMenuItem();
        propertyMenuItem = new javax.swing.JMenuItem();
        controlPanel = new javax.swing.JPanel();
        toolbarPanel = new javax.swing.JPanel();
        fixedToolBar = new javax.swing.JToolBar();
        moveInstanceToggleButton = new javax.swing.JToggleButton();
        cleanInstancesButton = new javax.swing.JButton();
        selectionToolBar = new javax.swing.JToolBar();
        selectedToolBar = new javax.swing.JToolBar();
        dataPanel = new javax.swing.JPanel();
        scalarPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        scalarCombo = new JComboBox(this.scalarComboModel);
        viewerPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        openButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        separatorLabel1 = new javax.swing.JLabel();
        toolButton = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exportMenu = new javax.swing.JMenu();
        fileExportToPNG = new javax.swing.JMenuItem();
        fileExportToPNGHR = new javax.swing.JMenuItem();
        fileExportToVTK = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        editClean = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuTool = new javax.swing.JMenu();
        memoryCheckMenuItem = new javax.swing.JMenuItem();
        separatorOption1 = new javax.swing.JSeparator();
        scalarsMenu = new javax.swing.JMenu();
        importScalarsOption = new javax.swing.JMenuItem();
        exportScalarsOption = new javax.swing.JMenuItem();
        separatorOption2 = new javax.swing.JSeparator();
        clusteringMenu = new javax.swing.JMenu();
        multidimensionalMenuItem = new javax.swing.JMenuItem();
        separatorOption3 = new javax.swing.JSeparator();
        toolOptions = new javax.swing.JMenuItem();

        deleteMenuItem.setText("Delete Surface");
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMenuItemActionPerformed(evt);
            }
        });
        surfacesPopupMenu.add(deleteMenuItem);

        propertyMenuItem.setText("Surfaces Properties");
        propertyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertyMenuItemActionPerformed(evt);
            }
        });
        surfacesPopupMenu.add(propertyMenuItem);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        controlPanel.setLayout(new java.awt.BorderLayout());

        toolbarPanel.setLayout(new java.awt.BorderLayout());

        fixedToolBar.setOrientation(1);
        fixedToolBar.setRollover(true);

        moveInstanceToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Forward16.gif"))); // NOI18N
        moveInstanceToggleButton.setFocusable(false);
        moveInstanceToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        moveInstanceToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        moveInstanceToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveInstanceToggleButtonActionPerformed(evt);
            }
        });
        fixedToolBar.add(moveInstanceToggleButton);

        cleanInstancesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Edit16.gif"))); // NOI18N
        cleanInstancesButton.setFocusable(false);
        cleanInstancesButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanInstancesButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanInstancesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanInstancesButtonActionPerformed(evt);
            }
        });
        fixedToolBar.add(cleanInstancesButton);

        toolbarPanel.add(fixedToolBar, java.awt.BorderLayout.NORTH);

        selectionToolBar.setOrientation(1);
        toolbarPanel.add(selectionToolBar, java.awt.BorderLayout.CENTER);

        selectedToolBar.setOrientation(1);
        selectedToolBar.setRollover(true);
        selectedToolBar.setMinimumSize(new java.awt.Dimension(12, 30));
        selectedToolBar.setPreferredSize(new java.awt.Dimension(12, 150));
        toolbarPanel.add(selectedToolBar, java.awt.BorderLayout.SOUTH);

        controlPanel.add(toolbarPanel, java.awt.BorderLayout.EAST);

        dataPanel.setLayout(new java.awt.BorderLayout());

        scalarPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        scalarPanel.setPreferredSize(new java.awt.Dimension(138, 41));

        jLabel2.setText("Color:");
        scalarPanel.add(jLabel2);

        scalarCombo.setPreferredSize(new java.awt.Dimension(85, 27));
        scalarCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                scalarComboItemStateChanged(evt);
            }
        });
        scalarPanel.add(scalarCombo);

        dataPanel.add(scalarPanel, java.awt.BorderLayout.NORTH);

        viewerPanel.setBackground(new java.awt.Color(255, 255, 255));
        viewerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        viewerPanel.setLayout(new java.awt.BorderLayout());
        dataPanel.add(viewerPanel, java.awt.BorderLayout.CENTER);

        controlPanel.add(dataPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(controlPanel, java.awt.BorderLayout.CENTER);

        toolBar.setRollover(true);

        openButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Open16.gif"))); // NOI18N
        openButton.setFocusable(false);
        openButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(openButton);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Save16.gif"))); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(saveButton);

        separatorLabel1.setText("     ");
        toolBar.add(separatorLabel1);

        toolButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Preferences16.gif"))); // NOI18N
        toolButton.setFocusable(false);
        toolButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolButtonActionPerformed(evt);
            }
        });
        toolBar.add(toolButton);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        statusLabel.setText("...");
        statusPanel.add(statusLabel);

        getContentPane().add(statusPanel, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("File");

        exportMenu.setText("export");

        fileExportToPNG.setText("Export PNG File");
        fileExportToPNG.setToolTipText("Show all fibers");
        fileExportToPNG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExportToPNGActionPerformed(evt);
            }
        });
        exportMenu.add(fileExportToPNG);

        fileExportToPNGHR.setText("Export PNG File (High Resolution)");
        fileExportToPNGHR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExportToPNGHRActionPerformed(evt);
            }
        });
        exportMenu.add(fileExportToPNGHR);

        fileExportToVTK.setText("Export VTK FIle");
        fileExportToVTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExportToVTKActionPerformed(evt);
            }
        });
        exportMenu.add(fileExportToVTK);

        jMenu1.add(exportMenu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        editClean.setText("Clean Projection");
        editClean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCleanActionPerformed(evt);
            }
        });
        jMenu2.add(editClean);

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        menuTool.setText("Tool");

        memoryCheckMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        memoryCheckMenuItem.setText("Memory Check");
        memoryCheckMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memoryCheckMenuItemActionPerformed(evt);
            }
        });
        menuTool.add(memoryCheckMenuItem);
        menuTool.add(separatorOption1);

        scalarsMenu.setText("Scalars");

        importScalarsOption.setText("Import Scalars");
        importScalarsOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importScalarsOptionActionPerformed(evt);
            }
        });
        scalarsMenu.add(importScalarsOption);

        exportScalarsOption.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exportScalarsOption.setText("Export Scalars");
        exportScalarsOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportScalarsOptionActionPerformed(evt);
            }
        });
        scalarsMenu.add(exportScalarsOption);

        menuTool.add(scalarsMenu);
        menuTool.add(separatorOption2);

        clusteringMenu.setText("Clustering");
        clusteringMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clusteringMenuActionPerformed(evt);
            }
        });

        multidimensionalMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        multidimensionalMenuItem.setText("Multidimensional Data");
        multidimensionalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                multidimensionalMenuItemActionPerformed(evt);
            }
        });
        clusteringMenu.add(multidimensionalMenuItem);

        menuTool.add(clusteringMenu);
        menuTool.add(separatorOption3);

        toolOptions.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        toolOptions.setText("Tool Option");
        toolOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolOptionsActionPerformed(evt);
            }
        });
        menuTool.add(toolOptions);

        jMenuBar1.add(menuTool);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void importScalarsOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importScalarsOptionActionPerformed
        if (view != null) {
            try {
                PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
                int result = OpenDialog.showOpenDialog(spm, new SCALARFilter(), this);

                if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                    if (model != null) {
                        final MessageDialog dialog = MessageDialog.show(this, "Importing scalars...");

                        Thread t = new Thread() {

                            @Override
                            public void run() {
                                try {
                                    String filename = OpenDialog.getFilename();
                                    Util.importScalars((FiberModel) model, filename);
                                    updateScalars(null);
                                } catch (IOException ex) {
                                    Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
                                } finally {
                                    dialog.close();
                                }
                            }
                        };

                        t.start();
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_importScalarsOptionActionPerformed

    private void scalarComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_scalarComboItemStateChanged
        if (evt.getStateChange() == ItemEvent.DESELECTED) {
            Scalar scalar = (Scalar) this.scalarCombo.getSelectedItem();
            if (scalar != null) {
                view.colorAs(scalar);
            }
        }
    }//GEN-LAST:event_scalarComboItemStateChanged

    private void fileExportToPNGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExportToPNGActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
            int result = SaveDialog.showSaveDialog(spm, new PNGFilter(), this, "image.png");

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = SaveDialog.getFilename();

                view.saveToPngImageFile(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fileExportToPNGActionPerformed

    private void fileExportToVTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExportToVTKActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
            int result = SaveDialog.showSaveDialog(spm, new VTKFilter(), this, "image.vtk");

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = SaveDialog.getFilename();

                vtkPolyDataWriter writer = new vtkPolyDataWriter();
                writer.SetFileName(filename);
                writer.SetInput(((FiberModel) model).getPolydata());
                writer.Write();
            }
        } catch (IOException ex) {
            Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fileExportToVTKActionPerformed

    private void toolButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolButtonActionPerformed
        toolOptionsActionPerformed(evt);
    }//GEN-LAST:event_toolButtonActionPerformed

    private void toolOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolOptionsActionPerformed
        FibersFrameOptions.getInstance(this).display(this);
    }//GEN-LAST:event_toolOptionsActionPerformed

    private void cleanInstancesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanInstancesButtonActionPerformed
        editCleanActionPerformed(evt);
}//GEN-LAST:event_cleanInstancesButtonActionPerformed

    private void editCleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCleanActionPerformed
        if (view != null) {
            view.cleanSelectedInstances();
        }
    }//GEN-LAST:event_editCleanActionPerformed

    private void memoryCheckMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memoryCheckMenuItemActionPerformed
        MemoryCheck.showMemoryCheck();
}//GEN-LAST:event_memoryCheckMenuItemActionPerformed

    private void multidimensionalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_multidimensionalMenuItemActionPerformed
        if (model != null) {
            Scalar s = MultidimensionalClusteringView.getInstance(this).display((FiberModel) model);
            updateScalars(s);
        }
}//GEN-LAST:event_multidimensionalMenuItemActionPerformed

    private void clusteringMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clusteringMenuActionPerformed
        if (model != null) {
            Scalar s = MultidimensionalClusteringView.getInstance(this).display((FiberModel) model);
            updateScalars(s);
        }
}//GEN-LAST:event_clusteringMenuActionPerformed

    private void exportScalarsOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportScalarsOptionActionPerformed
        if (viewerPanel != null) {
            try {
                PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
                int result = SaveDialog.showSaveDialog(spm, new SCALARFilter(), this, "scalars.scalar");

                if (result == JFileChooser.APPROVE_OPTION) {
                    if (model != null) {
                        String filename = SaveDialog.getFilename();
                        Util.exportScalars((FiberModel) model, filename);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}//GEN-LAST:event_exportScalarsOptionActionPerformed

    private void fileExportToPNGHRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExportToPNGHRActionPerformed
        try {
            PropertiesManager spm = PropertiesManager.getInstance(TensorConstants.PROPFILENAME);
            int result = SaveDialog.showSaveDialog(spm, new PNGFilter(), this, "image.png");

            if (result == JFileChooser.APPROVE_OPTION) {
                String filename = SaveDialog.getFilename();

                view.saveToPngImageFileHR(filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_fileExportToPNGHRActionPerformed

    private void moveInstanceToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveInstanceToggleButtonActionPerformed
    }//GEN-LAST:event_moveInstanceToggleButtonActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        getView().lock();
        ((FiberModel) model).removeSurfaces(getView().GetRenderer(), selectedSurfaces);
        getView().unlock();
        selectedSurfaces.clear();
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void propertyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertyMenuItemActionPerformed
        SurfaceOptions.getInstance(this).display(this, selectedSurfaces);
    }//GEN-LAST:event_propertyMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        /*vtkPlane plane = new vtkPlane();
        plane.SetOrigin(80,80,80);
        plane.SetNormal(1,0,0);

        vtkCutter cutter = new vtkCutter();
        cutter.SetCutFunction(plane);
        cutter.SetInput(((FiberModel)model).getPolydata());
        cutter.Update();

        vtkPolyDataMapper cutterMapper = new vtkPolyDataMapper();
        cutterMapper.SetInputConnection(cutter.GetOutputPort());

        //create plane actor
        vtkActor planeActor = new vtkActor();
        planeActor.GetProperty().SetColor(1.0,1,0);
        planeActor.GetProperty().SetLineWidth(2);
        planeActor.SetMapper(cutterMapper);

         */
        PlaneCutterClass plane = new PlaneCutterClass();
        plane.setGlyphType(1, 0);
        vtkActor actor  = plane.addPlaneCut(((FiberModel)model).getPolydata(), 0, true);
        

        view.lock();
        ((FiberModel)model).getActor().SetVisibility(0);
        view.GetRenderer().AddActor(actor);
        view.GetRenderer().Render();
        view.unlock();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    TRKFiberReaderComp reader = new TRKFiberReaderComp();
                    reader.setFilename("/Volumes/Data/Users/jpocom/Documents/Mestrado/Programas/data vispipeline/brain1_scan1_fiber_track_mni_class.trk");
                    reader.execute();

                    FibersFrameComp frame = new FibersFrameComp();
                    frame.input(reader.output());
                    frame.execute();
                } catch (IOException ex) {
                    Logger.getLogger(FibersFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (model != null) {
            view.repaint();
        }
    }

    @Override
    public void setModel(AbstractModel model) {
        if (model instanceof FiberModel) {
            if (model != null) {
                super.setModel(model);
                Scalar scalar = ((FiberModel) model).getSelectedScalar();

                if (scalar != null) {
                    updateScalars(scalar);
                } else {
                    updateScalars(((FiberModel) model).getScalars().get(0));
                }
                view.setModel((FiberModel) model);
            }
        }
    }

    public void addSelection(final AbstractSelection selection) {
        if (selection != null) {
            JToggleButton button = new JToggleButton();
            selectionButtonGroup.add(button);
            button.setIcon(selection.getIcon());
            button.setSelected(false);
            button.setToolTipText(selection.toString());

            button.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (view != null) {
                        view.setSelection(selection);
                    }
                }
            });

            selectionToolBar.add(button);
        }
    }

    public void addSelectedView(final SelectedFiberRepresentation selected, boolean isCliked) {
        if (selected != null) {
            JToggleButton button = new JToggleButton();
            selectedViewButtonGroup.add(button);
            button.setIcon(selected.getIcon());
            button.setSelected(isCliked);
            button.setToolTipText(selected.toString());

            button.addActionListener(new java.awt.event.ActionListener() {

                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ((FiberModel) getModel()).setSelectedView(selected);
                }
            });

            selectedToolBar.add(button);
        }
    }

    @Override
    public void addCoordinator(AbstractCoordinator coordinator) {
        super.addCoordinator(coordinator);
        if (coordinator instanceof IdentityCoordinator) {
            addSelection(new IdentityCoordinationSelection(this));
        }
    }

    public void updateScalars(Scalar scalar) {
        scalarComboModel.removeAllElements();
        for (Scalar s : ((FiberModel) model).getScalars()) {
            scalarComboModel.addElement(s);
        }

        if (scalar != null) {
            scalarCombo.setSelectedItem(scalar);
            ((FiberModel) model).setSelectedScalar(scalar);
        } else {
            scalarCombo.setSelectedItem(((FiberModel) model).getSelectedScalar());
        }

        model.setChanged();
        model.notifyObservers();
    }

    private void SyncCameras() {
        vtkCamera cam = view.GetRenderer().GetActiveCamera();
        camAxes.SetFocalPoint(0, 0, 0);
        double[] proj = cam.GetDirectionOfProjection();
        camAxes.SetPosition(-proj[0], -proj[1], -proj[2]);
        camAxes.SetViewUp(cam.GetViewUp());
        renAxes.ResetCameraClippingRange();
    }

    public void setViewerBackground(Color bg) {
        if (view != null) {
            view.setBackground(bg);
            view.repaint();
        }
    }

    public ViewPanel getView() {
        return view;
    }

    public Scalar getCurrentScalar() {
        return (Scalar) scalarCombo.getSelectedItem();
    }

    public void showOutline(boolean show) {
        if (view != null) {
            view.showOutline(show);
            view.repaint();
        }
    }

    public void changeStatus(String status) {
        this.statusLabel.setText(status);
    }

    public class ViewPanel extends vtkPanel {

        public ViewPanel() {
            picker = new vtkCellPicker();
            picker.SetTolerance(0.001);

            this.addMouseMotionListener(new MouseMotionListener());
            this.addMouseListener(new MouseClickedListener());
        }

        @Override
        public void paint(Graphics g) {
            if (model != null) {
                ((FiberModel) model).draw(ren);

                this.Render();
            }
        }

        public void setModel(FiberModel model) {
            GetRenderer().RemoveAllViewProps();

            GetRenderer().AddActor(model.getActor());

            // outline
            outline = new vtkOutlineFilter();
            outline.SetInput(model.getPolydata());

            outlineMapper = new vtkPolyDataMapper();
            outlineMapper.SetInput(outline.GetOutput());

            outlineActor = new vtkActor();
            outlineActor.SetMapper(outlineMapper);
            outlineActor.SetVisibility(0);

            GetRenderer().AddActor(outlineActor);

            // resest camera
            resetCamera();
        }

        public void colorAs(Scalar scalar) {
            if (model != null) {
                ((FiberModel) model).setSelectedScalar(scalar);
                model.notifyObservers();
            }
        }

        @Override
        public void setBackground(Color c) {
            if (model != null) {
                GetRenderer().SetBackground(c.getRed() / 255., c.getGreen() / 255., c.getBlue() / 255.);
                model.notifyObservers();
            }
        }

        public vtkActor getOutlineActor() {
            return outlineActor;
        }

        public void showOutline(boolean show) {
            outlineActor.SetVisibility(show ? 1 : 0);
        }

        private void saveToPngImageFileHR(String filename) {
            lock();
            rw.OffScreenRenderingOn();

            vtkRenderLargeImage renderLarge = new vtkRenderLargeImage();
            renderLarge.SetInput(view.GetRenderer());
            renderLarge.SetMagnification(5);

            vtkPNGWriter pnW = new vtkPNGWriter();
            pnW.SetFileName(filename);
            pnW.SetInput(renderLarge.GetOutput());
            pnW.Write();

            rw.OffScreenRenderingOff();
            unlock();
        }

        private void saveToPngImageFile(String filename) {
            lock();
            rw.OffScreenRenderingOn();

            vtkWindowToImageFilter filter = new vtkWindowToImageFilter();
            filter.SetInput(rw);

            vtkPNGWriter writer = new vtkPNGWriter();
            writer.SetInput(filter.GetOutput());
            writer.SetFileName(filename);
            writer.Write();

            rw.OffScreenRenderingOff();
            unlock();
        }

        public void cleanSelectedInstances() {
            if (model != null) {
                ((FiberModel) model).removeAllSurfaces(ren);
                model.cleanSelectedInstances();
                model.notifyObservers();
            }
        }

        public void setSelection(AbstractSelection selection) {
            this.selection = selection;
        }
        /*
        public void setSelectedView(SelectedFiberRepresentation selectedView) {
        this.selectedView = selectedView;
        }
         */

        class MouseMotionListener extends MouseMotionAdapter {

            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                super.mouseMoved(evt);
            }
        }

        class MouseClickedListener extends MouseAdapter {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super.mouseClicked(evt);
                if (model == null) {
                    return;
                }
                int cellId = picker.Pick(evt.getX(), getHeight() - evt.getY(), 0, GetRenderer());
                if (cellId == 0 || (cellId = picker.GetCellId()) == -1) {
                    return;
                }

                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    if (evt.isControlDown()) {
                        vtkActor act = picker.GetActor();
                        if (act instanceof vtkSurfaceActor) {
                            if (!selectedSurfaces.contains((vtkSurfaceActor) act)) {
                                selectedSurfaces.add((vtkSurfaceActor) act);
                            }
                        }
                    } else {
                        FiberInstance inst = ((FiberModel) model).getInstancesById(cellId);
                        changeStatus("Number of Instances in Selection: " + 0);
                        if (inst != null) {
                            changeStatus("Number of Instances in Selection: " + 1);
                            if (evt.getClickCount() == 1) {
                                model.setSelectedInstance(inst);
                                model.notifyObservers();
                            }
                            if (selection != null) {
                                selection.selected(model.getSelectedInstances());
                            }
                        }
                    }
                } else if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    vtkActor act = picker.GetActor();
                    if (act instanceof vtkSurfaceActor) {
                        if (!selectedSurfaces.contains((vtkSurfaceActor) act)) {
                            selectedSurfaces.add((vtkSurfaceActor) act);
                        }
                        surfacesPopupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
                    } else {
                        cleanSelectedInstances();
                        changeStatus("Number of Instances in Selection: " + 0);
                    }
                }
            }
        }
        private vtkCellPicker picker;
        private AbstractSelection selection;
        private vtkOutlineFilter outline;
        private vtkPolyDataMapper outlineMapper;
        private vtkActor outlineActor;
    }
    private DefaultComboBoxModel scalarComboModel = new DefaultComboBoxModel();
    private ViewPanel view = new ViewPanel();
    private ArrayList<vtkSurfaceActor> selectedSurfaces = new ArrayList<vtkSurfaceActor>();
    // For Axes
    private vtkRenderer renAxes;
    private vtkCamera camAxes;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cleanInstancesButton;
    private javax.swing.JMenu clusteringMenu;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenuItem editClean;
    private javax.swing.JMenu exportMenu;
    private javax.swing.JMenuItem exportScalarsOption;
    private javax.swing.JMenuItem fileExportToPNG;
    private javax.swing.JMenuItem fileExportToPNGHR;
    private javax.swing.JMenuItem fileExportToVTK;
    private javax.swing.JToolBar fixedToolBar;
    private javax.swing.JMenuItem importScalarsOption;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem memoryCheckMenuItem;
    private javax.swing.JMenu menuTool;
    private javax.swing.JToggleButton moveInstanceToggleButton;
    private javax.swing.JMenuItem multidimensionalMenuItem;
    private javax.swing.JButton openButton;
    private javax.swing.JMenuItem propertyMenuItem;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox scalarCombo;
    private javax.swing.JPanel scalarPanel;
    private javax.swing.JMenu scalarsMenu;
    private javax.swing.JToolBar selectedToolBar;
    private javax.swing.ButtonGroup selectedViewButtonGroup;
    private javax.swing.ButtonGroup selectionButtonGroup;
    private javax.swing.JToolBar selectionToolBar;
    private javax.swing.JLabel separatorLabel1;
    private javax.swing.JSeparator separatorOption1;
    private javax.swing.JSeparator separatorOption2;
    private javax.swing.JSeparator separatorOption3;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JPopupMenu surfacesPopupMenu;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton toolButton;
    private javax.swing.JMenuItem toolOptions;
    private javax.swing.JPanel toolbarPanel;
    private javax.swing.JPanel viewerPanel;
    // End of variables declaration//GEN-END:variables
}
