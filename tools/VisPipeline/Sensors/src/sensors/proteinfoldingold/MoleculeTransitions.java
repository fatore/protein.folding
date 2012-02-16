package sensors.proteinfoldingold;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensors.singlemolecule.ArocaUtil;

/**
 *
 * @author fm
 */
public class MoleculeTransitions {

    private int[] transitions;

    public MoleculeTransitions() {
        transitions = new int[27 * 27];
        Arrays.fill(transitions, 0);

    }

    public void read(String filename) throws IOException {

        BufferedReader in = null;
        String line = null;

        try {
            in = new BufferedReader(new FileReader(filename));

            line = in.readLine();

            //enquanto nao acabar o arquivo
            while (line != null) {
                try {

                    //se for uma linha valida
                    if (!line.startsWith("-")) {
                        StringTokenizer token = new StringTokenizer(line, " ", false);

                        String ini = token.nextToken();
                        String end = token.nextToken();

                        int index = (27 * (Integer.parseInt(ini) - 1)) + (Integer.parseInt(end) - 1);
                        transitions[index]++;

                    }
                    line = in.readLine();
                } catch (java.util.NoSuchElementException ex) {
                    throw new IOException(ex);
                }
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
        //System.out.printf(" %d ",1);
        for (int i = 1; i <= 27; i++) {
            System.out.printf("%7d ",i);
        }
        System.out.println();
        for (int i = 0; i < 27; i++) {
            System.out.printf("%2d |",(i+1));
            for (int j = 0; j < 27; j++) {
                //System.out.print("[" + (i + 1) + "," + (j + 1) + "] ");
                //System.out.print(transitions[(i * 27) + j] + " ");
                System.out.printf("%7d|", transitions[(i * 27) + j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        MoleculeTransitions ct = new MoleculeTransitions();

        //caminho padrao para os dados do experimento
        String filePath = "/home/fm/ic/Data/Resultados/maximos.dat";

        //caminho alternativo pode ser passado como primeiro parametro
        if (args.length > 0) {
            filePath = args[0];
        }

        ct.read(filePath);

        ct.print();
    }
}
