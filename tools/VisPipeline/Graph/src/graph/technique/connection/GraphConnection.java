/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.technique.connection;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import graph.model.Connectivity;
import java.io.IOException;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class GraphConnection {

    public abstract Connectivity execute(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException;

    public abstract Connectivity execute(DistanceMatrix dmat) throws IOException;

}
