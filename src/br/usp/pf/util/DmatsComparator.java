package br.usp.pf.util;

import java.io.File;
import java.io.PrintWriter;

import matrix.MatrixFactory;
import br.usp.pf.dmat.DistanceMatrix;


public class DmatsComparator {
	
	private float min;
	private float max;
	
	public DmatsComparator() {
		this.min = 1f;
		this.max = 0f;
	}
	
	public DistanceMatrix getComparationDmat(DistanceMatrix dmat1, DistanceMatrix dmat2) {
		
		if (dmat1.getElementCount() != dmat2.getElementCount()) {
			System.err.println("ERROR: MATRICES MUST HAVE SAME DIMENSION");
			return null;
		} 		
		
		DistanceMatrix result = new DistanceMatrix(dmat1.getElementCount());
//		result.setClassData(dmat1.getClassData());
//		result.setIds(dmat1.getIds());
		
		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {
				float d1 = dmat1.getDistance(i, j);
				float d2 = dmat2.getDistance(i, j);
				float dist = (float) Math.abs(d1 - d2);
//				float dist = (float) Math.pow(Math.abs(d1 - d2), 2);
//				float dist = (float) Math.pow(Math.abs(d1 - d2), 0.5);
//				float dist = (float) Math.log(1 + Math.abs(d1 - d2));
				result.setDistance(i, j, dist);
				min = (dist < min) ? dist : min;
				max = (dist > max) ? dist : max;
			}
		}
		
		return result;
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
	
	public void compareDmats(String dmat1File, String dmat2File) throws Exception {
		
		DistanceMatrix dmat1 = new DistanceMatrix(dmat1File);
		DistanceMatrix dmat2 = new DistanceMatrix(dmat2File);
		
		DistanceMatrix resultDmat = getComparationDmat(dmat1, dmat2);
		
		String folder = new File(dmat1File).getParentFile().getParentFile().getPath() + "/dmats/";
		String filename = folder + "dmats-comparation.dmat";
		resultDmat.save(filename);
	}
	
	public void compareProjections(String dy1File, String dy2File) throws Exception {
		
		DistanceMatrix dy1 = new DistanceMatrix(MatrixFactory.getInstance(dy1File), new EuclidianDistance());
		DistanceMatrix dy2 = new DistanceMatrix(MatrixFactory.getInstance(dy2File), new EuclidianDistance());
		
		DistanceMatrix resultDmat = getComparationDmat(dy1, dy2);
		
		String folder = new File(dy1File).getParentFile().getParentFile().getPath() + "/dmats/";
		String filename = folder + "projections-comparation.dmat";
		resultDmat.save(filename);
	}
	
	public void synchronizeDistances(String dmat1File, String dmat2File) throws Exception {
		DistanceMatrix dmat1 = new DistanceMatrix(dmat1File);
		DistanceMatrix dmat2 = new DistanceMatrix(dmat2File);
		
		float min = (dmat1.getMinDistance() < dmat2.getMinDistance()) ? dmat1.getMinDistance() : dmat2.getMinDistance();
		float max = (dmat1.getMaxDistance() > dmat2.getMaxDistance()) ? dmat1.getMaxDistance() : dmat2.getMaxDistance();
		
		float sum = 0;
		int count = 0;
		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {
				
				float dist = dmat1.getDistance(i, j);
				dist = (dist - min) / (max - min);
				dmat1.setDistance(i, j, dist);
				sum += dist;
				
				count++;
			}
		}
		float mean1 = sum / count;
		
		sum = count = 0;
		for (int i = 0; i < dmat2.getElementCount(); i++) {
			for (int j = i + 1; j < dmat2.getElementCount(); j++) {
				
				float dist = dmat2.getDistance(i, j);
				dist = (dist - min) / (max - min);
				dmat2.setDistance(i, j, dist);
				sum += dist;
				
				count++;
			}
		}
		float mean2 = sum / count;
		
		System.out.println("Mean of sequence " + new File(dmat1File).getParentFile().getParentFile().
				getParentFile().getParentFile().getName() + ": " + mean1);
		System.out.println("Mean of sequence " + new File(dmat2File).getParentFile().getParentFile().
				getParentFile().getParentFile().getName() + ": " + mean2);
		
		dmat1.save(new File(dmat1File).getPath());
		dmat2.save(new File(dmat2File).getPath());
		
	}
	
	public void synchronizeDistance(String dmatFile) throws Exception {
		DistanceMatrix dmat = new DistanceMatrix(dmatFile);
		
		float sum = 0;
		int count = 0;
		for (int i = 0; i < dmat.getElementCount(); i++) {
			for (int j = i + 1; j < dmat.getElementCount(); j++) {
				
				float dist = dmat.getDistance(i, j);
				dist = (dist - min) / (max - min);
				dmat.setDistance(i, j, dist);
				sum += dist;
				
				count++;
			}
		}
		float mean = sum / count;
		
		
		
		sum = 0;
		count = 0;
		for (int i = 0; i < dmat.getElementCount(); i++) {
			for (int j = i + 1; j < dmat.getElementCount(); j++) {
				
				float dist = dmat.getDistance(i, j);
				sum += Math.pow((dist - mean), 2);
				count++;
			}
		}
		float stdDev = sum / (count - 1);
		
		System.out.println("Sequence " + new File(dmatFile).getParentFile().getParentFile().
				getParentFile().getParentFile().getName() + ":");
		System.out.println("\tMean: " + mean);
		System.out.println("\tStandard Deviation: " + stdDev);
		
		dmat.save(new File(dmatFile).getPath());
		
	}
}










