package lab05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the use of iterators in Assignment 3's 
 * ArraySet, for Lab05.
 * 
 * @author CS 2420 course staff
 * @version June 6, 2023
 */
public class IteratorTester {
	
	private ArraySet<Integer> smallSetOfEvens;
	private ArraySet<Integer> largeSet;
	private Iterator<Integer> smallIterator;

	@BeforeEach
	void setUp() throws Exception {
		smallSetOfEvens = new ArraySet<Integer>();
		for(int half = 1; half <= 10; half++) 
			smallSetOfEvens.add(half*2); 
		// priority queue's backing array should be { 2, 4, 6, ..., 16, 18, 20 }
		
		smallIterator = smallSetOfEvens.iterator();
		
		largeSet = new ArraySet<Integer>();
		for(int i = 1; i <= 100; i++)
			largeSet.add(i);
		// priority queue's backing array should be { 1, 2, 3, ..., 98, 99, 100 }
	}

	@Test
	public void iterateOverEvenSet() {
		for(int expected = 2; expected <= 20; expected += 2) 
			assertEquals(expected, (int)smallIterator.next());
	}
	
	@Test
	public void expectedNoSuchElementThrown() {
		for(int count = 0; count < 10; count++) {
			smallIterator.next();
		}
		assertThrows(NoSuchElementException.class, () -> { smallIterator.next(); });
	}
	
	@Test
	public void hasNextReturnsTrue() {
		assertTrue(smallIterator.hasNext());
	}
	
	@Test
	public void hasNextReturnsFalseAtEnd() {
		for(int count = 0; count < 10; count++) {
			smallIterator.next();
		}
		assertFalse(smallIterator.hasNext());
	}
	
	@Test
	public void removeElement() {
		for(int i = 0; i < 10; i++) 
			smallIterator.next();
		assertTrue(smallSetOfEvens.contains(20));
		smallIterator.remove();
		assertFalse(smallSetOfEvens.contains(20));
	}
	
	@Test
	public void removeWithoutCallToNext() {
		assertThrows(IllegalStateException.class, () -> { smallIterator.remove(); });
	}
	
	@Test
	public void removeEverything() {
		while(smallIterator.hasNext()) {
			smallIterator.next();
			smallIterator.remove();
		}
		assertEquals(0, smallSetOfEvens.size());
	}
	
	@Test
	public void removeEveryOtherElement() {
		Iterator<Integer> iterator = largeSet.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			iterator.remove();
			if(iterator.hasNext()) {
				iterator.next();
			} 
		}
		assertEquals(50, largeSet.size());
		
		// set's backing array should now be { 2, 4, 6, ..., 96, 98, 100 }
		Object[] arr = largeSet.toArray();
		for(int i = 0; i < arr.length; i++) {
			assertEquals((i + 1) * 2, ((Integer)arr[i]).intValue());
		}
	}
}