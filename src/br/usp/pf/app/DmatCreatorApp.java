package br.usp.pf.app;


import br.usp.pf.dmat.DmatCreator;

public class DmatCreatorApp {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {
		
		DmatCreator dmc = new DmatCreator();

		dmc.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		DmatCreator dmc = new DmatCreator();
		String folder = "/work1/wokspace/pf/data/45568D/";
		dmc.createDmat(folder + "dy_file.data", folder + "jumps_file.data", folder);
		//CreateDistanceMatrix.createDmat(folder + "dy_file.data", null, folder);
	}
}
