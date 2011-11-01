package br.usp.pf.projections;

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

import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 * 
 * @author fm
 */
public class CreateDistanceMatrix {


	private static DistanceMatrix jumpsPathParser(DistanceMatrix dmat1, int size,
			String filename) throws IOException {

		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int i, j, k, x, y, prev, next, incFactor;
		float dist;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						if (dmat.getDistance(x, y) == 0) {
							dist = 0;
							incFactor = j - i - 1;
							next = Integer.parseInt(linePieces[i]);
							for (k = i + 1; k < j; k++) {
								prev = next;
								next = Integer.parseInt(linePieces[k]);
								dist += dmat1.getDistance(prev, next);
								// dist += dmat1.getDistance(prev, next) *
								// incFactor;
								incFactor--;
							}
							dist += dmat1.getDistance(next, y);
							dmat.setDistance(x, y, dist);
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
	
	private static DistanceMatrix energyParser(AbstractMatrix matrix) throws IOException {
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

	private static DistanceMatrix jumpsNoParser(DistanceMatrix dmat1, int size,
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
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						if (dmat.getDistance(x, y) == 0) {
							dmat.setDistance(x, y, j - i - 1);
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
	
	private static DistanceMatrix jumpsMaxDist(DistanceMatrix dmat1, int size,
			String filename) throws IOException {

		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;
		String line;
		String[] linePieces;
		int i, j, k, x, y, prev, next, incFactor;
		float dist;

		try {
			in = new BufferedReader(new FileReader(filename));

			line = in.readLine();
			while ((line = in.readLine()) != null) {

				linePieces = line.split(" ");

				for (i = 0; i < linePieces.length; i++) {
					for (j = i + 1; j < linePieces.length; j++) {
						x = Integer.parseInt(linePieces[i]);
						y = Integer.parseInt(linePieces[j]);
						if (dmat.getDistance(x, y) == 0) {
							dist = 0;
							incFactor = j - i - 1;
							next = Integer.parseInt(linePieces[i]);
							for (k = i + 1; k < j; k++) {
								prev = next;
								next = Integer.parseInt(linePieces[k]);	
								if (dmat1.getDistance(prev, next) > dist) {
									dist = dmat1.getDistance(prev, next);
								}
								incFactor--;
							}
							if (dmat1.getDistance(next, y) > dist) {
								dist = dmat1.getDistance(next, y);
							}
							dmat.setDistance(x, y, dist);
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

	/**
	 * 
	 * @param files: [dyFile, jumpsFile]
	 * @param outputFolder
	 * @param jumpsAction: "count", "max" or "sum"
	 * @param weights: [dy, jumps, energy]
	 * @throws Exception
	 */
	public static void createDmat(String[] files, String outputFolder, 
			String jumpsAction, int[] weights) throws Exception {
		
		List<DistanceMatrix> dmats = new ArrayList<DistanceMatrix>();
		List<Integer> weightList = new ArrayList<Integer>();

		AbstractMatrix matrix = MatrixFactory.getInstance(files[0]);
		
		int index = 0;
		dmats.add(new DistanceMatrix(matrix, new BinaryDistance()));
		weightList.add(weights[index]);
		index++;
		
		if (weights[index] != 0) {
			switch (jumpsAction) {			
				case "count":
					dmats.add(jumpsNoParser(dmats.get(0), matrix.getRowCount(), files[index]));
					break;
					
				case "max":
					dmats.add(jumpsMaxDist(dmats.get(0), matrix.getRowCount(), files[index]));
					break;
					
				case "sum":
					dmats.add(jumpsPathParser(dmats.get(0), matrix.getRowCount(), files[index]));
					break;
		
				default:
					throw new Exception();
			}
			weightList.add(weights[index]);
		}
		index++;
		
		if (weights[index] != 0) {
			dmats.add(energyParser(matrix));
			weightList.add(weights[index]);
		}
		
		for (int i = 0; i < dmats.get(0).getElementCount(); i++) {

			for (int j = i + 1; j < dmats.get(0).getElementCount(); j++) {

				float dist = 0;
				for (int k = 0; k < dmats.size(); k++) {
					int weight;
					if ((weight = weightList.get(k)) == 0) continue;
					dist += weight * 
							((dmats.get(k).getDistance(i, j) - dmats.get(k).getMinDistance()) 
									/ (dmats.get(k).getMaxDistance() - dmats.get(k).getMinDistance())); 
				}

				dmats.get(0).setDistance(i, j, dist);
			}
		}
		String outfile = "";
		for (int x : weights) {
			outfile += x + "-";
		}
		outfile = outputFolder + outfile + jumpsAction + ".dmat";
		
		dmats.get(0).save(outfile);
		
		
		System.out.println("Done!");
	}
}













