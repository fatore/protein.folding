/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public abstract class AbstractModel extends Observable {

    public AbstractModel() {
        this.instances = new ArrayList<AbstractInstance>();
        this.selinstances = new ArrayList<AbstractInstance>();
    }

    public ArrayList<AbstractInstance> getInstances() {
        return instances;
    }

    public void removeInstances(ArrayList<AbstractInstance> reminst) {
        HashSet<Integer> remids = new HashSet<Integer>();
        for (AbstractInstance ins : reminst) {
            if (ins.getModel() == this) {
                remids.add(ins.getId());
            }
        }

        ArrayList<AbstractInstance> newins = new ArrayList<AbstractInstance>();

        for (int i = 0; i < instances.size(); i++) {
            if (!remids.contains(instances.get(i).getId())) {
                newins.add(instances.get(i));
            } else {
                instances.get(i).setModel(null);
            }
        }

        instances = newins;

        setChanged();
    }

    public void removeSelectedInstances() {
        removeInstances(selinstances);
        selinstances = new ArrayList<AbstractInstance>();
        setChanged();
    }

    public void cleanSelectedInstances() {
        for (AbstractInstance pi : selinstances) {
            pi.setSelected(false);
        }

        selinstances = new ArrayList<AbstractInstance>();

        setChanged();
    }

    public void setSelectedInstances(ArrayList<AbstractInstance> selinst) {
        if (selinst != null) {
            cleanSelectedInstances();

            selinstances = new ArrayList<AbstractInstance>();

            for (AbstractInstance pi : selinst) {
                if (pi.getModel() == this) {
                    pi.setSelected(true);
                    selinstances.add(pi);
                }
            }

            setChanged();
        }
    }

    public void setSelectedInstance(AbstractInstance selinst) {
        if (selinst != null) {
            cleanSelectedInstances();

            if (selinst.getModel() == this) {
                selinst.setSelected(true);
                selinstances.add(selinst);
            }

            setChanged();
        }
    }

    public boolean hasSelectedInstances() {
        return (selinstances.size() > 0);
    }

    public ArrayList<AbstractInstance> getSelectedInstances() {
        return selinstances;
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    protected ArrayList<AbstractInstance> selinstances;
    protected ArrayList<AbstractInstance> instances;
}
