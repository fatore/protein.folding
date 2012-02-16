/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import projection.model.ProjectionModel;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class GraphModel extends ProjectionModel {

    public GraphModel() {
        this.connectivities = new ArrayList<Connectivity>();
        this.selsconn = null;
        this.instancesize = 4;
    }

    @Override
    public void draw(BufferedImage image, boolean highquality) {
        if (image != null) {
            if (selsconn != null) {
                selsconn.draw(this, image, highquality);
            }

            super.draw(image, highquality);
        }
    }

    public Connectivity getSelectedConnectivity() {
        return selsconn;
    }

    public void setSelectedConnectivity(Connectivity conn) {
        if (connectivities.contains(conn)) {
            selsconn = conn;
        } else {
            selscalar = null;
        }

        setChanged();
    }

    public ArrayList<Connectivity> getConnectivities() {
        return this.connectivities;
    }

    public Connectivity getConnectivityByName(String name) {
        for (Connectivity c : this.connectivities) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public void addConnectivity(Connectivity connectivity) {
        this.removeConnectivity(connectivity);
        this.connectivities.add(connectivity);
    }

    public void removeConnectivity(Connectivity connectivity) {
        this.connectivities.remove(connectivity);
    }

    /**
     * @return the sizebase
     */
    public int getInstanceSize() {
        return instancesize;
    }

    /**
     * @param aSizebase the sizebase to set
     */
    public void setInstanceSize(int instancesize) {
        this.instancesize = instancesize;
        setChanged();
    }

    public void perturb() {
        Random rand = new Random(7);

        float maxx = Float.NEGATIVE_INFINITY;
        float minx = Float.POSITIVE_INFINITY;
        float maxy = Float.NEGATIVE_INFINITY;
        float miny = Float.POSITIVE_INFINITY;

        for (int i = 0; i < instances.size(); i++) {
            GraphInstance pi = (GraphInstance) instances.get(i);

            if (maxx < pi.getX()) {
                maxx = pi.getX();
            }

            if (minx > pi.getX()) {
                minx = pi.getX();
            }

            if (maxy < pi.getY()) {
                maxy = pi.getY();
            }

            if (miny > pi.getY()) {
                miny = pi.getY();
            }
        }

        float diffx = (maxx - minx) / 1000;
        float diffy = (maxy - miny) / 1000;

        for (int i = 0; i < instances.size(); i++) {
            GraphInstance pi = (GraphInstance) instances.get(i);

            pi.setX(pi.getX() + diffx * rand.nextFloat());
            pi.setY(pi.getY() + diffy * rand.nextFloat());
        }
    }

    public void createConnectivities() {
//        try {
//            //Creating a Delaunay triangulation
//            float[][] projection = new float[instances.size()][];
//            for (int i = 0; i < projection.length; i++) {
//                projection[i] = new float[2];
//                projection[i][0] = instances.get(i).getX();
//                projection[i][1] = instances.get(i).getY();
//            }
//
//            //perturbing equal vertices
//            perturb();
//
//            try {
//                Delaunay d = new Delaunay();
//                Pair[][] neighborhood = d.execute(projection);
//                Connectivity con = new Connectivity(GraphConstants.DELAUNAY);
//                con.create(instances, neighborhood);
//                addConnectivity(con);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
//            }
//
//            int knnNumberNeighbors = 2; // Creating the KNN-R2 connectivity...
//            DenseMatrix dproj = new DenseMatrix();
//            for (int i = 0; i < projection.length; i++) {
//                dproj.addRow(new DenseVector(projection[i]));
//            }
//
//            String conname = "KNN-R2-" + knnNumberNeighbors;
//            Connectivity knnr2Con = new Connectivity(conname);
//            ANN appknnr2 = new ANN(knnNumberNeighbors);
//            Pair[][] neighborhood = appknnr2.execute(dproj, new Euclidean());
//            knnr2Con.create(instances, neighborhood);
//            addConnectivity(knnr2Con);
//        } catch (IOException ex) {
//            Logger.getLogger(GraphModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private int instancesize;
    protected Connectivity selsconn;
    protected ArrayList<Connectivity> connectivities;
}
