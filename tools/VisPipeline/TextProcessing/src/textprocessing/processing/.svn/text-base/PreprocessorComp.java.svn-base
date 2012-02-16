/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing.processing;

import java.io.IOException;
import java.util.ArrayList;
import matrix.AbstractMatrix;
import textprocessing.corpus.Corpus;
import textprocessing.processing.stemmer.StemmerFactory.StemmerType;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.annotations.Param;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Text Processing",
name = "Text Preprocessor",
description = "Process a set of documents and return a \"documents x terms\" " +
"matrix containing the frequency of terms.",
howtocite = "Salton, G. Developments in automatic text retrieval. Science, v. " +
"253, p. 974-980, 1991.")
public class PreprocessorComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        //creating the points matrix
        Preprocessor preproc = new Preprocessor(corpus);
        matrix = preproc.getMatrix(lowerCut, upperCut, numberGrams, stemmer);

        //creating the labels
        labels = new ArrayList<String>();
        for (Integer id : corpus.getIds()) {
            String label = corpus.getLabel(nrlines, id);
            labels.add(label);
        }
    }

    public void input(@Param(name = "corpus") Corpus corpus) {
        this.corpus = corpus;
        numberGrams = corpus.getNumberGrams();
    }

    public AbstractMatrix outputMatrix() {
        return matrix;
    }

    public ArrayList<String> outputLabels() {
        return labels;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new PreprocessorParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        matrix = null;
        corpus = null;
    }

    /**
     * @return the lowerCut
     */
    public int getLowerCut() {
        return lowerCut;
    }

    /**
     * @param lowerCut the lowerCut to set
     */
    public void setLowerCut(int lowerCut) {
        this.lowerCut = lowerCut;
    }

    /**
     * @return the upperCut
     */
    public int getUpperCut() {
        return upperCut;
    }

    /**
     * @param upperCut the upperCut to set
     */
    public void setUpperCut(int upperCut) {
        this.upperCut = upperCut;
    }

    /**
     * @return the numberGrams
     */
    public int getNumberGrams() {
        return numberGrams;
    }

    /**
     * @param numberGrams the numberGrams to set
     */
    public void setNumberGrams(int numberGrams) {
        this.numberGrams = numberGrams;
    }

    /**
     * @return the stemmer
     */
    public StemmerType getStemmer() {
        return stemmer;
    }

    /**
     * @param stemmer the stemmer to set
     */
    public void setStemmer(StemmerType stemmer) {
        this.stemmer = stemmer;
    }

    /**
     * @return the corpus
     */
    public Corpus getCorpus() {
        return corpus;
    }

    /**
     * @return the nrlines
     */
    public int getNumberLines() {
        return nrlines;
    }

    /**
     * @param nrlines the nrlines to set
     */
    public void setNumberLines(int nrlines) {
        this.nrlines = nrlines;
    }

    public static final long serialVersionUID = 1L;
    //input parameters should be serializable, otherwise
    //they should be transient
    private int lowerCut = 10;
    private int upperCut = -1;
    private int numberGrams = 1;
    private StemmerType stemmer = StemmerType.ENGLISH;
    private int nrlines = 1;
    //output parameters should be transient
    private transient AbstractMatrix matrix;
    private transient ArrayList<String> labels;
    //the parameters editor (it should be transient)
    private transient PreprocessorParamView paramview;
    //the input parameters should be transient
    private transient Corpus corpus;
}
