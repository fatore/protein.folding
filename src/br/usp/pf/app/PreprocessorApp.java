package br.usp.pf.app;

import java.io.File;

import br.usp.pf.preprocess.Preprocessor;

public class PreprocessorApp {
	
	public static void preprocess(String input, String output, int cut, boolean build) throws Exception {

		String filename = new File(input).getName().split("\\.")[0];
		String folder = output;
		if (cut > 0) {
			folder += "cut" + cut;
		} else {
			folder += "full";
		}
		folder += "/";
		new File(folder).mkdirs(); 

        Preprocessor pp = new Preprocessor(input, folder, cut, 8);
        pp.process(build);
    }
	
	public static void main(String[] args) throws Exception {
		String sequence = "2221";
		int gaps = 1000;
		
		// static
		PreprocessorApp.preprocess(
				"/work1/wokspace/pf/data/" + sequence + "/dinamico/" + gaps + "/minimo.dat", 
				"/work1/wokspace/pf/data/" + sequence + "/dinamico/" + gaps + "/", 3, true);
	}
}
