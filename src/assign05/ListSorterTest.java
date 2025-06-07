package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for ArraySet.
 *
 * @author Benjamin Faerber and David Chen
 * @version 2025-05-29
 */
public class ListSorterTest {
    @BeforeEach
    void setUp() throws Exception {

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void testEquals(){
        assertTrue(true);
    }

    private static <T> ArrayList<T> createArrayList(T[] arr) {
        ArrayList<T> lst = new ArrayList<>();
        for (T item : arr) {
            lst.add(item);
        }
        return lst;
    }

    @Test
    void testMergeSort() {
        ArrayList<Integer> lst = createArrayList(new Integer[] {1, 4, 5, 3, 2});
        ArrayList<Integer> expected = createArrayList(new Integer[] {1, 2, 3, 4, 5});
        ListSorter.insertionSort(lst);
        ListSorter.mergesort(lst, 1);

        assertArrayEquals(expected.toArray(), lst.toArray());
        assertArrayEquals(expected.toArray(), lst.toArray());
    }
}
