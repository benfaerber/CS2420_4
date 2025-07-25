package assign08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a "dictionary" of strings using a binary search tree and offers
 * methods for spell-checking documents.
 *
 * @author CS 2420 course staff and Benjamin Faerber and David Chen
 * @version March 6, 2025
 */
public class SpellChecker {

	private final BinarySearchTree<String> dictionary;

	/**
	 * Default constructor--creates empty dictionary.
	 */
	public SpellChecker() {
		dictionary = new BinarySearchTree<String>();
	}

	/**
	 * Creates dictionary from a list of words.
	 *
	 * @param words - the List of Strings used to build the dictionary
	 */
	public SpellChecker(List<String> words) {
		this();
		this.buildDictionary(words);
	}

	/**
	 * Creates dictionary from a file.
	 *
	 * @param dictionaryFile - the File that contains Strings used to build the
	 *                        dictionary
	 */
	public SpellChecker(File dictionaryFile) {
		this();
		this.buildDictionary(readFromFile(dictionaryFile));
	}

	/**
	 * Add a word to the dictionary.
	 *
	 * @param word - the String to be added to the dictionary
	 */
	public void addToDictionary(String word) {
		if (!dictionary.contains(word)){
			dictionary.add(word);
		}
	}

	/**
	 * Spell-checks a document against the dictionary.
	 * Makes a list of words from the document that are not in the dictionary.
	 *
	 * @param documentFile - the File that contains Strings to be looked up in the
	 *                      dictionary
	 * @return a List of misspelled words (i.e., words not in the dictionary)
	 */
	public List<String> spellCheck(File documentFile) {
		List<String> wordsToCheck = readFromFile(documentFile);
		List<String> misspelledWords = new ArrayList<String>();

		for (String word : wordsToCheck) {
			if (!dictionary.contains(word)) {
				misspelledWords.add(word);
			}
		}

		return misspelledWords;
	}

	/**
	 * Fills in the dictionary with the input list of words.
	 *
	 * @param words - the List of Strings to be added to the dictionary
	 */
	private void buildDictionary(List<String> words) {
		for (String word : words) {
			dictionary.add(word);
		}
	}

	/**
	 * Returns a list of the words contained in the specified file. (Note that
	 * symbols, digits, and spaces are ignored; and all words are converted
	 * to lower case.)
	 *
	 * @param file - the File to be read
	 * @return a List of the Strings in the input file
	 */
	private List<String> readFromFile(File file) {
		ArrayList<String> words = new ArrayList<String>();

		try {
			Scanner fileInput = new Scanner(file);

			/*
			 * The scanner can be directed how to delimit (or divide) the input. By default,
			 * it uses whitespace as the delimiter. The following statement specifies
			 * anything other than alphabetic characters as a delimiter (so that punctuation
			 * and such will be ignored). The string argument is a regular expression that
			 * specifies "anything but an alphabetic character". You need not understand any
			 * of this for the assignment.
			 */
			fileInput.useDelimiter("\\s*[^a-zA-Z]\\s*");

			while (fileInput.hasNext()) {
				String s = fileInput.next();
				if (!s.isEmpty())
					words.add(s.toLowerCase());
			}

			fileInput.close();

		}
		catch(FileNotFoundException e) {
			System.err.println("File " + file + " cannot be found.");
		}

		return words;
	}
}
