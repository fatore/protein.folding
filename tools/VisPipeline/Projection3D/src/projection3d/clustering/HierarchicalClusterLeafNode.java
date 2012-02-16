/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.clustering;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class is an extension of the HierarchicalClusterNode, representing the leaf nodes of a
 * hierarchical cluster tree. These Nodes are the only nodes containing the links to examples by
 * storing example ids.
 * @author Sebastian Land
 */
public class HierarchicalClusterLeafNode extends HierarchicalClusterNode {

    private Collection<Object> exampleIds;

    public HierarchicalClusterLeafNode(String clusterId) {
        super(clusterId);
    }

    public HierarchicalClusterLeafNode(String clusterId, Collection<Object> exampleIds) {
        super(clusterId);
        this.exampleIds = exampleIds;
    }

    public HierarchicalClusterLeafNode(int clusterId, Collection<Object> exampleIds) {
        this(clusterId + "", exampleIds);
    }

    public HierarchicalClusterLeafNode(int clusterId, Object exampleId) {
        super(clusterId + "");
        this.exampleIds = new LinkedList<Object>();
        this.exampleIds.add(exampleId);
    }

    /**
     * Returns an empty collection since it is leaf
     */
    @Override
    public Collection<HierarchicalClusterNode> getSubNodes() {
        return new LinkedList<HierarchicalClusterNode>();
    }

    /**
     * Get the number of subnodes: Is always 0 since this node is leaf
     */
    @Override
    public int getNumberOfSubNodes() {
        return 0;
    }

    @Override
    public double getDistance() {
        return 0d;
    }

    /**
     * Get all objects (as representend by their IDs) in this leaf.
     */
    @Override
    public Collection<Object> getExampleIdsInSubtree() {
        return exampleIds;
    }

    @Override
    public int getNumberOfExamplesInSubtree() {
        return exampleIds.size();
    }

    public void addSubNode(HierarchicalClusterLeafNode node) {
    }

    @Override
    public String toString() {
        return "(Cluster: " + getClusterId() + ") (Object Id: " + exampleIds.toString() + ")";
    }
}
