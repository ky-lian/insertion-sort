package com.company;

import java.util.Collections;
import java.util.List;


/**
 * This class contains methods for sorting lists of Objects, which implement {@link java.lang.Comparable} interface.
 */
public class InsertionSortSorter {

    /**
     * Sorting list of {@link java.lang.Comparable} objects in ascending order.
     * @param list List to sort
     */
    public static void sort(List<Comparable<Object>> list) {

        for (int i = 0; i < list.size(); ++i) {
            for (int j = i; j > 0; --j) {

                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    Collections.swap(list, j, j - 1);
                } else {
                    break;
                }

            }

        }
    }

}
