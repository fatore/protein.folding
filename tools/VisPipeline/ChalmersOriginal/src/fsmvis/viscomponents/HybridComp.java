/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fsmvis.viscomponents;

import fsmvis.data.DataItemCollection;
import fsmvis.engine.EndCriteria;
import fsmvis.engine.InterpolateSampleModel;
import fsmvis.engine.LayoutModel;
import fsmvis.engine.TooManyIterationsException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Hybrid Technique",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "Morrison, A.; Ross, G.; Chalmers, M. A hybrid layout algorithm "
+ "for sub-quadratic multidimensional scaling. In: Proceedings of the IEEE "
+ "Symposium on Information Visualization (InfoVis'02), Washington, DC, USA: "
+ "IEEE Computer Society, 2002b, p. 152.")
public class HybridComp extends AbstractComponent implements vispipelinebasics.interfaces.AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            try {
                //using a matrix
                DataItemCollection data = createDataCollection(matrix);
                EndCriteria ec = new EndCriteria(1, nriterations, 0, 0, data.getSize());

                LayoutModel model = new InterpolateSampleModel(data, ec, null);

                model.getProperties().setProperty(LayoutModel.FREENESS, Float.toString(freeness));
                model.getProperties().setProperty(LayoutModel.DAMPING_FACTOR, Float.toString(dampingfactor));
                model.getProperties().setProperty(LayoutModel.SPRING_FORCE, Float.toString(springforce));
                model.updateValues();

                for (int i = 0; i < nriterations; i++) {
                    model.doIteration();
                }

                projection = createProjection(matrix, data);
            } catch (TooManyIterationsException ex) {
                Logger.getLogger(SpringComp.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new IOException("A points matrix should be provided.");
        }
    }

    public void input(@Param(name = "points matrix") AbstractMatrix matrix) {
        this.matrix = matrix;
    }

    public AbstractMatrix output() {
        return projection;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("ERROR! It should be: TestHybridModelTime filename.data");
            System.exit(1);
        }

        System.out.println("========================");
        System.out.println("Hybrid Model - processing: " + args[0]);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(args[0]);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        //creating the projection
        long start = System.currentTimeMillis();
        HybridComp hybrid = new HybridComp();
        hybrid.setDampingFactor(0.3f);
        hybrid.setFreeness(0.3f);
        hybrid.setSpringForce(0.7f);
        hybrid.setNumberIterations((int) Math.sqrt(matrix.getRowCount()));
        hybrid.input(matrix);
        hybrid.execute();
        long finish = System.currentTimeMillis();

        System.out.println(matrix.getRowCount() + ";" + (finish - start) / 1000.0f);
    }

    public static final long serialVersionUID = 1L;
}
