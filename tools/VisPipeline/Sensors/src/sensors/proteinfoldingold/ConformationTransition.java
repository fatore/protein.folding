package sensors.proteinfoldingold;

import java.util.Arrays;

/**
 *
 * @author fm
 */
public class ConformationTransition {

    public Conformation ini;
    public Conformation end;

    public ConformationTransition() {
        ini = new Conformation();
        end = new Conformation();
    }

    @Override
    public int hashCode() {
        int[] h = new int[]{ini.hashCode(), end.hashCode()};

        int hash = 3;
        hash = 67 * hash + Arrays.hashCode(h);

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
        final ConformationTransition other = (ConformationTransition) obj;
        if (this.ini != other.ini && (this.ini == null || !this.ini.equals(other.ini))) {
            return false;
        }
        if (this.end != other.end && (this.end == null || !this.end.equals(other.end))) {
            return false;
        }
        return true;
    }
}
