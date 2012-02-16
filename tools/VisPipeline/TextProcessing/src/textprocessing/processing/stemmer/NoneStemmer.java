/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package textprocessing.processing.stemmer;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class NoneStemmer implements Stemmer {

    public String stem(String word) {
        return word;
    }

}
