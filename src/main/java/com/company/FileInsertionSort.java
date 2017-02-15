package com.company;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;
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
        Options options = new Options();

        options.addOption("i", "integer data type");
        options.addOption("s", "string data type");
        options.addOption("a", "ascending sort");
        options.addOption("d", "descending sort");
        options.addOption("help", "print this message");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (args.length == 0 || cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("FileInsertionSort [OPTION]... [INPUT FILE] [OUTPUT FILE]", options);
                return;
            }

            List<String> argList = cmd.getArgList();

            if (argList.size() == 0) {
                throw new ParseException("Input and output files were not specified.");
            }

            if (argList.size() == 1) {
                throw new ParseException("Output file was not specified.");
            }


            String inputFilePath = argList.get(0);
            String outputFilePath = argList.get(1);


            Comparator comparator;
            if (cmd.hasOption("a") && cmd.hasOption("d")) {
                throw new ParseException("Options -a and -d are mutually exclusive.");
            } else if (cmd.hasOption("a")) {
                comparator = Comparator.naturalOrder();
            } else if (cmd.hasOption("d")) {
                comparator = Comparator.reverseOrder();
            } else {
                comparator = Comparator.naturalOrder();
                System.out.println("Warning: order was not specified; data will be sorted in ascending order.");
            }

            Function parseStringFunction;
            if (cmd.hasOption("i") && cmd.hasOption("s")) {
                throw new ParseException("Options -i and -s are mutually exclusive.");
            } else if (cmd.hasOption("i")) {
                parseStringFunction = x -> Integer.parseInt(x.toString());
            } else if (cmd.hasOption("s")) {
                parseStringFunction = x -> x;
            } else {
                throw new ParseException("Data type was not specified.");
            }

            sortFileAndWrite(inputFilePath, outputFilePath, parseStringFunction, comparator);

            System.out.println(String.join(" ",
                    "Sort was completed successfully. Sorted data was written in the file: ", outputFilePath));

        } catch (ParseException | IllegalArgumentException e) {
            System.out.println(String.join(" ", "Error: ", e.getMessage()));
        } catch (IOException e) {
            System.out.println(String.join(" ", "Error while reading or writing file:", e.getMessage()));
        } catch (ClassCastException e) {
            System.out.println(String.join(" ", "Error while parsing input data: ", e.getMessage(),
                    "; make sure that there is no more than one element at line in input file and that selected",
                    "data type is valid for input file."));
        } catch (NullPointerException e) {
            System.out.println("Error: input or output file was not specified.");
        }


    }

    /**
     * Reads the specified input file, sorts it with insertion sort algorithm and writes result to the
     * specified output file. Each element must be written one by line in the input file. The elements of the output
     * file written one by line too.
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
            IllegalArgumentException, ClassCastException {


        if (sourceFilePath == null || outputFilePath == null || parseStringFunction == null) {
            throw new IllegalArgumentException("At least one argument is null.");
        }

        if (sourceFilePath.isEmpty() || outputFilePath.isEmpty()) {
            throw new IllegalArgumentException("Source or output file path can't be empty.");
        }

        if (sourceFilePath.equals(outputFilePath)) {
            throw new IllegalArgumentException("Source file can't be the same as output file.");
        }

        List<T> list = readListFromFile(sourceFilePath, parseStringFunction);
        InsertionSorter.sort(list, comparator);
        writeListToFile(list, outputFilePath);

    }

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


}
