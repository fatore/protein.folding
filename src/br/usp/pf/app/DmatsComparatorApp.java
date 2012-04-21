package br.usp.pf.app;


import br.usp.pf.util.DmatsComparator;

public class DmatsComparatorApp {
	
	public static void createMatrix(String file1, String file2, String outputFolder) throws Exception {
		
		DmatsComparator dmc = new DmatsComparator();

		dmc.compareDmats(file1, file2, outputFolder);
		
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
		
		DmatsComparator dmc = new DmatsComparator();
		
		String folder1 = "/work1/wokspace/pf/data/" + sequence + "/estatico/" + gaps + "/" + cutString + "/";
		dmc.compareDmats(folder1 + "static.dmat", folder1 + "dynamic.dmat", folder1);
		dmc.compareDys(folder1 + "static.prj.data", folder1 + "dynamic.prj.data", folder1);
		dmc.compareDmats(folder1 + "comparation.dmat", folder1 + "comparation.prj.dmat", folder1);
	}
}
