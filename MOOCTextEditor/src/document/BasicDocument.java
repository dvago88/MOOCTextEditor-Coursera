package document;

import java.util.ArrayList;
import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document {
    /**
     * Create a new BasicDocument object
     *
     * @param text The full text of the Document.
     */
    public BasicDocument(String text) {
        super(text);
    }


    @Override
    public int getNumWords() {
        return getTokens("[A-Za-z']+").size();
    }

    @Override
    public int getNumSentences() {
        int numberOfSentences = getTokens("[.?!]+").size();
        int length = getText().length();
        if (length == 0) {
            return 0;
        }
        if (getText().charAt(length - 1) != '.' && getText().charAt(length - 1) != '!' &&
                getText().charAt(length - 1) != '?' && getText().charAt(length - 1) != ' ') {
            numberOfSentences++;
        }

        return numberOfSentences;
    }

    @Override
    public int getNumSyllables() {
        int contador = 0;
        for (String palabras : getTokens("[A-Za-z']+")) {
            contador += countSyllables(palabras);
        }
        return contador;
    }


    /* The main method for testing this class.
     * You are encouraged to add your own tests.  */
    public static void main(String[] args) {
        /* Each of the test cases below uses the method testCase.  The first
         * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences 
		 * in the string, respectively.  You can use these examples to help clarify 
		 * your understanding of how to count syllables, words, and sentences.
		 */
        testCase(new BasicDocument("This is a test.  How many???  "
                        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new BasicDocument(""), 0, 0, 0);
        testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
        testCase(new BasicDocument("Here is a series of test sentences. Your program should "
                + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
                + "the correct amount of syllables (example, for example), "
                + "but most of them will."), 49, 33, 3);
        testCase(new BasicDocument("Segue"), 2, 1, 1);
        testCase(new BasicDocument("Sentence"), 2, 1, 1);
        testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
        testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
                32, 15, 1);
    }

}
