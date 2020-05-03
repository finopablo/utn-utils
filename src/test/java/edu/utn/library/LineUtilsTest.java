package edu.utn.library;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class LineUtilsTest {

    @Test
    public void testCountLines(){
        LineUtils lineUtils = new LineUtils("Hola buenos,\ndias\nHola\n que hace señora");

        assertEquals(4L , lineUtils.countLines());
    }

    @Test
    public void testGetLinesStartingWith(){
        LineUtils lineUtils = new LineUtils("Hola buenos,\ndias\nHola\n que hace señora");
        List<String> aux = new ArrayList<String>();
        aux.add("Hola buenos,");
        aux.add("Hola");

        assertEquals(aux, lineUtils.getLinesStartingWith("Hola"));
    }

    @Test
    public void testGetLinesContaining(){
        LineUtils lineUtils = new LineUtils("Hola buenos,\ndias\nHola\nque hace señora");
        List<String> aux = new ArrayList<String>();
        aux.add("que hace señora");

        assertEquals(aux, lineUtils.getLinesContaining("hace"));
    }

    @Test
    public void testGetLinesEndingWith(){
        LineUtils lineUtils = new LineUtils("Hola buenos,\ndias\nHola\nque hace señora");
        List<String> aux = new ArrayList<String>();
        aux.add("Hola");
        aux.add("que hace señora");

        assertEquals(aux, lineUtils.getLinesEndingWith("a"));
    }

    @Test
    public void testLoadFromFile() throws IOException {
        LineUtils lineUtils = new LineUtils("");
        List<String> aux = new ArrayList<>();
        aux.add("archivo de prueba programacion");
        lineUtils.loadFromFile("archivoPrueba.txt");


        assertEquals(aux.get(0), (lineUtils.getLines().get(0)).replaceAll("\\p{C}", ""));
    }

    @Test(expected = IOException.class)
    public void testLoadFromFileIsIOException() throws IOException{
        LineUtils lineUtils = new LineUtils("");
        List<String> aux = new ArrayList<>();
        aux.add("archivo de prueba programacion");
        lineUtils.loadFromFile("errorEsperado.txt");
    }

    @Test(expected = NullPointerException.class)
    public void testLoadFromFileIsNull() throws NullPointerException, IOException {
        LineUtils lineUtils = new LineUtils("");
        List<String> aux = new ArrayList<>();
        aux.add("archivo de prueba programacion");
        lineUtils.loadFromFile(null);

        assertEquals(aux.get(0), (lineUtils.getLines().get(0)).replaceAll("\\p{C}", ""));
    }

    @Test
    public void testSaveToFile() throws IOException {
        LineUtils lineUtils = new LineUtils("hola que tal");

        List<String> prueba = lineUtils.getLines();
        File archivo = new File("archivoAux.txt");
        archivo.delete();

        lineUtils.saveToFile(archivo.getName());
        lineUtils.loadFromFile(archivo.getName());

        assertTrue(archivo.exists());
        assertEquals(prueba.get(0), (lineUtils.getLines().get(0)).replaceAll("\\p{C}", ""));
    }

    @Test(expected = IOException.class)
    public void testSaveToFileIsIOException() throws IOException {
        LineUtils lineUtils = new LineUtils("");

        lineUtils.saveToFile("");
    }

    @Test(expected = NullPointerException.class)
    public void testSaveToFileIsNull() throws IOException {
        LineUtils lineUtils = new LineUtils("");

        lineUtils.saveToFile(null);
    }

}
