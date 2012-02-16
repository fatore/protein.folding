/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component;

import java.awt.datatransfer.Transferable;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Transfer handler of components from the components tree.
 * @author Fernando Vieira Paulovich
 */
public class ComponentTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {
        if (c instanceof JTree) {
            JTree tree = (JTree) c;
            Object lastPathComponent = ((DefaultMutableTreeNode) tree.getSelectionPath().
                    getLastPathComponent()).getUserObject();

            if (lastPathComponent instanceof ComponentTransfer) {
                return (ComponentTransfer) lastPathComponent;
            }
        }

        return null;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    public static final long serialVersionUID = 1L;
}
