package assign08;

import java.util.*;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root = null;
    private int size = 0;
    private ArrayList<Type> values;

    public BinarySearchTree() {}

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
        size++;
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
            newLeft.setParent(node);
            node.setLeftChild(newLeft);
        } else if (compare < 0) {
            BinaryNode<Type> right = node.getRightChild();
            BinaryNode<Type> newRight = addAux(right, key);
            newRight.setParent(node);
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
        BinaryNode<Type> node = searchAux(root, item);
        return node != null;
    }

    private BinaryNode<Type> searchAux(BinaryNode<Type> node, Type key) {
        if (node == null) {
            return null;
        }
        int compare = node.getData().compareTo(key);
        boolean isDuplicate = compare == 0;
        if (isDuplicate) {
            return node;
        }

        if (compare < 0) {
            return searchAux(node.getRightChild(), key);
        }

        return searchAux(node.getLeftChild(), key);
    }

    public boolean remove(Type item) {
        if (!contains(item)) {
            return false;
        }
        root = removeAux(root, item);
        size--;
        return true;
    }

    private BinaryNode<Type> removeAux(BinaryNode<Type> node, Type key) {
        if (node == null) {
            return null;
        }

        int compare = node.getData().compareTo(key);
        if (compare > 0) {
            node.setLeftChild(removeAux(node.getLeftChild(), key));
        } else if (compare < 0) {
            node.setRightChild(removeAux(node.getRightChild(), key));
        } else {
            if (node.getLeftChild() == null) {
                return node.getRightChild();
            } else if (node.getRightChild() == null) {
                return node.getLeftChild();
            }

            node.setData(minValue(node.getRightChild()));
            node.setRightChild(removeAux(node.getRightChild(), node.getData()));
        }
        return node;
    }

    private Type minValue(BinaryNode<Type> node) {
        Type minValue = node.getData();
        while (node.getLeftChild() != null) {
            minValue = node.getLeftChild().getData();
            node = node.getLeftChild();
        }
        return minValue;
    }

    public Type first() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return root.getLeftmostNode().getData();
    }

    public Type last() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return root.getRightmostNode().getData();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    public Iterator<Type> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Type> {
        private BinaryNode<Type> current;
        private BinaryNode<Type> lastReturned; // Needed for remove()

        public BSTIterator() {
            // Left most node will be the smallest
            current = root.getLeftmostNode();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Type next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = current;
            Type result = current.getData();

            // Have right child
            if (current.getRightChild() != null) {
                current = current.getRightChild();
                while (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                }
            } else {
                BinaryNode<Type> parent = current.getParent();
                while (parent != null && current == parent.getRightChild()) {
                    current = parent;
                    parent = parent.getParent();
                }
                current = parent;
            }

            return result;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException("next() must be called before remove()");
            }

            // If there are children, fix the link
            if (lastReturned.getLeftChild() != null && lastReturned.getRightChild() != null) {
                current = lastReturned;
            }

            BinarySearchTree.this.remove(lastReturned.getData());
            lastReturned = null;
        }
    }

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
        StringJoiner sj = new StringJoiner(", ");
        for (Type item : this.getValuesInOrder()) {
            sj.add(item.toString());
        }
        return sj.toString();
    }

    /**
     * Generates a DOT representation of the BST using DFS (no queue).
     * @return DOT format string
     */
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph BST {\n");

        if (root != null) {
            for (Type item : this) {
                sb.append("    \"").append(item).append("\";\n");
            }
            toDotAux(root, sb);
        }

        sb.append("}\n");
        return sb.toString();
    }

    /**
     * Helper method to add edges recursively (DFS traversal).
     */
    private void toDotAux(BinaryNode<Type> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        // Add left child edge
        if (node.getLeftChild() != null) {
            sb.append("    \"")
                    .append(node.getData())
                    .append("\" -> \"")
                    .append(node.getLeftChild().getData())
                    .append("\";\n");
            toDotAux(node.getLeftChild(), sb);
        }

        // Add right child edge
        if (node.getRightChild() != null) {
            sb.append("    \"")
                    .append(node.getData())
                    .append("\" -> \"")
                    .append(node.getRightChild().getData())
                    .append("\";\n");
            toDotAux(node.getRightChild(), sb);
        }
    }
}
