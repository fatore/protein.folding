/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projectiontesting.projection.stress;

import distance.dissimilarity.DissimilarityFactory.DissimilarityType;
import java.io.IOException;
import matrix.AbstractMatrix;
import matrix.reader.MatrixReaderComp;
import plsp.technique.PLSPProjection2DComp;
import projection.stress.StressComp;
import projection.stress.StressFactory.StressType;
import projectiontesting.Dump;

/**
 *
 * @author PC
 */
public class TestStressTimePLSP extends TestStressTime {

    @Override
    public void execute(String filename, StressType stresstype,
            String technique, int nrattempts, DissimilarityType dissstype) throws IOException {
        Dump.dump("=================================================");
        Dump.dump("Technique: " + technique + " - processing: " + filename);

        MatrixReaderComp reader = new MatrixReaderComp();
        reader.setFilename(filename);
        reader.execute();
        AbstractMatrix matrix = reader.output();

        float stress_value = Float.POSITIVE_INFINITY;
        float time = 0;
        AbstractMatrix projection = null;

        int init = 5;
        int end = 25;

        for (int i = init; i <= end; i++) {
            nrneighbors = i;

            for (int j = 0; j < nrattempts; j++) {
                long start = System.currentTimeMillis();
                AbstractMatrix projection_aux = project(matrix, dissstype);
                long finish = System.currentTimeMillis();

                StressComp stress = new StressComp();
                stress.setDissimilarityType(dissstype);
                stress.setStressType(stresstype);
                stress.input(projection_aux, matrix);
                stress.execute();
                float new_stress = stress.output();

                if (!Float.isNaN(new_stress) && stress_value > new_stress) {
                    stress_value = new_stress;
                    time = (finish - start) / 1000.0f;
                    projection = projection_aux;
                    projection.save(filename + "-" + technique + ".prj");
                }

                Dump.dump(technique + " number neighbors: " + i + " - "
                        + " time: " + (finish - start) / 1000.0f + "s - stress: " + stress.output());
            }
        }

        if (projection != null) {
            Dump.dump("=================================================");
            Dump.dump("Technique: " + technique + " - Dissimilarity: " + dissstype + " - processing: " + filename);
            Dump.dump(technique + " projection time: " + time + "s");

            StressComp stress = new StressComp();
            stress.setDissimilarityType(dissstype);
            stress.setStressType(StressType.NORMALIZED_KRUSKAL);
            stress.input(projection, matrix);
            stress.execute();

            Dump.dump(technique + " partial projection stress: " + stress_value);
            Dump.dump(technique + " projection stress: " + stress.output());
            Dump.dump("=================================================");
        }
    }

    @Override
    protected AbstractMatrix project(AbstractMatrix matrix, DissimilarityType dissstype) throws IOException {
        PLSPProjection2DComp plsp = new PLSPProjection2DComp();
        plsp.setFractionDelta(8.0f);
        plsp.setDissimilarityType(dissstype);
        plsp.setNumberIterations(80);
        plsp.setNumberNeighbors(nrneighbors);
        plsp.input(matrix);
        plsp.execute();
        AbstractMatrix projection = plsp.output();
        return projection;
    }
    private int nrneighbors;
}
