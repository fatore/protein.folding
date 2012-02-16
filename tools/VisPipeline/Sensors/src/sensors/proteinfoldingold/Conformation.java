package sensors.proteinfoldingold;

import java.util.Arrays;

/**
 *
 * @author fm
 */
// Conformation é uma representacao da disposicao espacial das ligacoes
public class Conformation {

    public boolean[] chain;

    public Conformation() {
        //uma conformacao é uma matriz 27 por 27 (vetor na verdade) representando as ligacoes
        chain = new boolean[27 * 27];

        //fill preenche a matriz com valores especificados (false no caso)
        Arrays.fill(chain, false);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Arrays.hashCode(this.chain);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final Conformation other = (Conformation) obj;
        for (int i = 0; i < chain.length; i++) {
            if (chain[i] != other.chain[i]) {
                return false;
            }
        }

        return true;
    }

}
