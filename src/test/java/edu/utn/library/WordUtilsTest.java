package edu.utn.library;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

}
