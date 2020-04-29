package edu.utn.library;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineUtils {
    private String text;

    public LineUtils(String text) {
        this.text = text;
    }

    public List<String> getLines() {
        return Arrays.asList(text.split("\n"));
    }

    public long countLines() {
        return getLines().size();
    }

    public List<String> getLinesStartingWith(String start) {
        return getLines().stream().filter(line -> line.startsWith(start)).collect(Collectors.toList());
    }

    public List<String> getLinesContaining(String data) {
        return getLines().stream().filter(line -> line.contains(data)).collect(Collectors.toList());
    }

    public List<String> getLinesEndingWith(String end) {
        return getLines().stream().filter(line -> line.startsWith(end)).collect(Collectors.toList());
    }

    public void loadFromFile(String filename) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(new File(filename));
        String tempText = "";
        char[] buffer = new char[1024];
        while (fileReader.read(buffer) != 0) {
            tempText = tempText + new String(buffer);
        }
        text = tempText;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(filename));
        fileWriter.write(text);
        fileWriter.close();
    }

}
