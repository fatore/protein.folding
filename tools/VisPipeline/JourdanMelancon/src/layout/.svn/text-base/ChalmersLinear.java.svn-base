/*
 * Created on 18 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * ChalmersLinear.java
 * 
 * 						 Copyright Fabien Jourdan
 						 Fabien.Jourdan@toulouse.inra.fr
 						 
						 This software is a computer program whose purpose is to [describe
						 functionalities and technical features of your software].

						 This software is governed by the CeCILL  license under French law and
						 abiding by the rules of distribution of free software.  You can  use, 
						 modify and/ or redistribute the software under the terms of the CeCILL
						 license as circulated by CEA, CNRS and INRIA at the following URL
						 "http://www.cecill.info". 

						 As a counterpart to the access to the source code and  rights to copy,
						 modify and redistribute granted by the license, users are provided only
						 with a limited warranty  and the software's author,  the holder of the
						 economic rights,  and the successive licensors  have only  limited
						 liability. 

						 In this respect, the user's attention is drawn to the risks associated
						 with loading,  using,  modifying and/or developing or reproducing the
						 software by the user in light of its specific status of free software,
						 that may mean  that it is complicated to manipulate,  and  that  also
						 therefore means  that it is reserved for developers  and  experienced
						 professionals having in-depth computer knowledge. Users are therefore
						 encouraged to load and test the software's suitability as regards their
						 requirements in conditions enabling the security of their systems and/or 
						 data to be ensured and,  more generally, to use and operate it in the 
						 same conditions as regards security. 

						 The fact that you are presently reading this means that you have had
						 knowledge of the CeCILL license and that you accept its terms.
 */

package layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import structure.DataSet;
import util.Vector2D;

/**
 * Straightforward implementation of Chalmers linear per iteration time MDS algorithm.
 * 
 * @author Guy Melancon
 * @author Fabien Jourdan
 * Created on  february 2004
 */
public class ChalmersLinear extends MDS {
	/**
 	* If optimized is set to true the iteration step will be done until stress improvement becomes very small.
 	* Else it will be the classical Chalmers Linear
 	*/
	private boolean optimized=false;

	/**
	 * A coefficient applied to each force vector.
	 * Strongly linked to other coef.
	 * Could influence easily the layout.
	 * I think it should be as big as possible (between 0 and 1).
	 * Regarding that if it is too big all the points have the same coordinates.
	 */
	private double delta;

	/**
	 * The list of all Nodes.
	 * This list is sued to randomized the choice of Nodes.
	 * Since it is an ArrayList the choice is made with a random number.
	 */
	private ArrayList nodeList;
	
	/**
	 * To each Node a set of V nodes is associated.
	 * @see VSize
	 */
	private HashMap Vneighbors;

	/**
	 * Only one ArrayList of Nodes is used for S.
	 * @see SSize
	 */
	private ArrayList Sneighbors;
	
	/**
	 * The Size of Subset V.
	 * Chalmers recommended to use 10.
	 * Note that this size influence computation speed an quality result.
	 */
	private int VSize= 10;
	
	/**
	 * The Size of Subset V.
	 * Chalmers recommended to use 5.
	 * Note that this size influence computation speed an quality result.
	 */
	private int SSize= 5;
	
 	/**
 	 * Default number of iterations is the number of nodes in graph.
 	 * It could be multiplied by this coef.
 	 */
	private int NBITERATIONS=0;

	/**
	 * Constructor generally used by kernel based methods
	 * @param d The new Dataset
	 * @param w representation space width
	 * @param h representation space height
	 * 
	 * </br><i>Cretated on 20 dcc. 2005</i>
	 */
	public ChalmersLinear(DataSet d,double w,double h) {super(d,w,h);}
	
	/**
 	* Main part of Position computation
 	*It used both when HDDMatrix is computed or given.
 	*/
	protected void doComputation()
	{
		//Initialize
		initParameters();
		// Give a random position to all nodes
		randomInit();
		//Fill V and S sets
		fillVSets();
		if (optimized)
		{
			double previousStress=Double.MAX_VALUE;
			double actualStress=getStress();
			int nbIter=0;
			while (actualStress>0.2&&nbIter<5)
			{
				doIterations();
				previousStress=actualStress;
				actualStress=getStress();
				nbIter++;
			}
		}
		else {doIterations();}	
		
	}
	
	/**
	 * Init parameters of Chalemers Linear algorithm.
	 * Important part since these parameters strongly influence drawing.
	 * All values were found experimentaly.
	 */
	protected void initParameters()
	{
		nodeList = new ArrayList();
		Vneighbors = new HashMap();
		Sneighbors = new ArrayList();
		double N=geoDataSet.size();
		if (getNumberOfIterations()==0)
		{
			//It means that the number of iterations was not provided
			setNumberOfIterations((int)N);
		}
		delta=0.1;//(VSize+SSize)/maxWidth;
	}
	
/**
 * What happened for each iteration
 * @param extent
 */
	protected void doIterations()
	{
		for (int nbIterations = 0; nbIterations <getNumberOfIterations(); nbIterations++) {
			if(delta>0.00001)
			{
				Iterator nodeIter =geoDataSet.getDataset().getCollection().iterator();
				while (nodeIter.hasNext()) {
					Object node =nodeIter.next();
					updateVS(node);
					Vector2D nodePos = updatePosition(node);
					geoDataSet.setX(node,nodePos.x);
					geoDataSet.setY(node,nodePos.y);
				}
				//Decreas delta to get forces with less intensity
				//delta=delta-delta/(2*Math.log(geoDataSet.size()));	
			}
		}
	}


/**
 * Fill V sets for each Node.
 */
	private void fillVSets() {
		Iterator nodeIter = geoDataSet.getDataset().getCollection().iterator();
		while (nodeIter.hasNext()) {
			Object node =nodeIter.next();
			//get V for the treated node
			ArrayList Vneigh = (ArrayList) Vneighbors.get(node);
			int nbNodesInV = 0;
			while (nbNodesInV < VSize) {
				// randomly select nodes to update V and fill S
				Random random = new Random();
				int card = geoDataSet.size();
				int randIndex = random.nextInt(card);
				Object sample = nodeList.get(randIndex);
				while (Vneigh.contains(sample) || sample.equals(node)) {
					randIndex = random.nextInt(card);
					sample = nodeList.get(randIndex);
				}
				Vneigh.add(sample);
				nbNodesInV++;
			}
		}
	}
	/**
	 * Update position of nodes with force computation
	 * @param node
	 */
	private Vector2D updatePosition(Object node) {
		Vector2D nodePos =new Vector2D(geoDataSet.getX(node),geoDataSet.getY(node));
		Vector2D force = computeForces(node);
		force.mult(this.delta);
		nodePos.add(force);
		return nodePos;
	}

