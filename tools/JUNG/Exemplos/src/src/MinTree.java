/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.io.PajekNetReader;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JFrame;
import org.apache.commons.collections15.FactoryUtils;

/**
 *
 * @author Fatore
 */
public class MinTree {

    Graph<String, Number> graph;
    Forest<String, Number> forest;

    VisualizationViewer<String, Number> vv;

    MutableTransformer layoutTransformer;

    Dimension preferredSize = new Dimension(300, 300);
    Dimension preferredLayoutSize = new Dimension(400, 400);
    Dimension preferredSizeRect = new Dimension(500, 250);

    public MinTree() throws IOException {

        PajekNetReader pnr = new PajekNetReader(FactoryUtils.instantiateFactory(Object.class));
        
        pnr.load("D:/full", graph);

        JFrame jf = new JFrame();
        vv = new VisualizationViewer(new FRLayout(graph));
        jf.getContentPane().add(vv);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
    }

    public static void main (String args[]) throws IOException {

        new MinTree();
    }
}
