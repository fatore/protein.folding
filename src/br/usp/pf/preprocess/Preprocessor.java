package br.usp.pf.preprocess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import br.usp.pf.core.Conformation;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;

/**
 * 
 * @author Fatore
 */
public class Preprocessor {

	// file to be pre processed
	private String inputFile;
	private String path;
	// number of read states
	private int readStates;
	// number of distinct states
	private int distinctStates;
	// number of threads
	private int noThreads;
	private int cut;
	private int liveIndex;
	
	private static UndirectedSparseGraph<Integer, Integer> graph;
	private static UnweightedShortestPath<Integer, Integer> sp;
	private static Map<Integer, Integer> incomEdges;
	private static int[] result;
	private HashMap<Integer, Integer> oldKey2NewKey;

	public Preprocessor(String inputFile, String path) {
		this.inputFile = inputFile;
		this.path = path;
		this.readStates = 0;
		this.distinctStates = 0;
		this.noThreads = 2;
		this.cut = 0;
	}
	
	public Preprocessor(String inputFile, String path, int noThreads) {
		this.inputFile = inputFile;
		this.path = path;
		this.readStates = 0;
		this.distinctStates = 0;
		this.noThreads = noThreads;
		this.cut = 0;
	}

	public void process(boolean build) throws Exception {

		List<Integer> experiment;
		// write dy file and get the experiment list
		if ((experiment = readStates()).size() == 0) {
			throw new Exception("Failed to load file!");
		}
		System.gc();

		if (build) {
			graph = buildGraph(experiment);
			System.gc();
			//printJumpsPath();
			printNoJumps();
		}
	}

