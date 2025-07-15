package assign10;

import java.util.*;

public class BinaryMaxHeap<E extends Comparable<? super E>> implements PriorityQueue<E> {
    private final Comparator<? super E> cmp;

    private ArrayList<E> heap = new ArrayList<>();
    private int size = 0;

    public BinaryMaxHeap() {
        this(Comparator.naturalOrder());
    }

    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this(new ArrayList<>(), cmp);
    }

    public BinaryMaxHeap(List<? extends E> list) {
        this(list, Comparator.naturalOrder());
    }

    public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
        this.cmp = cmp;
        this.buildHeap(list);
    }

    public void add(E item) {
        heap.add(item);
        percolateUp(size);
        size++;
    }

    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Your heap is empty");
        }
        return heap.get(0);
    }

    public E extract() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Your heap is empty");
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

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        buildHeap(new ArrayList<>());
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        // take advantage of O(1) iterator
        for (E item : heap) {
            array[index++] = item;
        }
        return array;
    }

    private int calculateParent(int index) {
        return (index - 1) / 2;
    }

    private int calculateLeft(int index) {
        return (index * 2) + 1;
    }

    private int calculateRight(int index) {
        return (index * 2) + 2;
    }

    private void swap(int index1, int index2) {
        E tmp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, tmp);
    }

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

    private void buildHeap(List<? extends E> list) {
        heap = new ArrayList<>(list);
        int parentStart = calculateParent(heap.size());
        size = heap.size();

        for (int i = parentStart; i >= 0; i--) {
            percolateDown(i);
        }
    }

    private int innerCompare(E a, E b) {
        // Always use comparator. (natural ordering is assigned sometimes)
        return cmp.compare(a, b);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ");
        for (E item : heap) {
            sj.add(item.toString());
        }
        return "heap{" + sj.toString() + "}";
    }
}
