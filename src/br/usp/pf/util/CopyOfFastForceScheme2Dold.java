package br.usp.pf.util;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.model.ProjectionModelComp;
import projection.technique.Projection;
import projection.view.ProjectionFrameComp;

/**
 * 
 * @author paulovich
 */
public class CopyOfFastForceScheme2Dold implements Projection {

	/**
	 */
	private float fixedEnergy;
	/**
	 */
	private int[] index;
	/**
	 */
	private int nrIterations;
	/**
	 */
	private int printInterval;
	private static final float EPSILON = 0.0000001f;
	private static final float ACCEPTABLE_ERROR = 0.1f;

	/**
	 */
	ProjectionModelComp model;
	/**
	 */
	ProjectionFrameComp frame;

	public CopyOfFastForceScheme2Dold() {
		this.nrIterations = 50;
		model = new ProjectionModelComp();
		frame = new ProjectionFrameComp();
	}

	public void setNumberIterations(int nriterations) {
		this.nrIterations = nriterations;
	}

	public void setEnergy(float energy) {
		this.fixedEnergy = energy;
	}

	/**
	 * @param printInterval
	 */
	public void setPrintInterval(int printInterval) {
		this.printInterval = printInterval;
	}

	@Override
	public AbstractMatrix project(AbstractMatrix matrix,
			AbstractDissimilarity diss) throws IOException {
		// create the indexes and shuffle them
		index = createIndex(matrix.getRowCount());

		// create the initial projection
		float[][] initial_proj = createInitialProjection(matrix.getRowCount(),
				matrix.getClassData());

		int compsize = (int) Math.sqrt(matrix.getRowCount());
		float decfactor = 1.0f;

		for (int i = 0; i < nrIterations; i++) {
			System.out.println("iteration: " + i);

			decfactor = (float) (Math.pow((1 + (i * 2 + 1)),
					(1.0f / (i * 2 + 1))) - 1);
			iteration(matrix, diss, initial_proj, compsize, decfactor);

			if (i % printInterval == 0) {
				AbstractMatrix projection = createFinalProjection(initial_proj,
						matrix.getIds(), matrix.getLabels(),
						matrix.getClassData());

				ProjectionModelComp model = new ProjectionModelComp();
				model.input(projection);
				model.execute();

				ProjectionFrameComp frame = new ProjectionFrameComp();
				frame.setTitle("iteration " + i);
				frame.input(model.output());
				frame.execute();
			}
		}

		AbstractMatrix projection = createFinalProjection(initial_proj,
				matrix.getIds(), matrix.getLabels(), matrix.getClassData());

		return projection;
	}

	@Override
	public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
		// Create the indexes and shuffle them
		index = createIndex(dmat.getElementCount());

		// create the initial projection
		float[][] initial_proj = createInitialProjection(
				dmat.getElementCount(), dmat.getClassData());

		int compsize = (int) Math.sqrt(dmat.getElementCount());
		float decfactor = 1.0f;
		
		int[] closeToCenter = createIndex(compsize/5);

		int i = 0;
		for (i = 0;; i++) {
			// System.out.println("iteration: " + i);

			decfactor = (float) (Math.pow((1 + (i * 2 + 1)),
					(1.0f / (i * 2 + 1))) - 1);
			// decfactor = 1/8f;
			if (iteration(dmat, initial_proj, compsize, decfactor, closeToCenter)) break;

			if (i % printInterval == 0) {
				AbstractMatrix projection = createFinalProjection(initial_proj,
						dmat.getIds(), dmat.getLabels(), dmat.getClassData());

				model.input(projection);
				model.execute();

				frame.setTitle("iteration " + i);
				frame.input(model.output());
				frame.execute();
			}
		}

		AbstractMatrix projection = createFinalProjection(initial_proj,
				dmat.getIds(), dmat.getLabels(), dmat.getClassData());

		model.input(projection);
		model.execute();

		frame.setTitle("iteration " + i);
		frame.input(model.output());
		frame.execute();

