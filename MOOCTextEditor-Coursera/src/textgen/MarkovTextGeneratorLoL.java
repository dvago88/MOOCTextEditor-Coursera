package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * An implementation of the MTG interface that uses a list of lists.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

    // The list of words with their next words
    private List<ListNode> wordList;

    // The starting "word"
    private String starter;

    // The random number generator
    private Random rnGenerator;

    public MarkovTextGeneratorLoL(Random generator) {
        wordList = new LinkedList<>();
        starter = "";
        rnGenerator = generator;
    }

    /**
     * Train the generator by adding the sourceText
     */
    @Override
    public void train(String sourceText) {
        String[] words = sourceText.split("\\s+");
//        String[] words = sourceText.split("\\W+");
        ListNode listNode = new ListNode(words[0]);
        wordList.add(listNode);
        int lastIndex = -1;
        int index = -1;
        for (int i = 1; i <= words.length - 1; i++) {
            String word = words[i];
            int count = 0;
            index = -1;
            index = getIndex(index, word, count);
            lastIndex = selector(lastIndex, index, word);
        }
        ListNode last;
        if (index != -1) {
            last = wordList.get(index);
        } else {
            last = wordList.get(wordList.size() - 1);
        }
        last.addNextWord(wordList.get(0).getWord());
    }

    /**
     * Generate the number of words requested.
     */
    @Override
    public String generateText(int numWords) {
        // TODO: Implement this method
        //solo falta arreglar que el numero random no siempre sea el mismo
        String output = "";
        ListNode listNode = wordList.get(0);
        starter = listNode.getWord();
        output += starter;
        for (int i = 1; i < numWords; i++) {
            String word = listNode.getRandomNextWord(rnGenerator);
            output += " "+word;
            int index = -1;

            int count = 0;
            for (ListNode list : wordList) {
                if (word.equals(list.getWord())) {
                    index = count;
                    break;
                }
                count++;
            }
            listNode = wordList.get(index);
        }
        return output;
    }

    // Can be helpful for debugging
    @Override
    public String toString() {
        String toReturn = "";
        for (ListNode n : wordList) {
            toReturn += n.toString();
        }
        return toReturn;
    }

    /**
     * Retrain the generator from scratch on the source text
     */
    @Override
    public void retrain(String sourceText) {
        wordList = new LinkedList<>();
        starter = "";
        train(sourceText);
    }

    private int selector(int lastIndex, int index, String word) {
        if (index == -1) {
            if (lastIndex != -1) {
                ListNode newNode = new ListNode(word);
                ListNode prevNode = wordList.get(lastIndex);
                prevNode.addNextWord(word);
                wordList.add(newNode);
                lastIndex = -1;
            } else {
                ListNode newNode = new ListNode(word);
                ListNode prevNode = wordList.get(wordList.size() - 1);
                prevNode.addNextWord(word);
                wordList.add(newNode);
            }
        } else {
            ListNode prevNode;
            if (lastIndex != -1) {
                prevNode = wordList.get(lastIndex);
            } else {
                prevNode = wordList.get(wordList.size() - 1);
            }
            prevNode.addNextWord(word);
            lastIndex = index;
        }
        return lastIndex;
    }

    private int getIndex(int index, String word, int count) {
        for (ListNode aWordList : wordList) {
            if (word.equals(aWordList.getWord())) {
                index = count;
                break;
            }
            count++;
        }
        return index;
    }


    /**
     * This is a minimal set of tests.  Note that it can be difficult
     * to test methods/classes with randomized behavior.
     *
     * @param args
     */
    public static void main(String[] args) {
        // feed the generator a fixed random value for repeatable behavior
        MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
        MarkovTextGeneratorLoL gen2 = new MarkovTextGeneratorLoL(new Random(4244));
        MarkovTextGeneratorLoL gen3 = new MarkovTextGeneratorLoL(new Random(14));
        String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
        String prueba = "hello perro vaca hello vaca hello perrro hello perro hello perro";
        System.out.println(textString);
        gen.train(textString);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
        String textString2 = "You say yes, I say no, " +
                "You say stop, and I say go, go, go, " +
                "Oh no. You say goodbye and I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "I say high, you say low, " +
                "You say why, and I say I don't know. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "Why, why, why, why, why, why, " +
                "Do you say goodbye. " +
                "Oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello. " +
                "You say yes, I say no, " +
                "You say stop and I say go, go, go. " +
                "Oh, oh no. " +
                "You say goodbye and I say hello, hello, hello. " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello, " +
                "I don't know why you say goodbye, I say hello, hello, hello,";
        System.out.println(textString2);
        gen2.train(textString2);
        gen3.train(textString2);
        gen.retrain(textString2);
        System.out.println(gen);
        System.out.println(gen.generateText(20));
        System.out.println(gen2.generateText(20));
        System.out.println(gen3.generateText(20));
    }

}

/**
 * Links a word to the next words in the list
 * You should use this class in your implementation.
 */
class ListNode {
    // The word that is linking to the next words
    private String word;

    // The next words that could follow it
    private List<String> nextWords;

    ListNode(String word) {
        this.word = word;
        nextWords = new LinkedList<>();
    }

    public String getWord() {
        return word;
    }

    public void addNextWord(String nextWord) {
        nextWords.add(nextWord);
    }

    public String getRandomNextWord(Random generator) {
        // The random number generator should be passed from
        // the MarkovTextGeneratorLoL class
        return nextWords.get(generator.nextInt(nextWords.size()));
    }

    public List<String> getNextWords() {
        return nextWords;
    }

    public String toString() {
        String toReturn = word + ": ";
        for (String s : nextWords) {
            toReturn += s + "->";
        }
        toReturn += "\n";
        return toReturn;
    }

}


