/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection3d.clustering;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Visualizes clusters as a bookmark like tree.
 *
 * @author Michael Wurst, Ingo Mierswa
 *
 */
public class ClusterTreeVisualization extends JTree implements TreeSelectionListener {

    private static final long serialVersionUID = 3994390578811027103L;

    private static class ClusterTreeLeaf {

        private String title;
        private Object id;

        public ClusterTreeLeaf(String title, Object id) {
            this.title = title;
            this.id = id;
        }

        @Override
        public String toString() {
            return title;
        }

        /** Returns the id. */
        public Object getId() {
            return id;
        }

        /** Returns the title. */
        public String getTitle() {
            return title;
        }
    }
    
    public ClusterTreeVisualization(DefaultMutableTreeNode root) {
        DefaultTreeModel model = new DefaultTreeModel(root);
        setModel(model);
        addTreeSelectionListener(this);
    }

    public ClusterTreeVisualization(HierarchicalClusterNode root) {
        DefaultTreeModel model = new DefaultTreeModel(generateTreeModel(root));
        setModel(model);
        addTreeSelectionListener(this);
    }

    private DefaultMutableTreeNode generateTreeModel(HierarchicalClusterNode cl) {
        DefaultMutableTreeNode result = new DefaultMutableTreeNode(cl);
        result.setAllowsChildren(true);

        // Add sub clusters
        for (HierarchicalClusterNode subNode : cl.getSubNodes()) {
            result.add(generateTreeModel(subNode));
        }

        return result;
    }

    private MutableTreeNode createLeaf(Object id) {
        DefaultMutableTreeNode newLeaf = new DefaultMutableTreeNode(new ClusterTreeLeaf(id.toString(), id));
        newLeaf.setAllowsChildren(false);
        return newLeaf;
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        System.err.println("evento");
        TreePath[] paths = getSelectionPaths();
        // If only one item has been selected, then change the text in the
        // description area
        if (paths == null) {
            return;
        }
        if (paths.length == 1) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) paths[0].getLastPathComponent();
            if (!node.getAllowsChildren()) {
                ClusterTreeLeaf leaf = (ClusterTreeLeaf) node.getUserObject();
            }
        }
    }
}
