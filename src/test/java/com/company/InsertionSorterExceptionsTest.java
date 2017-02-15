package com.company;

import org.junit.Test;

import java.util.Arrays;

/**
 * Insertion sort exception testing.
 */
public class InsertionSorterExceptionsTest {


    @Test(expected = IllegalArgumentException.class)
    public void nullList() {
        InsertionSorter.sort(null);
    }

    @Test(expected = ClassCastException.class)
    public void classCastException() {
        InsertionSorter.sort(Arrays.asList(new Comparable[]{1, "a"}));
    }

}
