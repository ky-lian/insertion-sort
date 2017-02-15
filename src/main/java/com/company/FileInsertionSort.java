package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class FileInsertionSort {

    public static void main(String[] args) {


    }

    /**
     * Reads the specified input file, sorts it with insertion sort algorithm and writes result to the
     * specified output file. Each element must be written from new line in the input file.
     * The parser from {@link java.lang.String} to {@link java.lang.Comparable} type must be specified.
     *
     * @param sourceFilePath      the path to input file to be sorted. Must not be null or empty. Cannot be equal to output
     *                            file path.
     * @param outputFilePath      the path to output file where result will be written. If file does not exist,
     *                            it will be created. Must not be null or empty. Cannot be equal to input file path.
     * @param parseStringFunction the function to parse from String to {@link java.lang.Comparable} data type.
     * @param comparator          the comparator to determine the order of the array.
     *                            A null value indicates that the elements' {@link java.lang.Comparable natural ordering}
     *                            should be used.
     * @throws IOException              if either source or output file was not found by the path specified.
     * @throws IllegalArgumentException if one of the arguments is null (except comparator argument),
     *                                  or if either source file path or output file path is empty string,
     *                                  or if source file path is equal to the output file path.
     * @throws ClassCastException       if it is impossible to parse string to target type with the specified parse
     *                                  function.
     */
    public static <T extends Comparable> void sortFileAndWrite(
            String sourceFilePath, String outputFilePath,
            Function<String, T> parseStringFunction, Comparator<? super T> comparator) throws IOException,
            IllegalArgumentException {


        if (sourceFilePath == null || outputFilePath == null || parseStringFunction == null) {
            throw new IllegalArgumentException("At least one argument is null.");
        }

        if (sourceFilePath.isEmpty() || outputFilePath.isEmpty()) {
            throw new IllegalArgumentException("Source or output file path can't be empty.");
        }

        if (sourceFilePath.equals(outputFilePath)) {
            throw new IllegalArgumentException("Source file can't be the same as output file.");
        }

        List<T> list = new ArrayList<>();
        try (Scanner fileToSort = new Scanner(Paths.get(sourceFilePath))) {
            while (fileToSort.hasNext()) {
                try {
                    T object = parseStringFunction.apply(fileToSort.nextLine());
                    list.add(object);
                } catch (IllegalArgumentException e) {
                    throw new ClassCastException(e.getMessage());
                }
            }
        }

        InsertionSorter.sort(list, comparator);

        try (PrintWriter writeFile = new PrintWriter(outputFilePath)) {
            for (T object : list) {
                writeFile.println(object);
            }
        }

    }


}
