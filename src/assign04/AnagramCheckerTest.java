package assign04;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * This class contains tests for ArraySet.
 * 
 * @author Benjamin Faerber and David Chen
 * @version 2025-05-29
 */
public class AnagramCheckerTest {
	String smallAllLowerString, largeString, smallWithCaps;
	Integer[] smallIntegerArr, largeIntegerArr, smallIntegerArrWithDup;
	String[] smallNameArr;

	@BeforeEach
	void setUp() throws Exception {
		smallAllLowerString = "ddacbf";
		smallWithCaps = "BDDacf";
		largeString = "cccccxcccccbbbbfbbbbbaaaaaaaa";
		smallIntegerArr = new Integer[]{3, 15, 6, 30, 20};
		smallIntegerArrWithDup = new Integer[]{3, 15, 6, 30, 20, 6, 3};
		smallNameArr = new String[]{"David", "Ben", "Joe", "Monica", "Ross", "Chandler", "Phoebe", "Rachel", "joe"};
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testStringSort() {
		// Can sort properly with caps
		String smallwithCapsExpected = "BDDacf";
		assertEquals(smallwithCapsExpected, AnagramChecker.sort(smallWithCaps));

		// The test from gradeScope
		String gradeScopeExpected = "FIa";
		assertEquals(gradeScopeExpected, AnagramChecker.sort("aFI"));

		// Sort larger string
		String largeExpected = "aaaaaaaabbbbbbbbbccccccccccfx";
		assertEquals(largeExpected, AnagramChecker.sort(largeString));


		// small sort all lowercase
		assertEquals("abcddf", AnagramChecker.sort(smallAllLowerString));
	}

	@Test
	void testInsertionSort() {
		AnagramChecker.insertionSort(smallIntegerArr, (Integer a, Integer b) -> Integer.compare(a, b));
		assertArrayEquals(new Integer[]{3, 6, 15, 20, 30}, smallIntegerArr);

		// Test duplicates
		AnagramChecker.insertionSort(smallIntegerArrWithDup, (Integer a, Integer b) -> Integer.compare(a, b));
		assertArrayEquals(new Integer[]{3, 3, 6, 6, 15, 20, 30}, smallIntegerArrWithDup);

		// Test generic
		AnagramChecker.insertionSort(smallNameArr, (String a, String b) -> a.compareTo(b));
		assertArrayEquals(new String[]{"Ben", "Chandler", "David", "Joe", "Monica", "Phoebe", "Rachel", "Ross", "joe"}, smallNameArr);

		// Test larger size
		Random random = new Random();
		Integer[] largeIntegerArr = new Integer[500];
		for (int i = 0; i < largeIntegerArr.length; i++) {
			largeIntegerArr[i] = random.nextInt(100);
		}
		AnagramChecker.insertionSort(largeIntegerArr, (Integer a, Integer b) -> Integer.compare(a, b));
		// Now that we've populated, lets make sure its sorted
		for (int i = 0; i < largeIntegerArr.length - 1; i++) {
			int shouldBeLower = largeIntegerArr[i];
			int shouldBeHigher = largeIntegerArr[i + 1];
			assertTrue(shouldBeHigher > shouldBeLower || shouldBeLower == shouldBeHigher);
		}
	}


	@Test
	void testAreAnagrams() {
		// Unequal lengths will fail
		assertFalse(AnagramChecker.areAnagrams("asdfasdf", "abc"));
		// satin and stain are anagrams
		assertTrue(AnagramChecker.areAnagrams("satin", "stain"));
		assertTrue(AnagramChecker.areAnagrams("sAtin", "sTain"));
		
		// These are not anagrams
		assertFalse(AnagramChecker.areAnagrams("spain", "stain"));
	}

	@Test
	void testLargestWordList() {
		// Testing sample txt
		String[] expectedGroup = new String[]{
				"traces", "carets", "Caters", "caster", "crates", "Reacts", "recast"
		};
		String[] gotGroup = AnagramChecker.getLargestAnagramGroup("sample_word_list.txt");
		assertArrayEquals(expectedGroup, gotGroup);

		// Testing empty txt/txt with no anagram
		String[] emptyGroup = AnagramChecker.getLargestAnagramGroup("empty.txt");
		assertArrayEquals(emptyGroup, new String[]{});

		String[] noAnagramGroup = AnagramChecker.getLargestAnagramGroup("no_anagram.txt");
		assertArrayEquals(noAnagramGroup, new String[]{});
	}

	@Test
	void testLargestAnagramGroupWithArray() {
		String[] partialWordList = new String[] {
				"carets",
				"Caller",
				"eat",
				"cellar",
				"recall",
				"Stab",
				"stale",
				"tabs",
				"recast",
				"darters",
				"Gallery",
				"starred"
		};
		String[] gotGroup = AnagramChecker.getLargestAnagramGroup(partialWordList);
		assertArrayEquals(new String[] {"recall", "Caller", "cellar"}, gotGroup);

		String[] noAnagramsArray = new String[] {
				"sheep",
				"horse",
				"dog",
				"rat",
		};
		assertArrayEquals(new String[]{}, AnagramChecker.getLargestAnagramGroup(noAnagramsArray));


	}
}