package spelling;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NearbyWordsTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void substitutionsTest() {
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\test_cases\\dict.txt");
        NearbyWords nw = new NearbyWords(d);
        List<String> d1 = nw.distanceOne("word", true);


        assertEquals("Substituciones completas", 6, d1.size());
    }

    @Test
    public  void ceroPalabras(){
        Dictionary d = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(d, "C:\\Users\\org\\Desktop\\_12cb4b1bb69bf9afee58424b3543c948_Course2StarterCode\\MOOCTextEditor-Coursera\\test_cases\\dict.txt");
        NearbyWords nw = new NearbyWords(d);
        List<String> d1 = nw.suggestions("worl",0);

        assertEquals("devulve cero",0,d1.size());

    }


}
