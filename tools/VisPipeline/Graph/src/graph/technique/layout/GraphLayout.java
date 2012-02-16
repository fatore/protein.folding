/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package graph.technique.layout;

import graph.model.Connectivity;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando
 */
public interface GraphLayout {

    public AbstractMatrix execute(AbstractMatrix matrix, Connectivity conn);

}
