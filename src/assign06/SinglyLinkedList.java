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

    /**
     * A single node of the linked list.
     * @param <E> The type the node contains
     */
    public class Node<E> {
        /**
         * The value this node contains (cannot be null)
         */
        private E value;
        /**
         * The next node in the list
         * (can be null if this is the end)
         */
        private Node<E> next = null;

        /**
         * Construct a new node
         * @param value the value the node holds
         * @param next the next in the list (can be null if this is the end)
         */
        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    /** Create a new SinglyLinkedList */
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

    /**
     * Inserts an element at the beginning of the list.
     * O(1) for a singly-linked list.
     *
     * @param element - the element to add
     */
    public void insertFirst(E element) {
        this.size++;

        if (this.list == null) {
            this.list = new Node<>(element, null);
            return;
        }

        this.list = new Node<>(element, this.list);
    }

    /**
     * Inserts an element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @param element - the element to add
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
     */
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

    /**
     * Gets the first element in the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() throws NoSuchElementException {
        if (this.list == null) {
            throw new NoSuchElementException();
        }
        return this.list.value;
    }

    /**
     * Gets the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
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

    /**
     * Deletes and returns the first element from the list.
     * O(1) for a singly-linked list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E deleteFirst() throws NoSuchElementException {
        if (this.list == null) {
            throw new NoSuchElementException();
        }
        this.size--;

        E toDelete = this.list.value;
        this.list = this.list.next;

        return toDelete;
    }

    /**
     * Deletes and returns the element at a specific position in the list.
     * O(N) for a singly-linked list.
     *
     * @param index - the specified position
     * @return the element at the position
     * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
     */
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

    /**
     * Determines the index of the first occurrence of the specified element in the list,
     * or -1 if this list does not contain the element.
     * O(N) for a singly-linked list.
     *
     * @param element - the element to search for
     * @return the index of the first occurrence; -1 if the element is not found
     */
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

    /**
     * Generates an array containing all of the elements in this list in proper sequence
     * (from first element to last element).
     * O(N) for a singly-linked list.
     *
     * @return an array containing all of the elements in this list, in order
     */
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

    /**
     * Convert a singly linked list to a string
     * @return a string representation of the singly linked list
     */
    @Override
    public String toString() {
        Object[] arr = this.toArray();
        StringJoiner sj = new StringJoiner(", ");
        for (Object t : arr) {
            sj.add(t.toString());
        }
        return "[" + sj + "]";
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return this.size;
    }

    /**
     * O(1) for a singly-linked list.
     *
     * @return true if this collection contains no elements; false, otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all of the elements from this list.
     * O(1) for a singly-linked list.
     */
    public void clear() {
        this.size = 0;
        this.list = null;
    }

    /** The iterator that allows iteration over a singly linked list */
    public class SinglyLinkedListIterator implements Iterator<E> {
        /** The current node of the iteration */
        private Node<E> currentNode = list;
        /** If the element can be removed */
        private boolean canRemove = false;
        /** Internal value for removal */
        private Node<E> lastReturned = null;
        /** Another internal value for removal */
        private Node<E> previousLastReturned = null;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            if (this.currentNode == null) {
                return false;
            }
            return this.currentNode.next != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
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

        /**
         * Removes the current item from the iterator (and outer list)
         */
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

    /**
     * @return an iterator over the elements in this list in proper sequence (from first
     * element to last element)
     */
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    /**
     * Throws an exception if a given index is invalid
     * @param index the index to validate
     * @throws IndexOutOfBoundsException
     */
    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

}
