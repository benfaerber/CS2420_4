package assign08;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root = null;

    public BinarySearchTree() {

    }

    public boolean add(Type item) {
        // First, the binary tree is empty
        if (root == null) {
            root = new BinaryNode<>(item);
            return true;
        }


    }

    private boolean addRE

    public void addAll(Collection<? extends Type> items) {
        // TODO
    }


    public boolean contains(Type item) {
        // TODO
        return false;
    }


    public boolean remove(Type item) {
        // TODO
        return false;
    }

    public Type first() throws NoSuchElementException {
        // TODO
        throw new NoSuchElementException();
    }

    public Type last() throws NoSuchElementException {
        // TODO
        throw new NoSuchElementException();
    }

    public int size() {
        // TODO
        return 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public Iterator<Type> iterator() {
        // TODO
        throw new NoSuchElementException();
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");

        if (root == null) {
            return sb.append("}\n").toString();


        }

        // Finish after iterator

        return "";
    }
}
