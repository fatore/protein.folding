package br.usp.pf.app;

import br.usp.pf.preprocess.Preprocessor;

public class PreprocessorApp {
	
	public static void preprocess(String input, String output, int cut, boolean build) throws Exception {

        Preprocessor pp = new Preprocessor(input, output, cut, 4);
        pp.process(build);
    }
	
	public static void main(String[] args) throws Exception {
		String sequence = "0012";
		int gaps = 1000;
		int cut = 0;
		
		String folder = "/home/fatore/workspace/pf/data/" + sequence + "/" + gaps + "/";
		Preprocessor pp = new Preprocessor(folder + "minimo.dat", folder, cut, 4);
        pp.process(true);
        pp.processRuns(folder + "runs.dat");
	}
}
