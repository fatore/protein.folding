/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projection.view.selection;

import visualizationbasics.view.selection.AbstractSelection;
import java.awt.Color;
import projection.model.ProjectionInstance;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import projection.model.ProjectionModel;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import visualizationbasics.color.ColorScale;
import visualizationbasics.color.ColorScaleFactory;
import visualizationbasics.color.DynamicScale;
import visualizationbasics.model.AbstractInstance;
import visualizationbasics.view.ModelViewer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class DynamicColorSelection extends AbstractSelection {

    public DynamicColorSelection(ModelViewer viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<AbstractInstance> selinst) {
        if (viewer.getModel() != null && selinst.size() > 0) {
            color = JColorChooser.showDialog(viewer, "Choose a color", Color.RED);
            if (color != null) {
                ((ProjectionModel) viewer.getModel()).changeColorScaleType(ColorScaleFactory.ColorScaleType.DYNAMIC_SCALE);
                //((ProjectionModel) viewer.getModel()).getColorTable().setColorScaleType(ColorScaleFactory.ColorScaleType.DYNAMIC_SCALE);
                ColorScale cScale = (DynamicScale) ((ProjectionModel) viewer.getModel()).getColorTable().getColorScale();
                int index = ((DynamicScale) cScale).addColor(color);
                ArrayList<Scalar> scalars = ((ProjectionModel) viewer.getModel()).getScalars();
                int i = 0;
                while (!scalars.get(i).getName().equals(ProjectionConstants.DYNAMIC_COLOR_SCALAR)) {
                    i++;
                }
                Scalar dynScalar = ((ProjectionModel) viewer.getModel()).getScalars().get(i);
                for (AbstractInstance ai : selinst) {
                    ((ProjectionInstance) ai).setScalarValue(dynScalar, index);
                }
                ((ProjectionModel) viewer.getModel()).setSelectedScalar(dynScalar);
                viewer.getModel().setSelectedInstances(selinst);
                viewer.getModel().notifyObservers();
            }
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/toolbarButtonGraphics/development/WebComponent16.gif"));
    }

    @Override
    public String toString() {
        return "Dynamic Color Selection";
    }

    public Color getColor() {
        return this.color;
    }
    
    private Color color;
}
