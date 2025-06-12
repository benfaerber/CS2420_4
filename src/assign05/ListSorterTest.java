package assign05;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class contains tests for ListSorter.
 *
 * @author Benjamin Faerber and David Chen
 * @version 2025-06-11
 */
public class ListSorterTest {
    private ArrayList<Integer> emptyList, smallIntList, smallIntWithDup, smallIntRever, medianIntList;
    private ArrayList<Character> smallCharList;
    private List<Integer> largeIntList;

    private ArrayList<Integer> smallIntListExp, smallIntWithDupExp, smallIntReverExp, medianIntListExp;
    private ArrayList<Character> smallCharListExp;
    private List<Integer> largeIntListExp;

    private PivotChooser<Integer> chooseFirstInt;
    private PivotChooser<Character> chooseFirstChar;
    private PivotChooser<Integer> chooseMedianInt;
    private PivotChooser<Character> chooseMedianChar;
    private PivotChooser<Integer> chooseRandomInt;
    private PivotChooser<Character> chooseRandomChar;

    @BeforeEach
    void setUp() throws Exception {
        emptyList = new ArrayList<>();
        smallIntList = createArrayList(new Integer[] {9, 8, 7, 10});
        smallIntWithDup = createArrayList(new Integer[] {2, 4, 4, 1});
        smallIntRever = createArrayList(new Integer[] {4, 3, 2, 1});
        smallCharList = createArrayList(new Character[] {'s', 'b', 'u', 'r'});
        medianIntList = createArrayList(new Integer[] {20, 10, 30, 12, 9, 50, 44, 100, 123, 99, 777, 60, 40, 123, 223, 213});
        largeIntList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++){
            largeIntList.add(i + 1);
        }
        Collections.shuffle(largeIntList);

        smallIntListExp = createArrayList(new Integer[] {7, 8, 9, 10});
        smallIntWithDupExp = createArrayList(new Integer[] {1, 2, 4, 4});
        smallIntReverExp = createArrayList(new Integer[] {1, 2, 3, 4});
        smallCharListExp = createArrayList(new Character[] {'b', 'r', 's', 'u'});
        medianIntListExp = createArrayList(new Integer[] {9, 10, 12, 20, 30, 40, 44, 50, 60, 99, 100, 123, 123, 213, 223, 777});
        largeIntListExp = new ArrayList<>(1000);
        for (int i = 0; i < largeIntList.size(); i++) {
            largeIntListExp.add(i + 1);
        }

        chooseFirstInt = new FirstPivotChooser<>();
        chooseFirstChar = new FirstPivotChooser<>();
        chooseMedianInt = new MedianOfThreePivotChooser<>();
        chooseMedianChar = new MedianOfThreePivotChooser<>();
        chooseRandomInt = new RandomPivotChooser<>();
        chooseRandomChar = new RandomPivotChooser<>();
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testMergeSort() {
        // General testing
        ListSorter.mergesort(smallIntRever, 2);
        assertArrayEquals(smallIntReverExp.toArray(), smallIntRever.toArray());
        ListSorter.mergesort(smallIntWithDup, 2);
        assertArrayEquals(smallIntWithDupExp.toArray(), smallIntWithDup.toArray());
        ListSorter.mergesort(medianIntList, 5);
        assertArrayEquals(medianIntListExp.toArray(), medianIntList.toArray());
        ListSorter.mergesort(largeIntList, 100);
        assertArrayEquals(largeIntListExp.toArray(), largeIntList.toArray());

        // Testing 0 threshold and empty list
        assertThrows(IllegalArgumentException.class, () -> ListSorter.mergesort(emptyList, 5));
        assertThrows(IllegalArgumentException.class, () -> ListSorter.mergesort(smallIntList, 0));
        assertThrows(IllegalArgumentException.class, () -> ListSorter.mergesort(smallCharList, -3));
    }

    @Test
    void testSwitchingToInsertionSort() {
        // Testing small size, generic, should use insertion sort right away: 10
        ListSorter.mergesort(smallIntList, 10);
        assertArrayEquals(smallIntListExp.toArray(), smallIntList.toArray());
        ListSorter.mergesort(smallCharList, 10);
        assertArrayEquals(smallCharListExp.toArray(), smallCharList.toArray());
    }


