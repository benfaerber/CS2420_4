package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains all tests for the BinaryMaxHeap class.
 *
 * @author Benjamin Faerber and David Chen
 * @version July 14, 2025
 */
public class BinaryMaxHeapTest {
    private BinaryMaxHeap<Integer> heap;
    private BinaryMaxHeap<String> heapStringLength;
    private BinaryMaxHeap<Integer> heapFromList;

    @BeforeEach
    public void setUp() {
        heap = new BinaryMaxHeap<>();
        // To Test Custom Comparator
        heapStringLength = new BinaryMaxHeap<>((String a, String b) -> Integer.compare(a.length(), b.length()));
        heapFromList = new BinaryMaxHeap<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

    @Test
    public void testAddAndPeek() {
        assertThrows(NoSuchElementException.class, () -> heap.peek());
        heap.add(10);
        assertEquals(10, heap.peek());
        heap.add(20);
        assertEquals(20, heap.peek());
        heap.add(15);
        assertEquals(20, heap.peek());
    }

    @Test
    public void testExtract() {
        assertThrows(NoSuchElementException.class, () -> heap.extract());
        heap.add(10);
        heap.add(20);
        heap.add(15);
        assertEquals(20, heap.extract());
        assertEquals(2, heap.size());
        assertEquals(15, heap.extract());
        assertEquals(1, heap.size());
        assertEquals(10, heap.extract());
        assertTrue(heap.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(9, heapFromList.size());
        assertEquals(0, heap.size());
        heap.add(10);
        assertEquals(1, heap.size());
        heap.add(20);
        assertEquals(2, heap.size());
        heap.add(15);
        heap.add(23);
        assertEquals(4, heap.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());
        assertFalse(heapFromList.isEmpty());
    }

    @Test
    public void testClear() {
        heap.add(21);
        heap.add(22);
        heap.add(23);
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(heap.size(), 0);
    }

    @Test
    public void testCustomComparator() {
        heapStringLength.add("hello world!");
        heapStringLength.add("hallo");
        heapStringLength.add("hi");
        heapStringLength.add("hallo2");
        heapStringLength.add("hi3");

        String[] expectedOrder = {"hello world!","hallo2", "hi", "hallo", "hi3"};
        assertArrayEquals(expectedOrder, heapStringLength.toArray());
    }
}