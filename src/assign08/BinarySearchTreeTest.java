package assign08;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {
    @BeforeEach
    void setUp() throws Exception {
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

        System.out.println(bst.toListString());
    }

    @Test
    void testAddAll() {

        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(1);

        BinarySearchTree<Integer> bstAddAll = new BinarySearchTree<>();
        bstAddAll.addAll(List.of(new Integer[]{2, 3, 4, 1}));

        assertEquals(bst.toListString(), bstAddAll.toListString());
    }

}




