package sensors.proteinfoldingold;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensors.singlemolecule.ArocaUtil;

/**
 *
 * @author fm
 */
public class ExperimentConformations {

    //numero de conformacoes
    private int count;
    //estrutura que armazena todas as conformacaos lidas (matrix 27*27) e sua respectiva incidencia nos dados
    protected HashMap<Conformation, Node> distinctConformations;

    public ExperimentConformations() {

        distinctConformations = new HashMap<Conformation, Node>();
    }

    public int getConformationKey(Conformation c) {
        return distinctConformations.get(c).key;
    }

    public int getNumberOfDistinctConformations() {
        return distinctConformations.size();
    }

    public void read(String filename) throws IOException {

        count = 0;
        int keyCounter = 0;

        //cria uma referencia para um leitor de buffer
        BufferedReader in = null;

        try {
            //cria um objeto para ler o arquivo especificado
            in = new BufferedReader(new FileReader(filename));

            //descartar a primeira linha, para evitar que se adicione uma prot inexistente
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

    public static Conformation readConformation(BufferedReader in) throws IOException {

        //ler uma linha do arquivo
        String line = in.readLine();

        //se nao chegou ao fim
        if (line != null) {

            //crie uma nova conformacao (vetor 27*27)
            Conformation p = new Conformation();

            //enquanto nao chegar ao fim do arquivo ou encontrar um -c ontinue lendo linhas, ou seja, leia todos os estados para formar uma conformacao
            while (line != null && !line.startsWith("-")) {

                try {
                    StringTokenizer token = new StringTokenizer(line, " ", false);

                    String ini = token.nextToken();
                    String end = token.nextToken();

                    //27 * ini-1 da a posicao da coluna e end - 1 da a posicao da linha da "matriz"
                    int index = (27 * (Integer.parseInt(ini) - 1)) + (Integer.parseInt(end) - 1);
//                    if (index > 10000) {
//                        System.err.println("Ow perae!");
//                        System.err.println("Deu merda aqui!!!");
//                        System.out.println("Esses sao os valores passados para o parser:\n ini = " + ini + "\nend = " + end);
//                    } else
                    p.chain[index] = true;

                    line = in.readLine();

                } catch (java.util.NoSuchElementException ex) {

                    //System.out.println("number proteins: " + count);
                    //System.out.println("number proteins conformations: " + conformation.size());

                    throw new IOException(ex);

                }
            }

            return p;
        }

        return null;
    }

    public void print() {
        System.out.println("total conformations: " + count);
        System.out.println("distinct conformations: " + distinctConformations.size());
        for (Node x : distinctConformations.values()) {
            System.out.println("Node: " + x.key + " incidence: " + x.incidence);
        }
    }
}
