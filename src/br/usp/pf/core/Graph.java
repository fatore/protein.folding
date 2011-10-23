package br.usp.pf.core;

import br.usp.pf.jung.mst.*;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * @author Francisco Morgani Fatore
 */
public class Graph {
    
    /**
	 */
    private String fileName;
    /**
	 */
    private UndirectedSparseGraph usg;
    /**
	 */
    private Vertex root;
    /**
	 */
    private int numVertices;
    /**
	 */
    private int verticesMinEnergy;
    /**
	 */
    private int verticesMaxEnergy;
    
    /**
	 */
    private float edgesMinEnergy;
    /**
	 */
    private float edgesMaxEnergy;
    
    public Graph(String inputFile) throws Exception {
        this.fileName = inputFile;
        loadGraph();
    }
    
    public UndirectedSparseGraph getUndirectedSparseGraph() {return usg;}
    /**
	 * @return
	 */
    public Vertex getRoot() {return root;}
    /**
	 * @return
	 */
    public int getNumVertices() {return numVertices;}
    /**
	 * @return
	 */
    public int getVerticesMaxEnergy() {return verticesMaxEnergy;}
    /**
	 * @return
	 */
    public int getVerticesMinEnergy() {return verticesMinEnergy;}
    /**
	 * @return
	 */
    public float getEdgesMinEnergy() {return edgesMinEnergy;}
    /**
	 * @return
	 */
    public float getEdgesMaxEnergy() {return edgesMaxEnergy;}
        
    private void loadGraph() throws Exception {

        usg = new UndirectedSparseGraph<Integer, MyLink>();

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        
        StringTokenizer token;
        
        //count number of vertices
        String line = br.readLine();

        if (line == null) {
            throw new Exception("Not a gph File!");
        }
        
        token = new StringTokenizer(line.trim());
        
        //skip first word *Vertices
        token.nextToken();

        numVertices = Integer.parseInt(token.nextToken());
        
        //store native state
        line = br.readLine();
        token = new StringTokenizer(line.trim());
        root = new Vertex();
        root.setKey(Integer.parseInt(token.nextToken()));
        root.setEnergy(Integer.parseInt(token.nextToken()));

        verticesMaxEnergy = root.getEnergy();
        verticesMinEnergy = root.getEnergy();
        
        //skip vertices 
        while (!line.startsWith("*Edges")) {            
            if (line == null) {
                throw new Exception("Not a gph File!");
            }
            token = new StringTokenizer(line.trim());
            token.nextToken();
            int energy = Integer.parseInt(token.nextToken());
            if (energy < verticesMinEnergy) throw new Exception("Node energy is lower than native state!");
            if (energy > verticesMaxEnergy) verticesMaxEnergy = energy;
            line = br.readLine();
        }
        
        edgesMinEnergy = Float.POSITIVE_INFINITY;
        edgesMaxEnergy = Float.NEGATIVE_INFINITY;

        int edgeCounter = 0;
        //read edges
        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line.trim());

            int source = Integer.parseInt(token.nextToken());
            int target = Integer.parseInt(token.nextToken());
            int weight = Integer.parseInt(token.nextToken());
            
            if (weight < edgesMinEnergy) edgesMinEnergy = weight;
            if (weight > edgesMaxEnergy) edgesMaxEnergy = weight;

            usg.addEdge(new MyLink(edgeCounter++, weight), source, target);

        }
    }   
}
