package br.usp.pf.app;


import br.usp.pf.projections.CreateDistanceMatrix;

public class CreateDistanceMatrixApp {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {

		CreateDistanceMatrix.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		String folder = "/home/fatore/workspace/pf/data/minimo-old/";
		CreateDistanceMatrix.createDmat(folder + "dy_file.data", folder + "jumps_file.data", folder);
		//CreateDistanceMatrix.createDmat(folder + "dy_file.data", null, folder);
	}
}
