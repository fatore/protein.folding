/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visualizer.projection.distance.view;

import java.util.ArrayList;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SimilarityMatrixGraph extends Graph {

    @Override
    public void setVertex(ArrayList<Vertex> vertex) {
        this.vertex = vertex;
    }

    @Override
    public Vertex getVertexByPosition(int x, int y) {
        for (Vertex v : this.vertex) {
            if (v.getX() == x && v.getY() == y) {
                return v;
            }
        }
        return null;
    }

    public ArrayList<Vertex> getVerticesByPosition(int x, int y) {
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        for (Vertex v : this.vertex) {
            if ((v.getX() == x && v.getY() == x) ||
                    (v.getX() == y && v.getY() == y)) {
                vertices.add(v);
            }
        }
        
        return vertices;
    }
}
