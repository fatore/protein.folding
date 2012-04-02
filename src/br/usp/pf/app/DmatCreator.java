package br.usp.pf.app;


import br.usp.pf.dm.DIstanceMatrixCreator;

public class DmatCreator {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {
		
		DIstanceMatrixCreator dmc = new DIstanceMatrixCreator();

		dmc.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		DIstanceMatrixCreator dmc = new DIstanceMatrixCreator();
		String folder = "/work1/wokspace/pf/data/45568D/";
		dmc.createDmat(folder + "dy_file.data", folder + "jumps_file.data", folder);
		//CreateDistanceMatrix.createDmat(folder + "dy_file.data", null, folder);
	}
}
