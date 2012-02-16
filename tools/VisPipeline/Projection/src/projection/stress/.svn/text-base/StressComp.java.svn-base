/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.stress;

import java.io.IOException;
import projection.stress.StressFactory.StressType;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import distance.DistanceMatrix;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import matrix.AbstractMatrix;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Projection.Basics",
name = "Calculate Stress",
description = "Calculate how the distances relations of a projection differ " +
"from the original distances relations.")
public class StressComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        Stress stresscalc = StressFactory.getInstance(stresstype);

        if (matrix != null) { //using a matrix
            AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
            stress = stresscalc.calculate(projection, matrix, diss);
        } else if (dmat != null) { //using a distance matrix
            stress = stresscalc.calculate(projection, dmat);
        } else {
            throw new IOException("A distance matrix or a points matrix should " +
                    "be provided.");
        }
    }

    public void input(@Param(name = "projection") AbstractMatrix projection,
            @Param(name = "points matrix") AbstractMatrix matrix) {
        this.projection = projection;
        this.matrix = matrix;
    }

    public void input(@Param(name = "projection") AbstractMatrix projection,
            @Param(name = "distance matrix") DistanceMatrix dmat) {
        this.projection = projection;
        this.dmat = dmat;
    }

    public Float output() {
        return stress;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new StressParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        projection = null;
        matrix = null;
        dmat = null;
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

    public boolean isDistanceMatrixProvided() {
        return (dmat != null);
    }

    /**
     * @return the stresstype
     */
    public StressType getStressType() {
        return stresstype;
    }

    /**
     * @param stresstype the stresstype to set
     */
    public void setStressType(StressType stresstype) {
        this.stresstype = stresstype;
    }

    public static final long serialVersionUID = 1L;
    private StressType stresstype = StressType.KRUSKAL;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private transient StressParamView paramview;
    private transient float stress;
    private transient AbstractMatrix projection;
    private transient AbstractMatrix matrix;
    private transient DistanceMatrix dmat;
}
