/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.technique.fastmap;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import projection.model.ProjectionModelComp;
import projection.view.ProjectionFrameComp;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractComponent;
import vispipelinebasics.interfaces.AbstractParametersView;

/**
 *
 * @author Fernando
 */
@VisComponent(hierarchy = "Projection.Technique",
name = "Fastmap",
description = "Project points from a multidimensional space to the plane "
+ "preserving the distance relations.",
howtocite = "???")
public class FastmapProjection2DComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //projecting
        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            FastmapProjection2D fastmap = new FastmapProjection2D();
            projection = fastmap.project(matrix, diss);
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

    @Override
    public AbstractParametersView getParametersEditor() {
//        if (paramview == null) {
//            paramview = new IDMAPProjectionParamView(this);
//        }
//
//        return paramview;
        return null;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
    }

    /**
     * @return the disstype
     */
    public DissimilarityType getDissimilarityType() {
        return disstype;
    }

    /**
     * @param disstype the disstype to set
     */
    public void setDissimilarityType(DissimilarityType diss) {
        this.disstype = diss;
    }

    public static void main(String[] args) {
         try {
            MatrixReaderComp reader = new MatrixReaderComp();
            reader.setFilename("D:\\Meus documentos\\FERNANDO\\Artigos\\2010\\mv_vis_2010\\code\\data\\mammals-7000-normcols.bin");
            reader.execute();
            AbstractMatrix matrix = reader.output();

            FastmapProjection2DComp fastmap = new FastmapProjection2DComp();
            fastmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
            fastmap.input(matrix);
            fastmap.execute();
            AbstractMatrix projection = fastmap.output();

            ProjectionModelComp model = new ProjectionModelComp();
            model.input(projection);
            model.execute();

            ProjectionFrameComp frame = new ProjectionFrameComp();
            frame.setTitle("Complete Projection");
            frame.input(model.output());
            frame.execute();
        } catch (IOException ex) {
            Logger.getLogger(FastmapProjection2DComp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    //private transient IDMAPProjectionParamView paramview;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
}
