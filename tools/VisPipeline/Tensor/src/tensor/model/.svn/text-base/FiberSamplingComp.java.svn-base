/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import projection.model.Scalar;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import visualizationbasics.model.AbstractInstance;
import vtk.vtkCellArray;
import vtk.vtkPoints;

/**
 *
 * @author jpocom
 */
@VisComponent(hierarchy = "Tensor.Features",
name = "Fiber's sampling",
description = "Sampling a bundle of fibers.")
public class FiberSamplingComp implements AbstractComponent {

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new FiberSamplingParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        modelInput = null;
    }

    @Override
    public void execute() throws IOException {
        if (modelInput != null) {
            modelOutput = new FiberModel();
            modelOutput.setHeader((TRKHeader)modelInput.getHeader().clone());

            HashSet<Integer> ids = new HashSet<Integer>();
            ArrayList<AbstractInstance> instances = modelInput.getInstances();

            FiberInstance fiber;
            vtkPoints points = new vtkPoints();
            vtkCellArray cells = new vtkCellArray();
            while (ids.size()<numberOfFibers) {

                int pos = (int)(Math.random()*instances.size());
                if (!ids.contains(pos)) {
                    ids.add(pos);
                    int offset = points.GetNumberOfPoints();
                    fiber = ((FiberInstance)instances.get(pos)).clone(modelOutput);

                    // for each point in the fibre
                    cells.InsertNextCell(fiber.numPoints);
                    for(int j=0; j<fiber.numPoints; j++) {
                        points.InsertNextPoint(fiber.getPointDouble(j));
                        cells.InsertCellPoint(offset+j);
                    }

                    modelOutput.getInstances().add(fiber);
                }
            }

            modelOutput.getPolydata().SetPoints(points);
            modelOutput.getPolydata().SetLines(cells);
            modelOutput.getPolydata().Update();
            
            for (Scalar s: modelInput.getScalars()) {
                modelOutput.addScalar(s.getName());
                //modelOutput.updatePolydataScalar(s);
            }
            
            modelOutput.setSelectedScalar(modelInput.getSelectedScalar());
        }
    }

    public void input(@Param(name = "Fiber model") FiberModel model) {
        this.modelInput = model;
    }

    public FiberModel output() {
        return modelOutput;
    }

    public int getNumberOfFibers() {
        return numberOfFibers;
    }

    public void setNumberOfFibers(int numberOfFibers) {
        this.numberOfFibers = numberOfFibers;
    }
    
    public static final long serialVersionUID = 1L;
    private int numberOfFibers = 2000;
    private transient FiberModel modelInput;
    private transient FiberModel modelOutput;
    private transient FiberSamplingParamView paramview;
}