	/**
	 * Compute forces for a given node
	 * @param node
	 */
	private Vector2D computeForces(Object node) {
		Vector2D force = new Vector2D();
		Vector2D pNode = new Vector2D(geoDataSet.getX(node),geoDataSet.getY(node));
		Iterator vIter = ((ArrayList) Vneighbors.get(node)).iterator();
		while (vIter.hasNext()) {
			Object neigh = vIter.next();
			Vector2D pNeigh = new Vector2D(geoDataSet.getX(neigh),geoDataSet.getY(neigh));
			double forceIntesity = geoDataSet.getSimilarity(node, neigh) - geoDataSet.getEuclideanDistance(node, neigh);
			Vector2D unit=geoDataSet.unitVector2D(neigh,node);
			Vector2D localForce =new Vector2D(unit.getX() * forceIntesity,unit.getY() * forceIntesity);
			force = Vector2D.add(force, localForce);
		}
		return force;
	}

	/**
	 * Update sets V and S
	 * @param node
	 */
	private void updateVS(Object node) {
		int nbNodesAddedInS = 0;
		Sneighbors.clear();
		//get V for the treated node
		ArrayList Vneigh = (ArrayList) Vneighbors.get(node);
		// randomly select nodes to update V and fill S
		Random random2 = new Random();
		int card = geoDataSet.size();
		//fill S set
		while (nbNodesAddedInS < SSize) {
			int randIndex = random2.nextInt(card);

			Object sample = nodeList.get(randIndex);
			while (Vneigh.contains(sample) || sample.equals(node)) {
				randIndex = random2.nextInt(card);
				sample = nodeList.get(randIndex);
			}
			double dist = geoDataSet.getSimilarity(sample, node);
			double maxDist = maxDistance(node, Vneigh);
			if (dist < maxDist) {
				Object maxNode = maxNodeDistance(node, Vneigh);
				Vneigh.remove(maxNode);
				Vneigh.add(sample);
			} else {
				if (!Sneighbors.contains(sample)) {
					Sneighbors.add(sample);
					nbNodesAddedInS++;
				}
			}
		}
	}


/**
 * 
 * @param sample
 * @param Vneigh
 * @return The node with the ...
 */
	private Object maxNodeDistance(Object sample, ArrayList Vneigh) {
		// sample remplace dans V(node) le
		// point de distance max (on suppose
		// ici que sample est a une distance plus petite
		Iterator iter = Vneigh.iterator();
		double max = Double.MIN_VALUE;
		Object maxNode = null;
		while (iter.hasNext()) {
			Object node =  iter.next();
			if (max < geoDataSet.getSimilarity(sample, node)) {
				max = Math.max(max,geoDataSet.getSimilarity(sample,node));
				maxNode = node;
			}
		}
		return maxNode;
	}
	/**
	 * @param sample
	 * @param Vneigh
	 * @return Max High Dimensional Distance
	 */
	private double maxDistance(Object sample, ArrayList Vneigh) {
		Iterator iter = Vneigh.iterator();
		double max = Double.MIN_VALUE;
		while (iter.hasNext()) {
			Object node =  iter.next();
			max = Math.max(max, geoDataSet.getSimilarity(sample,node));
		}
		return max;
	}
	/**
	 * Elements are randomly placed
	 * 
	 * </br><i>Created on 20 dcc. 2005</i>
	 */
	private void randomInit()
	{
		//Random position
		Iterator nodeIter = geoDataSet.getDataset().getCollection().iterator();
		//A little hack to avoid going through all nodes if they already have coordinates..
		boolean givenData=false;
		while (nodeIter.hasNext() && !givenData) {
			Object node = nodeIter.next();
			nodeList.add(node);
			Vneighbors.put(node, new ArrayList());
			if (geoDataSet.getX(node)==Double.MIN_VALUE || geoDataSet.getY(node)==Double.MIN_VALUE)
			{
				geoDataSet.setX(node,random.nextDouble()*maxWidth);
				geoDataSet.setY(node,random.nextDouble()*maxHeight);
			}
			else{givenData=true;}
		}
	}
	
	/** 
 	* @return the number of iterations
 	*/
	protected int getNumberOfIterations() {return NBITERATIONS;}
	/**
	 * Set the Number of iterations.
	 * For instance, is called in Chalemers Sampling
	 * @param n the chosen number of iterations
	 */
	protected void setNumberOfIterations(int n){NBITERATIONS=n;}
	
	protected double getDelta() {return delta;}
	
	//protected void setDelta(double delta) {this.delta = delta;}

	public void setOptimisation(){optimized=true;}
}