/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package plmp.technique.streaming;

import distance.dissimilarity.AbstractDissimilarity;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import plmp.technique.PLMPProjection;
import matrix.AbstractMatrix;
import matrix.reader.BinaryMatrixReader;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.technique.idmap.IDMAPProjectionComp;

/**
 *
 * @author Fernando
 */
public class GenerateStreammingProjection {

    public GenerateStreammingProjection(String directory, AbstractMatrix samplematrix) throws IOException {
        this.matrices = getMatricesFilenames(directory);
        this.samplematrix = samplematrix;
        this.currentMatrix = 0;

        //projecting the sample
        IDMAPProjectionComp idmap = new IDMAPProjectionComp();
        idmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        idmap.setFractionDelta(8.0f);
        idmap.setInitialization(InitializationType.FASTMAP);
        idmap.setNumberIterations(100);
        idmap.input(samplematrix);
        idmap.execute();
        this.sampleproj = idmap.output();
    }

    public int getNumberTimestamps() {
        return matrices.size();
    }

    public AbstractMatrix getSampleProjection() {
        return sampleproj;
    }

    public void setSampleProjection(AbstractMatrix sampleproj) throws IOException {
        this.sampleproj = sampleproj;
    }

    public AbstractMatrix project(AbstractMatrix matrix, AbstractDissimilarity diss) throws IOException {
        if (samplematrix != null && sampleproj != null) {
            PLMPProjection mmds = new PLMPProjection();
            mmds.setFractionDelta(8.0f);
            mmds.setNumberIterations(100);
            mmds.setSampleMatrix(samplematrix);
            mmds.setSampleProjection(sampleproj);
            AbstractMatrix projection = mmds.project(matrix, diss);
            return projection;
        } else {
            return null;
        }
    }

    public AbstractMatrix getCurrentMatrix() throws IOException {
        BinaryMatrixReader bmr = new BinaryMatrixReader();
        return bmr.read(matrices.get(currentMatrix).getPath());
    }

    public File getCurrentMatrixFilename() throws IOException {
        return matrices.get(currentMatrix);
    }

    public int nextMatrix() throws IOException {
        currentMatrix++;
        if (currentMatrix >= matrices.size()) {
            currentMatrix = 0;
        }
        return currentMatrix;
    }

    public int previousMatrix() throws IOException {
        currentMatrix--;
        if (currentMatrix < 0) {
            currentMatrix = matrices.size() - 1;
        }
        return currentMatrix;
    }

    private ArrayList<File> getMatricesFilenames(String directory) {
        File dir = new File(directory);
        File[] files = dir.listFiles();

        ArrayList<File> filenames = new ArrayList<File>();

        for (File f : files) {
            if (f.getName().endsWith(".bin")) {
                filenames.add(f);
            }
        }

        Collections.sort(filenames);

        return filenames;
    }

    private AbstractMatrix samplematrix;
    private AbstractMatrix sampleproj;
    private ArrayList<File> matrices;
    private int currentMatrix;
}
