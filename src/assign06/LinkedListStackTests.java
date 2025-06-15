package assign06;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class contains tests for LinkedListsStackTests.
 *
 * @author Benjamin Faerber
 * @version 2025-06-14
 */
public class LinkedListStackTests {

    private LinkedListStack<Character> lettersAD;
    private LinkedListStack<Integer> emptyStack;
    private LinkedListStack<Integer> numberStack;

    @BeforeEach
    void setUp() throws Exception {
        this.lettersAD = new LinkedListStack<>();
        lettersAD.push('D');
        lettersAD.push('C');
        lettersAD.push('B');
        lettersAD.push('A');

        this.emptyStack = new LinkedListStack<>();

        this.numberStack = LinkedListStack.of(1, 2, 3, 4, 5);
        System.out.println(this.numberStack.toString());
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testPush() {
        LinkedListStack<Integer> numbersStackPush = new LinkedListStack<>();
        numbersStackPush.push(4);
        numbersStackPush.push(3);
        numbersStackPush.push(2);
        numbersStackPush.push(1);

        assertArrayEquals(new Integer[] {1, 2, 3, 4}, numbersStackPush.toArray());
    }

    @Test
    void testIsEmpty() {
        assertFalse(this.lettersAD.isEmpty());
        assertTrue(this.emptyStack.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(4, this.lettersAD.size());

        this.lettersAD.pop();
        assertEquals(3, this.lettersAD.size());
    }

    @Test
    void testPeek() {
        assertEquals('A', this.lettersAD.peek());

        assertThrows(NoSuchElementException.class, () -> this.emptyStack.peek());

        assertEquals(1, this.numberStack.peek());
    }

    @Test
    void testPop() {
        assertEquals('A', this.lettersAD.pop());
        assertArrayEquals(new Character[] {'B', 'C', 'D'}, this.lettersAD.toArray());

        assertEquals('B', this.lettersAD.pop());
        assertArrayEquals(new Character[] {'C', 'D'}, this.lettersAD.toArray());

        assertEquals('C', this.lettersAD.pop());
        assertArrayEquals(new Character[] {'D'}, this.lettersAD.toArray());
    }

    @Test
    void testClear() {
        this.lettersAD.clear();
        assertEquals(0, this.lettersAD.size());

        this.lettersAD.push('A');
        assertEquals(1, this.lettersAD.size());
    }
}
