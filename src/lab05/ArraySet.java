package lab05;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A set powered by an ordered Array internally
 * 
 * @author Benjamin Faerber and David Chen
 * @version 2025-05-29
 */
public class ArraySet<E> implements Set<E>, Iterable<E> {
	/**
	 * The internal representation of the set
	 */
	private E[] internalArray;
	/**
	 * How much capacity is given to a new set?
	 */
	static int DEFAULT_CAPACITY = 10;
	/**
	 * What is used to order the set? (note: by default the items "compareTo" will be used)
	 */
	Comparator<? super E> cmp;
	
	/**
	 * Creates an empty ArraySet
	 */
	public ArraySet() {
		this.internalArray = this.createWithCapacity(DEFAULT_CAPACITY);
		this.cmp = new Comparator<E>() {
			@SuppressWarnings("unchecked")
			public int compare(E a, E b) {
				return ((Comparable<? super E>) a).compareTo(b);

			}
		};
	}

	/**
	 * Create an empty ArraySet with a custom comparator
	 * 
	 * @param cmp the comparator to use while inserting
	 */
	public ArraySet(Comparator<? super E> cmp) {
		this.cmp = cmp;
		this.internalArray = this.createWithCapacity(DEFAULT_CAPACITY);
	}

	/**
	 * Create a new generic array with given capacity
	 * 
	 * @param capacity size to create generic array
	 * @return generic array
	 */
	@SuppressWarnings("unchecked")
	private E[] createWithCapacity(int capacity) {
		E[] arr = (E[]) new Object[capacity];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = null;
		}
		return arr;
	}

	/**
	 * To unit test the internal structure of the set
	 * @return the internal representation
	 */
	protected E[] viewInternalForTesting() {
		return this.internalArray;
	}
	
	/**
	 * @return View the capacity of the inner data structure
	 */
	protected int viewInternalCapacity() {
		return this.internalArray.length;
	}
	
	/**
	 * Binary Searches through a list to find the index
	 * 
	 * @param arr   the array to search through
	 * @param value the value to find
	 * @return the index found or (position it should be found at minus 1)
	 */
	public int binarySearch(E[] arr, E value) {
		int foundNullSpace = arr.length;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null) {
				foundNullSpace = i;
				break;
			}
		}

		int lower = 0;
		int upper = foundNullSpace - 1;
		int middle = 0;

		while (lower <= upper) {
			middle = (lower + upper) / 2;

			if (arr[middle] == null) {
				return (middle * -1) - 1;
			}

			int compare = this.cmp.compare(value, arr[middle]);
			if (compare == 0) {
				return middle;
			}

			if (compare < 0) {
				upper = middle - 1;
			} else if (compare > 0) {
				lower = middle + 1;
			}
		}

		return (lower * -1) - 1;
	}

	/**
	 * Adds an element to the set.
	 *
	 * @param item - the element to add
	 */
	public void add(E item) {
		int foundItemIndex = this.binarySearch(this.internalArray, item);

		// Its already in the set!
		if (foundItemIndex >= 0) {
			return;
		}

		int nullCount = 0;
		for (int i = 0; i < this.internalArray.length; i++) {
			if (this.internalArray[i] == null) {
				nullCount++;
			}
		}

		if (nullCount == 1) {
			E[] newSize = this.createWithCapacity(this.internalArray.length * 2);
			for (int i = 0; i < this.internalArray.length; i++) {
				newSize[i] = this.internalArray[i];
			}
			this.internalArray = newSize;
		}

		
		int flipAt = Math.abs(foundItemIndex + 1);
		for (int i = this.internalArray.length - 1; i > flipAt; i--) {
			this.internalArray[i] = this.internalArray[i - 1];
		}
		this.internalArray[flipAt] = item;

	}

	/**
	 * Print the internal implementation with no label
	 */
	public void printInternal() {
		this.printInternal("");
	}

	/**
	 * Print the internal implementation with a custom label
	 * 
	 * @param label A label to print before
	 */
	public void printInternal(String label) {
		System.out.print(label + " [");
		for (int i = 0; i < this.internalArray.length; i++) {
			System.out.print(this.internalArray[i]);
			if (i != this.internalArray.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	/**
	 * Removes and returns an element equal to the given item if such an element is
	 * present.
	 *
	 * @param item - the element to be removed
	 * @return the element that was removed if present; otherwise null
	 */
	public E remove(E item) {
		int remIndex = this.binarySearch(this.internalArray, item);

		if (remIndex < 0) {
			return null;
		}

		E remItem = this.internalArray[remIndex];

		this.internalArray[remIndex] = null;

		for (int j = remIndex + 1; j < this.internalArray.length; j++) {
			if (this.internalArray[j] == null) {
				return remItem;
			}
			E temp = this.internalArray[j - 1];
			this.internalArray[j - 1] = internalArray[j];
			this.internalArray[j] = temp;
		}
		return null;
	}

	/**
	 * Indicates whether this set contains an element equal to the given item.
	 *
	 * @param item - the object to be checked for containment in this set
	 * @return true if the item is contained in this set; otherwise false
	 */
	public boolean contains(E item) {
		int foundIndex = this.binarySearch(this.internalArray, item);
		return foundIndex >= 0;
	}

	/**
	 * Indicates whether this set contains all the elements in the collection. If
	 * the collection is empty, this returns true.
	 *
	 * @param items - a collection of objects to be checked for containment in this
	 *              set
	 * @return true if all items are contained in this set; otherwise false
	 */
	public boolean containsAll(Collection<? extends E> items) {
		for (E item : items) {
			if (!this.contains(item)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns all the elements of the set in an array.
	 *
	 * @return array containing all elements of the set
	 */
	public Object[] toArray() {
		Object[] copied = new Object[this.size()];
		for (int i = 0; i < this.size(); i++) {
			copied[i] = this.internalArray[i];
		}

		return copied;
	}

	/**
	 * @return the number of elements in the set
	 */
	public int size() {
		int arrayCount = 0;
		for (int i = 0; i < this.internalArray.length; i++) {
			if (this.internalArray[i] == null) {
				break;
			}
			arrayCount++;
		}
		return arrayCount;
	}

	/**
	 * @return true if this set is empty; otherwise false
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Removes all the elements from this set, resulting in an empty set.
	 */
	public void clear() {
		this.internalArray = this.createWithCapacity(DEFAULT_CAPACITY);
	}

	public class ArraySetIterator implements Iterator<E> {
		private int currentIndex = 0;
		private ArraySet<E> set;
		private boolean canRemove = false;

		public ArraySetIterator() {
		}

		@Override
		public boolean hasNext() {
			return this.currentIndex < ArraySet.this.size();
		}

		@Override
		public E next() {
			if (! this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.canRemove = true;
			E current = ArraySet.this.internalArray[this.currentIndex];
			this.currentIndex++;
			return current;
		}

		public void remove() {
			if (! this.canRemove) {
				throw new IllegalStateException();
			}

			for (int i = currentIndex; i < ArraySet.this.size(); i++) {
				ArraySet.this.internalArray[i-1] = ArraySet.this.internalArray[i];
			}

			ArraySet.this.internalArray[currentIndex] = null;
			currentIndex--;

			this.canRemove = false;

		}
	}

	@Override
	public Iterator<E> iterator() {
		return new ArraySetIterator();
	}
}
