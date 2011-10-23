package br.usp.pf.app;

import java.io.File;

import br.usp.pf.preprocess.Preprocessor;

public class PreprocessorApp {
	
	public static void preprocess(String file, String action, int noconf) throws Exception {

		String filename = new File(file).getName().split("\\.")[0];
		String folder = "data/" + filename + "/" + noconf + "/";
		new File(folder).mkdirs(); 

        Preprocessor pp = new Preprocessor(file, folder);
        pp.setNoConformations(noconf);

        pp.process(action);
    }
	
	public static void main(String[] args) throws Exception {
		PreprocessorApp.preprocess("/d/ic/protein_folding/data/raw/23-09-11.dat", "path", 1200);
	}
}
