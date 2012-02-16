/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package vispipeline.component;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ComponentTransfer implements Serializable, Transferable {

    private ComponentTransfer(Class compproxyclass, String compname) {
        this.compproxyclass = compproxyclass;
        this.compname = compname;
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(compflavor)) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{compflavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(compflavor);
    }

    public ComponentProxy getComponentProxy() throws IOException {
        try {
            Object instance = compproxyclass.newInstance();
            return new ComponentProxy((AbstractComponent) instance);
        } catch (InstantiationException ex) {
            Logger.getLogger(ComponentTransfer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ComponentTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static ComponentTransfer getInstance(Class compproxyclass, String compname) {
        Class[] interfaces = compproxyclass.getInterfaces();

        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i] == AbstractComponent.class) {
                return new ComponentTransfer(compproxyclass, compname);
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return compname;
    }

    public static final long serialVersionUID = 1L;
    public static final DataFlavor compflavor = new DataFlavor(ComponentProxy.class, "vizcomponent");
    private Class compproxyclass;
    private String compname;
}