	private List<Integer> readStates() throws Exception {				
		
		long start = System.currentTimeMillis();

		// this list represents the experiment in a simplified way, -1 = *
		List<Integer> experiment = new ArrayList<Integer>();

		// structure that represents all the conformations within the read file and their incidences in data
		HashMap<Conformation, State> conformations = new HashMap<Conformation, State>();

		// IO
		BufferedReader in = null;
		PrintWriter out = null;
		PrintWriter infoFile = null;

		System.out.println("Opening file...");
		in = new BufferedReader(new FileReader(inputFile));
		System.out.println("Begin of preprocess of file " + inputFile);
		System.out.println("Reading file and labeling states\n\tThis will take a while...");

		String line = in.readLine();
		// check if there is a header
		if ((line.startsWith("E")) || (line.startsWith("[E]"))) {
			// ignore first line (header)
			line = in.readLine();
		}

		StringTokenizer st;

		while (line != null) {
			
			// ignore empty lines
			if (line.equals("")) if ((line = in.readLine()) == null) {break;}
			st = new StringTokenizer(line, " ");
			if (st.countTokens() == 3) {

				int energy = Integer.parseInt(st.nextToken()); 
				st.nextToken(); // ignore second tokentes
				int noContacts = Integer.parseInt(st.nextToken());

				Conformation conf = new Conformation();
				line = conf.read(in);

				// if has been already read
				if (conformations.containsKey(conf)) {
					conformations.get(conf).incidence++;
				} // else add state to the hash
				else {
					State s = new State();
					s.incidence = 1;
					s.id = distinctStates++;
					s.energy = energy;
					s.contacts = noContacts;

					conformations.put(conf, s);
				}
				readStates++;
				// add state to experiment array
				experiment.add(conformations.get(conf).id);
			} else {				
				// if it is not a valid state it is a asterisk
				while (line.startsWith("*")) {
					// ignore all lines with *
					if ((line = in.readLine()) == null) {break;}
				}
				experiment.add(-1);
				// out.println("*");
			}
		}
		
		infoFile = new PrintWriter(new File(path + "info" + ".txt").getAbsoluteFile());
		
		System.out.println(readStates + " states were read.");
		infoFile.println(readStates + " states were read.");
		
		System.out.println(distinctStates + " states are distinct.");
		infoFile.println(distinctStates + " states are distinct.");
		
		System.out.println(" writing DY file...");

		if (in != null) {
			in.close();
		}
		
				
		// structure to build dy file
		ArrayList<FileStates> fileStates = new ArrayList<FileStates>();
		
		System.out.println("mean incidence: " + Math.round(readStates / (double) distinctStates));
		infoFile.println("mean incidence: " + Math.round(readStates / (double) distinctStates));
		
		System.out.println("cut = " + cut);
		infoFile.println("cut = " + cut);
		
		oldKey2NewKey = new HashMap<Integer, Integer>();
		
		liveIndex = 0;
		for (Conformation c : conformations.keySet()) {
			if (conformations.get(c).getIncidence() > cut) {
				State s = conformations.get(c);
				FileStates fs = new FileStates();
				fs.conf = c;
				fs.id = liveIndex;
				oldKey2NewKey.put(s.id, liveIndex);
				fs.contacts = s.contacts;
				fs.energy = s.energy;
				fileStates.add(fs);
				liveIndex++;
			}
		}

		int deadIndex = liveIndex;
		
		for (Conformation c : conformations.keySet()) {
			if (conformations.get(c).getIncidence() <= cut) {
				State s = conformations.get(c);
				FileStates fs = new FileStates();
				fs.conf = c;
				fs.id = deadIndex;
				fs.contacts = s.contacts;
				fs.energy = s.energy;
				fileStates.add(fs);
				oldKey2NewKey.put(s.id, deadIndex);
				deadIndex++;
			}
		}
		
		System.out.println(liveIndex + " states survived the cut");
		infoFile.println(liveIndex + " states survived the cut");
		
		if (infoFile != null) {
			infoFile.close();
		}

		Collections.sort(fileStates);

		out = new PrintWriter(new File(path + "dy_file" + ".data").getAbsoluteFile());
		

		// file header
		out.println("DY");
		// number of states (only distinct and live)
		out.println(distinctStates);
		// number of columns
		out.println(27 * 27);
		// print a blank line
		out.println();
		// start printing all states
		for (FileStates fs : fileStates) {
			// state id
			out.print(fs.id + ";");
			// print the conformation sequence
			for (boolean b : fs.conf.getChain()) {
				if (b) {
					out.print(1 + ";");
				} else {
					out.print(0 + ";");
				}
			}
			// state energy
			out.println(fs.energy);
		}

		if (out != null) {
			out.close();
		}

		long finish = System.currentTimeMillis();
		// Logger.getLogger(this.getClass().getName()).log(Level.INFO,
		// "Read time: {0}s", (finish - start) / 1000.0f);
		System.out.printf("Reading process took: %.2f mins\n",
				((finish - start) / 60000.0f));
		
		return experiment;
	}

	private UndirectedSparseGraph<Integer, Integer> buildGraph(List<Integer> exp) throws Exception {

		System.out.println("Building graph...");
		UndirectedSparseGraph<Integer, Integer> graph = new UndirectedSparseGraph<Integer, Integer>();

		HashSet<EdgesPair> pairs = new HashSet<EdgesPair>();

		int id = 0;
		for (int i = 0; i < exp.size() - 1; i++) {
			
			int source = oldKey2NewKey.get(exp.get(i));
			int target = oldKey2NewKey.get(exp.get(i + 1));
			
			EdgesPair pair = new EdgesPair(source, target);
			if (!pairs.contains(pair)) {
				pairs.add(pair);
				graph.addEdge(id++, target, source);
			}
		}
		
		return graph;
	}
	
