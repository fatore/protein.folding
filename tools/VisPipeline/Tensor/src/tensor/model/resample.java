/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import datamining.normalization.NormalizeRows;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import matrix.AbstractMatrix;
import matrix.dense.DenseMatrix;
import matrix.dense.DenseVector;
import matrix.reader.BinaryMatrixReader;
import matrix.writer.BinaryMatrixWriter;


/**
 *
 * @author jpocom
 */
public class resample {
    static {
        System.loadLibrary("vtkCommonJava");
        System.loadLibrary("vtkFilteringJava");
        System.loadLibrary("vtkGenericFilteringJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkHybridJava");
        System.loadLibrary("vtkImagingJava");
        System.loadLibrary("vtkIOJava");
        System.loadLibrary("vtkGraphicsJava");
        System.loadLibrary("vtkRenderingJava");
        System.loadLibrary("vtkVolumeRenderingJava");
    }
    public static void main(String argv[]) {
        try {
            NormalizeRows norm = new NormalizeRows();


            BinaryMatrixReader reader = new BinaryMatrixReader();
            //AbstractMatrix fourier = reader.read("f:/Jorge/dataset/vispipeline/fiber_5_beginEnd_All_NotNormalized.bin");
            AbstractMatrix fourier = reader.read("f:/Jorge/dataset/vispipeline/fiber_5_beginEnd_classified_NotNormalized.bin");
            fourier = norm.execute(fourier);




            FiberModel model = new FiberModel();
            TRKModelReader readerf = new TRKModelReader();
            //readerf.read(model, "f:/Jorge/dataset/pbc2009icdm/brain1/brain1_scan1_fiber_track_mni.trk");
            readerf.read(model, "f:/Jorge/dataset/pbc2009icdm/brain1/brain1_scan1_fiber_track_mni_class.trk");

            FiberStatisticComp comp = new FiberStatisticComp();
            comp.input(model);
            comp.execute();
            AbstractMatrix spatial = comp.output();
            spatial = norm.execute(spatial);


            DenseMatrix rpt = new DenseMatrix();
            for (int i = 0; i<spatial.getRowCount(); i++) {
                float[] f = fourier.getRow(i).getValues();
                float[] s = spatial.getRow(i).getValues();
                float [] c = new float[f.length+s.length];
                System.err.println(f.length+" "+s.length);
                
                for (int ii=0; ii<f.length; ii++) {
                    c[ii]=f[ii];
                }
                for (int ii=0; ii<s.length; ii++) {
                    c[f.length+ii]=s[ii];
                }


                rpt.addRow(new DenseVector(c, fourier.getRow(i).getId(), fourier.getRow(i).getKlass() ));
            }

            BinaryMatrixWriter writer = new BinaryMatrixWriter();
            //writer.write(rpt, "f:/Jorge/dataset/vispipeline/fiber_5_beginEnd_spatial_50000_All_NotNormalized.bin");
            writer.write(rpt, "f:/Jorge/dataset/vispipeline/fibers_fourier_spatial_classified_Normalized.bin");


        } catch (IOException ex) {
            Logger.getLogger(resample.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
































