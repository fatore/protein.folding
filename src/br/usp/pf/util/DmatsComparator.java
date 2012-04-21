package br.usp.pf.util;

import matrix.MatrixFactory;
import br.usp.pf.dmat.BinaryDistance;
import distance.DistanceMatrix;


public class DmatsComparator {
	
	public DistanceMatrix getCompareDmat(DistanceMatrix dmat1, DistanceMatrix dmat2) {
		
		if (dmat1.getElementCount() != dmat2.getElementCount()) {
			System.err.println("ERROR: MATRICES MUST HAVE SAME DIMENSION");
			return null;
		} 		
		int size = dmat1.getElementCount();
		
		DistanceMatrix resultDmat = new DistanceMatrix(size);
		
		float sumOfMeans = 0;
		
		for (int i = 0; i < resultDmat.getElementCount(); i++) {
			int count = 0;
			float sum = 0;
			for (int j = i + 1; j < resultDmat.getElementCount(); j++) {
				float d1 = dmat1.getDistance(i, j);
				float d2 = dmat2.getDistance(i, j);
				float dist = Math.abs(d1 - d2);
				sum += dist;
				resultDmat.setDistance(i, j, dist);
				count++;
			}
			float mean = 0;
			if (count != 0) {
				mean = sum / count;
			}
			sumOfMeans += mean;
		}
		
		float meanOfMeans = sumOfMeans / size;
		
		System.out.println("MEAN: " + meanOfMeans);
		
		resultDmat.setClassData(dmat1.getClassData());
		
//		normalize(resultDmat);
		
		return resultDmat;
	}
	
	private void normalize(DistanceMatrix dmat) {//
		float min = dmat.getMinDistance();
		float max = dmat.getMaxDistance();
		for (int i = 0; i < dmat.getElementCount(); i++) {
			for (int j = i + 1; j < dmat.getElementCount(); j++) {
				// get distance
				float dist = dmat.getDistance(i, j);
				
				// normalize between 0 and 1
				dist = (dist - min) / (max - min);
				dmat.setDistance(i, j, dist);
			}
		}
	}
	
	public void compareDmats(String dmat1File, String dmat2File, String outputFolder) throws Exception {
		
		DistanceMatrix dmat1 = new DistanceMatrix(dmat1File);
		DistanceMatrix dmat2 = new DistanceMatrix(dmat2File);
		
		DistanceMatrix resultDmat = getCompareDmat(dmat1, dmat2);
		
		String outfile = outputFolder + "comparation";
		outfile += ".dmat";
		resultDmat.save(outfile);
		System.out.println("Done!");
	}
	
	public void compareDys(String dy1File, String dy2File, String outputFolder) throws Exception {
		
		DistanceMatrix dy1 = new DistanceMatrix(MatrixFactory.getInstance(dy1File), new EuclidianDistance());
		DistanceMatrix dy2 = new DistanceMatrix(MatrixFactory.getInstance(dy2File), new EuclidianDistance());
		
		DistanceMatrix resultDmat = getCompareDmat(dy1, dy2);
		
		String outfile = outputFolder + "comparation";
		outfile += ".prj.dmat";
		resultDmat.save(outfile);
		System.out.println("Done!");
	}
}










