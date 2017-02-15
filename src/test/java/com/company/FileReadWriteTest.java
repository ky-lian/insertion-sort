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
import java.util.*;
import java.util.function.Function;

/**
 * File read and write testing.
 */
public class FileReadWriteTest {

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
    public void FileReadAndWriteIntegerTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(4, 1, 2, 3, 5));

        writeListToFile(list, inputFile.getPath());
        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), outputFile.getPath(), Integer::parseInt, Comparator.naturalOrder());
        List<Integer> resultList = readListFromFile(outputFile.getAbsolutePath(), Integer::parseInt);

        Assertions.assertArrayEquals(resultList.toArray(), new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    public void FileReadAndWriteStringTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("c", "b", "a"));

        writeListToFile(list, inputFile.getPath());
        FileInsertionSort.sortFileAndWrite(inputFile.getPath(), outputFile.getPath(), x -> x, Comparator.naturalOrder());

        List<String> resultList = readListFromFile(outputFile.getAbsolutePath(), x -> x);

        Assertions.assertArrayEquals(resultList.toArray(), new String[]{"a", "b", "c"});
    }


}