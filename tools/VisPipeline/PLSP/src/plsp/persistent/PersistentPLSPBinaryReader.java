/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plsp.persistent;

import datamining.neighbors.Pair;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PersistentPLSPBinaryReader {

    public static PersistentPLSPProjection2D read(String filename) throws IOException {
        PersistentPLSPProjection2D plsp = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(filename));
            dis = new DataInputStream(bis);
            //reading patches
            int nPatches = dis.readInt();
            System.out.println("#patches = " + nPatches);
            ArrayList<ArrayList<Integer>> patches = new ArrayList<ArrayList<Integer>>();
            for (int i=0; i< nPatches; i++){
                int nElem = dis.readInt();
                ArrayList<Integer> patch = new ArrayList<Integer>();
                System.out.println(nElem + " = Size of Cluster " + i);
                for (int j=0; j< nElem; j++){
                    patch.add(dis.readInt());
                }
                patches.add(patch);
            }
            System.out.println("#patches = " + patches.size());

            //reading control points
            int nCPoints = dis.readInt();
            System.out.println("#cPoint = " + nCPoints);
            ArrayList<ArrayList<Integer>> controlPoints = new ArrayList<ArrayList<Integer>>();
            for (int i=0; i< nCPoints; i++){
                int nElem = dis.readInt();
                ArrayList<Integer> cPoints = new ArrayList<Integer>();
                for (int j=0; j< nElem; j++){
                    cPoints.add(dis.readInt());
                }
                controlPoints.add(cPoints);
            }

            //reading neighborhodd graphs
            int numNGraphs = dis.readInt();            
            System.out.println("#Neighbour Graphs = " + numNGraphs);
            ArrayList<Pair[][]> neighborhoodGraphs = new ArrayList<Pair[][]>();
            for (int i=0; i<numNGraphs; i++){
                int rows = dis.readInt();
                Pair[][] nGraph = new Pair[rows][];
                for (int j=0; j<rows; j++){
                    int cols = dis.readInt();
                    nGraph[j] = new Pair[cols];
                    for (int k=0; k<cols; k++){
                        nGraph[j][k] = new Pair(0, 0.0f);
                        nGraph[j][k].index = dis.readInt();
                        nGraph[j][k].value = dis.readFloat();
                    }
                }
                neighborhoodGraphs.add(nGraph);
            }

            plsp = new PersistentPLSPProjection2D();
            plsp.setClusters(patches);
            plsp.setCpoints(controlPoints);
            plsp.setNeighborhoodGraphs(neighborhoodGraphs);
        } catch (IOException ex) {
            throw new IOException(ex);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }

                if (bis != null) {
                    bis.close();
                }
            } catch (IOException ex) {
                throw new IOException(ex);
            }
        }

        return plsp;
    }
}
