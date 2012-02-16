/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.view.selectedview;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import projection3d.surface.SurfaceFactory;
import projection3d.surface.SurfaceFactory.SurfaceType;
import tensor.model.FiberInstance;
import tensor.model.FiberModel;
import tensor.view.FibersFrame;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkSurfaceActor;


/**
 *
 * @author jpocom
 */
public class SurfaceSplatSelectedFiber extends SelectedFiberRepresentation {

    public SurfaceSplatSelectedFiber(FibersFrame viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<FiberInstance> selinst) {
        int numTotalPoints = 0;
        for (FiberInstance fiber: selinst) {
            numTotalPoints += fiber.getNumberOfPoints();
        }

        double[] color = new double[3];
        double[][] points = new double[numTotalPoints][3];
        int pos = 0;
        for (FiberInstance fiber: selinst) {
            for(int i = 0; i < fiber.getNumberOfPoints(); i++) {
                double[] point = fiber.getPointDouble(i);
                points[pos][0] = point[0];
                points[pos][1] = point[1];
                points[pos++][2] = point[2];
            }
            color[0] += fiber.getColor().getRed();
            color[1] += fiber.getColor().getGreen();
            color[2] += fiber.getColor().getBlue();
        }
        color[0] /= selinst.size() * 256.;
        color[1] /= selinst.size() * 256.;
        color[2] /= selinst.size() * 256.;


        vtkSurfaceActor actor = SurfaceFactory.getInstance(SurfaceType.SPLAT, points, "");
        actor.GetProperty().SetColor(color);

        ArrayList<AbstractInstance> instances = new ArrayList<AbstractInstance>();
        for (AbstractInstance ai: selinst) {
            instances.add(ai);
        }
        actor.setInstances(instances);

        ((FiberModel)viewer.getModel()).addSurface(actor);
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/tensor/util/surface.png"));
    }

    @Override
    public String toString() {
        return "Selected fibers represented as a density surface";
    }

}
