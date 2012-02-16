/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ncd;

import java.io.IOException;
import textprocessing.corpus.Corpus;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;
import distance.DistanceMatrix;
import ncd.NcdDistanceMatrixFactory.CompressorType;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Distance.Technique",
name = "Normal Compress Distance (NCD)",
description = "Create a distance matrix calculated using the Scaled Normal " +
"Compress Distance (NCDs) approach",
howtocite = "Telles, G. P.; Minghim, R.; Paulovich, F.V. " +
"Normalized Compression Distances for Visual Analysis of " +
"Document Collections. Computers & Graphics Journal, Special " +
"Issue on Visual Analytics, 31(3), 2007.")
public class NormalCompressDistanceComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (corpus != null) {
            NormalCompressDistance ncd = new NormalCompressDistance(comptype, corpus);

            dmat = new DistanceMatrix(corpus.getIds().size());
            dmat.setIds(corpus.getIds());
            dmat.setClassData(corpus.getClassData());

            for (int i = 0; i < dmat.getElementCount(); i++) {
                for (int j = i + 1; j < dmat.getElementCount(); j++) {
                    dmat.setDistance(i, j, ncd.calculateNewNCD(i, j));
                }
            }
        } else {
            throw new IOException("A corpus should be provided.");
        }
    }

    public void input(@Param(name = "corpus") Corpus corpus) {
        this.corpus = corpus;
    }

    public DistanceMatrix output() {
        return dmat;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new NormalCompressDistanceParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        corpus = null;
        dmat = null;
    }

    /**
     * @return the compType
     */
    public CompressorType getCompressorType() {
        return comptype;
    }

    /**
     * @param compType the compType to set
     */
    public void setCompressorType(CompressorType compType) {
        this.comptype = compType;
    }

    public static final long serialVersionUID = 1L;
    private CompressorType comptype = CompressorType.BZIP2;
    private transient NormalCompressDistanceParamView paramview;
    private transient Corpus corpus;
    private transient DistanceMatrix dmat;
}
