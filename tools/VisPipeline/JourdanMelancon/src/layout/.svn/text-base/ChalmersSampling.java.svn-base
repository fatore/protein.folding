/*
 * Created on 20 oct. 2005 by Fabien Jourdan, Fabien.Jourdan@toulouse.inra.fr
 * ChalmersSampling.java
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
import util.SimilarityEngine;
import util.Vector2D;

/**
 * This class is an implementation of the sample algorithm proposed by Morrison and Chalmers in 2003.
 * </br>Commented on 20 oct. 2005
 * @author Fabien
 */
public class ChalmersSampling extends MDS{

	private Random random = new Random();
	/**
	 * The kernel.
	 */
	protected ArrayList samples=null;
	/**
	 * nodeList is used as a template for computations
	 */
	protected ArrayList nodeList=new ArrayList();
	/**
	 * For computing an MDS on the kernel it requires to associate a dataSet to the kernel.
	 */
	private DataSet sampleDataSet;
	/**
	 * The layaout computed on the kernel.
	 */
	private ChalmersLinear chalmersLinearOnKernel = null;
	/**
	 * Since we will need to compute all closestParents, we compute them once and then store them.
	 */
	private HashMap closestParents=new HashMap();
	/**
	 * A kernel minimum size to get a meaningful kernel drawing.
	 */
	private int kernelMinimumSize=60;
	/**
	 * Call the super constructor
	 * @param d
	 * @param w
	 * @param h
	 * 
	 * </br><i>Cretated on 3 nov. 2005</i>
	 */
	public ChalmersSampling(DataSet d,double w,double h) {super(d,w,h);}
	
	/**
	 * The computation.
	 * Note that if the size of the dataset is lower than the maximum kernel size, then the linear computation is applied.
	 */
	public void doComputation()
	{
		if (geoDataSet.size()<=kernelMinimumSize) 
		{
			ChalmersLinear linear=new ChalmersLinear(this.getGeoDataSet().getDataset(),maxWidth,maxHeight);
			linear.doComputation();
			//linear.computePositionsWithoutScaling();
			//System.err.println("kernel stress "+linear.getStress());
			Iterator all=geoDataSet.getDataset().getCollection().iterator();
			while(all.hasNext())
			{
				Object node=all.next();
				geoDataSet.setX(node,linear.getX(node));
				geoDataSet.setY(node,linear.getY(node));
			}
		}
		else
		{
			initParameters();
			if (chalmersLinearOnKernel==null)
			{
				SimilarityEngine simEngine=((DataSet)geoDataSet.getDataset()).getSimilarityEngine();
				DataSet kernelDataSet=new DataSet(samples,simEngine);
				chalmersLinearOnKernel = new ChalmersLinear(kernelDataSet,this.maxWidth,this.maxHeight);				
				//This means that iterations will be done until there is no efficient improvement
				chalmersLinearOnKernel.setOptimisation();
				chalmersLinearOnKernel.computePositionsWithoutScaling();
				//System.err.println("kernel stress "+chalmersLinearOnKernel.getStress());
				//Affect their new coordinates to kernel nodes
				Iterator allInKernel=kernelDataSet.getCollection().iterator();
				while(allInKernel.hasNext())
				{
					Object node=allInKernel.next();
					geoDataSet.setX(node,chalmersLinearOnKernel.getX(node));
					geoDataSet.setY(node,chalmersLinearOnKernel.getY(node));
				}
			}
			
			Iterator nodeIter =geoDataSet.getDataset().getCollection().iterator();
			while (nodeIter.hasNext()) {
				Object node =nodeIter.next();
				if (!samples.contains(node))
					{
						Vector2D nodePos = updatePosition(node);
						geoDataSet.setX(node,nodePos.getX());
						geoDataSet.setY(node,nodePos.getY());
					}
				}		
			//System.err.println("Refinement beginning");
			refinement();
			//System.err.println("Refinement end");
			//this.scale();
		}

	}
	
	/**
	 * The sample is built in this initialisation phase
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	protected void initParameters()
	{
		nodeList=new ArrayList();
		if (samples==null) buildSample();
		buildClosestParentsHashMap();
	}
	
	/**
	 * Build the sample
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	private void buildSample()
	{
		int sampleSize=(int)Math.sqrt(geoDataSet.size());
		if(sampleSize<kernelMinimumSize) sampleSize=kernelMinimumSize;
		samples=sample(sampleSize);
	}
	
	/**
	 * Samples the prescribed number of elements from the given list.
	 * Sampled elements are substracted from the original list.
	 * @param nodeList The list from which the samples are taken.
	 * @param i The number of elements to sample from the original nodeList.
	 * @return Returns a sample set in the form of an ArrayList object.
	 */
	private ArrayList sample(int k) {
		ArrayList sample = new ArrayList();
		Random random = new Random();
		int size=0;
		while (size<k) {
			int index = random.nextInt(geoDataSet.size());
			Object o=geoDataSet.getItem(index);
			if(!sample.contains(o)){sample.add(o);size++;}
		}
		return sample;
	}
	