    @Test
    void testPivotChoosers(){
        // Choose first as pivot
        assertEquals(9, smallIntList.get(chooseFirstInt.getPivotIndex(smallIntList, 0, smallIntList.size()-1)));
        assertEquals(20, medianIntList.get(chooseFirstInt.getPivotIndex(smallIntList, 0, smallIntList.size()-1)));
        assertEquals('s', smallCharList.get(chooseFirstChar.getPivotIndex(smallCharList, 0, smallCharList.size()-1)));

        // Choose the median of three as pivot
        assertEquals(9, smallIntList.get(chooseMedianInt.getPivotIndex(smallIntList, 0, smallIntList.size()-1)));
        assertEquals(100, medianIntList.get(chooseMedianInt.getPivotIndex(medianIntList, 0, medianIntList.size()-1)));
        assertEquals('r', smallCharList.get(chooseMedianChar.getPivotIndex(smallCharList, 0, smallCharList.size()-1)));

        // Choose random as pivot
        for (int i = 0; i < 1000; i++) {  // Repeat to cover randomness
            int pivotIndexforSmallIntList = chooseRandomInt.getPivotIndex(smallIntList, 0, smallIntList.size()-1);
            assertTrue(pivotIndexforSmallIntList >= 0 && pivotIndexforSmallIntList <= 3);
            assertFalse(pivotIndexforSmallIntList >= 100 && pivotIndexforSmallIntList <= 150);
            int pivotIndexForMedianIntList =  chooseRandomInt.getPivotIndex(medianIntList, 0, smallIntList.size()-1);
            assertTrue(pivotIndexForMedianIntList >= 0 && pivotIndexForMedianIntList <= 15);
            assertFalse(pivotIndexForMedianIntList >= 100 && pivotIndexForMedianIntList <= 150);
            int pivotIndexForSmallCharList = chooseRandomChar.getPivotIndex(smallCharList, 0, smallCharList.size()-1);
            assertTrue(pivotIndexForSmallCharList >= 0 && pivotIndexForSmallCharList <= 3);
            assertFalse(pivotIndexForSmallCharList >= 100 && pivotIndexForSmallCharList <= 150);
        }
    }

    @Test
    void testQuickSort(){
        // Use first pivot chooser
        ListSorter.quicksort(smallIntList, chooseFirstInt);
        assertArrayEquals(smallIntListExp.toArray(), smallIntList.toArray());
        ListSorter.quicksort(smallIntRever, chooseFirstInt);
        assertArrayEquals(smallIntReverExp.toArray(), smallIntRever.toArray());
        ListSorter.quicksort(smallIntWithDup, chooseFirstInt);
        assertArrayEquals(smallIntWithDupExp.toArray(), smallIntWithDup.toArray());
        ListSorter.quicksort(largeIntList, chooseFirstInt);
        assertArrayEquals(largeIntListExp.toArray(), largeIntList.toArray());
        ListSorter.quicksort(smallCharList, chooseFirstChar);
        assertArrayEquals(smallCharListExp.toArray(), smallCharList.toArray());

        // Use median of three pivot chooser
        ListSorter.quicksort(smallIntList, chooseMedianInt);
        assertArrayEquals(smallIntListExp.toArray(), smallIntList.toArray());
        ListSorter.quicksort(largeIntList, chooseMedianInt);
        assertArrayEquals(largeIntListExp.toArray(), largeIntList.toArray());

        // Use random pivot chooser
        ListSorter.quicksort(smallIntList, chooseRandomInt);
        assertArrayEquals(smallIntListExp.toArray(), smallIntList.toArray());
        ListSorter.quicksort(largeIntList, chooseRandomInt);
        assertArrayEquals(largeIntListExp.toArray(), largeIntList.toArray());
    }

    @Test
    void testAscendingList() {
        List<Integer> list10 = ListSorter.generateAscending(10);
        assertArrayEquals(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10},  list10.toArray());
    }

    @Test
    void testDescendingList() {
        List<Integer> list10 = ListSorter.generateDescending(10);
        assertArrayEquals(new Integer[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},  list10.toArray());
    }

    @Test
    void testPermutted() {
        List<Integer> list10 = ListSorter.generatePermuted(10);
        for (int i = 1; i < 10; i++) {
            assertTrue(list10.contains(i));
        }
    }

    /**
     * A private helper method to create an ArrayList
     *
     * @param arr array that we want to turn into an ArrayList
     * @return ArrayList that contains all elements from the array.
     * @param <T> any type of object
     */
    private static <T> ArrayList<T> createArrayList(T[] arr) {
        ArrayList<T> lst = new ArrayList<>();
        for (T item : arr) {
            lst.add(item);
        }
        return lst;
    }
}
