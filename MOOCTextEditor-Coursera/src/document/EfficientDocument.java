package document;

import java.util.List;

/**
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words,
 * and sentences and then stores those values.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document {

    private int numWords;  // The number of words in the document
    private int numSentences;  // The number of sentences in the document
    private int numSyllables;  // The number of syllables in the document

    public EfficientDocument(String text) {
        super(text);
        processText();
    }


    /**
     * Take a string that either contains only alphabetic characters,
     * or only sentence-ending punctuation.  Return true if the string
     * contains only alphabetic characters, and false if it contains
     * end of sentence punctuation.
     *
     * @param tok The string to check
     * @return true if tok is a word, false if it is punctuation.
     */
    private boolean isWord(String tok) {
        // Note: This is a fast way of checking whether a string is a word
        // You probably don't want to change it.
        return !(tok.indexOf("!") >= 0 || tok.indexOf(".") >= 0 || tok.indexOf("?") >= 0);
    }

    private void processText() {

        List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
        int countWords = 0;
        int countSyl = 0;
        int countSen = 0;
        for (String word : tokens) {
            if (isWord(word)) {
                countWords++;
                countSyl += countSyllables(word);
            } else {
                countSen++;
            }
        }
        if (tokens.size() > 0) {
            if (isWord(tokens.get(tokens.size() - 1))) {
                countSen++;
            }
        }
        numWords = countWords;
        numSyllables = countSyl;
        numSentences = countSen;
    }

    @Override
    public int getNumSentences() {
        return numSentences;
    }

    @Override
    public int getNumWords() {
        return numWords;
    }

    @Override
    public int getNumSyllables() {
        return numSyllables;
    }

    // Can be used for testing
    // We encourage you to add your own tests here.
    public static void main(String[] args) {
        testCase(new EfficientDocument("This is a test.  How many???  "
                        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
                16, 13, 5);
        testCase(new EfficientDocument(""), 0, 0, 0);
        testCase(new EfficientDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new EfficientDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
        testCase(new EfficientDocument("Here is a series of test sentences. Your program should "
                + "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
                + "the correct amount of syllables (example, for example), "
                + "but most of them will."), 49, 33, 3);
        testCase(new EfficientDocument("Segue"), 2, 1, 1);
        testCase(new EfficientDocument("Sentence"), 2, 1, 1);
        testCase(new EfficientDocument("Sentences?!"), 3, 1, 1);
        testCase(new EfficientDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
                32, 15, 1);

    }


}