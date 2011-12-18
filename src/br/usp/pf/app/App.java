package br.usp.pf.app;

public class App {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			printUsage();
			System.exit(0);
		}
		String operation = args[0];
		
		try {
			if (operation.equals("-pp")) {
				String rawFile = args[1];
				int noconf = Integer.parseInt(args[2]);
				int cut = Integer.parseInt(args[3]);
				boolean build = Boolean.parseBoolean(args[4]);
				PreprocessorApp.preprocess(rawFile, noconf, cut, build);
			}
			if (operation.equals("-dmat")) {
				String folder = args[1];
				String action = args[2];
				int w1 = Integer.parseInt(args[3]);
				int w2 = Integer.parseInt(args[4]);
				int w3 = Integer.parseInt(args[5]);
				CreateDistanceMatrixApp.createMatrix(folder, action, w1, w2, w3);
			}
			if (operation.equals("-pj")) {
				String file = args[1];
				int printInterval = Integer.parseInt(args[2]);
				boolean fast = Boolean.parseBoolean(args[3]);
				ProjectionApp.project(file, printInterval, fast);
			}
			if (operation.equals("-3d")) {
				String projFile = args[1];
				GLApp.visualize(projFile);
			}
			
		} catch (Exception e) {
			System.out.println("Error!");
			e.printStackTrace();
			printUsage();
		}
	}
	
	public static void printUsage() {
		System.out.println("usage:");
		System.out.println("\t preprocess: -pp [min_file] [noconf] [cut]:int" +
			"[build_graph]:  true or false");
		System.out.println("\t create dmat: -dmat [files_folder] [action]:'count', 'sum', 'max' [weights, 3]");
		System.out.println("\t project: -pj [dmat_file] [printInterval] [fast]: true or false");
		System.out.println("\t visualize: -3d [projection_file] ");
	}
}
