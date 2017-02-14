package com.company;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * This class contains methods for sorting lists of Objects, which implement {@link java.lang.Comparable} interface.
 */
public class InsertionSortSorter {

    /**
     * Sorts the specified list of {@link java.lang.Comparable} objects in ascending order.
     *
     * @param list the list to be sorted
     */
    public static void sort(List<Comparable<Object>> list) {

        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts the specified list of {@link java.lang.Comparable} objects according to the order induced
     * by the specified comparator.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order of the array.
     *                   A null value indicates that the elements' {@link java.lang.Comparable natural ordering} should be used.
     */
    public static void sort(List<Comparable<Object>> list, Comparator<Comparable<Object>> comparator) {

        for (int i = 0; i < list.size(); ++i) {
            for (int j = i; j > 0; --j) {

                if (comparator.compare(list.get(j - 1), list.get(j)) > 0) {
                    Collections.swap(list, j, j - 1);
                } else {
                    break;
                }

            }

        }
    }

}
