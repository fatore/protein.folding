/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projection.stress;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class StressFactory {

    public enum StressType {

        KRUSKAL, NORMALIZED_KRUSKAL, PARTIAL_NORMALIZED_KRUSKAL, SAMMON, QUADRATIC_STRESS
    }

    public static Stress getInstance(StressType type) {
        if (type.equals(StressType.KRUSKAL)) {
            return new KruskalStress();
        } else if (type.equals(StressType.NORMALIZED_KRUSKAL)) {
            return new NormalizedKruskalStress();
        } else if (type.equals(StressType.PARTIAL_NORMALIZED_KRUSKAL)) {
            return new PartialNormalizedKruskalStress();
        } else if (type.equals(StressType.SAMMON)) {
            return new SammonStress();
        } else if (type.equals(StressType.QUADRATIC_STRESS)) {
            return new QuadraticStress();
        } else {
            return null;
        }
    }

}
