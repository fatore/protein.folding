/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.selection;

import visualizationbasics.view.selection.AbstractSelection;
import datamining.clustering.BKmeans;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import projection.model.ProjectionInstance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import matrix.MatrixFactory;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.MatrixReaderComp;
import plsp.model.PLSPProjectionModel;
import plsp.model.PLSPProjectionModelComp;
import plsp.persistent.PersistentPLSPBinaryReaderComp;
import plsp.persistent.PersistentPLSPProjection2D;
import plsp.view.ProjectionFrame;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import projection.view.ProjectionFrameComp;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.util.OpenDialog;
import visualizationbasics.util.PropertiesManager;
import visualizationbasics.util.filter.DATAandBINMultipleFilter;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PLSPReprojectSelection extends AbstractSelection {

    public PLSPReprojectSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() instanceof PLSPProjectionModel) {
            if (selinst.size() > 0) {
                if (matrix == null) {
                    try {
                        PropertiesManager spm = PropertiesManager.getInstance(ProjectionConstants.PROPFILENAME);
                        int result = OpenDialog.showOpenDialog(spm, new DATAandBINMultipleFilter(), viewer);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            try {
                                String filename = OpenDialog.getFilename();
                                matrix = MatrixFactory.getInstance(filename);
                            } catch (IOException ex) {
                                Logger.getLogger(PLSPReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(PLSPReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (matrix != null) {
                    System.out.println("\n\n\nA new selection has been started");
                    //storing selected patches ID
                    HashSet<Integer> patchSet = new HashSet<Integer>();
                    for (AbstractInstance pi : selinst) {
                        Integer patchID = (Integer) ((PLSPProjectionModel) viewer.getModel()).getPatchMap().get(pi.getId());
                        patchSet.add(patchID);
                    }
                    System.out.println("PatchSet size = " + patchSet.size());
                    System.out.println("Number of instances in selection = " + selinst.size() + "\n");

                    //will split if more than one patch was selected or
                    //if not all instances of one patch was select
                    if (patchSet.size() > 1
                            || selinst.size() != ((PLSPProjectionModel) viewer.getModel()).getPatches().get((Integer) patchSet.iterator().next()).size()) {

                        System.out.println("Total of Patches: " + ((PLSPProjectionModel) viewer.getModel()).getPatches().size());
                        System.out.println("Number of Patches in Selection: " + patchSet.size());
                        System.out.println("Before new patch");
                        Iterator it = patchSet.iterator();
                        while (it.hasNext()) {
                            Integer patchID = (Integer) it.next();
                            System.out.println("Number of instances in patch " + patchID + " is " + ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID).size());
                        }

                        //Creating new patch based on selection (selected instances)
                        ((PLSPProjectionModel) viewer.getModel()).addPatch(selinst);
                        int index = ((PLSPProjectionModel) viewer.getModel()).getPatches().indexOf(selinst);
                        System.out.println("Index of new patch: " + index);
                        System.out.println("Total of Patches: " + ((PLSPProjectionModel) viewer.getModel()).getPatches().size());

                        //removing selected instances from their old patches and updating on patchMap
                        for (AbstractInstance pi : selinst) {
                            Integer patchID = (Integer) ((PLSPProjectionModel) viewer.getModel()).getPatchMap().get(pi.getId());
                            // System.out.println(pi.getId() + "  ==== Patch " + ((PLSPProjectionModel) viewer.getModel()).getPatchMap().get(pi.getId()));
                            ((PLSPProjectionModel) viewer.getModel()).getPatchMap().put(pi.getId(), index);
                            // System.out.println(pi.getId() + "  ==== Patch " + ((PLSPProjectionModel) viewer.getModel()).getPatchMap().get(pi.getId()));
                            ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID).remove(pi);
                        }

                        //removing patches which will not be reprojected; lesser than a threshold
                        System.out.println("After new patch");
                        ArrayList<Integer> remList = new ArrayList<Integer>();
                        it = patchSet.iterator();
                        float notReprojectThreshold = (float) Math.sqrt(matrix.getRowCount()) / 2.0f;
                        while (it.hasNext()) {
                            Integer patchID = (Integer) it.next();
                            int patchSize = ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID).size();
                            System.out.println("Number of instances in patch " + patchID + " is " + patchSize);
                            if (patchSize < notReprojectThreshold || patchSize == 0) {
                                System.out.println("  - removing patch from the list");
                                //((PLSPProjectionModel) viewer.getModel()).getPatches().remove(((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID));
                                remList.add(patchID);
                            }
                        }

                        //revoming from the list of patches to reprojection
                        for (Integer patchID : remList) {
                            patchSet.remove(patchID);
                        }
                        System.out.println("Total of Patches: " + ((PLSPProjectionModel) viewer.getModel()).getPatches().size());
                        patchSet.add(index);


                        //build a matrix with 2D information for each instance
                        //matrix2D for each patch
                        System.out.println("\nBuilding Matrix with 2D information");
                        Scalar cdataScalar = new Scalar("cdata");
                        ArrayList<AbstractMatrix> matrices2D = new ArrayList<AbstractMatrix>();
                        int totalInstances = 0;
                        it = patchSet.iterator();
                        System.out.println("Number of patches: " + patchSet.size());
                        while (it.hasNext()) {
                            AbstractMatrix matrix2D = new DenseMatrix();
                            ArrayList<String> attributes = new ArrayList<String>();
                            attributes.add("x");
                            attributes.add("y");
                            matrix2D.setAttributes(attributes);

                            Integer patchID = (Integer) it.next();
                            ArrayList<AbstractInstance> patch = ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID);
                            System.out.println(patch.size() + " is the size of patch " + patchID);
                            for (int i = 0; i < patch.size(); i++) {
                                ProjectionInstance ins = (ProjectionInstance) patch.get(i);

                                float[] coord = new float[2];
                                coord[0] = ins.getX();
                                coord[1] = ins.getY();
                                AbstractVector vector = new DenseVector(coord, ins.getId(), ins.getScalarValue(cdataScalar));
                                matrix2D.addRow(vector);
                            }
                            System.out.println("  - Size of matrix2D: " + matrix2D.getRowCount());
                            totalInstances = totalInstances + matrix2D.getRowCount();
                            matrices2D.add(matrix2D);
                        }
                        System.out.println("Number of patches: " + matrices2D.size());
                        System.out.println("Total of instances: " + totalInstances);


                        //compute k-Means 2D and get Control Points (medoids) for each patch
                        System.out.println("\nComputing Bissection k-Means for each patch and getting C. Points");
                        AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.EUCLIDEAN);
                        //percentage of points of each cluster to use
                        float perc = (float) Math.pow(matrix.getRowCount(), 0.75) / matrix.getRowCount();
                        int medoids[][] = new int[matrices2D.size()][];
                        for (int i = 0; i < matrices2D.size(); i++) {
                            try {
                                //defining the number of clusters to be found
                                int nrcluster = (int) (matrices2D.get(i).getRowCount() * perc);
                                nrcluster = (nrcluster > 3) ? nrcluster : 3;
                                System.out.println(" - " + nrcluster + " is the number of CP of matrix2D " + i);
                                BKmeans bkmeans = new BKmeans(nrcluster);
                                bkmeans.execute(diss, matrices2D.get(i));
                                medoids[i] = bkmeans.getMedoids(matrices2D.get(i));
                            } catch (IOException ex) {
                                Logger.getLogger(PLSPReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        //preparing data to reproject: Control Points and Control Points Coordination for each patch
                        ArrayList<ArrayList<float[]>> cpointsproj = new ArrayList<ArrayList<float[]>>();
                        ArrayList<ArrayList<Integer>> cPoints = new ArrayList<ArrayList<Integer>>();
                        for (int i = 0; i < medoids.length; i++) {
                            ArrayList<float[]> cpProj = new ArrayList<float[]>();
                            ArrayList<Integer> cPointsPatch = new ArrayList<Integer>();
                            System.out.println("Control Points from matrix2D: " + i);
                            for (int j = 0; j < medoids[i].length; j++) {
                                float cpCoord[] = new float[2];
                                cpCoord[0] = ((DenseVector) matrices2D.get(i).getRow(j)).getValue(0);
                                cpCoord[1] = ((DenseVector) matrices2D.get(i).getRow(j)).getValue(1);
                                cpProj.add(cpCoord); //C. Points coordinates

                                cPointsPatch.add(medoids[i][j]); //adding C. Points index
                                System.out.print(medoids[i][j] + "; ");
                            }
                            cPoints.add(cPointsPatch);//indexes
                            cpointsproj.add(cpProj);//coordinates
                            System.out.println();
                        }
                        long start = System.currentTimeMillis();
                        //load original space from matrix for each instance in patches
                        System.out.println("\nLoading from Original Matrix with ND information");
                        ArrayList<AbstractMatrix> matricesND = new ArrayList<AbstractMatrix>();
                        it = patchSet.iterator();
                        while (it.hasNext()) {
                            AbstractMatrix matrixND = new DenseMatrix();
                            Integer patchID = (Integer) it.next();
                            ArrayList<AbstractInstance> patch = ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID);
                            System.out.println(patch.size() + " is the size of patch " + patchID);
                            for (int i = 0; i < patch.size(); i++) {
                                //int rowIndex = matrix.getIds().indexOf(patch.get(i).getId());
                                int rowIndex = patch.get(i).getId();
                                // System.out.println("   - rowIndex = " + rowIndex);
                                // System.out.println("   -       ID = " + patch.get(i).getId());
                                AbstractVector vector = matrix.getRow(rowIndex);
                                matrixND.addRow(vector);
                            }
                            matricesND.add(matrixND);
                        }
                        System.out.println("Number of matrixNDs: " + matricesND.size());
                        long finish = System.currentTimeMillis();
                        System.out.println("Time to load: " + (finish - start) / 1000.0f + "s");

                        //reproject
                        System.out.println("\nReprojecting patches");
                        float lspThreshold = 2.0f * (float) Math.sqrt(matrix.getRowCount());
                        ArrayList<float[][]> reProjection = new ArrayList<float[][]>();
                        for (int i = 0; i < matricesND.size(); i++) {
                            PersistentPLSPProjection2D plsp = new PersistentPLSPProjection2D();
                            System.out.println("Size of patch: " + matricesND.get(i).getRowCount());
                            if (matricesND.get(i).getRowCount() > lspThreshold) { //reproject with P-LSP
                                try {
                                    System.out.println(" - Reprojecting with P-LSP");
                                    float[][] projection = plsp.reproject(matricesND.get(i), diss, matrices2D.get(i));
                                    reProjection.add(projection);
                                } catch (IOException ex) {
                                    Logger.getLogger(PLSPReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else { //reproject with LSP
                                System.out.println(" - Reprojecting with LSP");
                                ArrayList<Integer> cluster = new ArrayList<Integer>();
                                for (int j = 0; j < matricesND.get(i).getRowCount(); j++) {
                                    cluster.add(j);
                                }
                                try {
                                    float[][] projection = plsp.projectCluster(matricesND.get(i), diss, cluster, cPoints.get(i), cpointsproj.get(i), null, null);
                                    reProjection.add(projection);
                                } catch (IOException ex) {
                                    Logger.getLogger(PLSPReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }

                        //update position for each reprojected instance
                        System.out.println("\nUpdating instances positions");
                        it = patchSet.iterator();
                        int patchIndex = 0;
                        while (it.hasNext()) {
                            Integer patchID = (Integer) it.next();
                            ArrayList<AbstractInstance> patch = ((PLSPProjectionModel) viewer.getModel()).getPatches().get(patchID);
                            for (int i = 0; i < patch.size(); i++) {
                                ((ProjectionInstance) patch.get(i)).setX(reProjection.get(patchIndex)[i][0]);
                                ((ProjectionInstance) patch.get(i)).setY(reProjection.get(patchIndex)[i][1]);
                            }
                            patchIndex++;
                        }

                        if (viewer.getModel() != null) {
                            viewer.getModel().setSelectedInstances(selinst);
                            viewer.getModel().notifyObservers();
                        }
                    } else {
                        System.out.println("\n Full Patch Selection \n");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "You need to use the Persistent PLSP Model to Perform this Operation.");
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Undo16.gif"));
    }

    @Override
    public String toString() {
        return "P-LSP Reproject Selection (Patch Splitting)";
    }

    public static void main(String[] args) {
        try {
            MatrixReaderComp reader = new MatrixReaderComp();
            //reader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample_headScalars_withoutLast.bin");
            //reader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\viscontest08.30_noAVG_normCol.bin");
            reader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample888_multifield.0099_noAVG_normCol_proj.bin");
//            reader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample444_multifield.0099_noAVG_normCol_Proj.bin");
            reader.execute();

            PersistentPLSPBinaryReaderComp plspReader = new PersistentPLSPBinaryReaderComp();
            //plspReader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample_headScalars_withoutLast.plspbin");
            //plspReader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\viscontest08.30_noAVG_normCol.plspbin");
            plspReader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample888_multifield.0099_noAVG_normCol_proj.plspbin");
            //plspReader.setFilename("C:\\Users\\User\\Desktop\\PLSP\\sample444_multifield.0099_noAVG_normCol_Proj.plspbin");
            plspReader.execute();

            PLSPProjectionModelComp plspModel = new PLSPProjectionModelComp();
            plspModel.input(reader.output());
            plspModel.input(plspReader.output());
            plspModel.execute();

            ProjectionFrameComp fcomp1 = new ProjectionFrameComp();
            fcomp1.input(plspModel.output());
            fcomp1.execute();
        } catch (IOException ex) {
            Logger.getLogger(ProjectionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private AbstractMatrix matrix;
}
