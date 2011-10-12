/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.pf.projections;

import distance.DistanceMatrix;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.MatrixFactory;

/**
 * 
 * @author PC
 */
public class CreateDistanceMatrix {

	public DistanceMatrix execute(AbstractMatrix matrix, float w1, float w2,
			float w3, float w4, String filename, String NCDfile)
			throws IOException {

		DistanceMatrix dmat1 = new DistanceMatrix(matrix, new BinaryDistance());
		DistanceMatrix dmat2 = jumpsParser(matrix.getRowCount(), filename);
		DistanceMatrix dmat3 = energy(matrix);
		DistanceMatrix dmat4 = NCDParser(NCDfile);

		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {

				float dist1 = dmat1.getDistance(i, j);
				dist1 /= dmat1.getMaxDistance();

				float dist2 = dmat2.getDistance(i, j);
				dist2 /= dmat2.getMaxDistance();

				float dist3 = dmat3.getDistance(i, j);
				dist3 /= dmat3.getMaxDistance();

				float dist4 = dmat3.getDistance(i, j);
				dist4 /= dmat4.getMaxDistance();

				// combinando as distancias
				dmat1.setDistance(i, j, dist1 * w1 + dist2 * w2 + dist3 * w3
						+ dist4 * w4);
			}
		}

		return dmat1;
	}

	public DistanceMatrix execute(AbstractMatrix matrix, float w1, float w2,
			float w3, String filename) throws IOException {

		DistanceMatrix dmat1 = new DistanceMatrix(matrix, new BinaryDistance());
		DistanceMatrix dmat2 = jumpsParser(matrix.getRowCount(), filename);
		DistanceMatrix dmat3 = energy(matrix);

		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {

				float dist1 = dmat1.getDistance(i, j);
				dist1 /= dmat1.getMaxDistance();

				float dist2 = dmat2.getDistance(i, j);
				dist2 /= dmat2.getMaxDistance();

				float dist3 = dmat3.getDistance(i, j);
				dist3 /= dmat3.getMaxDistance();

				// combinando as distancias
				dmat1.setDistance(i, j, dist1 * w1 + dist2 * w2 + dist3 * w3);
			}
		}

		return dmat1;
	}
	
	public DistanceMatrix execute(AbstractMatrix matrix, float w1,
			float w3, String filename) throws IOException {

		DistanceMatrix dmat1 = new DistanceMatrix(matrix, new BinaryDistance());
		DistanceMatrix dmat3 = energy(matrix);

		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {

				float dist1 = dmat1.getDistance(i, j);
				dist1 /= dmat1.getMaxDistance();

				float dist3 = dmat3.getDistance(i, j);
				dist3 /= dmat3.getMaxDistance();

				// combinando as distancias
				dmat1.setDistance(i, j, dist1 * w1 + dist3 * w3);
			}
		}

		return dmat1;
	}
	

	public DistanceMatrix execute(AbstractMatrix matrix) throws IOException {

		DistanceMatrix dmat1 = new DistanceMatrix(matrix, new BinaryDistance());

		for (int i = 0; i < dmat1.getElementCount(); i++) {
			for (int j = i + 1; j < dmat1.getElementCount(); j++) {

				
				float dist1 = dmat1.getDistance(i, j);
				if (dist1 <= 0 ) System.out.println(dist1);
				dist1 /= dmat1.getMaxDistance();

				// combinando as distancias
				dmat1.setDistance(i, j, dist1);
			}
		}

		return dmat1;
	}

	private DistanceMatrix NCDParser(String filename) throws IOException {

		BufferedReader in = null;
		in = new BufferedReader(new FileReader(filename));

		DistanceMatrix dmat1 = new DistanceMatrix(Integer.parseInt(in
				.readLine()));

		float max = Float.parseFloat(in.readLine());

		for (int i = 0; i < dmat1.getElementCount() - 1; i++) {
			String[] tokens = in.readLine().split(" ");
			for (int j = 0; j < tokens.length; j++) {

				dmat1.setDistance(i, j + i + 1, Float.parseFloat(tokens[j]));
			}
		}

		if (in != null) {
			in.close();
		}

		return dmat1;
	}

	private DistanceMatrix energy(AbstractMatrix matrix) throws IOException {
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

	private DistanceMatrix jumpsParser(int size, String filename)
			throws IOException {
		DistanceMatrix dmat = new DistanceMatrix(size);
		BufferedReader in = null;

		try {
			in = new BufferedReader(new FileReader(filename));

			String line = in.readLine(); // ignora primeira linha
			while ((line = in.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line, " ");

				if (tokenizer.countTokens() == 3) {
					int x = Integer.parseInt(tokenizer.nextToken());
					int y = Integer.parseInt(tokenizer.nextToken());
					int dist = Integer.parseInt(tokenizer.nextToken());

					dmat.setDistance(x, y, (float) Math.pow(dist, 2));
//					dmat.setDistance(x, y, dist);
				} else {
					System.out.println("ERRO: " + line);
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
}
