package com.company;

import org.junit.Test;

import java.util.Arrays;

/**
 * Insertion sort exception testing.
 */
public class InsertionSortSorterExceptionsTest {


    @Test(expected = IllegalArgumentException.class)
    public void nullListTest() {
        InsertionSortSorter.sort(null);
    }

    @Test(expected = ClassCastException.class)
    public void classCastExceptionTest() {
        InsertionSortSorter.sort(Arrays.asList(new Comparable[]{1, "a"}));
    }

}
