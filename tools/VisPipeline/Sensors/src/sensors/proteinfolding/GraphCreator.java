/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.proteinfolding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fm
 */
class Edge {

    int source;
    int target;
    int weight;
    int incidence;
}

public class GraphCreator {

    HashMap<Transition, Edge> edges;
    int edgesCounter;

    public void readEdges(String minFile, String maxFile) throws IOException {

        edges = new HashMap<Transition, Edge>();

        BufferedReader minReader = null;
        BufferedReader maxReader = null;

        String previousMinLine = null;
        String nextMinLine = null;
        String maxLine = null;


        try {

            minReader = new BufferedReader(new FileReader(minFile));
            maxReader = new BufferedReader(new FileReader(maxFile));

            previousMinLine = minReader.readLine();

            while ((nextMinLine = minReader.readLine()) != null) {

                maxLine = maxReader.readLine();

                Transition t = new Transition(Integer.parseInt(previousMinLine.split(" ")[0]), Integer.parseInt(nextMinLine.split(" ")[0]));

                int newWeight = Integer.parseInt(maxLine.split(" ")[0]);

                //se a transicao ja existe
                if (edges.containsKey(t)) {

                    Edge e = new Edge();

                    //chaves se mantem as mesmas
                    e.source = edges.get(t).source;
                    e.target = edges.get(t).target;

                    //compare o valor da energia, se o novo valor lido for menor, substitua
                    if (newWeight < edges.get(t).weight) {

                        //novo valor atriui-se ao peso
                        e.weight = newWeight;
                    } else {
                        e.weight = edges.get(t).weight;
                    }

                    e.incidence = edges.get(t).incidence + 1;

                    edges.put(t, e);

                } //senao adicione a primeira
                else {

                    Edge e = new Edge();
                    //no inicial equivale a conformacao inicial da transicao
                    e.source = t.source;
                    //no destino equivale a conformacao destino da transicao
                    e.target = t.target;
                    //o inicial peso da aresta
                    e.weight = newWeight;
                    //incidencia inicial da aresta é 1
                    e.incidence = 1;

                    edges.put(t, e);

                    edgesCounter++;
                }

                previousMinLine = nextMinLine;

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphCreator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (minReader != null) {
                minReader.close();
            }
            if (maxReader != null) {
                maxReader.close();
            }
        }

    }

    public void createJungGraph(String minFile, String maxFile, String outputFile, int inc) throws IOException {

        readEdges(minFile, maxFile);

        PrintWriter out = null;

        out = new PrintWriter(new File(outputFile).getAbsoluteFile());

        out.println("*Vertices " + edgesCounter);
        out.println("*Edges");

        for (Edge e : edges.values()) {
            if (e.incidence > inc) {
                out.println(e.source + " " + e.target + " " + e.weight);
            }
        }
        if (out != null) {
            out.close();
        }
    }
}
