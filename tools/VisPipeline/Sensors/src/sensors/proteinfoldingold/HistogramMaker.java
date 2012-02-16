package sensors.proteinfoldingold;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author fm
 */
public class HistogramMaker {

    public static void write(String input, String output) throws Exception {

        ExperimentConformationTransitions ect = new ExperimentConformationTransitions();

        ect.read(input);

        try {
            PrintWriter out = new PrintWriter(new File(output).getAbsoluteFile());
            try {
                for (Node x : ect.ec.distinctConformations.values()) {
                    for (int i = 50000; i < x.incidence; i++) {
                        out.println(x.key);
                    }
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        //caminho padrao para os dados do experimento
        String input = "/home/fm/ic/Data/Resultados/maximos.dat";
        String output = "/home/fm/ic/incidencia-50000.txt";

        write(input, output);
    }
}
