package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * File write exception testing.
 */
public class FileReadWriteExceptionTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test(expected = IllegalArgumentException.class)
    public void nullArgSourceFile() throws IOException {
        FileInsertionSort.sortFileAndWrite(null, "", Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullArgOutputFile() throws IOException {
        FileInsertionSort.sortFileAndWrite("", null, Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullArgParseStringFunction() throws IOException {
        FileInsertionSort.sortFileAndWrite("", "", null, Comparator.naturalOrder());
    }

    @Test(expected = IOException.class)
    public void noSourceFileExist() throws IOException {
        File outputFile = folder.newFile("output.txt");
        FileInsertionSort.sortFileAndWrite("asf/asd", outputFile.getPath(), Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IOException.class)
    public void noOutputFileExist() throws IOException {
        File inputFile = folder.newFile("input.txt");
        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), "/asd/asd", Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptySourceFileString() throws IOException {
        FileInsertionSort.sortFileAndWrite("", "asf", Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyOutputFileString() throws IOException {
        FileInsertionSort.sortFileAndWrite("asf", "", Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = IllegalArgumentException.class)
    public void sameFileException() throws IOException {
        File outputFile = folder.newFile("output.txt");
        FileInsertionSort.sortFileAndWrite(outputFile.getPath(), outputFile.getPath(),
                Integer::parseInt, Comparator.naturalOrder());
    }

    @Test(expected = ClassCastException.class)
    public void mixedDataType() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Comparable> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, "b"));
        try (PrintWriter writeFile = new PrintWriter(inputFile.getPath())) {
            for (Comparable object : list) {
                writeFile.println(object);
            }
        }

        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), outputFile.getPath(), Integer::parseInt, Comparator.naturalOrder());

    }

}
