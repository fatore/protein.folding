package br.usp.pf.app;


import br.usp.pf.util.DmatsComparator;

public class DmatsComparatorApp {
	
	public static void createMatrix(String file1, String file2) throws Exception {
		
		DmatsComparator dmc = new DmatsComparator();

		dmc.compareDmats(file1, file2);
		
    }

	public static void main(String[] args) throws Exception {
		
		String sequence1 = "0012";
		String sequence2 = "0012-frust";
		String sequence3 = "2221";
		String sequence4 = "43157";
		int gaps = 1000;
		int cut = 0;
		
		String cutString;
		if (cut > 0) {
			cutString = "cut" + cut;
		} else {
			cutString = "full";
		}
		String folder;
		
		DmatsComparator dmc = new DmatsComparator();
		
		// compare
		folder = "/home/fatore/workspace/pf/data/" + sequence1 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.compareDmats(folder + "static.dmat", folder + "dynamic.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence2 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.compareDmats(folder + "static.dmat", folder + "dynamic.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence3 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.compareDmats(folder + "static.dmat", folder + "dynamic.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence4 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.compareDmats(folder + "static.dmat", folder + "dynamic.dmat");		
		
		// synchronize
		folder = "/home/fatore/workspace/pf/data/" + sequence1 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.synchronizeDistance(folder + "dmats-comparation.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence2 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.synchronizeDistance(folder + "dmats-comparation.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence3 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.synchronizeDistance(folder + "dmats-comparation.dmat");
		
		folder = "/home/fatore/workspace/pf/data/" + sequence4 + "/" + gaps + "/" + cutString + "/dmats/";
		dmc.synchronizeDistance(folder + "dmats-comparation.dmat");
		
		System.out.println("Done!");
	}
}
