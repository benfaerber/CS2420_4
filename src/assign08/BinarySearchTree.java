package assign08;

import java.util.*;

/**
 * A binary search tree ADT.
 * This is a graph based data structure that sorts nodes in a tree structure.
 * The leftmost node will be the smallest element and the rightmost node will be the largest element
 * This allows for fast lookups.
 * @param <Type> The type that the individual node should contain
 * @author Benjamin Faerber and David Chen
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
    private BinaryNode<Type> root = null;
    private int size = 0;
    private ArrayList<Type> values;

    public BinarySearchTree() {}

    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     */
    public boolean add(Type item) {
        if (root == null) {
            root = new BinaryNode<>(item);
            size++;
            return true;
        }

        BinaryNode<Type> current = root;
        BinaryNode<Type> parent = null;
        int compare = 0;

        while (current != null) {
            parent = current;
            compare = item.compareTo(current.getData());

            if (compare < 0) {
                current = current.getLeftChild();
            } else if (compare > 0) {
                current = current.getRightChild();
            } else {
                return false;
            }
        }

        BinaryNode<Type> newNode = new BinaryNode<>(item);
        newNode.setParent(parent);

        if (compare < 0) {
            parent.setLeftChild(newNode);
        } else {
            parent.setRightChild(newNode);
        }

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

    /**
     * Ensures that this set contains all items in the specified collection.
     * Unlike add, this does not return anything.
     *
     * @param items - the collection of items whose presence is ensured in this set
     */
    public void addAll(Collection<? extends Type> items) {
        for (Type item : items) {
            this.add(item);
        }
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     */
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

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     */
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

            node.setData(node.getRightChild().getLeftmostNode().getData());
            node.setRightChild(removeAux(node.getRightChild(), node.getData()));
        }
        return node;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    public Type first() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }

        return root.getLeftmostNode().getData();
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    public Type last() throws NoSuchElementException {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return root.getRightmostNode().getData();
    }

    /**
     * Returns the number of items in this set.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this set contains no items.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    public void clear() {
        root = null;
    }

    /**
     * An iterator that iterates over the binary search tree in order
     * @return An iterator of Type
     */
    public Iterator<Type> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<Type> {
        private BinaryNode<Type> current;
        /** Need to store the last for remove */
        private BinaryNode<Type> lastReturned;

        public BSTIterator() {
            if (root == null) {
                current = null;
                return;
            }

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
     * Helper method to add edges recursively
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
