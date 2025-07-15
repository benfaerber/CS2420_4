package assign10;

import java.util.*;

/**
 * This class creates a generic binary max heap that implements a priority queue,
 * storing elements in a way that the maximum element is always at the root.
 *
 * @author Ben Faerber and David Chen
 * @version July 14, 2025
 */
public class BinaryMaxHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
    private final Comparator<? super E> cmp;
    private ArrayList<E> heap;
    private int size;

    /**
     * Constructs an empty binary max heap using natural ordering.
     */
    public BinaryMaxHeap() {
        this(Comparator.naturalOrder());
    }

    /**
     * Constructs an empty binary max heap using the specified comparator.
     *
     * @param cmp the comparator to use for ordering elements
     */
    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this(new ArrayList<>(), cmp);
    }

    /**
     * Constructs a binary max heap from the given list using natural ordering.
     *
     * @param list the list of elements to initialize the heap
     * @throws IllegalArgumentException if the list is null
     */
    public BinaryMaxHeap(List<? extends E> list) {
        this(list, Comparator.naturalOrder());
    }

    /**
     * Constructs a binary max heap from the given list using the specified comparator.
     *
     * @param list the list of elements to initialize the heap
     * @param cmp  the comparator to use for ordering elements
     * @throws IllegalArgumentException if the list is null
     */
    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        if (list == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }
        this.cmp = cmp;
        this.heap = new ArrayList<>();
        this.size = 0;
        buildHeap(list);
    }

    /**
     * Adds the given item to this priority queue.
     * O(1) average case, O(log N) worst case.
     *
     * @param item the item to add
     */
    @Override
    public void add(E item) {
        heap.add(item);
        percolateUp(size);
        size++;
    }

    /**
     * Returns, but does not remove, the highest priority item.
     * O(1)
     *
     * @return the highest priority item
     * @throws NoSuchElementException if the heap is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Your heap is empty");
        }
        return heap.get(0);
    }

    /**
     * Removes and returns the highest priority item.
     * O(log N)
     *
     * @return the highest priority item
     * @throws NoSuchElementException if the heap is empty
     */
    public E extract() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty");
        }

        E result = heap.get(0);
        E last = heap.remove(size - 1);
        size--;

        // Special case for if this is the root
        if (size == 0) {
            return result;
        }

        heap.set(0, last);
        percolateDown(0);

        return result;
    }

    /**
     * Returns the number of items in the heap.
     * O(1)
     *
     * @return the number of items
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the heap is empty.
     * O(1)
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all items from the heap.
     * O(1)
     */
    @Override
    public void clear() {
//        buildHeap(new ArrayList<>());
        heap = new ArrayList<>();
        size = 0;
    }

    /**
     * Returns an array of items in the heap, with the root at index 0.
     * O(N)
     *
     * @return array of items
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        // take advantage of O(1) iterator
        for (E item : heap) {
            array[index++] = item;
        }
        return array;
    }

    /**
     * Calculates the parent index for a given node index.
     *
     * @param index the node index
     * @return the parent index
     */
    private int calculateParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Calculates the left child index for a given node index.
     *
     * @param index the node index
     * @return the left child index
     */
    private int calculateLeft(int index) {
        return (index * 2) + 1;
    }

    /**
     * Calculates the right child index for a given node index.
     *
     * @param index the node index
     * @return the right child index
     */
    private int calculateRight(int index) {
        return (index * 2) + 2;
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param index1 the index of the first element
     * @param index2 the index of the second element
     */
    private void swap(int index1, int index2) {
        E tmp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, tmp);
    }

    /**
     * Percolates an element up the heap to maintain the max-heap property.
     *
     * @param index the index of the element to percolate
     */
    private void percolateUp(int index) {
        while (index > 0) {
            int parent = calculateParent(index);
            E tempAtCurrent =  heap.get(index);
            E tempAtParent = heap.get(parent);

            if (innerCompare(tempAtCurrent, tempAtParent) <= 0) {
                break;
            }

            heap.set(index, tempAtParent);
            heap.set(parent, tempAtCurrent);
            index = parent;
        }
    }

    /**
     * Percolates an element down the heap to maintain the max-heap property.
     *
     * @param index the index of the element to percolate
     */
    private void percolateDown(int index) {
        while (true) {
            int leftChild = calculateLeft(index);
            int rightChild = calculateRight(index);
            int largest = index;

            if (leftChild < size && innerCompare(heap.get(leftChild), heap.get(largest)) > 0) {
                largest = leftChild;
            }

            if (rightChild < size && innerCompare(heap.get(rightChild), heap.get(largest)) > 0) {
                largest = rightChild;
            }

            if (largest == index) {
                break;
            }

            swap(index, largest);
            index = largest;
        }
    }

    /**
     * Builds a max-heap from the given list in O(N) time.
     *
     * @param list the list of elements to build the heap from
     */
    private void buildHeap(List<? extends E> list) {
//        heap = new ArrayList<>(list);
        heap.clear();
        if (!list.isEmpty()) {
            heap.addAll(list);
        }
        int parentStart = calculateParent(heap.size());
        size = heap.size();
        // should i = (size - 2)/2 ?
        for (int i = parentStart; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Compares two elements using the comparator or natural ordering.
     *
     * @param a the first element
     * @param b the second element
     * @return the comparison result
     */
    private int innerCompare(E a, E b) {
        // Always use comparator. (natural ordering is assigned sometimes)
        return cmp.compare(a, b);
    }

    /**
     * Returns a string representation of the heap.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (E item : heap) {
            sj.add(item.toString());
        }
        return "heap{" + sj.toString() + "}";
    }
}
