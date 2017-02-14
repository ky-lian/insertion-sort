package com.company;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


/**
 * Insertion sort testing, including Integer and String sorting, ascending and descending order.
 */
@RunWith(Parameterized.class)
public class InsertionSortSorterTest {


    private final Comparable[] inputArray;

    private final Comparable[] expectedArray;

    public InsertionSortSorterTest(Comparable[] inputArray, Comparable[] expectedArray) {
        this.inputArray = inputArray;
        this.expectedArray = expectedArray;
    }

    @Parameterized.Parameters(name = "{index}: insertion sort")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Comparable[]{7, 3, 8, 10, 9, 6, 5, 4, 1, 2}, new Comparable[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}}, // average case, no repeat
                {new Comparable[]{5, 8, 9, 4, 7, 7, 4, 2, 7, 7}, new Comparable[]{2, 4, 4, 5, 7, 7, 7, 7, 8, 9}}, // average case, with repeat
                {new Comparable[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, new Comparable[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}}, // worst case
                {new Comparable[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new Comparable[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}}, // best case
                {new Comparable[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, new Comparable[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, // best case, with repeat
                {new Comparable[]{1}, new Comparable[]{1}}, // minimal set
                {new Comparable[]{"a", "d", "b", "c"}, new Comparable[]{"a", "b", "c", "d"}}, // average case, no repeat
                {new Comparable[]{"a", "d", "d", "c"}, new Comparable[]{"a", "c", "d", "d"}}, // average case, with repeat
                {new Comparable[]{"d", "c", "b", "a"}, new Comparable[]{"a", "b", "c", "d"}}, // worst case
                {new Comparable[]{"a", "b", "c", "d"}, new Comparable[]{"a", "b", "c", "d"}}, // best case
                {new Comparable[]{"a", "a", "a", "a"}, new Comparable[]{"a", "a", "a", "a"}}, // best case, with repeat
                {new Comparable[]{"a"}, new Comparable[]{"a"}} // minimal set
        });
    }

    @Test
    public void test() {
        InsertionSortSorter.sort(Arrays.asList(inputArray));
        Assertions.assertArrayEquals(inputArray, expectedArray);
    }

}