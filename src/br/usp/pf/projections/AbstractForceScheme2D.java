package br.usp.pf.projections;

import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import projection.model.ProjectionModelComp;
import projection.technique.Projection;
import projection.view.ProjectionFrameComp;

/**
 * 
 * @author Fatore
 */
public abstract class AbstractForceScheme2D implements Projection {
	
	protected static Random rand = new Random(10);

	protected int[] index;
	protected int printInterval;
	protected static final float EPSILON = 0.0000001f;
	protected static final float ACCEPTABLE_ERROR = 0.001f;
	protected int counter;

	ProjectionModelComp model;
	ProjectionFrameComp frame;
	AbstractMatrix finalProjection;

	public AbstractForceScheme2D() {
		model = new ProjectionModelComp();
		frame = new ProjectionFrameComp();
	}

	/**
	 * @param printInterval
	 */
	public void setPrintInterval(int printInterval) {
		this.printInterval = printInterval;
	}

	@Override
	public AbstractMatrix project(DistanceMatrix dmat) throws IOException {
		// Create the indexes and shuffle them
		index = createIndex(dmat.getElementCount());
		
		//fix cdata mapping
		dmat.setClassData(fixedCdata(dmat));

		// create the initial projection
		float[][] initial_proj = createInitialProjection(dmat);
		
		finalProjection = createFinalProjection(initial_proj, dmat);

		model.input(finalProjection);
		model.execute();

		frame.setTitle("iteration " + counter);
		frame.input(model.output());
		frame.execute();

		int compsize = (int) Math.sqrt(dmat.getElementCount());
		float decfactor = 1.0f;
		
		int[] closeToCenter = createIndex(compsize/5);

		for (counter = 1;; counter++) {
			// System.out.println("iteration: " + i);

			decfactor = (float) (Math.pow((1 + (counter * 2 + 1)), (1.0f / (counter * 2 + 1))) - 1);
//		    decfactor = 1/8f;
			if (iteration(dmat, initial_proj, compsize, decfactor, closeToCenter)) break;

			if (counter % printInterval == 0) {
				finalProjection = createFinalProjection(initial_proj,dmat);

				model.input(finalProjection);
				model.execute();

				frame.setTitle("iteration " + counter);
				frame.input(model.output());
				frame.execute();
			}
		}

		finalProjection = createFinalProjection(initial_proj,dmat);
		
		return finalProjection;
	}

	protected float[] fixedCdata(DistanceMatrix dmat) {
		
		float[] cdata = new float[dmat.getElementCount()]; 
		float[] cdata_aux = dmat.getClassData();

		for (int i = 0; i < cdata.length; i++) {
			cdata[dmat.getIds().get(i)] = cdata_aux[i];
		}

		return cdata;
	}

	/**
	 * @param nriterations
	 *            the nriterations to set
	 */

	protected abstract boolean iteration(DistanceMatrix dmat, float[][] aux_proj,
			int compsize, float decfactor, int[] closeToCenter);

	
	protected int[] createIndex(int size) {
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
	
	protected float[][] createInitialProjection(DistanceMatrix dmat) {
		
		List<Integer> ids = dmat.getIds();
		
		float[] cdata = dmat.getClassData();
		
		HashMap<Float, ArrayList<Integer>> energyIntervals = new HashMap<Float, ArrayList<Integer>>();
		
		for (int i = 0; i < dmat.getElementCount(); i++) {
			float key = cdata[ids.get(i)];
			if (!energyIntervals.containsKey(key)) {
				energyIntervals.put(key, new ArrayList<Integer>());
			}
			energyIntervals.get(key).add(ids.get(i));
		}
		
		float[][] aux_proj = new float[dmat.getElementCount()][];

		// find which index represents the native state
		// by convention its known that the first element represents the native state
		int nativeIndex = ids.get(0);
		float nativeEnergy = cdata[nativeIndex];
		
		for (List<Integer> list : energyIntervals.values()) {
			float delta_e = Math.abs(nativeEnergy - cdata[list.get(0)]);
			double delta_t = (2 * Math.PI) / list.size();
			double theta = 0.0;
			for (Integer i : list) {
				aux_proj[i] = new float[2];
//				aux_proj[i][0] = (float) rand.nextFloat() - 0.5f;
//				aux_proj[i][1] = (float) rand.nextFloat() - 0.5f;
				aux_proj[i][0] = (float) Math.cos(theta) * delta_e;
				aux_proj[i][1] = (float) Math.sin(theta) * delta_e;
//				aux_proj[i][0] = (float) Math.cos(theta) * dmat.getDistance(nativeIndex, ids.get(i));
//				aux_proj[i][1] = (float) Math.sin(theta) * dmat.getDistance(nativeIndex, ids.get(i));
				theta += delta_t;
			}
		}

		return aux_proj;
	}
	
	protected AbstractMatrix createFinalProjection(float[][] proj, DistanceMatrix dmat) {
		AbstractMatrix projection = new DenseMatrix();

		for (int i = 0; i < proj.length; i++) {
			int id = dmat.getIds().get(i);
			if (dmat.getLabels().isEmpty()) {
				projection.addRow(
					new DenseVector(proj[id], id, dmat.getClassData()[id]));
			} else {
				projection.addRow(
					new DenseVector(proj[id], id, dmat.getClassData()[id]),
					dmat.getLabels().get(id));
			}
		}

		return projection;
	}

	public AbstractMatrix project(AbstractMatrix arg0,
			AbstractDissimilarity arg1) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}