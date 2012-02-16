/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package parallelcoordinates;

import datamining.normalization.NormalizationComp;
import datamining.normalization.NormalizationFactory.NormalizationType;
import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import javax.swing.JFrame;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import parallelcoordinates.model.ParallelCoordinatesModelComp;
import parallelcoordinates.model.view.ParallelCoordinatesFrame;
import parallelcoordinates.model.view.ParallelCoordinatesFrameComp;
import projection.model.ProjectionModelComp;
import projection.technique.idmap.IDMAPProjection.InitializationType;
import projection.technique.idmap.IDMAPProjectionComp;
import projection.view.ProjectionFrameComp;
import visualizationbasics.coordination.IdentityCoordinatorComp;

/**
 *
 * @author Fernando
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        IdentityCoordinatorComp coord = new IdentityCoordinatorComp();
        coord.execute();

        String filename = "D:\\dados\\auto-mpg.data";
        MatrixReaderComp mrc = new MatrixReaderComp();
        mrc.setFilename(filename);
        mrc.execute();
        AbstractMatrix data = mrc.output();

        ParallelCoordinatesModelComp pcmodel = new ParallelCoordinatesModelComp();
        pcmodel.input(data);
        pcmodel.execute();

        ParallelCoordinatesFrameComp pcframe = new ParallelCoordinatesFrameComp();
        pcframe.setTitle(filename);
        pcframe.input(pcmodel.output());
        pcframe.attach(coord.output());
        pcframe.execute();

        NormalizationComp norm = new NormalizationComp();
        norm.setNormalizationType(NormalizationType.NORMALIZE_COLUMNS);
        norm.input(data);
        norm.execute();
        data = norm.output();

        IDMAPProjectionComp idmap = new IDMAPProjectionComp();
        idmap.setDissimilarityType(DissimilarityType.EUCLIDEAN);
        idmap.setFractionDelta(8.0f);
        idmap.setInitialization(InitializationType.FASTMAP);
        idmap.setNumberIterations(100);
        idmap.input(data);
        idmap.execute();

        ProjectionModelComp pmodel = new ProjectionModelComp();
        pmodel.input(idmap.output());
        pmodel.execute();

        ProjectionFrameComp pframe = new ProjectionFrameComp();
        pframe.setTitle(filename);
        pframe.input(pmodel.output());
        pframe.attach(coord.output());
        pframe.execute();






//        ParallelCoordinatesFrame view = new ParallelCoordinatesFrame();
//        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        view.setVisible(true);

//        try {
//            String filename = "D:/dados/EletrodoPuro-img.data";
//            AbstractMatrix matrix = MatrixFactory.getInstance(filename);
//
//            ParallelCoordinatesModel newmodel = new ParallelCoordinatesModel(matrix.getAttributes());
//            for (int i = 0; i < matrix.getRowCount(); i++) {
//                PolylineInstance pi = new PolylineInstance((ParallelCoordinatesModel) newmodel, matrix.getRow(i));
//            }
//
//            ((ParallelCoordinatesModel) newmodel).calculateSilhouette();
//
//            ParallelCoordinatesFrame view = new ParallelCoordinatesFrame();
//            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            view.setSize(800, 800);
//            view.setModel(newmodel);
//            view.setVisible(true);
//
//        } catch (IOException ex) {
//            Logger.getLogger(ParallelCoordinatesFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
