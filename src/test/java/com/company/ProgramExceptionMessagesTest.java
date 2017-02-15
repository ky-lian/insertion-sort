package com.company;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Tests exception messages of program.
 */
public class ProgramExceptionMessagesTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void nullArgSourceFile() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d", null, ""});
    }

    @Test
    public void nullArgOutputFile() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d", "", null});
    }

    @Test
    public void wrongOption() throws IOException {
        FileInsertionSort.main(new String[]{"-z", "-d", "", ""});
    }

    @Test
    public void mutuallyExclusiveDataOptionsTest() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-i", "", ""});
    }

    @Test
    public void mutuallyExclusiveOrderOptionsTest() throws IOException {
        FileInsertionSort.main(new String[]{"-d", "-a", "", ""});
    }

    @Test
    public void noSourceFileExist() throws IOException {
        File outputFile = folder.newFile("output.txt");
        FileInsertionSort.main(new String[]{"-s", "-d", "asd", outputFile.getAbsolutePath()});
    }

    @Test
    public void noOutputFileExist() throws IOException {
        File inputFile = folder.newFile("input.txt");
        FileInsertionSort.main(new String[]{"-s", "-d", inputFile.getAbsolutePath(), "asd/asf"});
    }

    @Test
    public void emptySourceFileString() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d", "", "asd/asf"});
    }

    @Test
    public void emptyOutputFileString() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d", "asd", ""});
    }

    @Test
    public void sameFileException() throws IOException {
        File outputFile = folder.newFile("output.txt");
        FileInsertionSort.main(new String[]{"-s", "-d", outputFile.getAbsolutePath(), outputFile.getAbsolutePath()});
    }

    @Test
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

        FileInsertionSort.main(new String[]{"-i", "-d", inputFile.getAbsolutePath(), outputFile.getAbsolutePath()});

    }

    @Test
    public void noOutputFileSpecified() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d", "asd"});
    }

    @Test
    public void noInputAndOutputFileSpecified() throws IOException {
        FileInsertionSort.main(new String[]{"-s", "-d"});
    }

    @Test
    public void noOptionsSpecified() throws IOException {
        FileInsertionSort.main(new String[]{"asd", "asf"});
    }

    @Test
    public void noDataTypeOptionSpecified() throws IOException {
        FileInsertionSort.main(new String[]{"-a", "asd", "asf"});
    }

    @Test
    public void multipleDataInRowTest() throws Exception {

        File inputFile = folder.newFile("input.txt");
        File outputFile = folder.newFile("output.txt");

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));


        try (PrintWriter writeFile = new PrintWriter(inputFile.getPath())) {
            for (Integer object : list) {
                writeFile.print(object + " ");
            }
        }

        FileInsertionSort.main(new String[]{"-i", "-d", inputFile.getPath(), outputFile.getPath()});

    }


}
