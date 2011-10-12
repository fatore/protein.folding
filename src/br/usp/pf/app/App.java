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
				PreprocessorApp.preprocess(rawFile, noconf);
			}
			if (operation.equals("-pj")) {
				String dyFile = args[1];
				String distFile = args[2];
				int printInterval = Integer.parseInt(args[3]);
				int w1 = Integer.parseInt(args[4]);
				int w2 = Integer.parseInt(args[5]);
				int w3 = Integer.parseInt(args[6]);
				ProjectionApp.project(dyFile, distFile, w1, w2, w3, printInterval);
			}
			if (operation.equals("-vz")) {
				String projFile = args[1];
				GLApp.visualize(projFile);
			}
			
		} catch (Exception e) {
			System.out.println("Error!");
			printUsage();
		}
	}
	
	public static void printUsage() {
		System.out.println("usage:");
		System.out.println("\t preprocess: -pp [min_file] [noconf]");
		System.out.println("\t project: -pj [dy_file] [dist_file] [printInterval]");
		System.out.println("\t visualize: -vz [projection_file] ");
	}
}
