/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.persistent;

import datamining.neighbors.Pair;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PersistentPLSPBinaryWriter {

    public void write(PersistentPLSPProjection2D plsp, String filename) throws IOException {
        BufferedOutputStream bos = null;
        DataOutputStream out = null;

        try {
            bos = new BufferedOutputStream(new FileOutputStream(filename));
            out = new DataOutputStream(bos);
            //number of patches
            out.writeInt(plsp.getClusters().size());
            System.out.println("#patches = " + plsp.getClusters().size());
            //writting patches
            for (int i = 0; i < plsp.getClusters().size(); i++) {
                ArrayList<Integer> patch = plsp.getClusters().get(i);
                out.writeInt(patch.size());
                for (int j = 0; j < patch.size(); j++) {
                    out.writeInt(patch.get(j));
                }
            }

            //numer of control points
            out.writeInt(plsp.getCpoints().size());
            System.out.println("#cPoints = " + plsp.getCpoints().size());
           //writting control points
            for (int i = 0; i < plsp.getCpoints().size(); i++) {
                ArrayList<Integer> cPoints = plsp.getCpoints().get(i);
                out.writeInt(cPoints.size());
                for (int j = 0; j < cPoints.size(); j++) {
                    out.writeInt(cPoints.get(j));
                }
            }

            //number of neighborhood graphs
            out.writeInt( plsp.getNeighborhoodGraphs().size() );
            System.out.println("#Neighbour Graphs = " + plsp.getNeighborhoodGraphs().size());
            //writting neighborhood graphs
            for (int i=0; i<plsp.getNeighborhoodGraphs().size(); i++){
                Pair [][] nGraph = plsp.getNeighborhoodGraphs().get(i);
                out.writeInt(nGraph.length);
                for (int j=0; j<nGraph.length; j++){
                    out.writeInt(nGraph[j].length);
                    for (int k=0; k<nGraph[j].length; k++){
                        out.writeInt(nGraph[j][k].index);
                        out.writeFloat(nGraph[j][k].value);
                    }
                }
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }

                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PersistentPLSPBinaryWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
