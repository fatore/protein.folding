package br.usp.pf.preprocess;

import br.usp.pf.core.Conformation;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.graph.util.Pair;

import java.io.*;
import java.util.*;

/**
 * 
 * @author Francisco Morgani Fatore
 */
class FileStates {

	Conformation conf;
	int contacts;
	int id;
	int energy;
}

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
	private int noConformations;
	
	private static UndirectedSparseGraph<Integer, Integer> graph;
	private static UnweightedShortestPath<Integer, Integer> sp;
	private static Map<Integer, Integer> incomEdges;
	private static int[] result;

	public Preprocessor(String inputFile, String path) {
		this.inputFile = inputFile;
		this.path = path;
		this.readStates = 0;
		this.distinctStates = 0;
		this.noThreads = 2;
	}

	public void process() throws Exception {

		Stack<Integer> experiment;
		// write dy file and get the experiment list
		if ((experiment = readStates()).empty()) {
			throw new Exception("Failed to load file!");
		}
		System.gc();

		graph = buildGraph(experiment);
		System.gc();
		
		printJumpsPath();
	}

	private Stack<Integer> readStates() throws Exception {

		long start = System.currentTimeMillis();

		// this stack represents the experiment in a simplified way, -1 = *
		Stack<Integer> experiment = new Stack<Integer>();

		// structure that represents all the conformations within the read file
		// and its incidences in data
		HashMap<Conformation, State> conformations = new HashMap<Conformation, State>();

		// IO
		BufferedReader in = null;
		PrintWriter out = null;

		System.out.println("Opening file...");
		in = new BufferedReader(new FileReader(inputFile));
		System.out.println("Begin of preprocess of file " + inputFile);
		System.out
				.println("Reading file and labeling states\n\tThis will take a while...");

		String line = in.readLine();
		// check if there is a header
		if ((line.startsWith("E")) || (line.startsWith("[E]"))) {
			// discard first line (header)
			line = in.readLine();
		}

		StringTokenizer st;

		int count = noConformations;
		while ((line != null) && (count > 0)) {
			st = new StringTokenizer(line, " ");
			if (st.countTokens() == 3) {

				int energy = Integer.parseInt(st.nextToken());
				st.nextToken();
				int noContacts = Integer.parseInt(st.nextToken());

				Conformation conf = new Conformation();
				// read conformation
				line = conf.read(in);

				// if this conformation has been already read
				if (conformations.containsKey(conf)) {

					conformations.get(conf).incidence++;
				} // else add state to the hash
				else {
					count--;
					State s = new State();
					// assigns a id to the state
					s.id = distinctStates;
					// first occurrence
					s.incidence = 1;
					// assings energy value to the state
					s.energy = energy;
					// assigns the number of contacts
					s.contacts = noContacts;

					conformations.put(conf, s);

					distinctStates++;
				}
				readStates++;
				// add state to experiment array
				experiment.push(conformations.get(conf).id);
				// out.println(conformations.get(conf).id);
			} else {
				// if it is not a valid state it is a asterisc
				while (line.startsWith("*")) {
					if ((line = in.readLine()) == null) {
						break;
					}
				}
				experiment.push(-1);
				// out.println("*");
			}
		}
		System.out.println(readStates + " states were read.");
		System.out.println(distinctStates + " states are distinct.");
		System.out.println(" writing DY file...");

		if (in != null) {
			in.close();
		}

		// structure to build dy file
		FileStates[] fileStates = new FileStates[distinctStates];
		int i = 0;

		for (Conformation c : conformations.keySet()) {
			fileStates[i] = new FileStates();
			fileStates[i].conf = c;
			i++;
		}
		i = 0;

		for (State s : conformations.values()) {
			fileStates[i].id = s.id;
			fileStates[i].contacts = s.contacts;
			fileStates[i].energy = s.energy;
			i++;
		}

		Arrays.sort(fileStates, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				FileStates f1 = (FileStates) o1;
				FileStates f2 = (FileStates) o2;
				return f2.contacts - f1.contacts;
			}
		});

		out = new PrintWriter(new File(path + "dy_file" + ".data").getAbsoluteFile());

		// file header
		out.println("DY");
		// number of states (only distinct)
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

	private UndirectedSparseGraph<Integer, Integer> buildGraph(Stack<Integer> exp) throws Exception {

		System.out.println("Building graph...");
		UndirectedSparseGraph<Integer, Integer> graph = new UndirectedSparseGraph<Integer, Integer>();

		int cur;
		int nxt;
		int id = 0;

		exp.push(-1);

		// if already starts with a *
		if ((nxt = exp.pop()) == -1) {
			if (!exp.empty()) {
				nxt = (int) exp.pop();
			}
		}

		while (!exp.empty()) {
			// current is last next
			cur = nxt;

			if ((nxt = exp.pop()) == -1) {
				if (!exp.empty()) {
					nxt = (int) exp.pop();
				}
			} else {
				graph.addEdge(id++, cur, nxt);
			}
		}
		
		return graph;
	}
	
	private void printNoJumps() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this will take a long time...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "dist_file" + ".data").
				getAbsoluteFile());

		out.println("x y distance");

		Integer[] vertices = new Integer[graph.getVertices().size()];
		graph.getVertices().toArray(vertices);

		int div = vertices.length / noThreads;
		int begin = 0;
		int end = div + (vertices.length % noThreads);

		DistanceGetter[] threads = new DistanceGetter[noThreads];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new DistanceGetter(i, vertices, begin, end, graph, out);
			threads[i].start();
			begin = end;
			end = begin + div;
		}
		// wait for threads to finish
		for (int i = 0; i < threads.length; i++) {
			System.out.printf("\tWaiting for thread %d to finish...\n", i);
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
	
	private void printJumpsPath() throws Exception {

		long start = System.currentTimeMillis();

		System.out.println("Writing distance files, this will take a long time...");

		PrintWriter out = null;
		out = new PrintWriter(new File(path + "jumps_file" + ".data").getAbsoluteFile());

		// print header
		out.println("x y [path]");

		// get number of vertices
		int numVertices = graph.getVertices().size();
		
		// result should not be more than 50 
		result = new int[50];
		
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
			if (i % (numVertices / 100.0) == 0) {
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

	public String getDyFile() {
		return path;
	}

	/**
	 * @return
	 */
	public String getInputFile() {
		return inputFile;
	}

	/**
	 * @return
	 */
	public int getDistinctStates() {
		return distinctStates;
	}

	/**
	 * @return
	 */
	public int getNoThreads() {
		return noThreads;
	}

	/**
	 * @return
	 */
	public int getReadStates() {
		return readStates;
	}

	/**
	 * @param noConformations
	 */
	public void setNoConformations(int noConformations) {
		this.noConformations = noConformations;
	}

	/**
	 * @param noThreads
	 */
	public void setNoThreads(int noThreads) {
		this.noThreads = noThreads;
	}

}
