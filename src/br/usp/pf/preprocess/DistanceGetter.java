package br.usp.pf.preprocess;

import java.io.PrintWriter;

import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

class DistanceGetter extends Thread {

    private static UndirectedSparseGraph<Integer, Integer> graph;
    private static Integer[] vertices;
    private PrintWriter out;
    private int begin;
    private int end;
    private int id;

    public DistanceGetter(int id, Integer[] a, int begin, int end, UndirectedSparseGraph<Integer, Integer> graph, PrintWriter out) {
        this.id = id;
        DistanceGetter.vertices = a;
        this.begin = begin;
        this.end = end;
        DistanceGetter.graph = graph;
        this.out = out;
    }

    private void getDistances() {
        for (int i = begin; i < end; i++) {
            UnweightedShortestPath<Integer, Integer> sp = new UnweightedShortestPath<Integer, Integer>(graph);
            for (Integer y : graph.getVertices()) {
                Number dist = sp.getDistance(vertices[i], y);
                if ((dist != null) && (dist.intValue() != 0)) {
                    out.println(vertices[i] + " " + y + " " + (dist.intValue() - 1));    
                }
            }
        }
    }

    @Override
    public void run() {
        getDistances();
    }
}