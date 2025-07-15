package assign10;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindKLargestTest {

    private ArrayList<Integer> listRange, listReverse, listRandom;
    private ArrayList<String> animals;

    @BeforeEach
    public void setUp() {
        listRange = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        listReverse = new ArrayList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        listRandom = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 1, 3, 5, 7, 9));
        animals = new ArrayList<>(Arrays.asList(
                "dog",
                "fish",
                "elephant",
                "rabbit"
        ));
    }

    @Test
    public void testFindKLargest() {
        List<Integer> result = FindKLargest.findKLargestHeap(listRange, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result.toArray());

        List<Integer> result2 = FindKLargest.findKLargestHeap(listRandom, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result2.toArray());

        List<Integer> result3 = FindKLargest.findKLargestHeap(listReverse, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result3.toArray());
    }

    @Test
    public void testFindKLargestWithComparator() {
        Comparator<String> strLength = (String a, String b) -> Integer.compare(a.length(), b.length());

        List<String> animalsLargest = FindKLargest.findKLargestHeap(animals, 2, strLength);
        assertArrayEquals(new String[] {"elephant", "rabbit"}, animalsLargest.toArray());

        Comparator<Integer> reversedNumber = (Integer a1, Integer a2) -> Integer.compare(a2, a1);
        List<Integer> numsResult = FindKLargest.findKLargestHeap(listRange, 3, reversedNumber);
        assertArrayEquals(new Integer[] { 1, 2, 3 }, numsResult.toArray());
    }

    @Test
    public void testFindKLargestSort() {
        List<Integer> result = FindKLargest.findKLargestSort(listRange, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result.toArray());

        List<Integer> result2 = FindKLargest.findKLargestSort(listRandom, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result2.toArray());

        List<Integer> result3 = FindKLargest.findKLargestSort(listReverse, 3);
        assertArrayEquals(new Integer[] { 10, 9, 8 }, result3.toArray());
    }

    @Test
    public void testFindKLargestWithComparatorSort() {
        Comparator<String> strLength = (String a, String b) -> Integer.compare(a.length(), b.length());

        List<String> animalsLargest = FindKLargest.findKLargestSort(animals, 2, strLength);
        assertArrayEquals(new String[] {"elephant", "rabbit"}, animalsLargest.toArray());

        Comparator<Integer> reversedNumber = (Integer a1, Integer a2) -> Integer.compare(a2, a1);
        List<Integer> numsResult = FindKLargest.findKLargestSort(listRange, 3, reversedNumber);
        assertArrayEquals(new Integer[] { 1, 2, 3 }, numsResult.toArray());
    }
}