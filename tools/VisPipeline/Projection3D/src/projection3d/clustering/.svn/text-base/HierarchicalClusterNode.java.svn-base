/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.clustering;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents a not leaf node of a hierarchical cluster tree.
 * It holds only references on the children and not on the contained examples.
 * Hence contained examples are gathered recursivly from leaf nodes.
 *
 * @author Sebastian Land
 */
public class HierarchicalClusterNode {

    private Collection<HierarchicalClusterNode> subNodes;
    private double distance;
    private String clusterId;

    public HierarchicalClusterNode(String clusterId) {
        this.clusterId = clusterId;
        this.subNodes = new ArrayList<HierarchicalClusterNode>();
    }

    public HierarchicalClusterNode(int clusterId, double distance) {
        this(clusterId + "");
        this.distance = distance;
    }

    public HierarchicalClusterNode(String clusterId, double distance) {
        this(clusterId);
        this.distance = distance;
    }

    /**
     * Get all children of this node.
     *
     * @return Iterator of ClusterNode
     */
    public Collection<HierarchicalClusterNode> getSubNodes() {
        return subNodes;
    }

    /**
     * Get the number of subnodes.
     *
     * @return the number of subnodes.
     */
    public int getNumberOfSubNodes() {
        return subNodes.size();
    }

    /**
     * Get the weight of this node. This might represent similarity to another
     * cluster or something else.
     *
     * @return double
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Get all objects (as representend by their IDs) in the subtree including
     * the objects located directly at this node. Note that objects can occur
     * more than once.
     *
     * @return Iterator of String
     */
    public Collection<Object> getExampleIdsInSubtree() {
        Collection<Object> examples = new LinkedList<Object>();
        for (HierarchicalClusterNode subNode : subNodes) {
            examples.addAll(subNode.getExampleIdsInSubtree());
        }
        return examples;
    }

    /**
     * Returns the number of objects in the subtree including the objects at the
     * current node. Note that objects can occur more than once and are
     * counted as such. To get the number of distinct objects use the
     * getObjectsInSubtree function first and use the resulting list to calculate
     * the number of distinct objects.
     *
     * @return number of objects
     */
    public int getNumberOfExamplesInSubtree() {
        int subtreeSum = 0;
        for (HierarchicalClusterNode subNode : subNodes) {
            subtreeSum += subNode.getNumberOfExamplesInSubtree();
        }
        return subtreeSum;
    }

    public void addSubNode(HierarchicalClusterNode node) {
        subNodes.add(node);
    }

    public int getTotalNumberOfSubnodes() {
        if (getNumberOfSubNodes() == 0) {
            return 1;
        } else {
            int count = 0;
            for (HierarchicalClusterNode child : getSubNodes()) {
                count += child.getTotalNumberOfSubnodes();
            }
            count++;
            return count;
        }
    }

    public String getClusterId() {
        return clusterId;
    }

    @Override
    public String toString() {
        return clusterId + ": " + getNumberOfExamplesInSubtree() + " (" + getDistance() + ")";
    }
}
