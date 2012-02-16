/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.selection;

import datamining.neighbors.Pair;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import projection.model.ProjectionInstance;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import matrix.AbstractMatrix;
import plsp.model.topic.PLSPTopicProjectionModel;
import plsp.persistent.PersistentPLSPProjection2D;
import topics.view.TopicProjectionFrame;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;
import visualizationbasics.view.selection.AbstractSelection;

/*
 * @author Danilo Medeiros Eler
 */
public class NewPLSPTopicReprojectSelection extends AbstractSelection {

    public NewPLSPTopicReprojectSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() instanceof PLSPTopicProjectionModel) {
            if (selinst.size() > 0) {
//                if (matrix == null) {
//                    try {
//                        PropertiesManager spm = PropertiesManager.getInstance(ProjectionConstants.PROPFILENAME);
//                        int result = OpenDialog.showOpenDialog(spm, new DATAandBINMultipleFilter(), viewer);
//
//                        if (result == JFileChooser.APPROVE_OPTION) {
//                            try {
//                                String filename = OpenDialog.getFilename();
//                                matrix = MatrixFactory.getInstance(filename);
//                            } catch (IOException ex) {
//                                Logger.getLogger(NewPLSPTopicReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        }
//                    } catch (IOException ex) {
//                        Logger.getLogger(NewPLSPTopicReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }

//                if (matrix != null) {
                // if (idPos == null) {
                System.out.println("Creating id mapping");
                //creating map between ID and position in matrix
                //idPos = new HashMap<Integer, Integer>();
//                    idPos = new Hashtable();
//                    ArrayList<Integer> ids = matrix.getIds();
//                    int size = ids.size();
//                    for (int i = 0; i < size; i++) {
//                        idPos.put(ids.get(i), i);
//                    }
                //}
//                    for (int i = 0; i < size; i++) {
//                        System.out.print("key: " + ids.get(i) + " value: " + i);
//                        System.out.println(" return: " + idPos.get(ids.get(i)));
//                    }

                PLSPTopicProjectionModel plspModel = ((PLSPTopicProjectionModel) viewer.getModel());


                ArrayList<ArrayList<AbstractInstance>> auxPatches = plspModel.getPatches();
                for (int i = 0; i < auxPatches.size(); i++) {
                    for (int j = 0; j < auxPatches.get(i).size(); j++) {
                        int id = auxPatches.get(i).get(j).getId();
//                            System.out.println("key: " + plspModel.getPatches().get(i).get(j).getId() + " value: " +
//                                    idPos.get(id));
                    }
                }


                float meanX = 0.0f;
                float meanY = 0.0f;

                System.out.println("\n\n\nA new selection has been started");
                //storing selected patches ID
                HashSet<Integer> patchSet = new HashSet<Integer>();
                Hashtable selSizes = new Hashtable();
                Hashtable selCP = new Hashtable();
                for (AbstractInstance pi : selinst) {
                    Integer patchID = (Integer) plspModel.getPatchMap().get(pi.getId());
                    //patch IDs
                    patchSet.add(patchID);
                    //patch Sizes
                    if (selSizes.get(patchID) != null) {
                        Integer cont = (Integer) selSizes.get(patchID);
                        cont = cont + 1;
                        selSizes.put(patchID, cont);
                    } else {
                        selSizes.put(patchID, 1);
                    }
                    //selected control points
                    if (plspModel.getCpoints().get(patchID).contains(pi)) {
                        if (selCP.get(patchID) != null) {
                            ArrayList<AbstractInstance> cps = (ArrayList<AbstractInstance>) selCP.get(patchID);
                            cps.add(pi);
                            selCP.put(patchID, cps);
                        } else {
                            ArrayList<AbstractInstance> cps = new ArrayList<AbstractInstance>();
                            cps.add(pi);
                            selCP.put(patchID, cps);
                        }
                    }
                    //computing mean from instances coordinates
                    meanX = meanX + ((ProjectionInstance) pi).getX();
                    meanY = meanY + ((ProjectionInstance) pi).getY();
                }
                meanX = meanX / selinst.size();
                meanY = meanY / selinst.size();
                System.out.println("PatchSet size = " + patchSet.size());
                System.out.println("Number of instances in selection = " + selinst.size() + "\n");

                ((TopicProjectionFrame) this.viewer).changeStatus("Instances: " + selinst.size() + " - Patches: " + patchSet.size());



//                    float perc = (float) Math.pow(matrix.getRowCount(), 0.75) / matrix.getRowCount();
                System.out.println("Total of Patches: " + plspModel.getPatches().size());
                System.out.println("Number of Patches in Selection: " + patchSet.size());
                Iterator it = patchSet.iterator();
                while (it.hasNext()) {
                    Integer patchID = (Integer) it.next();
                    System.out.println("Number of instances in patch " + patchID + " is " + plspModel.getPatches().get(patchID).size());
                    System.out.println("   - number of selected instances is " + (Integer) selSizes.get(patchID));
                    int nrcluster = 0;
                    if (selCP.get(patchID) != null) {
                        System.out.println("   - number of control points in selected patch is " + ((ArrayList<ProjectionInstance>) selCP.get(patchID)).size());
//                            nrcluster = (int) (((ArrayList<ProjectionInstance>) selCP.get(patchID)).size() * perc);
                    } else {
                        System.out.println("   - no control points was selected");
                    }
                    System.out.println("   - number of control point to reprojection is " + nrcluster);
                    nrcluster = (nrcluster > 3) ? nrcluster : 3;
                    System.out.println("   - REAL number of control point to reprojection is " + nrcluster);
                }

                System.out.println("\nComputing control points NN to (" + meanX + " ; " + meanY + ")");
                int totalSelCPs = 0;
                it = patchSet.iterator();
                Hashtable cpNN = new Hashtable();
                while (it.hasNext()) {
                    Integer patchID = (Integer) it.next();
                    int numCPs = 0;
                    if (selCP.get(patchID) != null) {
                        numCPs = (int) ((ArrayList<ProjectionInstance>) selCP.get(patchID)).size();
                    }
                    totalSelCPs = totalSelCPs + numCPs;
                    System.out.println("Patch ID is " + patchID);
                    System.out.println("  - CP size is " + plspModel.getCpoints().get(patchID).size());
                    System.out.println("  - selected CPs in patch is " + numCPs);

                    //all control points werer selected
//                        if (plspModel.getCpoints().get(patchID).size() - numCPs == 0) {
//                            System.out.println("  - number CPs to compute NN is null");
//                        } else {
                    Pair[] pairs = new Pair[plspModel.getCpoints().get(patchID).size()];
                    int cont = 0;
                    for (int i = 0; i < plspModel.getCpoints().get(patchID).size(); i++) {
                        ProjectionInstance pi = (ProjectionInstance) plspModel.getCpoints().get(patchID).get(i);
//                                if (numCPs == 0) {
                        float dist = (float) Math.sqrt((meanX - pi.getX()) * (meanX - pi.getX()) + (meanY - pi.getY()) * (meanY - pi.getY()));
                        pairs[i] = new Pair(i, dist);
//                                } else if (!((ArrayList<ProjectionInstance>) selCP.get(patchID)).contains(pi)) {
//                                    float dist = (float) Math.sqrt((meanX - pi.getX()) * (meanX - pi.getX()) + (meanY - pi.getY()) * (meanY - pi.getY()));
//                                    pairs[cont] = new Pair(i, dist);
//                                    cont++;
//                                }
                    }
                    System.out.println("  - number CPs to compute NN is " + pairs.length);
                    System.out.println("  - sorting");
                    cpNN.put(patchID, this.bubbleSort(pairs));
//                        }

                }
                System.out.println("Total of Selectec Control Points: " + totalSelCPs);

                //getting 2D coordinates for each control point
                it = patchSet.iterator();
                Hashtable cpProj = new Hashtable();
                Hashtable cpWeight = new Hashtable();
                while (it.hasNext()) {
                    ArrayList<float[]> cp2D = new ArrayList<float[]>();
                    Integer patchID = (Integer) it.next();
                    System.out.println("Patch ID is " + patchID);
                    //number of selected control points
                    int nrSelCP = 0;
                    if (selCP.get(patchID) != null) {
                        nrSelCP = ((ArrayList<ProjectionInstance>) selCP.get(patchID)).size();
                    }

                    System.out.println("  - number of Sel CPs is " + nrSelCP);
//                        int nrCP = (int) (nrSelCP * perc);
//                        System.out.println("  - number of CPs to change is " + nrCP);
                    float[] weights = new float[plspModel.getCpoints().get(patchID).size()];
                    for (int i = 0; i < plspModel.getCpoints().get(patchID).size(); i++) {
                        ProjectionInstance pi = (ProjectionInstance) plspModel.getCpoints().get(patchID).get(i);
                        float[] cpCoord = new float[2];
                        cpCoord[0] = pi.getX();
                        cpCoord[1] = pi.getY();
                        cp2D.add(cpCoord);
                        weights[i] = 1.0f;
                    }

                    //number of controls points with new coordinates
                    int s = (Integer) selSizes.get(patchID); //the number of selected points on the subset (patch)
                    int c = plspModel.getCpoints().get(patchID).size(); //the number of control points on the subset (patch)
                    int r = plspModel.getPatches().get(patchID).size(); //the total number of points on the subset (patch)
                    int nrCP = Math.round((s * c) / r);//(nrCP > 3) ? nrCP : 3;
                    if (nrCP == 0) {
                        nrCP = 1;
                    }
                    System.out.println("  - REAL number of CPs to change is " + nrCP);

                    //changing coordinates of selected control points
                    //if the patch was not selected as a whole
                    if (!(plspModel.getPatches().get(patchID).size() == ((Integer) selSizes.get(patchID)))) {
//                            if (nrSelCP > 0) {
//                                int cont = 0;  //i < nrCP &&
//                                for (int i = 0; i < nrSelCP; i++) {
//                                    ProjectionInstance pi = ((ArrayList<ProjectionInstance>) selCP.get(patchID)).get(i);
//                                    int index = plspModel.getCpoints().get(patchID).indexOf(pi);
//                                    cp2D.get(index)[0] = meanX + (float) (Math.random() - 0.5) * 5.0f;
//                                    cp2D.get(index)[1] = meanY + (float) (Math.random() - 0.5) * 5.0f;
//                                    cont++;
//                                    weights[index] = 20.0f;
//                                }
//                                System.out.println("  - changed coordinates from selecions is " + cont);
//                            } else {
                        //getting nearest CPs from centroid (meanX, meanY)
                        for (int i = 0; i < nrCP; i++) {
                            int index = ((Pair[]) cpNN.get(patchID))[i].index;
                            cp2D.get(index)[0] = meanX + (float) (Math.random() - 0.5) * 5.0f;
                            cp2D.get(index)[1] = meanY + (float) (Math.random() - 0.5) * 5.0f;
                            weights[index] = 20.0f;
                        }
//                            }
                    }
                    cpWeight.put(patchID, weights);

//                        //changing coodinates of remainder control points
//                        int contBk = cont;
//                        //null if all CPoints are in selection
//                        for (int i = contBk, j = 0; i < nrCP && cpNN.get(patchID) != null; i++, j++) {
//                            int index = ((Pair[]) cpNN.get(patchID))[j].index;
//                            cp2D.get(index)[0] = meanX + (float) (Math.random() - 0.5) * 5;
//                            cp2D.get(index)[1] = meanY + (float) (Math.random() - 0.5) * 5;
//                            cont++;
//                        }
//                        System.out.println("  - changed coordinates from remainder is " + (cont - contBk));
//                        System.out.println("  - total changed coordinates is " + cont);
                    cpProj.put(patchID, cp2D);
                }

                //reprojecting patches
                System.out.println("\nReprojeting");
                AbstractDissimilarity diss = DissimilarityFactory.getInstance(DissimilarityType.EUCLIDEAN);
                Hashtable reProjection = new Hashtable();
                it = patchSet.iterator();
                while (it.hasNext()) {
                    Integer patchID = (Integer) it.next();
                    if (plspModel.getPatches().get(patchID).size() == ((Integer) selSizes.get(patchID))) {
                        System.out.println("  - Patch " + patchID + " does not need to be reprojected");
                        float[][] projection = new float[plspModel.getPatches().get(patchID).size()][2];
                        for (int i = 0; i < projection.length; i++) {
                            projection[i][0] = meanX + (float) (Math.random() - 0.5) * 5;
                            projection[i][1] = meanY + (float) (Math.random() - 0.5) * 5;
                        }
                        reProjection.put(patchID, projection);
                    } else {
                        System.out.println("  - Reprojecting Patch " + patchID);
                        PersistentPLSPProjection2D plsp = new PersistentPLSPProjection2D();
                        ArrayList<Integer> cluster = new ArrayList<Integer>();
                        for (int j = 0; j < plspModel.getPatches().get(patchID).size(); j++) {
                            //cluster.add((Integer) idPos.get(plspModel.getPatches().get(patchID).get(j).getId()));
                            cluster.add(plspModel.getPatches().get(patchID).get(j).getId());
                        }
                        ArrayList<Integer> cpoints = new ArrayList<Integer>();
                        for (int j = 0; j < plspModel.getCpoints().get(patchID).size(); j++) {
                            //cpoints.add((Integer) idPos.get(plspModel.getCpoints().get(patchID).get(j).getId()));
                            cpoints.add(plspModel.getCpoints().get(patchID).get(j).getId());
                        }
                        try {
                            float[][] projection = plsp.projectCluster(null, diss, cluster, cpoints,
                                    (ArrayList<float[]>) cpProj.get(patchID), plspModel.getNeighborhoodGraphs().get(patchID),
                                    (float[]) cpWeight.get(patchID));
                            reProjection.put(patchID, projection);
                        } catch (IOException ex) {
                            Logger.getLogger(NewPLSPTopicReprojectSelection.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }


                //update position for each reprojected instance
                System.out.println("\nUpdating instances positions");
                it = patchSet.iterator();
                while (it.hasNext()) {
                    Integer patchID = (Integer) it.next();
                    ArrayList<AbstractInstance> patch = plspModel.getPatches().get(patchID);
                    float[][] projection = (float[][]) reProjection.get(patchID);
                    for (int i = 0; i < patch.size(); i++) {
                        ((ProjectionInstance) patch.get(i)).setX(projection[i][0]);
                        ((ProjectionInstance) patch.get(i)).setY(projection[i][1]);
                    }
                }

                if (viewer.getModel() != null) {
                    viewer.getModel().setSelectedInstances(selinst);
                    viewer.getModel().notifyObservers();
                }
                ((TopicProjectionFrame) this.viewer).changeStatus("Number of Instances in Selection: " + selinst.size() + " --- Number of Patches in Selection: " + patchSet.size());
//                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "You need to use the PLSP Topic Projection Model to Perform this Operation.");
        }
    }

    public Pair[] bubbleSort(Pair[] pairs) {
        Pair temp = new Pair(0, 0.0f);
        for (int i = 0; i < pairs.length - 1; i++) {
            for (int j = 0; j < pairs.length - 1; j++) {
                if (pairs[j].value > pairs[j + 1].value) {
                    temp.index = pairs[j].index;
                    temp.value = pairs[j].value;

                    pairs[j].index = pairs[j + 1].index;
                    pairs[j].value = pairs[j + 1].value;

                    pairs[j + 1].index = temp.index;
                    pairs[j + 1].value = temp.value;
                }
            }
        }
        return pairs;
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/general/Undo16.gif"));
    }

    @Override
    public String toString() {
        return "P-LSP Reproject Selection (No Splitting)";
    }

    private AbstractMatrix matrix;
    //private HashMap<Integer, Integer> idPos = null;
    private Hashtable idPos = null;
}
