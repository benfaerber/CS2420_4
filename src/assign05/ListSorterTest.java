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

    private static <T> void printArrayList(String label, ArrayList<T> lst) {
        System.out.println(label + ":");
        for (T item : lst) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    @Test
    void testMergeSort() {
        // Test without caring about the insertion sort stuff
        ArrayList<Integer> lst = createArrayList(new Integer[] {1, 4, 5, 3, 2, 20, 50, 22, 30});
        ArrayList<Integer> expected = createArrayList(new Integer[] {1, 2, 3, 4, 5, 20, 22, 30, 50});

        ListSorter.mergesort(lst, 3);
        printArrayList("Long Merge Sort", lst);
        assertArrayEquals(expected.toArray(), lst.toArray());
    }

    @Test
    void testInsertionSort() {
        ArrayList<Integer> lst = createArrayList(new Integer[] {1, 4, 5, 3, 2});
        ArrayList<Integer> expected = createArrayList(new Integer[] {1, 2, 3, 4, 5});
        ListSorter.insertionSort(lst);
        assertArrayEquals(expected.toArray(), lst.toArray());

        ArrayList<Integer> reverse = createArrayList(new Integer[] {12, 8, 5, 3});
        ArrayList<Integer> reverseExpected = createArrayList(new Integer[] {3, 5, 8, 12});
        ListSorter.insertionSort(reverse);
        assertArrayEquals(reverseExpected.toArray(), reverse.toArray());

        ArrayList<Integer> insertionTest = createArrayList(new Integer[] {1, 3, 2, 4, 5});
        ArrayList<Integer> InsertionExpected = createArrayList(new Integer[] {1, 2, 3, 4, 5});
        ListSorter.insertionSort(insertionTest);
        assertArrayEquals(InsertionExpected.toArray(), insertionTest.toArray());

        ArrayList<Integer> insertionRangeTest = createArrayList(new Integer[] {1, 3, 2, 5, 2, 6});
        ArrayList<Integer> InsertionRangeExpected = createArrayList(new Integer[] {1, 2, 3, 5, 2, 6});
        ListSorter.insertionSort(insertionRangeTest, 0, 3);
        assertArrayEquals(insertionRangeTest.toArray(), InsertionRangeExpected.toArray());

        // Sort from middle
        ArrayList<Integer> insertionMiddle = createArrayList(new Integer[] {1, 3, 2, 10, 2, 6});
        ArrayList<Integer> insertionMiddleExpected = createArrayList(new Integer[] {1, 3, 2, 2, 6, 10});
        ListSorter.insertionSort(insertionMiddle, 2, 6);
        printArrayList("Insertion middle", insertionMiddle);
        assertArrayEquals(insertionMiddleExpected.toArray(), insertionMiddle.toArray());
    }

    @Test
    void testUnderThreshold() {
        ArrayList<Integer> lst = createArrayList(new Integer[] {1, 4, 5, 3, 2});
        ArrayList<Integer> expected = createArrayList(new Integer[] {1, 2, 3, 4, 5});

        ListSorter.mergesort(lst, 10);
        assertArrayEquals(expected.toArray(), lst.toArray());
    }

    @Test
    void testPivotChoosers(){
        ArrayList<Integer> lst = createArrayList(new Integer[] {1, 4, 5, 3, 2});

        PivotChooser<Integer> firstChooser = new FirstPivotChooser<>();
        PivotChooser<Integer> medianChooser = new MedianOfThreePivotChooser<>();
        PivotChooser<Integer> randomChooser = new RandomPivotChooser<>();

        assertEquals(0, firstChooser.getPivotIndex(lst, 0, lst.size() - 1));

        // Sorted list: 1 2 3 4 5, median = 2
        assertEquals(4, medianChooser.getPivotIndex(lst, 0, lst.size() - 1));

        // Test random
        int randomIndex = randomChooser.getPivotIndex(lst, 0, lst.size() - 1);
        assertTrue(randomIndex >= 0 && randomIndex <= lst.size() - 1);
    }

    @Test
    void testQuickSort(){
        PivotChooser<Integer> firstChooser = new FirstPivotChooser<>();
        PivotChooser<Integer> medianChooser = new MedianOfThreePivotChooser<>();
        PivotChooser<Integer> randomChooser = new RandomPivotChooser<>();

        ArrayList<Integer> lstWithFirst = createArrayList(new Integer[] {1, 4, 5, 3, 2, 20, 50, 22, 30});
        ArrayList<Integer> lstWithFirstExpected = createArrayList(new Integer[] {1, 2, 3, 4, 5, 20, 22, 30, 50});
        ListSorter.quicksort(lstWithFirst, firstChooser);
        assertArrayEquals(lstWithFirstExpected.toArray(), lstWithFirst.toArray());

        ArrayList<Integer> small = createArrayList(new Integer[] {1, 2, 3, 4});
        ArrayList<Integer> smallEx = createArrayList(new Integer[] {1, 2, 3, 4});
        ListSorter.quicksort(small, randomChooser);
        System.out.println(small);
        assertArrayEquals(smallEx.toArray(), small.toArray());
    }
}
