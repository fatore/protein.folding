/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.view.selectedview;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import tensor.model.FiberInstance;
import tensor.model.FiberModel;
import tensor.view.FibersFrame;
import vtk.vtkActor;
import vtk.vtkCellArray;
import vtk.vtkPoints;
import vtk.vtkPolyData;
import vtk.vtkPolyDataMapper;
import vtk.vtkSurfaceActor;
import vtk.vtkTubeFilter;

/**
 *
 * @author jpocom
 */
public class TubeSelectedFiber extends SelectedFiberRepresentation {

    public TubeSelectedFiber(FibersFrame viewer) {
        super(viewer);
    }

    @Override
    public void selected(ArrayList<FiberInstance> selinst) {
        if (selinst != null) {
            vtkPoints points = new vtkPoints();
            vtkCellArray cells = new vtkCellArray();

            double[] color = new double[3];
            for (FiberInstance fiber : selinst) {
                cells.InsertNextCell(fiber.getNumberOfPoints());
                for (int i = 0; i < fiber.getNumberOfPoints(); i++) {
                    cells.InsertCellPoint(points.GetNumberOfPoints());
                    points.InsertNextPoint(fiber.getPointDouble(i));
                }

                color[0] += fiber.getColor().getRed();
                color[1] += fiber.getColor().getGreen();
                color[2] += fiber.getColor().getBlue();
            }
            color[0] /= selinst.size();
            color[1] /= selinst.size();
            color[2] /= selinst.size();

            vtkPolyData po = new vtkPolyData();
            po.SetPoints(points);
            po.SetLines(cells);

            vtkTubeFilter tu = new vtkTubeFilter();
            tu.SetInput(po);
            tu.SetRadius(0.3);
            tu.SetNumberOfSides(6);

            vtkPolyDataMapper ma = new vtkPolyDataMapper();
            ma.SetInput(tu.GetOutput());
            ma.SetScalarVisibility(0);

            vtkSurfaceActor actor = new vtkSurfaceActor("");
            actor.SetMapper(ma);
            actor.GetProperty().SetColor(color);

            ((FiberModel) viewer.getModel()).addSurface(actor);
        }
    }

    @Override
    public ImageIcon getIcon() {
        return new ImageIcon(getClass().getResource("/tensor/util/tube.png"));
    }

    @Override
    public String toString() {
        return "Selected fibers represented as tubes";
    }
}
