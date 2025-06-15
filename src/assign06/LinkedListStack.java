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

    public static <E> LinkedListStack<E> of(E... items) {
        LinkedListStack<E> stack = new LinkedListStack<>();
        for (int i = items.length - 1; i >= 0; i--) {
            stack.push(items[i]);
        }
        return stack;
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public E peek() throws NoSuchElementException {
        return this.list.getFirst();
    }

    @Override
    public E pop() throws NoSuchElementException {
        return this.list.deleteFirst();
    }

    @Override
    public void push(E element) {
        this.list.insertFirst(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }

    public Object[] toArray() {
        return this.list.toArray();
    }

    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (E item : this.list) {
            sj.add(item.toString());
        }
        return "Stack(" + sj + ")";
    }
}
