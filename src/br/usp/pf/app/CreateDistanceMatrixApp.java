package br.usp.pf.app;


import br.usp.pf.projections.CreateDistanceMatrix;

public class CreateDistanceMatrixApp {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {

		CreateDistanceMatrix.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		String folder = "/work1/wokspace/pf/data/16-03-12/";
		CreateDistanceMatrix.createDmat(folder + "dy_file.data", null, folder);
		//CreateDistanceMatrix.createDmat(folder + "dy_file.data", null, folder);
	}
}
