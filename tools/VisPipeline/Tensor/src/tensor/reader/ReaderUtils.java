/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tensor.reader;

/**
 *
 * @author jpocom
 */
class ReaderUtils {

    public static int getOrientationAsInt(String label, boolean forAnalyze) {
        int rtn = -1;
        if (label.equalsIgnoreCase("TRANSVERSE")) {
            if (forAnalyze) {
                rtn = 0;
            } else {
                rtn = 2;
            }
        } else if (label.equalsIgnoreCase("CORONAL")) {
            if (forAnalyze) {
                rtn = 1;
            } else {
                rtn = 3;
            }
        } else if (label.equalsIgnoreCase("SAGITTAL")) {
            if (forAnalyze) {
                rtn = 2;
            } else {
                rtn = 4;
            }
        } else if (label.equalsIgnoreCase("TRANSVERSEF") && forAnalyze) {
            rtn = 3;
        } else if (label.equalsIgnoreCase("CORONALF") && forAnalyze) {
            rtn = 4;
        } else if (label.equalsIgnoreCase("SAGITTALF") && forAnalyze) {
            rtn = 5;
        }
        return rtn;
    }

    public static String getOrientationLabel(String code) {
        String rtn = code;
        //System.out.println("ReaderUtils recd Code " + code);
        if (code == null) {
            return rtn;
        }
        if (!code.startsWith("-")) {
            if (code.equalsIgnoreCase("2")) {
                rtn = "Transverse";
            } else if (code.equalsIgnoreCase("3")) {
                rtn = "Coronal";
            } else if (code.equalsIgnoreCase("4")) {
                rtn = "Sagittal";
            }
        } else if (code.startsWith("-")) {
            if (code.substring(1).equalsIgnoreCase("2")) {
                rtn = "TransverseF";
            } else if (code.substring(1).equalsIgnoreCase("3")) {
                rtn = "CoronalF";
            } else if (code.substring(1).equalsIgnoreCase("4")) {
                rtn = "SagittalF";
            }
        }
        return rtn;
    }
}
