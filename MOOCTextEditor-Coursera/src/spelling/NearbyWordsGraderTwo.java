package spelling;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;

public class NearbyWordsGraderTwo {
    public static void main(String args[]) {
        int tests = 0;
        int incorrect = 0;
        String feedback = "";
        PrintWriter out;

        try {
            out = new PrintWriter("C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\grader_output\\module5.part2.out");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();
            DictionaryLoader.loadDictionary(d, "C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\test_cases\\dict2.txt");
            NearbyWords nw = new NearbyWords(d);

            feedback += "** Test 1: 2 suggestions... ";
            List<String> d1 = nw.suggestions("dag", 4);
            feedback += "" + d1.size() + " suggestions returned.\n";

            feedback += "** Test 2: Checking suggestion correctness... ";
            feedback += "Suggestions: ";
            for (String i : d1) {
                feedback += i + ", ";
            }

            feedback += "\n** Test 3: 3 suggestions... ";
            d1 = nw.suggestions("fare", 3);
            feedback += "" + d1.size() + " suggestions returned.\n";

            feedback += "** Test 4: Checking suggestion correctness... ";
            feedback += "Suggestions: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";
            
        } catch (Exception e) {
            out.println(feedback + "Runtime error: " + e);
            return;
        }

        out.println(feedback += "Tests complete. Make sure everything looks right.");
        out.close();
    }
}
