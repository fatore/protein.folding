package br.usp.pf.app;


import br.usp.pf.dmat.DmatCreator;

public class DmatCreatorApp {
	
	public static void createMatrix(String dyFile, String jumpsFile, String outputFolder) throws Exception {
		
		DmatCreator dmc = new DmatCreator();

		dmc.createDmat(dyFile, jumpsFile, outputFolder) ;
    }

	public static void main(String[] args) throws Exception {
		
		String sequence = "43157";
		int gaps = 1000;
		int cut = 3;
		
		String cutString;
		if (cut > 0) {
			cutString = "cut" + cut;
		} else {
			cutString = "full";
		}
		
		DmatCreator dmc = new DmatCreator();
		
		// static 
		String staticFolder = "/work1/wokspace/pf/data/" + sequence + "/estatico/" + gaps + "/" + cutString + "/";
		dmc.createDmat(staticFolder + "dy_file.data", null, staticFolder);
		
		// dynamic
		String dynamicFolder = "/work1/wokspace/pf/data/" + sequence + "/dinamico/" + gaps + "/" + cutString + "/";
		dmc.createDmat(dynamicFolder + "dy_file.data", dynamicFolder + "jumps_file.data", staticFolder);
	}
}
