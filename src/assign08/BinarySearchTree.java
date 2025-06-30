package assign08;

import java.util.*;

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

        if (root.getData().compareTo(item) == 0) {
            return false;
        }

        this.addAux(root, item);
        return true;
    }

    private BinaryNode<Type> addAux(BinaryNode<Type> node, Type key) {
        if (node == null) {
            return new BinaryNode<>(key);
        }

        int compare = node.getData().compareTo(key);
        if (compare > 0) {
            BinaryNode<Type> left = node.getLeftChild();
            BinaryNode<Type> newLeft = addAux(left, key);
            node.setLeftChild(newLeft);
        } else if (compare < 0) {
            BinaryNode<Type> right = node.getRightChild();
            BinaryNode<Type> newRight = addAux(right, key);
            node.setRightChild(newRight);
        }

        return node;
    }


    public void addAll(Collection<? extends Type> items) {
        for (Type item : items) {
            this.add(item);
        }
    }


    public boolean contains(Type item) {
        // TODO: After create iterator
        return false;
    }


    public boolean remove(Type item) {
        // TODO
        return false;
    }

    public Type first() throws NoSuchElementException {
        // TODO: after create iterator
        throw new NoSuchElementException();
    }

    public Type last() throws NoSuchElementException {
        // TODO: after create iterator
        throw new NoSuchElementException();
    }

    public int size() {
        // TODO: after create iterator
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

    private ArrayList<Type> values;

    private void addSingleNodeToValues(BinaryNode<Type> node) {
        if (node != null) {
            addSingleNodeToValues(node.getLeftChild());
            values.add(node.getData());
            addSingleNodeToValues(node.getRightChild());
        }
    }

    private ArrayList<Type> getValuesInOrder() {
        this.values = new ArrayList<>();
        addSingleNodeToValues(root);
        ArrayList<Type> valuesCopy = new ArrayList<>(values);
        // Make it so it starts fresh next time
        this.values = new ArrayList<>();
        return valuesCopy;
    }


    public String toListString() {
        StringJoiner sb = new StringJoiner(", ");
        for (Type item : this.getValuesInOrder()) {
            sb.add(item.toString());
        }
        return sb.toString();
    }

    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph G {\n");

        if (root == null) {
            return sb.append("}\n").toString();
        }

        ArrayList<Type> values = getValuesInOrder();

        // Finish after iterator

        return "";
    }
}
