package sensors.proteinfoldingold;

/**
 *
 * @author fm
 */
public class Node {
    public int key;
    public int incidence;

    public Node() {
        this.key = 0;
        this.incidence = 0;
    }

    public Node(int k, int i) {
        this.key = k;
        this.incidence = i;
    }
}
