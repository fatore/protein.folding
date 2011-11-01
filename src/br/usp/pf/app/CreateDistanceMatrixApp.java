package br.usp.pf.app;


import br.usp.pf.projections.CreateDistanceMatrix;

public class CreateDistanceMatrixApp {
	
	public static void createMatrix(String folder, String action, int w1, int w2, int w3) throws Exception {

		CreateDistanceMatrix.createDmat(new String[]{folder +"dy_file.data", folder + "jumps_file.data"},
				folder, action, new int[]{w1, w2, w3});
    }

	public static void main(String[] args) throws Exception {
		int noConf = 600;
		String folder = "data/23-09-11/"+ noConf +"/";
		String action = "count";
		int w1 = 1; 
		int w2 = 0;
		int w3 = 0;
		CreateDistanceMatrix.createDmat(new String[]{folder +"dy_file.data", folder + "jumps_file.data"},
				folder, action, new int[]{w1, w2, w3});
	}
}
