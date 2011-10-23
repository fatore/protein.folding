package br.usp.pf.app;

import br.usp.pf.projections.CreateDistanceMatrix;

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
				String action = args[2]; 
				int noconf = Integer.parseInt(args[3]);
				PreprocessorApp.preprocess(rawFile, action, noconf);
			}
			if (operation.equals("-dmat")) {
				String dyFile = args[1];
				String pathFile = args[2];
				CreateDistanceMatrix.createDyAndPath(dyFile, pathFile, "");
				
			}
			if (operation.equals("-pj")) {
				String file = args[1];
				int printInterval = Integer.parseInt(args[2]);
				ProjectionApp.project(file, printInterval);
			}
			if (operation.equals("-project-3d")) {
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
		System.out.println("\t preprocess: -pp [min_file] [action] [noconf]");
		System.out.println("\t create dmat: -dmat [file] *[dmat_file] [action]: 'dy', 'energy', 'dist' or 'dist_path'");
		System.out.println("\t project: -pj [dmat_file] [printInterval]");
		System.out.println("\t visualize: -vz-3d [projection_file] ");
	}
}