	private void printNoJumps() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this may take serveral minutes...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "jumps_file" + ".data").getAbsoluteFile());

		// print header
		out.println("x y [path]");
		
		out.println(liveIndex);

		Integer[] vertices = new Integer[graph.getVertices().size()];
		graph.getVertices().toArray(vertices);

		int div = vertices.length / noThreads;
		int begin = 0;
		int end = div + (vertices.length % noThreads);

		DistanceGetter[] threads = new DistanceGetter[noThreads];
		
		for (int i = 0; i < threads.length ; i++) {
			threads[i] = new DistanceGetter(i, vertices, begin, end, graph, out);
			threads[i].start();
			begin = end;
			end = begin + div;
		}
		// wait for threads to finish
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		if (out != null) {
			out.close();
		}

		long finish = System.currentTimeMillis();
		System.out.printf(
				"Distance calculation process took: %d mins %d secs\n",
				(int) ((finish - start) / 60000),
				(int) ((finish - start) % 60000) / 1000);

	}
	
	private void printJumpsPathCompact() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this will take a long time...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "jumps_file" + ".data").getAbsoluteFile());

		// print header
		out.println("x y [path]");
		
		

		// get number of vertices
		int numVertices = graph.getVertices().size();
		
		// result should`t be more than 500 ??
		result = new int[500];
		
		// redundancy avoidance matrix
		boolean[][] countedMatrix = new boolean[numVertices][numVertices];
		
		// distance between vertices
		Number dist;
		
		// output
		String output;
		
		// counters
		int i, j, k, l;
		
		// path max size
		int limit;
		
		// for all vertices
		for (i = 0; i < numVertices; i++) {
			
			// print progress
			if (i % (numVertices / 100) == 0) {
				System.out.println("progress: " + i * 100 / (float) numVertices + "%");
				//System.gc();
			}
			
			// get graph shortest paths
			sp = new UnweightedShortestPath<Integer, Integer>(graph);
			
			// get incoming edges for the source vertices[i]
			incomEdges = sp.getIncomingEdgeMap(i);
			
            // for each i get its respective distance for ALMOST all vertices
			for (j = numVertices - 1; j > i; j--) {
            	
            	// if the connection has already been calculated
            	if (countedMatrix[i][j]) {
            		continue;
            	}
            	
            	// get distance between i and j
            	dist = sp.getDistance(i, j);
            	
            	// TODO verify if this check is necessary
            	if (dist == null) { 
					System.err.println("null distance between" + i + " and " + j);
					continue;
				}

            	// TODO verify if this check is necessary
            	if (dist.intValue() == 0) { 
            		System.err.println("distance = 0 between " + i + " and " + j);
            		continue;
            	}

            	if (dist.intValue() == 1) { 
            		out.println(i + " " + j);
            		continue;
            	}

        		output = "";
        		limit = getShortestPath(i, j);
        		for (k = limit; k >= 0; k--) {
        			output += result[k] + " ";
    				for (l = k - 1; l >= 0 ; l-- ) {
    					countedMatrix[result[k]][result[l]] = true;
    				}
        		}
        		out.println(output);
            }
            
            // clear used data
            incomEdges.clear();
            sp.reset();
		}

		System.out.println("progress: finished");

		if (out != null) {
			out.close();
		}

		long finish = System.currentTimeMillis();
		System.out.printf(
				"Distance calculation process took: %d mins %d secs\n",
				(int) ((finish - start) / 60000),
				(int) ((finish - start) % 60000) / 1000);

	}
	
	private void printJumpsPath() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this will take a long time...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "jumps_file" + ".data").getAbsoluteFile());

		// print header
		out.println("x y [path]");
		
		out.println(liveIndex);

		// get number of vertices
		int numVertices = graph.getVertices().size();
		
		// result should not be more than 50 
		result = new int[500];
		
		// distance between vertices
		Number dist;
		
		// output
		String output;
		
		// counters
		int i, j, k, l;
		
		// path max size
		int limit;
		
		// for all vertices
		for (i = 0; i < numVertices; i++) { 
		//for (i = 0; i < liveStates; i++) {			
		
			// print progress
			if (numVertices / 100 != 0) {
				if (i % (numVertices / 100) == 0) {
					System.out.println("progress: " + i * 100 / (float) numVertices + "%");
					//System.gc();
				}
			}
			
			// get graph shortest paths
			sp = new UnweightedShortestPath<Integer, Integer>(graph);
			
			// get incoming edges for the source vertices[i]
			incomEdges = sp.getIncomingEdgeMap(i);
			
            // for each i get its respective distance for ALMOST all vertices
			for (j = numVertices - 1; j > 0; j--) { 
			//for (j = liveStates - 1; j > 0; j--) {
            	
            	// get distance between i and j
            	dist = sp.getDistance(i, j);
            	
            	// TODO verify if this check is necessary
            	if (dist == null) { 
					System.err.println("null distance between" + i + " and " + j);
					continue;
				}

            	if (dist.intValue() == 0) { 
            		continue;
            	}

            	if (dist.intValue() == 1) { 
            		out.println(i + " " + j);
            		continue;
            	}

        		output = "";
        		limit = getShortestPath(i, j);
        		for (k = limit; k >= 0; k--) {
        			output += result[k] + " ";
        		}
        		out.println(output);
            }
            
            // clear used data
            incomEdges.clear();
            sp.reset();
		}

		System.out.println("progress: finished");

		if (out != null) {
			out.close();
		}

		long finish = System.currentTimeMillis();
		System.out.printf(
				"Distance calculation process took: %d mins %d secs\n",
				(int) ((finish - start) / 60000),
				(int) ((finish - start) % 60000) / 1000);

	}
	
	private int getShortestPath(int v1, int v2) {
    	Pair<Integer> ends;
    	int i = 0;
    	while (v2 != v1) {
    		result[i++] = v2;
    		ends = graph.getEndpoints(incomEdges.get(v2));
    		v2 = (ends.getFirst() != v2 ? ends.getFirst() : ends.getSecond());
    	} 
    	result[i] = v2;
    	return i;
    }
	
	private void printJumpsPathParallel() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this will take a long time...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "dist_path_file" + ".data").getAbsoluteFile());

		out.println("x y [path]");

		Integer[] vertices = new Integer[graph.getVertices().size()];
		graph.getVertices().toArray(vertices);
		sp = new UnweightedShortestPath<Integer, Integer>(graph);
		
		for (int i = 0; i < vertices.length; i++) {
			if (i % vertices.length/100 < i) {
				System.out.println("progress: " + i * 100 / (float) vertices.length + "%");
			}
            for (int j = i; j < vertices.length; j++) {
            	Number dist = sp.getDistance(vertices[i], vertices[j]);
            	//TODO verify if exists dist == null for the full file
            	if ((dist != null) && (dist.intValue() > 1)) {
            		ConformationsShortestPath csp = 
            				new ConformationsShortestPath(sp, graph, vertices[i], vertices[j], out);
            		Thread t = new Thread(csp);
            		t.start();
            	}
				if ((dist != null) && (dist.intValue() == 1)) { 
					out.println(vertices[i] + " " + vertices[j]);
				}
            }
        }
		System.out.println("progress: finished");
		
		if (out != null) {
			out.close();
		}

		long finish = System.currentTimeMillis();
		System.out.printf(
				"Distance calculation process took: %d mins %d secs\n",
				(int) ((finish - start) / 60000),
				(int) ((finish - start) % 60000) / 1000);
	}

	public String getDyFile() {return path;}
	public String getInputFile() {return inputFile;}
	public int getDistinctStates() {return distinctStates;}
	public int getNoThreads() {return noThreads;}
	public int getReadStates() {return readStates;}
	
	public void setNoConformations(int noConformations) {}
	public void setNoThreads(int noThreads) {this.noThreads = noThreads;}
	public void setCut(int cut) {this.cut = cut;}
}

class FileStates implements Comparable<FileStates>{

	Conformation conf;
	int contacts;
	int id;
	int energy;
	
	@Override
	public int compareTo(FileStates o) {
		return energy - o.energy;
	}

	@Override
	public String toString() {
		return energy + ";";
	}
}
class EdgesPair {
	
	int source;
	int target;
	
	public EdgesPair(int source, int target) {
		this.source = source;
		this.target = target;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + source;
		result = prime * result + target;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EdgesPair other = (EdgesPair) obj;
		if (source != other.source)
			return false;
		if (target != other.target)
			return false;
		return true;
	}
}