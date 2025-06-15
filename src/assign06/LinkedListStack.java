package assign06;

import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * A stack created using an internal linked list
 *
 * @version 06/14/2025
 * @author Benjamin Faerber
 */
public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list;

    public LinkedListStack() {
        this.list = new SinglyLinkedList<>();
    }

    /**
     * Construct a new list from variadic arguments
     *
     * @param items A list of param items
     * @return A newly constructed list
     * @param <E> the type
     */
    public static <E> LinkedListStack<E> of(E... items) {
        LinkedListStack<E> stack = new LinkedListStack<>();
        for (int i = items.length - 1; i >= 0; i--) {
            stack.push(items[i]);
        }
        return stack;
    }

    /**
     * Removes all of the elements from the stack.
     */
    public void clear() {
        this.list.clear();
    }

    /**
     * @return true if the stack contains no elements; false, otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E peek() throws NoSuchElementException {
        return this.list.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    public E pop() throws NoSuchElementException {
        return this.list.deleteFirst();
    }

    /**
     * Adds a given element to the stack, putting it at the top of the stack.
     *
     * @param element - the element to be added
     */
    public void push(E element) {
        this.list.insertFirst(element);
    }

    /**
     * @return the number of elements in the stack
     */
    public int size() {
        return this.list.size();
    }

    /** Convert the stack into an array */
    public Object[] toArray() {
        return this.list.toArray();
    }

    /** Convert the stack to a string formatted as Stack(1, 2, 3, 4) */
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (E item : this.list) {
            sj.add(item.toString());
        }
        return "Stack(" + sj + ")";
    }
}
