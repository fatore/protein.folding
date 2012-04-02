package br.usp.pf.dm;

import distance.DistanceMatrix;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.usp.pf.util.CountProteinConformation;

import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 * 
 * @author fm
 */
public class CreateDistanceMatrix {

	private int liveStates;

	private DistanceMatrix jumpsSumParser(DistanceMatrix dmat1, int size,
			String filename) throws IOException {

		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int i, j, k, x, y, prev, next, incFactor;
		float distSum;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			
			liveStates = Integer.parseInt(in.readLine());
			
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						distSum = 0;
						incFactor = j - i - 1;
						next = Integer.parseInt(linePieces[i]);
						for (k = i + 1; k < j; k++) {
							prev = next;
							next = Integer.parseInt(linePieces[k]);
							distSum += dmat1.getDistance(prev, next);
							// dist += dmat1.getDistance(prev, next) *
							// incFactor;
							incFactor--;
						}
						distSum += dmat1.getDistance(next, y);
						
						if (distSum > dmat.getDistance(x, y)) {
							dmat.setDistance(x, y, distSum);
						}
					}
				}
			}
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					Logger.getLogger(CountProteinConformation.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}
		}

		return dmat;
	}
	
	private DistanceMatrix energyParser(AbstractMatrix matrix) throws IOException {
		DistanceMatrix dmat = new DistanceMatrix(matrix.getRowCount());

		for (int i = 0; i < matrix.getRowCount(); i++) {
			float klass1 = matrix.getRow(i).getKlass();

			for (int j = i + 1; j < matrix.getRowCount(); j++) {
				float klass2 = matrix.getRow(j).getKlass();
				dmat.setDistance(i, j, Math.abs(klass1 - klass2));
			}
		}

		return dmat;
	}

	private DistanceMatrix jumpsPathCountParser(DistanceMatrix dmat1, int size,
			String filename)
			throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int i, j, x, y;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			
			liveStates = Integer.parseInt(in.readLine());
			
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						int jumps = j - i - 1;
						if (dmat.getDistance(x, y) != 0) {
							if (jumps != dmat.getDistance(x, y)) {
								System.err.println("error");
							}
						}
						if (jumps > dmat.getDistance(x, y)) {
							dmat.setDistance(x, y, jumps);
						}
					}
				}
			}
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					Logger.getLogger(CountProteinConformation.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}
		}

		return dmat;
	}
	
	private DistanceMatrix jumpsCountParser(DistanceMatrix dmat1, int size,
			String filename)
			throws IOException {
		
		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int x, y;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			
			liveStates = Integer.parseInt(in.readLine());
			
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				x = Integer.parseInt(linePieces[0]);
				y = Integer.parseInt(linePieces[1]);
				int jumps = Integer.parseInt(linePieces[2]);
				dmat.setDistance(x, y, jumps);
			}
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					Logger.getLogger(CountProteinConformation.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}
		}

		return dmat;
	}
	
	private DistanceMatrix jumpsMaxDist(DistanceMatrix dmat1, int size,
			String filename) throws IOException {

		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int i, j, k, x, y, next, incFactor;
		float maxDist;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			
			liveStates = Integer.parseInt(in.readLine());
						
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						maxDist = dmat1.getDistance(x, y);
						incFactor = j - i - 1;
						next = Integer.parseInt(linePieces[i]);
						for (k = i + 1; k < j; k++) {
							next = Integer.parseInt(linePieces[k]);	
							if (dmat1.getDistance(x, next) > maxDist) {
								maxDist = dmat1.getDistance(x, next);
							}
							incFactor--;
						}
						if (maxDist > dmat.getDistance(x, y)) {
							dmat.setDistance(x, y, maxDist);
						}
					}
				}
			}
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException ex) {
					Logger.getLogger(CountProteinConformation.class.getName())
							.log(Level.SEVERE, null, ex);
				}
			}
		}

		return dmat;
	}
	
	private void normalizeDistanceMatrix(DistanceMatrix distanceMatrix) {
		float min = distanceMatrix.getMinDistance();
		float max = distanceMatrix.getMaxDistance();
		
		for (int i = 0; i < distanceMatrix.getElementCount(); i++) {
			for (int j = i + 1; j < distanceMatrix.getElementCount(); j++) {
				// get distance
				float dist = distanceMatrix.getDistance(i, j);
				
				// normalize between 0 and 1
				dist = (dist - min) / (max - min);
				distanceMatrix.setDistance(i, j, dist);
			}
		}
	}

	/**
	 * 
	 * @param files: [dyFile, jumpsFile]
	 * @param outputFolder
	 * @param jumpsAction: "count", "max" or "sum"
	 * @param weights: [dy, jumps, energy]
	 * @throws Exception
	 */
	public void createDmat(String dyFile, String jumpsFile, String outputFolder) throws Exception {
		
		// create metric distance matrix
		DistanceMatrix metric = new DistanceMatrix
				(MatrixFactory.getInstance(dyFile), new BinaryDistance());
		
		System.out.println("algo");
		
		float min = metric.getMinDistance();
		float max = metric.getMaxDistance();
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(dyFile));

			in.readLine();
			liveStates = Integer.parseInt(in.readLine());
			
			in.close();
			
		} catch (FileNotFoundException ex) {
			throw new IOException(ex.getMessage());
		}
		
		// if exists, read jumps matrix
//		if (jumpsFile != null) {
//			dmats.add(jumpsCountParser(dmats.get(0), matrix.getRowCount(), jumpsFile));
//		}
//		
//		
//		
//		for (int i = 0; i < dmats.get(0).getElementCount(); i++) {
//			for (int j = i + 1; j < dmats.get(0).getElementCount(); j++) {
//				float dist = 0;
//				dist += (dmats.get(0).getDistance(i, j)) / (dmats.get(0).getMaxDistance());
//				if (jumpsFile != null) {
//					dist += (dmats.get(1).getDistance(i, j)) / (dmats.get(1).getMaxDistance()) * dist;
//					dist = dist / 2;
//				}
//				dmats.get(0).setDistance(i, j, dist);
//			}
//		}
//		String outfile = outputFolder + "dmat";
//		if (jumpsFile != null) {
//			outfile += "-jumps";
//		}
//		outfile += ".data";
//		
//		DistanceMatrix resultDmat = new DistanceMatrix(liveStates);
//		float[] cdata = new float[liveStates];
//		for (int i = 0; i < liveStates; i++) {
//			cdata[i] = dmats.get(0).getClassData()[i];
//			for (int j = i + 1; j < liveStates; j++) {
//				resultDmat.setDistance(i, j, dmats.get(0).getDistance(i, j));
//			}
//		}
//		resultDmat.setClassData(cdata);
//		resultDmat.save(outfile);
		
		System.out.println("Done!");
	}
}













