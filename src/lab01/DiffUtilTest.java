package lab01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiffUtilTest {
	
	@Test
	public void testAllSameNum() {
	  int arr[] = new int[] { 3, 3, 3 };
	  assertEquals(0, DiffUtil.findSmallestDiff(arr));
	}
		
	@Test
	public void testNegAndPos() {
	  int arr[] = new int[] { 52, 4, -8, 0, -17 };
	  assertEquals(4, DiffUtil.findSmallestDiff(arr));
	}
	
	@Test
	public void testTooSmallArray() {
	  int arr[] = new int[0];
	  assertThrows(IllegalArgumentException.class, () -> { DiffUtil.findSmallestDiff(arr); });
	}
	
	@Test
	public void testPostiveSmallestDiff() {
	  int arr[] = new int[] {-3, 9, 100, 45, 99, 105};
	  assertEquals(1, DiffUtil.findSmallestDiff(arr));
	}
	
	@Test
	public void testAllNegative() {
	  int arr[] = new int[] {-5, -6, -10};
	  assertEquals(1, DiffUtil.findSmallestDiff(arr));
	}
	
	@Test
	public void testDiffEqualsZero() {
		int arr[] = new int[] {2, 6, 7, 2};
		assertEquals(0, DiffUtil.findSmallestDiff(arr));
	}
	
	@Test
	public void testRepeatingNumbers() {
		int arr[] = new int[] {2, 6, 2, 6, 2, 6};
		assertEquals(0, DiffUtil.findSmallestDiff(arr));
	}
	
	@Test
	public void testReverseInt() {
		Integer[] arr = new Integer[] {1, 4, 5, 2};
		assertArrayEquals(arr, new Integer[] {2, 5, 4, 1});
	}
	
	@Test
	public void testReverseString() {
		String[] arr = new String[] {"hello", "goodbye"};
		DiffUtil.reverse(arr);
		assertEquals(arr, "olleh");
	}
}
