package br.usp.pf.app;

import java.io.File;

import br.usp.pf.preprocess.Preprocessor;

public class PreprocessorApp {
	
	public static void preprocess(String file, int noconf, int cut, boolean build) throws Exception {

		String filename = new File(file).getName().split("\\.")[0];
		String folder = "data/" + filename + "/" + noconf + "/";
		new File(folder).mkdirs(); 

        Preprocessor pp = new Preprocessor(file, folder);
        pp.setNoConformations(noconf);
        pp.setCut(cut);

        pp.process(build);
    }
	
	public static void main(String[] args) throws Exception {
		PreprocessorApp.preprocess(
				"data/raw/8444.dat", 99999999, 3, false);
	}
}
