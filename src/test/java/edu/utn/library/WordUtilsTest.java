package edu.utn.library;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WordUtilsTest {

    @Test
    public void testCountWords() {
        WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?");
        int count = wordUtils.countWords();
        assertEquals(7, count);
    }

    @Test
    public void testCountWordsMoreLines() {
        WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?\nHola");
        int count = wordUtils.countWords();
        assertEquals(8, count);
    }

    @Test
    public void testGetWords() {
        WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?\nHola");
        List<String> list = wordUtils.getWords();
        assertEquals(8, list.size());
        assertEquals("Hola", list.get(0));
        assertEquals("como", list.get(1));
        assertEquals("estas", list.get(2));
    }

    @Test
    public void testWordsStaringWithOk() {
        try {
            WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?\nHola");
            long count = wordUtils.countWordsStartingWith("Ho");
            assertEquals(2, count);
        } catch (TextIsNullException e) {
            fail("Se paso un valor valido");
        }
    }

    @Test(expected = TextIsNullException.class)
    public void testWordsStaringWithFail() throws TextIsNullException {
        WordUtils wordUtils = new WordUtils("HOLA COMO ESTAS?");
        long count = wordUtils.countWordsStartingWith(null);
    }

    @Test
    public void testCountOcurrences(){
        WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?\nHola");
        long count = wordUtils.countOcurrences("Hola");

        assertEquals(2, count);

        count = wordUtils.countOcurrences("estas");
        assertEquals(1, count);
    }

    @Test
    public void testGetOcurrencesByWord(){
        WordUtils wordUtils = new WordUtils("Hola, como estas; Yo bien. y vos?\nHola");
        Map<String, Long> a = wordUtils.getOcurrencesByWord();
        Long b = 2L;
        assertNotNull(a.get("Hola"));
        assertEquals(b, a.get("Hola"));
        assertNull(a.get("facu"));
    }

}
