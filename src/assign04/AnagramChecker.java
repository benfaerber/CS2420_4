package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class AnagramChecker {
	public static String sort(String value) {
		Comparator<Character> cmp = new Comparator<>() {			
			@Override
			public int compare(Character a, Character b) {
				return a.compareTo(b);
			}
		};

		char[] charArr = value.toCharArray();
		Character[] characterArr = new Character[charArr.length];
		for (int i = 0; i < charArr.length; i++) {
			characterArr[i] = charArr[i];
		}
		AnagramChecker.insertionSort(characterArr,cmp);
		StringBuilder sb = new StringBuilder();
		for (Character character : characterArr) {
			sb.append(character);
		}

		return sb.toString();
	}
	
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
	
	public static boolean areAnagrams(String a, String b) {
		if (a.length() != b.length()) {
			return false;
		}
 		
		String aSorted = AnagramChecker.sort(a.toLowerCase());
		String bSorted = AnagramChecker.sort(b.toLowerCase());
	
		
		return aSorted.equals(bSorted);
	}
	
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
	
	public static String[] getLargestAnagramGroup(String filename) {
		String[] lines = AnagramChecker.readFileLines(filename);
		return AnagramChecker.getLargestAnagramGroup(lines);
	}
	
	private static String[] readFileLines(String filename) {
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
		
		System.out.println(sb.toString());
		
		return sb.toString().split("\n");
	}
}
