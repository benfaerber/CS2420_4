package assign04;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for ArraySet.
 * 
 * @author Benjamin Faerber and David Chen
 * @version 2025-05-29
 */
public class AnagramCheckerTest {
	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testStringSort(){
		String smallString = "ddacbf";
		String smallExpected = "abcddf";
		assertEquals(smallExpected, AnagramChecker.sort(smallString));
		String largeString = "cccccxcccccbbbbfbbbbbaaaaaaaa";
		String largeExpected = "aaaaaaaabbbbbbbbbccccccccccfx";
		assertEquals(largeExpected, AnagramChecker.sort(largeString));
	}
	
	@Test
	void testStringSortCases(){
		String smallString = "aIF";
		assertEquals("FIa", AnagramChecker.sort(smallString));
	}


	@Test
	void testInsertionSort(){
		Integer[] array = new Integer[]{3, 15, 6, 30, 20};
		AnagramChecker.insertionSort(array, (Integer a, Integer b) -> Integer.compare(a,b));
		assertArrayEquals(new Integer[]{3, 6, 15, 20, 30}, array);

		// Test duplicates
		Integer[] arrayWithDup = new Integer[]{3, 15, 6, 30, 20, 6, 3};
		AnagramChecker.insertionSort(arrayWithDup, (Integer a, Integer b) -> Integer.compare(a,b));
		assertArrayEquals(new Integer[]{3, 3, 6, 6, 15, 20, 30}, arrayWithDup);

		// Test generic
		String[] names = new String[]{"David", "Ben", "Joe", "Monica", "Ross", "Chandler", "Phoebe", "Rachel", "joe"};
		AnagramChecker.insertionSort(names, (String a, String b) -> a.compareTo(b));
		assertArrayEquals(new String[]{"Ben", "Chandler", "David", "Joe", "Monica", "Phoebe", "Rachel", "Ross", "joe"}, names);

		// Test larger size
	}
	
	
	@Test
	void testAreAnagrams() {
		// Unequal lengths will fail
		assertFalse(AnagramChecker.areAnagrams("asdfasdf", "abc"));
		
		// satin and stain are anagrams
		assertTrue(AnagramChecker.areAnagrams("satin", "stain"));
		assertTrue(AnagramChecker.areAnagrams("sAtin", "sTain"));
		
	}
	
	@Test
	void testLargestWordList() {
		String[] expectedGroup = new String[] {
			"traces", "carets", "Caters", "caster", "crates", "Reacts", "recast"
		};
		
		String[] gotGroup = AnagramChecker.getLargestAnagramGroup("sample_word_list.txt");
				
		for (String a : gotGroup) {
			System.out.println("Got Group: " + a);
		}
		
		assertArrayEquals(expectedGroup, gotGroup);
	}
}