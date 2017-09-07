package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class EfficientDocumentGrader {
    public static void main(String[] args) {
        try
        {
            System.out.println("Sentences, words, and syllables:");
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\test_cases\\mod2TestCases.txt"));
            String line;
            PrintWriter out = new PrintWriter("C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\grader_output/module2.part1.out", "utf-8");
            while ((line = br.readLine()) != null)
            {
                EfficientDocument doc = new EfficientDocument(line);
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
