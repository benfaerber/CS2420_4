package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * A linked list connected one-way from head to tail
 *
 * @version 06/14/2025
 * @author Benjamin Faerber
 */
public class SinglyLinkedList<E> implements List<E> {
    /** The first node of the linked list */
    private Node<E> list = null;

    /**
     * Store the size and modify after insert
     * (maintains O(1) speed)
     */
    private int size = 0;

    public class Node<E> {
        private E value;
        private Node<E> next = null;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    public SinglyLinkedList() {

    }

    /**
     * Construct a new list from variadic arguments
     *
     * @param items A list of param items
     * @return A newly constructed list
     * @param <E> the type
     */
    @SafeVarargs
    public static <E> SinglyLinkedList<E> of(E... items) {
        SinglyLinkedList<E> list = new SinglyLinkedList<>();
        for (int i = items.length - 1; i > 0; i--) {
            list.insertFirst(items[i]);
        }

        return list;
    }

    public void insertFirst(E element) {
        this.size++;

        if (this.list == null) {
            this.list = new Node<>(element, null);
            return;
        }

        this.list = new Node<>(element, this.list);
    }

    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        this.validateIndex(index);

        if (index == 0) {
            this.insertFirst(element);
            return;
        }

        Node<E> currentNode = this.list;
        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next;
        }

        Node<E> oldNext = currentNode.next;
        currentNode.next = new Node<>(element, currentNode.next);
        this.size++;
    }

    public E getFirst() throws NoSuchElementException {
        if (this.list == null) {
            throw new NoSuchElementException();
        }
        return this.list.value;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        this.validateIndex(index);

        Node<E> currentNode = this.list;
        int currentIndex = 0;
        while (currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode.value;
    }

    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (this.list == null) {
            throw new NoSuchElementException();
        }
        this.size--;

        E toDelete = this.list.value;
        this.list = this.list.next;

        return toDelete;
    }

    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        this.validateIndex(index);

        if (index == 0) {
            return this.deleteFirst();
        }

        Node<E> previous = this.list;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.next;
        }

        Node<E> toDelete = previous.next;
        previous.next = toDelete.next;
        this.size--;

        return toDelete.value;
    }

    @Override
    public int indexOf(E element) {
        int currentIndex = 0;
        for (E item : this) {
            if (item.equals(element)) {
                return currentIndex;
            }
            currentIndex++;
        }

        return -1;
    }

    public Object[] toArray() {
        if (this.size == 0) {
            return new Object[] {};
        }
        Object[] built = new Object[this.size];
        Node<E> currentNode = this.list;
        for (int i = 0; i < this.size; i++) {
            built[i] = currentNode.value;
            currentNode = currentNode.next;
        }

        return built;
    }

    @Override
    public String toString() {
        Object[] arr = this.toArray();
        StringJoiner sj = new StringJoiner(", ");
        for (Object t : arr) {
            sj.add(t.toString());
        }
        return "[" + sj + "]";
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.size = 0;
        this.list = null;
    }

    public class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> currentNode = list;
        private boolean canRemove = false;
        private Node<E> lastReturned = null;
        private Node<E> previousLastReturned = null;


        @Override
        public boolean hasNext() {
            if (this.currentNode == null) {
                return false;
            }
            return this.currentNode.next != null;
        }

        @Override
        public E next() {
            if (! this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.canRemove = true;

            Node<E> node = currentNode;
            this.previousLastReturned = this.lastReturned;
            this.lastReturned = this.currentNode;
            this.currentNode = this.currentNode.next;

            return node.value;
        }

        public void remove() {
            if (! this.canRemove) {
                throw new IllegalStateException();
            }

            if (this.lastReturned == list) {
                list = list.next;
            } else if (previousLastReturned != null) {
                previousLastReturned.next = lastReturned.next;
            }

            this.lastReturned = null;
            this.canRemove = false;
            SinglyLinkedList.this.size--;
        }
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

}
