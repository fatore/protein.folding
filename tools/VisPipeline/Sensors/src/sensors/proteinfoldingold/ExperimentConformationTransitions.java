package sensors.proteinfoldingold;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensors.singlemolecule.ArocaUtil;

/**
 *
 * @author fm
 */
public class ExperimentConformationTransitions {

    protected HashMap<ConformationTransition, Edge> transitions = null;

    protected ExperimentConformations ec = null;

    public ExperimentConformationTransitions() {

        transitions = new HashMap<ConformationTransition, Edge>();
    }

    public void read(String filename) throws IOException {

        //registar as chaves das conformacoes
        ec = new ExperimentConformations();
        ec.read(filename);

        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(filename));

            Edge e = null;

            //descarta a primeira linha (supoe que comeca com -)
            in.readLine();

            ConformationTransition t = new ConformationTransition();

            Conformation c = null;

            c = ExperimentConformations.readConformation(in);

            while (true) {
                //conformacao atual é a final do passo anterior
                t.ini = c;

                //avanca para proxima conformacao
                if ((c = ExperimentConformations.readConformation(in)) == null) {
                    break;
                }
                t.end = c;

                //print aqui !*
                //System.out.println("\t" + ec.getConformationKey(t.ini) + " -> " + ec.getConformationKey(t.end));

                //se a transicao ja existe
                if (transitions.containsKey(t)) {

                    e = new Edge();
                    //chaves se mantem as mesmas
                    e.source = transitions.get(t).source;
                    e.target = transitions.get(t).target;

                    //incrementa o peso da aresta
                    e.weight = transitions.get(t).weight + 1;

                    transitions.put(t, e);
                } //senao adicione a primeira
                else {

                    e = new Edge();
                    //no inicial equivale a conformacao inicial da transicao
                    e.source = ec.getConformationKey(t.ini);
                    //no destino equivale a conformacao destino da transicao
                    e.target = ec.getConformationKey(t.end);
                    //o inicial peso da aresta
                    e.weight = 1;

                    transitions.put(t, e);
                }

                t = new ConformationTransition();
            }

        } catch (FileNotFoundException ex) {

            throw new IOException(ex.getMessage());

        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(ArocaUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void print() {
        for (Edge x : transitions.values()) {
            System.out.println("source: " + x.source + " target: " + x.target  + " weight: " + x.weight);
        }
    }



    public static void main(String[] args) throws IOException {

        ExperimentConformationTransitions ect = new ExperimentConformationTransitions();

        //caminho padrao para os dados do experimento
        String filePath = "/home/fm/ic/Data/Resultados/maximos.dat";

        //caminho alternativo pode ser passado como primeiro parametro
        if (args.length > 0) {
            filePath = args[0];
        }

        ect.read(filePath);

        //ect.print();
    }
}
