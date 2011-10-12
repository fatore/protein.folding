package br.usp.pf.preprocess;

import br.usp.pf.core.Conformation;
import edu.uci.ics.jung.graph.*;
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

	public Preprocessor(String inputFile, String path) {
		this.inputFile = inputFile;
		this.path = path;
		this.readStates = 0;
		this.distinctStates = 0;
		this.noThreads = 2;
	}

	public void process() throws Exception {

		Stack<Integer> exp;
		// write dy file and get the experiment list
		if ((exp = readStates()).empty()) {
			throw new Exception("Failed to load file!");
		}
		System.gc();

		// build the graph for the specified experiment
		 buildGraph(exp);
		System.gc();
	}

	private Stack<Integer> readStates() throws Exception {

		long start = System.currentTimeMillis();

		// this stack represents the experiment in a simplified way, -1 = *
		Stack<Integer> experiment = new Stack();

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

	private void buildGraph(Stack<Integer> exp) throws Exception {

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

		long start = System.currentTimeMillis();

		System.out
				.println("Writing distance files, this will take a long time...");

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

	public String getDyFile() {
		return path;
	}

	public String getInputFile() {
		return inputFile;
	}

	public int getDistinctStates() {
		return distinctStates;
	}

	public int getNoThreads() {
		return noThreads;
	}

	public int getReadStates() {
		return readStates;
	}

	public void setNoConformations(int noConformations) {
		this.noConformations = noConformations;
	}

	public void setNoThreads(int noThreads) {
		this.noThreads = noThreads;
	}

}
