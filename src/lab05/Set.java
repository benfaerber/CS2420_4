package lab05;

import java.util.Collection;

/**
 * A set with operations to add, remove, and query elements.
 *
 * @author CS 2420 course staff
 * @version 2025-05-22
 *
 * @param <E> - the type of elements contained in this set
 */
public interface Set<E> {
    /**
     * Adds an element to the set.
     *
     * @param item - the element to add
     */
    void add(E item);

    /**
     * Removes and returns an element equal to the given item if
     * such an element is present.
     *
     * @param item - the element to be removed
     * @return the element that was removed if present; otherwise null
     */
    E remove(E item);

    /**
     * Indicates whether this set contains an element equal to the
     * given item.
     *
     * @param item - the object to be checked for containment in this set
     * @return true if the item is contained in this set; otherwise false
     */
    boolean contains(E item);

    /**
     * Indicates whether this set contains all the elements in
     * the collection. If the collection is empty, this returns true.
     *
     * @param items - a collection of objects to be checked for containment in this set
     * @return true if all items are contained in this set; otherwise false
     */
    boolean containsAll(Collection<? extends E> items);

    /**
     * Returns all the elements of the set in an array.
     *
     * @return array containing all elements of the set
     */
    Object[] toArray();

    /**
     * @return the number of elements in the set
     */
    int size();

    /**
     * @return true if this set is empty; otherwise false
     */
    boolean isEmpty();

    /**
     * Removes all the elements from this set, resulting in an empty set.
     */
    void clear();
}