	/**
	 * All the closest parent will be stored in a HashMap.
	 * Where the key will be a node and the value its parent.
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	private void buildClosestParentsHashMap()
	{
		Iterator nodeIter =geoDataSet.getDataset().getCollection().iterator();
		while (nodeIter.hasNext()) {
			Object node =nodeIter.next();
			double minCurrentDist = Double.MAX_VALUE;
			Object closestParent = null;
			Iterator sampleIter = samples.iterator();
			while (sampleIter.hasNext()) 
				{
				Object sampled = sampleIter.next();
				double distance = geoDataSet.getSimilarity(node, sampled);
				if ((distance < minCurrentDist)&& (!sampled.equals(node))) {
					minCurrentDist = distance;
					closestParent = sampled;
				}
				}
			closestParents.put(node,closestParent);
		}
	}
	
	/**
	 * Return a subset of kernel set.
	 * @param k
	 * @return
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	protected ArrayList sampleOfSamples(int k)
	{
		ArrayList sample = new ArrayList();
		Random random = new Random();
		int size=0;
		while (size<k) {
			int index = random.nextInt(samples.size());
			Object o=samples.get(index);
			if(!sample.contains(o)){sample.add(o);size++;}
		}
		return sample;		
	}
	
	/**
	 * Finds the closest parent node, that is the node belonging to the sample
	 * sitting at a minimum hDistance from the selected node.
	 * @param node The node seeking for a parent.
	 * @return Returns a reference to its closest parent node (belonging to the sample).
	 */
	protected Object findHDClosestParent(Object node) {return closestParents.get(node);}


	/**
	 * Update position of nodes according to their closest parents.
	 * @param node
	 */
	private Vector2D updatePosition(Object node) {
		Vector2D nodePos = new Vector2D(geoDataSet.getX(node),geoDataSet.getY(node));
		//Get nearest node in  sample
		Object closestParent=findHDClosestParent(node);	
		nodePos=initialPosition(node,closestParent);
		//Init parameters for force computation
		int nbIterations=5;
		double delta=nbIterations/maxWidth;
		for (int i=0;i<nbIterations;i++)
		{			
			ArrayList s=sampleOfSamples(nbIterations);
			Vector2D force = computeForces(node,closestParent,s);
			force.mult(delta);
			nodePos.add(force);
			delta=delta-delta/(2*Math.log(geoDataSet.size()));
		}
		return nodePos;
	}
	/**
	 * Compute the force according to the sample, closest parent and for a given node
	 * @param node
	 * @param closestParent
	 * @param ssample
	 * @return A force vector to update node coordinates
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	private Vector2D computeForces(Object node,Object closestParent,ArrayList ssample)
	{
		Vector2D force=new Vector2D();

		if (!ssample.contains(closestParent))
		{
			ssample.add(closestParent);
		}
		Iterator allUsedNodes=ssample.iterator();
		while(allUsedNodes.hasNext())
		{
			Object usedNode=allUsedNodes.next();
			Vector2D pUsedNode = new Vector2D(geoDataSet.getX(usedNode),geoDataSet.getY(usedNode));
			Vector2D pNode= new Vector2D(geoDataSet.getX(node),geoDataSet.getY(node));
			double forceIntesity = geoDataSet.getSimilarity(node,usedNode) - geoDataSet.getEuclideanDistance(node,usedNode);
			Vector2D unit=geoDataSet.unitVector2D(usedNode,node);
			unit.mult(forceIntesity);//Vector2D localForce =new Vector2D(unit.getX() * forceIntesity,unit.getY() * forceIntesity);
			force = Vector2D.add(force,unit);
		}
		return force;
	}
	/**
	 * Define a circle center on closest parent Node.
	 * Then find the quarter part of the circle which is the best for localisation of node
	 * Do a binary search to find where to initialy place the node
	 * 
	 * 
	 * @param node
	 * @param closestParent
	 * @return the initial position on the circle
	 */
	private Vector2D initialPosition(Object node,Object closestParent) {

		geoDataSet.setX(node,(geoDataSet.getX(closestParent)+geoDataSet.getX(closestParents.get(closestParent)))/2);
		geoDataSet.setY(node,(geoDataSet.getY(closestParent)+geoDataSet.getY(closestParents.get(closestParent)))/2);
		return new Vector2D(geoDataSet.getX(node),geoDataSet.getY(node));
	}
	
	/**
	 * The computation provides a drwing of less quality than those obtained with the linear algorithm.
	 * To refine the drawing we compute forces for each node by taking into account a constant number of nodes.
	 * It adds a linear step to the algorithm, but help to provide a better drawing.
	 * 
	 * </br><i>Created on 3 nov. 2005</i>
	 */
	private void refinement()
	{
		Iterator nodeIter =geoDataSet.getDataset().getCollection().iterator();
		while (nodeIter.hasNext()) {
			Object node =nodeIter.next();
			int size=20;
			ArrayList nearsestSample=new ArrayList();
			//ArrayList nearsestSample=sample(size);
			int cpt1=0;
			Object templateNode=node;
			while (nearsestSample.size()<size && cpt1<30)
				{
					Object o=closestParents.get(templateNode);
					templateNode=o;
					if(!nearsestSample.contains(o))
						nearsestSample.add(o);
					else
					{
						boolean found=false;
						int cpt=0;
						while (!found && cpt<10)
						{
							Object templateNode2=geoDataSet.getItem(random.nextInt(geoDataSet.size()));	
							if(geoDataSet.getSimilarity(node,templateNode2)<0.2)
								{templateNode=templateNode2;found=true;}
							cpt++;
						}
					}
					cpt1++;
				}
				double delta=0.1;//size/maxWidth;//(9/(double)nbIterations)*0.1;//0.01;//(9/(double)nbIterations)*0.1;
				for (int i=0;i<nearsestSample.size();i++)
				{			
					Vector2D force = computeForces(node,closestParents.get(node),nearsestSample);
					force.mult(delta);
					geoDataSet.setX(node,geoDataSet.getX(node)+force.getX());
					geoDataSet.setY(node,geoDataSet.getY(node)+force.getY());
					delta=delta-delta/(2*Math.log(geoDataSet.size()));
				}
			}			
	}

}
