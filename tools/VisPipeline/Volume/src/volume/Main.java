/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package volume;

import java.io.IOException;
import javax.swing.JFrame;
import plmp.technique.streaming.GenerateSample;
import plmp.technique.streaming.GenerateStreammingProjection;
import plmp.technique.streaming.SampleProjectionModel;
import plmp.technique.streaming.StreammingProjectionFrame;
import matrix.AbstractMatrix;
import matrix.AbstractVector;
import projection.model.ProjectionInstance;
import projection.model.Scalar;
import projection.util.ProjectionConstants;
import visualizationbasics.coordination.IdentityCoordinatorComp;
import volume.model.EmptyVolumeReaderComp;
import volume.model.VolumeModelComp;
import volume.view.SimpleVolumeFrameComp;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String desc = "D:\\viscontest\\multifieldintervals.txt";
        String dir = "D:\\viscontest\\normglobal-small";

        //generate the sample
        GenerateSample gensample = new GenerateSample(desc);
        AbstractMatrix samplematrix = gensample.generateSpamBase(null, 800);

        //generating the streamming projections
        GenerateStreammingProjection gsp = new GenerateStreammingProjection(dir, samplematrix);
        SampleProjectionModel samplemodel = new SampleProjectionModel(samplematrix);
        Scalar cdata = samplemodel.addScalar(ProjectionConstants.CDATA);
        AbstractMatrix sampleproj = gsp.getSampleProjection();

        int nrows = sampleproj.getRowCount();
        for (int i = 0; i < nrows; i++) {
            AbstractVector row = sampleproj.getRow(i);
            ProjectionInstance pi = new ProjectionInstance(samplemodel, row.getId(),
                    row.getValue(0), row.getValue(1));
            pi.setScalarValue(cdata, row.getKlass());
        }

        IdentityCoordinatorComp ident = new IdentityCoordinatorComp();
        ident.execute();

        StreammingProjectionFrame frame = new StreammingProjectionFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setSampleModel(samplemodel, gsp);
        frame.setVisible(true);
        frame.addCoordinator(ident.output());

        //projecting the first time stamp
        frame.projectTimestamp();

        EmptyVolumeReaderComp vreader = new EmptyVolumeReaderComp();
        vreader.setX(1);
        vreader.setY(1);
        vreader.setZ(1);
        vreader.setWidth(150);
        vreader.setHeight(62);
        vreader.setDepth(62);
        vreader.execute();

        VolumeModelComp vmodel = new VolumeModelComp();
        vmodel.input(vreader.outputVolume());
        vmodel.execute();

        SimpleVolumeFrameComp sframe = new SimpleVolumeFrameComp();
        sframe.input(vmodel.output(), vreader.outputImageData());
        sframe.attach(ident.output());
        sframe.execute();
    }

}
