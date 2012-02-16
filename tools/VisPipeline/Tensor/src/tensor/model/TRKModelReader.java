/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tensor.model;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projection.model.Scalar;
import tensor.io.BinaryBufferedFile;
import tensor.io.BinaryFile;
import tensor.io.FormatException;
import vtk.vtkCellArray;
import vtk.vtkPoints;

/**
 *
 * @author jpocom
 */
public class TRKModelReader {

    public void read(FiberModel model, String filename) throws IOException {
        this.model = model;

        BinaryFile bf = new BinaryBufferedFile(filename,1024);
        model.getHeader().read(bf);
        readStreamlinesTrk(bf);

        bf.close();
    }

    // structure of each entry of the self.streamline list:
    // [[X1,Y1,Z1,SCALAR1...],...,[Xn,Yn,Zn,SCALARn...]]
    private void readStreamlinesTrk(BinaryFile dis) throws IOException {
        FiberInstance fiber;
        try {
            vtkPoints points = new vtkPoints();
            vtkCellArray cells = new vtkCellArray();

            Scalar s = model.addScalar("...");
            // for each fiber
            for (int i=0; i< model.getHeader().getNCount(); i++) {
                int nPoints = dis.readInteger();
                int offset = points.GetNumberOfPoints();

                fiber = new FiberInstance(model, i, nPoints, offset, Color.YELLOW);
                dis.readFloatArray(fiber.data, 0, fiber.data.length);

                // for each point in the fiber
                cells.InsertNextCell(nPoints);
                for(int j=0; j<nPoints; j++) {
                    points.InsertNextPoint(fiber.getPointDouble(j));
                    cells.InsertCellPoint(offset+j);
                }
                fiber.setScalarValue(s, 0.f);
            }
            model.getPolydata().SetPoints(points);
            model.getPolydata().SetLines(cells);
            model.getPolydata().Update();
     
        } catch (EOFException ex) {
            Logger.getLogger(TRKModelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FormatException ex) {
            Logger.getLogger(TRKModelReader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private FiberModel model;
}
