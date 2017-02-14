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
     * @throws IllegalArgumentException if the specified list is null.
     * @throws ClassCastException       if some of the list's object type prevents it from being compared to another object.
     */
    public static <T extends Comparable> void sort(List<T> list) throws IllegalArgumentException, ClassCastException {
        sort(list, Comparator.naturalOrder());
    }

    /**
     * Sorts the specified list of {@link java.lang.Comparable} objects according to the order induced
     * by the specified comparator.
     *
     * @param list       the list to be sorted
     * @param comparator the comparator to determine the order of the array.
     *                   A null value indicates that the elements' {@link java.lang.Comparable natural ordering}
     *                   should be used.
     * @throws IllegalArgumentException if the specified list is null.
     * @throws ClassCastException       if some of the list's object type prevents it from being compared to another object.
     */
    public static <T extends Comparable> void sort(List<T> list, Comparator<? super T> comparator)
            throws IllegalArgumentException, ClassCastException {

        if (list == null) {
            throw new IllegalArgumentException("Sortable list can't be null");
        }

        if (comparator == null) {
            comparator = Comparator.naturalOrder();
        }

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
