package br.usp.pf.app;


import br.usp.pf.dmat.DmatCreator;

public class DmatCreatorApp {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {
		
		DmatCreator dmc = new DmatCreator();

		dmc.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		
		String sequence = "0012-frust";
		int gaps = 1000;
		int cut = 0;
		
		String cutString;
		if (cut > 0) {
			cutString = "cut" + cut;
		} else {
			cutString = "full";
		}
		
		DmatCreator dmc = new DmatCreator();
		String folder = "/home/fatore/workspace/pf/data/" + sequence + "/" + gaps + "/" + cutString + "/";
		
		// static 
		dmc.createDmat(folder + "dy_file.data", null, folder);
		
		// dynamic
		dmc.createDmat(folder + "dy_file.data", folder + "jumps_file.data", folder);
	}
}