		return projection;
	}

	/**
	 * @param nriterations
	 *            the nriterations to set
	 */

	private boolean iteration(DistanceMatrix dmat, float[][] aux_proj,
			int compsize, float decfactor, int[] closeToCenter) {
		
		float[] cdata = dmat.getClassData();
		double maxDist = 0;

		// for each instance
		for (int i = 0; i < dmat.getElementCount(); i++) {
			int instance = index[i];

			// for each other instance
			// for (int j = 0; j < compsize; j++) {
			// int instance2 = (int) (dmat.getElementCount() * Math.random());

			for (int j = 0; j < dmat.getElementCount(); j++) {
				int instance2 = index[j];
				if (instance == instance2) {
					continue;
				}

				// distance between projected instances
				double x1x2 = (aux_proj[instance2][0] - aux_proj[instance][0]);
				double y1y2 = (aux_proj[instance2][1] - aux_proj[instance][1]);
				double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);
				
				if (dr2 < EPSILON) {
					dr2 = EPSILON;
				}

				float drn = dmat.getDistance(instance, instance2);

				// Calculating the (fraction of) delta
				double delta = Math.sqrt(drn) - Math.sqrt(dr2);
				delta *= Math.abs(delta);
				delta *= decfactor;
				
				if (delta > maxDist) 
					maxDist = delta;

				if (Math.abs(cdata[instance2] - fixedEnergy) < EPSILON) { // estado // nativo // não // move
					aux_proj[instance2][0] = 0;
					aux_proj[instance2][1] = 0;
				} else { // só move os não nativos
					aux_proj[instance2][0] += delta * (x1x2 / dr2);
					aux_proj[instance2][1] += delta * (y1y2 / dr2);
				}

				if (aux_proj[instance2][0] != aux_proj[instance2][0]) {
					System.out.println("Error: Force Scheme >> delta" + delta
							+ ", x1x2=" + x1x2 + ", dr2=" + dr2 + ", drn="
							+ drn);
				}
			}
		}
		if (maxDist < ACCEPTABLE_ERROR)
			return true;
		return false;
	}

	private void iteration(AbstractMatrix matrix, AbstractDissimilarity diss,
			float[][] aux_proj, int compsize, float decfactor) {
		float[] cdata = matrix.getClassData();

		// for each instance
		for (int ins1 = 0; ins1 < matrix.getRowCount(); ins1++) {
			int instance = index[ins1];

			// for each other instance
			for (int ins2 = 0; ins2 < compsize; ins2++) {
				int instance2 = (int) (matrix.getRowCount() * Math.random());

				if (instance == instance2) {
					continue;
				}

				// distance between projected instances
				double x1x2 = (aux_proj[instance2][0] - aux_proj[instance][0]);
				double y1y2 = (aux_proj[instance2][1] - aux_proj[instance][1]);
				double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);

				if (dr2 < EPSILON) {
					dr2 = EPSILON;
				}

				float drn = diss.calculate(matrix.getRow(instance),
						matrix.getRow(instance2));

				// Calculating the (fraction of) delta
				double delta = Math.sqrt(drn) - Math.sqrt(dr2);
				delta *= Math.abs(delta);
				delta *= decfactor;

				// moving ins2 -> ins1
				if (Math.abs(cdata[instance2] - fixedEnergy) < EPSILON) { // estado
																			// nativo
																			// não
																			// move
					aux_proj[instance2][0] = 0;
					aux_proj[instance2][1] = 0;
				} else { // só move os não nativos
					aux_proj[instance2][0] += delta * (x1x2 / dr2);
					aux_proj[instance2][1] += delta * (y1y2 / dr2);
				}

				if (aux_proj[instance2][0] != aux_proj[instance2][0]) {
					System.out.println("Error: Force Scheme >> delta" + delta
							+ ", x1x2=" + x1x2 + ", dr2=" + dr2 + ", drn="
							+ drn);
				}
			}
		}
	}

	private int[] createIndex(int size) {
		// Create the indexes and shuffle them
		ArrayList<Integer> index_aux = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			index_aux.add(i);
		}

		int[] index_local = new int[size];
		for (int ind = 0, j = 0; j < index_local.length; ind += index_aux
				.size() / 10, j++) {
			if (ind >= index_aux.size()) {
				ind = 0;
			}

			index_local[j] = index_aux.get(ind);
			index_aux.remove(ind);
		}

		return index_local;
	}

	private float[][] createInitialProjection(int size, float[] cdata) {
		float[][] aux_proj = new float[size][];

		for (int i = 0; i < size; i++) {
			aux_proj[i] = new float[2];

			if (Math.abs(cdata[i] - fixedEnergy) < EPSILON) {
				aux_proj[i][0] = 0;
				aux_proj[i][1] = 0;
			} else {
				aux_proj[i][0] = (float) Math.random() - 0.5f;
				aux_proj[i][1] = (float) Math.random() - 0.5f;
			}
		}

		return aux_proj;
	}

	private AbstractMatrix createFinalProjection(float[][] proj,
			ArrayList<Integer> ids, ArrayList<String> labels, float[] cdata) {
		AbstractMatrix projection = new DenseMatrix();

		for (int i = 0; i < proj.length; i++) {
			if (labels.isEmpty()) {
				projection
						.addRow(new DenseVector(proj[i], ids.get(i), cdata[i]));
			} else {
				projection.addRow(
						new DenseVector(proj[i], ids.get(i), cdata[i]),
						labels.get(i));
			}
		}

		return projection;
	}

}
