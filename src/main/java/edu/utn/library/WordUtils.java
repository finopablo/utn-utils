package edu.utn.library;

import java.util.*;

public class WordUtils {

    private static final String REGEXP_SPLIT = "( |,|;|\\.|\\\n)+";
    private final String text;

    public WordUtils(String text) {
        this.text = text;
    }

    private String[] splitText() {
        return text.split(REGEXP_SPLIT);
    }

    public int countWords() {
        return splitText().length;
    }

    public List<String> getWords() {
        return Arrays.asList(splitText());
    }

    public long countWordsStartingWith(String start) throws TextIsNullException {
        final String param = Optional.ofNullable(start).orElseThrow(TextIsNullException::new);
        final List<String> words = getWords();
        return words.stream().filter(o -> o.startsWith(param)).count();
    }

    public long countOcurrences(String word) {
        final List<String> words = getWords();
        return words.stream().filter(o -> o.equals(words)).count();
    }


    public Map<String, Long> getOcurrencesByWord() {
        final Map<String, Long> wordMap = new HashMap<>();
        getWords().forEach(word -> {
            Long count = 1L;
            if (wordMap.containsKey(word)) {
                count = wordMap.get(word) + 1;
            }
            wordMap.put(word, count);
        });
        return wordMap;
    }
}
