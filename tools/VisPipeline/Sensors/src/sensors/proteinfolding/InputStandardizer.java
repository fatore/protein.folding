/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensors.proteinfolding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensors.proteinfoldingold.Conformation;
import sensors.proteinfoldingold.Node;
import static sensors.proteinfoldingold.ExperimentConformations.readConformation;
import sensors.singlemolecule.ArocaUtil;

/**
 *
 * @author fm
 */
public class InputStandardizer {

    public static void standardizeMinimus(String inputFile, String outputFile) throws IOException {

        //numero de conformacoes
        int count = 0;
        //numero de conformacoes distintas
        int keyCounter = 0;

        //estrutura que armazena todas as conformacaos lidas (matrix 27*27) e sua respectiva incidencia nos dados
        HashMap<Conformation, Node> distinctConformations = new HashMap<Conformation, Node>();

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            in = new BufferedReader(new FileReader(inputFile));

            out = new PrintWriter(new File(outputFile).getAbsoluteFile());

            //descartar a primeira linha
            in.readLine();

            //cria um referencia para uma conformacao
            Conformation p = null;

            Node n = null;

            //leia todas as conformacaos
            while ((p = readConformation(in)) != null) {

                //se ja foi encontrada tal conformacao
                if (distinctConformations.containsKey(p)) {
                    n = new Node();
                    //key se mantem o mesmo
                    n.key = distinctConformations.get(p).key;

                    //incrementa o contador de incidencia
                    n.incidence = distinctConformations.get(p).incidence + 1;

                    distinctConformations.put(p, n);
                } //senao conte a primeira
                else {

                    n = new Node();

                    //atribui uma chave ao no
                    n.key = keyCounter++;

                    //primeira ocorrencia desta conformacao
                    n.incidence = 1;

                    distinctConformations.put(p, n);
                }

                count++;

                //System.out.println(n.key);
                out.println(n.key);

            }

            System.out.println("# total " + count);
            System.out.println("# distinct " + keyCounter);

        } catch (FileNotFoundException ex) {

            throw new IOException(ex.getMessage());

        } finally {

            if (in != null) {
                try {
                    in.close();
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(ArocaUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    public static void standardizeMaximus(String inputFile, String outputFile) throws IOException {

        BufferedReader in = null;

        PrintWriter out = null;

        try {
            in = new BufferedReader(new FileReader(inputFile));

            out = new PrintWriter(new File(outputFile).getAbsoluteFile());

            String line = in.readLine();

            while (line != null) {

                if (line.startsWith("-")) {

                    StringTokenizer token = new StringTokenizer(line);

                    //System.out.println(token.nextToken(" "));
                    out.println(token.nextToken(" "));
                }                
                line = in.readLine();

            }

        } catch (FileNotFoundException ex) {

            throw new IOException(ex.getMessage());

        } finally {

            if (in != null) {
                    in.close();
                    }
            if (out != null) {
                    out.close();
            }

        }
    }
}
