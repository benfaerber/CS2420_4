package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class determines if two words are anagrams and finds the largest group of anagrams in a list of words.
 * Two words are anagrams if they contain the same letters in the same frequency.
 *
 * @author Benjamin Faerber and David Chen
 * @version 2025-06-03
 */
public class AnagramChecker {
	/**
	 * Sorts each character of a string in alphabetical order.
	 *
	 * @param value - String that we want to sort
	 * @return sorted string - String where each character is sorted alphabetically
	 */
	public static String sort(String value) {
		char[] charArr = value.toCharArray();
		Character[] characterArr = new Character[charArr.length];
		for (int i = 0; i < charArr.length; i++) {
			characterArr[i] = charArr[i];
		}
		AnagramChecker.insertionSort(characterArr, Comparator.naturalOrder());
		StringBuilder sb = new StringBuilder();
		for (Character character : characterArr) {
			sb.append(character);
		}

		return sb.toString();
	}

	/**
	 * Perform insertion sort by default comparator.
	 *
	 * @param array - T[] of array that we want to sort
	 * @param cmp - Default comparator
	 * @param <T> - Type of object
	 */
	public static <T> void insertionSort(T[] array, Comparator<? super T> cmp) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				T currentUnsortStar = array[j];
				T currentUnsortEnd = array[j - 1];
				if (cmp.compare(currentUnsortStar, currentUnsortEnd) < 0) {
					T temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
	}

	/**
	 * See if 2 strings are anagrams (if string a can be rearranged into string b)
	 *
	 * @param a The first string to compare
	 * @param b The second string to compare
	 * @return true if the following strings are anagrams
	 */
	public static boolean areAnagrams(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}
 		
		String aSorted = AnagramChecker.sort(a.toLowerCase());
		String bSorted = AnagramChecker.sort(b.toLowerCase());	
		
		return aSorted.equals(bSorted);
	}


	/**
	 * Find the largest group of anagrams in a list of potential anagrams
	 *
	 * @param anagrams - String array of anagrams
	 * @return The largest group of anagrams -String[]
	 */
	public static String[] getLargestAnagramGroup(String[] anagrams) {		
		String[][] groups = new String[anagrams.length][anagrams.length];
		
		for (int i = 0; i < groups.length; i++) {
			for (int j = 0; j < groups[i].length; j++) {
				if (i == j) {
					continue;
				}
				
				String a = anagrams[i];
				String b = anagrams[j];
				
				if (AnagramChecker.areAnagrams(a, b)) {
					for (int placeAt = 0; placeAt < groups[i].length; placeAt++) {
						if (placeAt == 0 && groups[i][0] == null) {
							groups[i][0] = a;
							groups[i][1] = b;
							break;
						}
						
						if (groups[i][placeAt] == null) {
							groups[i][placeAt] = b;
							break;
						}
					}
				}
				
			}
		}
		
//		Arrays.sort(groups, (Comparator<String[]>) (String[] arrA, String[] arrB) -> {
//			int sizeA = 0;
//			int sizeB = 0;
//			
//			for (String aItem : arrA) {
//				if (aItem != null) {
//					sizeA++;
//				}
//			}
//			
//			for (String bItem : arrB) {
//				if (bItem != null) {
//					sizeB++;
//				}
//			}
//			
//			return Integer.compare(sizeA, sizeB);
//		});
				
		AnagramChecker.insertionSort(groups, (Comparator<String[]>) (String[] arrA, String[] arrB) -> {
			int sizeA = 0;
			int sizeB = 0;
			
			for (String aItem : arrA) {
				if (aItem != null) {
					sizeA++;
				}
			}
			
			for (String bItem : arrB) {
				if (bItem != null) {
					sizeB++;
				}
			}
			
			return Integer.compare(sizeA, sizeB);
		});
		
		if (groups.length == 0) {
			return new String[] {};
		}
		
		String[] toTrim = groups[groups.length - 1];
		int largest = 0;
		for (largest = 0; largest < toTrim.length; largest++) {
			if (toTrim[largest] == null) {
				break;
			}
		}
		
		String[] trimmed = new String[largest];
		for (int i = 0; i < trimmed.length; i++) {
			trimmed[i] = toTrim[i];
		}
		return trimmed;
	}

	/**
	 * Find the largest group of anagrams in a new line delmited text file
	 *
	 * @param filename - A new line-delimited text file to check
	 * @return An array of the largest anagram group
	 */
	public static String[] getLargestAnagramGroup(String filename) {
		String[] lines = AnagramChecker.readFileLines(filename);
		return AnagramChecker.getLargestAnagramGroup(lines);
	}

	/**
	 * Private method to read a file and return a list of lines
	 *
	 * @param filename - the name of the file to open (starting in the project root)
	 * @return a list of lines or empty on failure
	 */
	public static String[] readFileLines(String filename) {
		StringBuilder sb = new StringBuilder();
		try {
			File handler = new File("./" + filename);
			try (Scanner reader = new Scanner(handler)) {
				while (reader.hasNextLine()) {
					if (!sb.isEmpty()) {
						sb.append("\n");	
					}
					sb.append(reader.nextLine());
				}
			}
		} catch (FileNotFoundException e) {
			return new String[] {};
		}
		
		if (sb.isEmpty()) {
			return new String[] {};
		}

		return sb.toString().split("\n");
	}
}
