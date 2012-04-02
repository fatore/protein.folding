package br.usp.pf.app;

import java.io.File;

import br.usp.pf.preprocess.Preprocessor;

public class PreprocessorApp {
	
	public static void preprocess(String input, String output, int cut, boolean build) throws Exception {

		String filename = new File(input).getName().split("\\.")[0];
		String folder = output + filename;
		if (cut > 0) {
			folder += "-cut=" + cut;
		}
		folder += "/";
		new File(folder).mkdirs(); 

        Preprocessor pp = new Preprocessor(input, folder, 8);
        pp.setCut(cut);
        pp.process(build);
    }
	
	public static void main(String[] args) throws Exception {
		PreprocessorApp.preprocess(
				"/work1/wokspace/pf/data/45568D.dat", "/work1/wokspace/pf/data/", 0, true);
	}
}
