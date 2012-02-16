/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package visualizationbasics.coordination;

import visualizationbasics.model.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class IdentityCoordinator extends AbstractCoordinator {

    @Override
    public void coordinate(ArrayList<AbstractInstance> selins, Object parameter) {
        if (selins.size() > 0) {
            //creating an index to speed-up the coordination process
            HashSet<Integer> selids = new HashSet<Integer>();
            for (AbstractInstance sel : selins) {
                selids.add(sel.getId());
            }
            
            for (AbstractModel am : models) {
                if (am != selins.get(0).getModel()) {
                    ArrayList<AbstractInstance> selcoord = new ArrayList<AbstractInstance>();

                    ArrayList ins = am.getInstances();
                    for (int i = 0; i < ins.size(); i++) {
                        AbstractInstance ai = (AbstractInstance) ins.get(i);

                        if (selids.contains(ai.getId())) {
                            selcoord.add(ai);
                        }
                    }

                    am.setSelectedInstances(selcoord);
                    am.notifyObservers();
                }
            }
        } else {
            for (AbstractModel am : models) {
                am.cleanSelectedInstances();
            }
        }
    }

}
