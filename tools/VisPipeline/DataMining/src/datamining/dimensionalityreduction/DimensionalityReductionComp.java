/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datamining.dimensionalityreduction;

import datamining.dimensionalityreduction.DimensionalityReductionFactory.DimensionalityReductionType;
import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando V. Paulovic
 */
@VisComponent(hierarchy = "Transformation",
name = "Dimensionality Reduction",
description = "reduce the data dimensionality to a defined value smaller than the " +
"original dimensionality.")
public class DimensionalityReductionComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        AbstractDimensionalityReduction red = DimensionalityReductionFactory.getInstance(type, nrdimensions);
        AbstractDissimilarity diss = DissimilarityFactory.getInstance(disstype);
        output = red.reduce(input, diss);
    }

    public void input(@Param(name = "points matrix") AbstractMatrix input) {
        this.input = input;
    }

    public AbstractMatrix output() {
        return output;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new DimensionalityReductionParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        input = null;
        output = null;
    }

    /**
     * @return the nrdimensions
     */
    public int getNumberDimensions() {
        return nrdimensions;
    }

    /**
     * @param nrdimensions the nrdimensions to set
     */
    public void setNumberDimensions(int nrdimensions) {
        this.nrdimensions = nrdimensions;
    }

    /**
     * @return the type
     */
    public DimensionalityReductionType getDimensionalityReductionType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setDimensionalityReductionType(DimensionalityReductionType type) {
        this.type = type;
    }

    /**
     * @return the diss
     */
    public DissimilarityType getDissimilarityType() {
        return disstype;
    }

    /**
     * @param diss the diss to set
     */
    public void setDissimilarityType(DissimilarityType disstype) {
        this.disstype = disstype;
    }

    public int numberOriginalDimensions() {
        return input.getDimensions();
    }

    public static final long serialVersionUID = 1L;
    private DissimilarityType disstype = DissimilarityType.EUCLIDEAN;
    private DimensionalityReductionType type = DimensionalityReductionType.PCA;
    private int nrdimensions = 2;
    private transient DimensionalityReductionParamView paramview;
    private transient AbstractMatrix input;
    private transient AbstractMatrix output;
}
