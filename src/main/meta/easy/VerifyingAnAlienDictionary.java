package main.meta.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <pre>
 * 953. Verifying an Alien Dictionary
 * Easy
 * Topics
 * Companies
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 *
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 *
 * Example 1:
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * All characters in words[i] and order are English lowercase letters.
 * Topics
 * Array
 * Hash Table
 * String
 * </pre>
 */
public class VerifyingAnAlienDictionary {

	public static class SolutionBruteForce {
		/**
		 * Verifies if words are sorted lexicographically according to an alien dictionary. This solution creates a
		 * custom comparator based on the alien order and checks if sorting the words with this comparator results in
		 * the same order.
		 *
		 * @param words
		 * 		Array of strings to verify
		 * @param order
		 * 		String representing the alien dictionary order
		 * @return true if words are sorted according to the alien dictionary, false otherwise
		 *
		 * 		Time Complexity: O(n log n * m)
		 * 		- n: number of words
		 * 		- m: average length of words
		 * 		- Arrays.sort() uses O(n log n) comparisons
		 * 		- Each comparison takes O(m) time
		 *
		 * 		Space Complexity: O(n + k)
		 * 		- O(k) for the orderIndex array (k=26 for lowercase English letters)
		 * 		- O(n) for the sortedWords array
		 */
		public boolean isAlienSorted(String[] words, String order) {
			// Create a mapping of each character to its position in the alien order
			int[] orderIndex = new int[26];
			for (int i = 0; i < order.length(); i++)
				orderIndex[order.charAt(i) - 'a'] = i;

			// Define a custom comparator based on the alien order
			Comparator<String> compare = (w1, w2) -> {
				// Compare characters at the same position in both words
				for (int i = 0; i < Math.min(w1.length(), w2.length()); i++) {
					if (w1.charAt(i) != w2.charAt(i))
						return orderIndex[w1.charAt(i) - 'a'] - orderIndex[w2.charAt(i) - 'a'];
				}
				// If one word is a prefix of the other, the shorter word comes first
				return w1.length() - w2.length();
			};

			// Make a copy of the original array and sort it
			String[] sortedWords = words.clone();
			Arrays.sort(sortedWords, compare);

			// Check if the sorted order matches the original order
			return Arrays.equals(words, sortedWords);
		}
	}

	public static class SolutionOptimal {
		/**
		 * Verifies if words are sorted lexicographically according to an alien dictionary. This solution directly
		 * checks if each adjacent pair of words is in the correct order.
		 *
		 * @param words
		 * 		Array of strings to verify
		 * @param order
		 * 		String representing the alien dictionary order
		 * @return true if words are sorted according to the alien dictionary, false otherwise
		 *
		 * 		Time Complexity: O(n * m)
		 * 		- n: number of words
		 * 		- m: average length of words
		 * 		- We check each pair of adjacent words once
		 *
		 * 		Space Complexity: O(k)
		 * 		- O(k) for the orderIndex array (k=26 for lowercase English letters)
		 */
		public boolean isAlienSorted(String[] words, String order) {
			// Create a mapping of each character to its position in the alien order
			int[] orderIndex = new int[26];
			for (int i = 0; i < order.length(); i++)
				orderIndex[order.charAt(i) - 'a'] = i;

			// Check each adjacent pair of words
			for (int i = 0; i < words.length - 1; i++) {
				String w1 = words[i], w2 = words[i + 1];

				// Compare characters at each position
				for (int j = 0; j < w1.length(); j++) {
					// If w2 is shorter than w1 and all characters matched so far,
					// then w2 should come before w1
					if (j == w2.length())
						return false;

					// If characters differ, check if they are in the correct order
					if (w1.charAt(j) != w2.charAt(j)) {
						if (orderIndex[w1.charAt(j) - 'a'] > orderIndex[w2.charAt(j) - 'a']) {
							return false;
						}
						break; // No need to check further characters
					}
				}
			}
			return true;
		}
	}
}
