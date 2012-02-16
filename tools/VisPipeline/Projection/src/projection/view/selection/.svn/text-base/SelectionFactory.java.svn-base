/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.view.selection;

import visualizationbasics.view.selection.AbstractSelection;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class SelectionFactory {

    public enum SelectionType {

        SPLITTING, SELECTION, RANGE
    }

    public static AbstractSelection getInstance(SelectionType type, ModelViewer viewer) {

        if (type == SelectionType.SPLITTING) {
            return new SplittingSelection(viewer);
        } else if (type == SelectionType.SELECTION) {
            return new InstanceSelection(viewer);
        } else if (type == SelectionType.RANGE) {
            return new RangeSelection(viewer);
        } 

        return null;
    }
}
