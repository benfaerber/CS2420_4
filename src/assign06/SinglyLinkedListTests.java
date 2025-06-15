package assign06;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class contains tests for SinglyLinkedList.
 *
 * @author Benjamin Faerber
 * @version 2025-06-14
 */
public class SinglyLinkedListTests {

    private SinglyLinkedList<Integer> listTo4, listTo3;

    @BeforeEach
    void setUp() throws Exception {
        this.listTo4 = new SinglyLinkedList<>();
        listTo4.insertFirst(4);
        listTo4.insertFirst(3);
        listTo4.insertFirst(2);
        listTo4.insertFirst(1);

        // 8 10 8
        this.listTo3 = SinglyLinkedList.of(8, 10, 8);
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testInsertFirst() {
        assertArrayEquals(new Integer[] {1, 2, 3, 4}, listTo4.toArray());
        assertEquals(4, listTo4.size());
    }

    @Test
    void testDeleteFirst() {
        listTo4.deleteFirst();
        assertArrayEquals(new Integer[] {2, 3, 4}, listTo4.toArray());

        listTo4.deleteFirst();
        assertArrayEquals(new Integer[] {3, 4}, listTo4.toArray());

        listTo4.deleteFirst();
        listTo4.deleteFirst();
        assertArrayEquals(new Integer[] {}, listTo4.toArray());

        // When the list is empty, deleting should throw this exception
        assertThrows(NoSuchElementException.class, () -> listTo4.deleteFirst());
    }

    @Test
    void testGet() {
        assertArrayEquals(new Integer[] {1,2,3,4}, listTo4.toArray());

        assertEquals(1, listTo4.get(0));
        assertEquals(2, listTo4.get(1));
        assertEquals(3, listTo4.get(2));
        assertEquals(4, listTo4.get(3));
    }

    @Test
    void testIsEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertTrue(list.isEmpty());

        list.insertFirst(4);

        assertFalse(list.isEmpty());
    }

    @Test
    void testClear() {
        listTo3.clear();

        assertEquals(0, listTo3.size());
        assertArrayEquals(new Object[] {}, listTo3.toArray());

        // Can we still add to it?
        listTo3.insertFirst(100);
        listTo3.insertFirst(200);

        assertArrayEquals(new Object[] {200, 100}, listTo3.toArray());
    }

    @Test
    void testIterator() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(4);
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(1);

        Object[] values = list.toArray();

        int currentIndex = 0;
        for (Integer item : list) {
            int currentValue = (int) values[currentIndex++];
            assertEquals(currentValue, item);
        }
    }

    @Test
    void testIteratorRemove() {
        Iterator<Integer> iter = listTo4.iterator();
        // At 1
        assertEquals(1, iter.next());
        iter.remove();
        assertEquals(2, iter.next());
        iter.remove();

        assertEquals(2, listTo4.size());
        System.out.println(listTo4.toString());
        assertArrayEquals(new Integer[] {3, 4}, listTo4.toArray());
    }

    @Test
    void testInsertAtIndex() {
        listTo4.insert(2, 10);

        assertArrayEquals(new Integer[] {1, 2, 10, 3, 4}, listTo4.toArray());

        // Starts: 8 10 8
        // Test insert last index
        listTo3.insert(2, 10);
        assertArrayEquals(new Integer[] {8, 10, 10, 8}, listTo3.toArray());

        // Test insert first index
        listTo3.insert(0, 12);

        assertArrayEquals(new Integer[] {12, 8, 10, 10, 8}, listTo3.toArray());
    }

    @Test
    void testDelete() {
        listTo4.delete(2);
        System.out.println("List " + listTo4.toString());
        assertArrayEquals(new Integer[] {1, 2, 4}, listTo4.toArray());

        listTo4.delete(2);
        System.out.println("List " + listTo4.toString());

        assertArrayEquals(new Integer[] {1, 2}, listTo4.toArray());

        // Starts: 8 10 8
        listTo3.delete(0);
        assertArrayEquals(new Integer[] {10, 8}, listTo3.toArray());

        listTo3.delete(0);
        assertArrayEquals(new Integer[] {8}, listTo3.toArray());

    }

    @Test
    void testIndexOf() {
        assertEquals(2, listTo4.indexOf(3));
        assertEquals(-1, listTo4.indexOf(12));
    }
}
