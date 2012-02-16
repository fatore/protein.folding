/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package plsp.model;

import datamining.neighbors.Pair;
import projection.model.*;
import java.util.ArrayList;
import java.util.Hashtable;
import visualizationbasics.model.AbstractInstance;

/**
 *
 * @author Danilo Medeiros Eler
 */
public class PLSPProjectionModel extends ProjectionModel {

    public PLSPProjectionModel() {
        this.patches = new ArrayList<ArrayList<AbstractInstance>>();
        this.cpoints = new ArrayList<ArrayList<AbstractInstance>>();
        this.neighborhoodGraphs = new ArrayList<Pair[][]>();
    }

    public void addPatch(ArrayList<AbstractInstance> patch) {
        this.patches.add(patch);
    }

    public ArrayList<Pair[][]> getNeighborhoodGraphs() {
        return neighborhoodGraphs;
    }

    public void addNeighborhoodGraph(Pair[][] patchNeighbour) {
        this.neighborhoodGraphs.add(patchNeighbour);
    }

    public ArrayList<ArrayList<AbstractInstance>> getCpoints() {
        return cpoints;
    }

    public ArrayList<ArrayList<AbstractInstance>> getPatches() {
        return patches;
    }

    public void addPatchControlPoints(ArrayList<AbstractInstance> cpoints) {
        this.cpoints.add(cpoints);
    }

    public void generatePatchMap() {
        if (this.patchMap == null) {
            this.patchMap = new Hashtable();
        } else {
            this.patchMap.clear();
        }

        for (int i = 0; i < this.patches.size(); i++) {
            for (int j = 0; j < this.patches.get(i).size(); j++) {
                this.patchMap.put(this.patches.get(i).get(j).getId(), i);
            }
        }
    }

    public Hashtable getPatchMap() {
        return patchMap;
    }
    protected Hashtable patchMap = null;
    protected ArrayList<ArrayList<AbstractInstance>> cpoints = null;
    protected ArrayList<ArrayList<AbstractInstance>> patches = null;
    protected ArrayList<Pair[][]> neighborhoodGraphs = null;
}
