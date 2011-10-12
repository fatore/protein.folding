package br.usp.pf.projections;

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
public class FastForceScheme2D implements Projection {

	private float fixedEnergy;
	private int[] index;
	private int nrIterations;
	private int printInterval;
	private static final float EPSILON = 0.0000001f;
	private static final float ACCEPTABLE_ERROR = 0.001f;
	private int counter;

	ProjectionModelComp model;
	ProjectionFrameComp frame;
	AbstractMatrix projection;

	public FastForceScheme2D() {
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

	public void setPrintInterval(int printInterval) {
		this.printInterval = printInterval;
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

		counter = 0;
		for (counter = 0;; counter++) {
			// System.out.println("iteration: " + i);

			decfactor = (float) (Math.pow((1 + (counter * 2 + 1)), (1.0f / (counter * 2 + 1))) - 1);
//		    decfactor = 1/8f;
			if (iteration(dmat, initial_proj, compsize, decfactor, closeToCenter)) break;

			if (counter % printInterval == 0) {
				projection = createFinalProjection(initial_proj,
						dmat.getIds(), dmat.getLabels(), dmat.getClassData());

				model.input(projection);
				model.execute();

				frame.setTitle("iteration " + counter);
				frame.input(model.output());
				frame.execute();
			}
		}

		projection = createFinalProjection(initial_proj,
				dmat.getIds(), dmat.getLabels(), dmat.getClassData());
		
		model.input(projection);
		model.execute();

		frame.setTitle("iteration " + counter);
		frame.input(model.output());
		frame.execute();
		
		dmat.save(dmat.getElementCount() + ".dmat");
		projection.save(dmat.getElementCount() + ".prj");

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
		int[] mostDistElem = new int[2]; 

		// for each instance
		for (int i = 0; i < dmat.getElementCount(); i++) {
			int instance1 = index[i];

			// for each other instance
		 for (int j = 0; j < compsize; j++) {
			 int instance2 = (int) (dmat.getElementCount() * Math.random());

//			for (int j = 0; j < dmat.getElementCount(); j++) {
//				int instance2 = index[j];
				
				if (instance1 == instance2) {
					continue;
				}

				// distance between projected instances
				double x1x2 = (aux_proj[instance2][0] - aux_proj[instance1][0]);
				double y1y2 = (aux_proj[instance2][1] - aux_proj[instance1][1]);
				double dr2 = Math.sqrt(x1x2 * x1x2 + y1y2 * y1y2);
				
				if (dr2 < EPSILON) {
					dr2 = EPSILON;
				}

				float drn = dmat.getDistance(instance1, instance2);

				// Calculating the (fraction of) delta
				double delta = Math.sqrt(drn) - Math.sqrt(dr2);
				delta *= Math.abs(delta);
				delta *= decfactor;
				
				if (delta > maxDist) {
					maxDist = delta;
					mostDistElem[0] = instance1;
					mostDistElem[1] = instance2;
				}
					
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
		System.out.println("iteration: " + counter + " maxDist: " + maxDist);
		return false;
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

	@Override
	public AbstractMatrix project(AbstractMatrix arg0,
			AbstractDissimilarity arg1) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
