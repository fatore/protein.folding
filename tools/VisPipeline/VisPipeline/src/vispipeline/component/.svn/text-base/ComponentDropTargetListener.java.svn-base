/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component;

import java.awt.Color;
import java.awt.Point;
import vispipeline.pipeline.Pipeline;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import vispipeline.view.VisPipeline;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ComponentDropTargetListener implements DropTargetListener {

    public ComponentDropTargetListener(JPanel panel, Pipeline pipeline) {
        this.panel = panel;
        this.pipeline = pipeline;
    }

    public void dragEnter(DropTargetDragEvent e) {
        if (e.isDataFlavorSupported(ComponentTransfer.compflavor)) {
            ComponentProxy proxy = null;

            try {
                ComponentTransfer trans = (ComponentTransfer) e.getTransferable().
                        getTransferData(ComponentTransfer.compflavor);

                //get the component proxy
                proxy = trans.getComponentProxy();

                //setting the compproxy position
                proxy.setPosition(e.getDropTargetContext().getDropTarget().
                        getComponent().getMousePosition());
            } catch (UnsupportedFlavorException ufe) {
                Logger.getLogger(ComponentDropTargetListener.class.getName()).log(Level.SEVERE, null, ufe);
            } catch (IOException ex) {
                Logger.getLogger(ComponentDropTargetListener.class.getName()).log(Level.SEVERE, null, ex);
            }

            compproxy = proxy;
            pipeline.addComponent(compproxy);

            e.acceptDrag(DnDConstants.ACTION_COPY);
        } else {
            e.rejectDrag();
        }
    }

    public void dragOver(DropTargetDragEvent e) {
        if (compproxy != null &&
                e.getDropTargetContext().getDropTarget().getComponent().getMousePosition() != null) {
            Point newpos = new Point(e.getDropTargetContext().getDropTarget().getComponent().getMousePosition());
            newpos.x = newpos.x - compproxy.getWidth() / 2;
            newpos.y = newpos.y - compproxy.getHeight() / 2;

            //setting the compproxy position
            compproxy.setPosition(newpos);
            e.acceptDrag(DnDConstants.ACTION_COPY);
            panel.repaint();
        } else {
            e.rejectDrag();
        }
    }

    public void dropActionChanged(DropTargetDragEvent e) {
        e.acceptDrag(DnDConstants.ACTION_COPY);
    }

    public void dragExit(DropTargetEvent e) {
        if (compproxy != null) {
            pipeline.removeComponent(compproxy);
            panel.repaint();
        }
    }

    public void drop(DropTargetDropEvent e) {
        if (compproxy != null) {
            if (compproxy.description().trim().length() > 0) {
                VisPipeline.ConsoleOutput.append("[ADDED] " + compproxy.toString() + " - " +
                        compproxy.description(), Color.BLACK, false, false, true);
            }

            compproxy = null;
            panel.repaint();

            e.acceptDrop(DnDConstants.ACTION_COPY);
            e.dropComplete(true);
        } else {
            e.rejectDrop();
            e.dropComplete(false);
        }
    }

    private ComponentProxy compproxy;
    private Pipeline pipeline;
    private JPanel panel;
}
