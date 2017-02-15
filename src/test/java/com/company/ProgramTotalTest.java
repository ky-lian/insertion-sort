package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Tests the different arguments passed to program.
 */
public class ProgramTotalTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    private static <T> List<T> readListFromFile(String filePath, Function<String, T> parseStringFunction)
            throws IOException {
        List<T> list = new ArrayList<>();
        try (Scanner fileToSort = new Scanner(Paths.get(filePath))) {
            while (fileToSort.hasNext()) {
                try {
                    T object = parseStringFunction.apply(fileToSort.nextLine());
                    list.add(object);
                } catch (IllegalArgumentException e) {
                    throw new ClassCastException(e.getMessage());
                }
            }
        }
        return list;
    }

    private static <T> void writeListToFile(List<T> list, String filePath) throws FileNotFoundException {
        try (PrintWriter writeFile = new PrintWriter(filePath)) {
            for (T object : list) {
                writeFile.println(object);
            }
        }
    }

    @Test
    public void stringDataAscendingTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("c", "b", "a"));

        writeListToFile(list, inputFile.getPath());

        FileInsertionSort.main(new String[]{"-s", "-a", inputFile.getPath(), outputFile.getPath()});
        List<String> resultList = readListFromFile(outputFile.getAbsolutePath(), x -> x);
        Assertions.assertArrayEquals(resultList.toArray(), new String[]{"a", "b", "c"});

    }

    @Test
    public void stringDataDescendingTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("a", "b", "c"));

        writeListToFile(list, inputFile.getPath());

        FileInsertionSort.main(new String[]{"-s", "-d", inputFile.getPath(), outputFile.getPath()});
        List<String> resultList = readListFromFile(outputFile.getAbsolutePath(), x -> x);
        Assertions.assertArrayEquals(resultList.toArray(), new String[]{"c", "b", "a"});

    }

    @Test
    public void integerDataAscendingTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));

        writeListToFile(list, inputFile.getPath());

        FileInsertionSort.main(new String[]{"-i", "-a", inputFile.getPath(), outputFile.getPath()});
        List<Integer> resultList = readListFromFile(outputFile.getAbsolutePath(), Integer::parseInt);
        Assertions.assertArrayEquals(resultList.toArray(), new Integer[]{1, 2, 3});

    }

    @Test
    public void integerDataDescendingTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));

        writeListToFile(list, inputFile.getPath());

        FileInsertionSort.main(new String[]{"-i", "-d", inputFile.getPath(), outputFile.getPath()});
        List<Integer> resultList = readListFromFile(outputFile.getAbsolutePath(), Integer::parseInt);
        Assertions.assertArrayEquals(resultList.toArray(), new Integer[]{3, 2, 1});

    }


}