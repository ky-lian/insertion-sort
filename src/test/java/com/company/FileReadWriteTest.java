package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

/**
 * File read and write testing.
 */
public class FileReadWriteTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void FileReadAndWriteIntegerTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(4, 1, 2, 3, 5));
        try (PrintWriter writeFile = new PrintWriter(inputFile.getPath())) {
            for (Integer object : list) {
                writeFile.println(object);
            }
        }

        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), outputFile.getPath(), Integer::parseInt, Comparator.naturalOrder());

        List<Integer> resultList = new ArrayList<>();
        try (Scanner resultFile = new Scanner(outputFile.getAbsoluteFile())) {
            while (resultFile.hasNext()) {
                String currentLine = resultFile.nextLine();
                Integer object = Integer.parseInt(currentLine);
                resultList.add(object);
            }
        }

        Assertions.assertArrayEquals(resultList.toArray(), new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    public void FileReadAndWriteStringTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("c", "b", "a"));
        try (PrintWriter writeFile = new PrintWriter(inputFile.getPath())) {
            for (String object : list) {
                writeFile.println(object);
            }
        }

        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), outputFile.getPath(), x -> x, Comparator.naturalOrder());

        List<String> resultList = new ArrayList<>();
        try (Scanner resultFile = new Scanner(outputFile.getAbsoluteFile())) {
            while (resultFile.hasNext()) {
                resultList.add(resultFile.nextLine());
            }
        }

        Assertions.assertArrayEquals(resultList.toArray(), new String[]{"a", "b", "c"});
    }
}