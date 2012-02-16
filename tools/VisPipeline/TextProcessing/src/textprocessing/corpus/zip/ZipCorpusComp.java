/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing.corpus.zip;

import java.io.IOException;
import textprocessing.corpus.Corpus;
import vispipelinebasics.annotations.VisComponent;
import vispipelinebasics.interfaces.AbstractParametersView;
import vispipelinebasics.interfaces.AbstractComponent;

/**
 *
 * @author Fernando Vieira Paulovich
 */
@VisComponent(hierarchy = "Text Processing",
name = "Zip File Corpus",
description = "Represents a set of text documents in a *.zip file.")
public class ZipCorpusComp implements AbstractComponent {

    @Override
    public void execute() throws IOException {
        if (url.trim().length() > 0) {
            corpus = new ZipCorpus(url, nrGrams);
        } else {
            throw new IOException("A corpus file name must be provided.");
        }
    }

    public Corpus output() {
        return corpus;
    }

    @Override
    public AbstractParametersView getParametersEditor() {
        if (paramview == null) {
            paramview = new ZipCorpusParamView(this);
        }

        return paramview;
    }

    @Override
    public void reset() {
        corpus = null;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the nrGrams
     */
    public int getNumberGrams() {
        return nrGrams;
    }

    /**
     * @param nrGrams the nrGrams to set
     */
    public void setNumberGrams(int nrGrams) {
        this.nrGrams = nrGrams;
    }

    public static final long serialVersionUID = 1L;
    private transient ZipCorpusParamView paramview;
    private String url = "";
    private int nrGrams = 1;
    private transient ZipCorpus corpus;
}
