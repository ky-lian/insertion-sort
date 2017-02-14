package com.company;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;


/**
 * Reverse order Insertion sort testing, including Integer and String sorting.
 */
@RunWith(Parameterized.class)
public class InsertionSortSorterReverseOrderTest {


    private final Comparable<Object>[] inputArray;

    private final Comparable<Object>[] expectedArray;

    public InsertionSortSorterReverseOrderTest(Comparable<Object>[] inputArray, Comparable<Object>[] expectedArray) {
        this.inputArray = inputArray;
        this.expectedArray = expectedArray;
    }

    @Parameterized.Parameters(name = "{index}: insertion sort (reverse order)")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Comparable[]{7, 3, 8, 10, 9, 6, 5, 4, 1, 2}, new Comparable[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}}, // average case, no repeat
                {new Comparable[]{5, 8, 9, 4, 7, 7, 4, 2, 7, 7}, new Comparable[]{9, 8, 7, 7, 7, 7, 5, 4, 4, 2}}, // average case, with repeat
                {new Comparable[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, new Comparable[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}}, // worst case
                {new Comparable[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new Comparable[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}}, // best case
                {new Comparable[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new Comparable[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, // best case, with repeat
                {new Comparable[]{1}, new Comparable[]{1}}, // minimal set
                {new Comparable[]{"a", "d", "b", "c"}, new Comparable[]{"d", "c", "b", "a"}}, // average case, no repeat
                {new Comparable[]{"a", "d", "d", "c"}, new Comparable[]{"d", "d", "c", "a"}}, // average case, with repeat
                {new Comparable[]{"d", "c", "b", "a"}, new Comparable[]{"d", "c", "b", "a"}}, // worst case
                {new Comparable[]{"a", "b", "c", "d"}, new Comparable[]{"d", "c", "b", "a"}}, // best case
                {new Comparable[]{"a", "a", "a", "a"}, new Comparable[]{"a", "a", "a", "a"}}, // best case, with repeat
                {new Comparable[]{"a"}, new Comparable[]{"a"}} // minimal set
        });
    }

    @Test
    public void sort() {
        InsertionSortSorter.sort(Arrays.asList(inputArray), Comparator.reverseOrder());
        Assertions.assertArrayEquals(inputArray, expectedArray);
    }

}