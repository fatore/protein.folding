/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package projectiontesting;

import java.io.IOException;

/**
 *
 * @author PC
 */
public class TestAllStressFile {

    public static void main(String[] args) throws IOException {
        //TestStressFile.main(new String[]{"D:\\dados\\mammals-10000-normcols.bin", "D:\\dados\\glimmer\\mammals-10000-normcols.bin.csv.out-glimmer.prj"});
        TestStressFile.main(new String[]{"D:\\dados\\segmentation-normcols.data", "D:\\dados\\segmentation-normcols.data-plmp-spam.prj"});
//        TestStressFile.main(new String[]{"D:\\dados\\wdbc-std.data", "D:\\dados\\glimmer\\wdbc-std.data.csv.out-glimmer.prj"});
//        TestStressFile.main(new String[]{"D:\\dados\\winequality-white-std.data", "D:\\dados\\glimmer\\winequality-white-std.data.csv.out-glimmer.prj"});
//        TestStressFile.main(new String[]{"D:\\dados\\shuttle_trn_corr-normcols.data", "D:\\dados\\glimmer\\shuttle_trn_corr-normcols.data.csv.out-glimmer.prj"});
//        TestStressFile.main(new String[]{"D:\\dados\\multifield.0099-normcols.bin-30000.bin", "D:\\dados\\glimmer\\multifield.0099-normcols.bin-30000.bin.csv.out-glimmer.prj"});
    }

}
