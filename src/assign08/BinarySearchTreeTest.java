package assign08;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> basicBst, tree;

    @BeforeEach
    void setUp() throws Exception {
        basicBst = new BinarySearchTree<>();
        basicBst.addAll(List.of(new Integer[]{2, 3, 4, 1}));
        tree = new BinarySearchTree<>();
    }

    @AfterEach
    void tearDown() throws Exception {}


    @Test
    void testBinarySearchTree() {

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();

        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(1);

        System.out.println(bst.toDot());
    }

    @Test
    void testAddAll() {
        List<Integer> items = Arrays.asList(5, 3, 7);
        tree.addAll(items);

        tree.addAll(items);
        for (int i : items) {
            assertTrue(tree.contains(i));
        }
    }

    @Test
    void testRemoveLeafNode() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        assertTrue(tree.remove(5));
        assertFalse(tree.contains(5));
    }

    @Test
    void testRemoveNodeWithOneChild() {
        tree.add(10);
        tree.add(5);
        tree.add(3);
        assertTrue(tree.remove(5));
        assertFalse(tree.contains(5));
        assertTrue(tree.contains(3));
    }

    @Test
    void testRemoveNodeWithTwoChildren() {
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(12);
        tree.add(18);
        assertTrue(tree.remove(15));
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(12));
        assertTrue(tree.contains(18));
    }

    @Test
    void testFirstAndLast() {
        tree.add(20);
        tree.add(10);
        tree.add(30);
        tree.add(5);
        tree.add(35);
        assertEquals(5, tree.first());
        assertEquals(35, tree.last());
    }

    @Test
    void testFirstThrowsOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> tree.first());
    }

    @Test
    void testLastThrowsOnEmpty() {
        assertThrows(NoSuchElementException.class, () -> tree.last());
    }

    @Test
    void testIterator() {
        tree.addAll(Arrays.asList(10, 5, 15, 3, 7));
        Iterator<Integer> it = tree.iterator();
        List<Integer> actual = new ArrayList<>();
        while (it.hasNext()) {
            actual.add(it.next());
        }
        assertEquals(Arrays.asList(3, 5, 7, 10, 15), actual);
    }

    @Test
    void testContains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.addAll(List.of(new Integer[]{2, 3, 4, 1}));

        // Should find the 2 (first)
        assertTrue(bst.contains(2));
        // Should find the rightmost (4)
        assertTrue(bst.contains(4));
        // Should find the leftmost
        assertTrue(bst.contains(1));

        // Doesn't have a 5
        assertFalse(bst.contains(5));
    }

    @Test
    void testFirst() {
        assertEquals(1, basicBst.first());
    }

    @Test
    void testLast() {
        assertEquals(4, basicBst.last());
    }

    @Test
    void testToDot(){
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(5);
        bst.add(3);
        bst.add(7);
        bst.add(2);
        bst.add(4);
        System.out.print(bst.toDot());
    }
}




