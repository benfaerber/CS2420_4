package assign03;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for ArraySet.
 * 
 * @author Benjamin Faerber and David Chen
 * @version 2025-05-29
 */
public class ArraySetTest {
	@BeforeEach
	void setUp() throws Exception {

	}
	
	@Test
	public void testToArray() {
		// 2 5 8 10 0 0 0 0
		// We add 9
		// 2 5 8 9 10 0 0 0
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(2);

		set.add(5);

		set.add(8);

		set.add(10);

		set.add(9);

		assertArrayEquals(new Integer[] { 2, 5, 8, 9, 10, null, null, null, null, null }, set.viewInternalForTesting());
		assertArrayEquals(new Integer[] { 2, 5, 8, 9, 10 }, set.toArray());
	}

	@Test
	public void testAddItem() {
		// 2 5 8 10 0 0 0 0
		// We add 9
		// 2 5 8 9 10 0 0 0
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(2);

		set.add(5);

		set.add(8);

		set.add(10);

		set.add(9);

		assertArrayEquals(new Integer[] { 2, 5, 8, 9, 10 }, set.toArray());
	}

	@Test
	public void testAddUnordered() {
		// 2 5 8 10 0 0 0 0
		// We add 9
		// 2 5 8 9 10 0 0 0
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(5);

		set.add(3);

		set.add(2);

		set.add(1);

		assertArrayEquals(new Integer[] { 1, 2, 3, 5 }, set.toArray());
	}

	@Test
	public void testAddItemWith1Null() {
		// 2 5 8 10 0 0 0 0
		// We add 9
		// 2 5 8 9 10 0 0 0
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(1);

		set.add(2);

		set.add(3);

		set.add(4);

		set.add(5);
		set.add(6);
		set.add(7);
		set.add(8);
		set.add(10);
		set.add(9);

		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, set.toArray());
	}

	@Test
	void testRemove() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(2);

		set.add(5);

		set.add(8);

		set.add(10);

		set.add(11);
		set.add(12);

		set.remove(10);

		assertArrayEquals(new Integer[] { 2, 5, 8, 11, 12, null, null, null, null, null },
				set.viewInternalForTesting());

		set.remove(5);
		assertArrayEquals(new Integer[] { 2, 8, 11, 12, null, null, null, null, null, null },
				set.viewInternalForTesting());
	}

	@Test
	void testContains() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(2);

		set.add(5);

		set.add(8);

		set.add(10);

		set.add(11);
		set.add(12);

		set.remove(10);

		assertTrue(set.contains(11));
		assertTrue(set.contains(2));

		assertFalse(set.contains(1234));

	}

	@Test
	void testContainsAll() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(2);
		set.add(5);
		set.add(8);
		set.add(10);
		set.add(11);
		set.add(12);
		set.remove(10);

		ArrayList<Integer> containsAll1 = new ArrayList<>();
		containsAll1.add(2);
		containsAll1.add(5);
		containsAll1.add(8);
		assertTrue(set.containsAll(containsAll1));

		ArrayList<Integer> containsAll2 = new ArrayList<>();
		containsAll2.add(2);
		containsAll2.add(6);
		containsAll2.add(8);
		assertFalse(set.containsAll(containsAll2));

		ArrayList<Integer> containsAllEmpty = new ArrayList<>();
		assertTrue(set.containsAll(containsAllEmpty));
	}

	@Test
	public void testSize() {
		// 2 5 8 10 0 0 0 0
		// We add 9
		// 2 5 8 9 10 0 0 0
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.add(4);

		assertEquals(4, set.size());

		// After adding duplicates, it should be the same size
		set.add(4);
		set.add(4);
		assertEquals(4, set.size());

		set.add(10);
		assertEquals(5, set.size());
	}

	@Test
	public void testResize() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		for (int i = 0; i < 15; i++) {
			set.add(i);
		}

		// Should be 15 long
		assertEquals(15, set.size());
		// But have capacity for 20
		assertEquals(20, set.viewInternalCapacity());
		
		for (int i = 0; i < 15; i++) {
			set.add(i + 15);
		}
		// Should be 15 long
		assertEquals(30, set.size());
		// But have capacity for 20
		assertEquals(40, set.viewInternalCapacity());

	}

	@Test
	public void testIsEmpty() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		assertTrue(set.isEmpty());

		set.add(4);
		assertFalse(set.isEmpty());

		set.remove(4);
		assertTrue(set.isEmpty());
	}

	@Test
	public void testClear() {
		ArraySet<Integer> set = new ArraySet<Integer>();
		set.add(4);
		set.add(2);
		set.add(3);
		assertEquals(3, set.size());

		set.clear();
		assertTrue(set.isEmpty());
	}

	@Test
	public void testAddItemWithString() {
		ArraySet<String> set = new ArraySet<String>();
		set.add("d");

		set.add("b");

		set.add("a");

		set.add("c");
		assertArrayEquals(new String[] { "a", "b", "c", "d" }, set.toArray());
	}

	@Test
	public void testAddItemWithStringCustomComparator() {
		ArraySet<String> set = new ArraySet<String>((String a, String b) -> Integer.compare(a.length(), b.length()));
		set.add("ddd");

		set.add("dd");

		set.add("ddddd");

		set.add("a");
		assertArrayEquals(new String[] { "a", "dd", "ddd", "ddddd" }, set.toArray());
	}
}