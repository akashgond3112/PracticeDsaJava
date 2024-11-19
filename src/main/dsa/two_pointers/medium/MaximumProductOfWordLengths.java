package main.dsa.two_pointers.medium;

/*
318. Maximum Product of Word Lengths
Medium

Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

Example 1:

Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: words = ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.

Constraints:

2 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists only of lowercase English letters.
*/

public class MaximumProductOfWordLengths {
	public static int maxProduct(String[] words) {
		int n = words.length;
		int[] bitmasks = new int[n];
		int[] lengths = new int[n];

		// Create bitmasks for each word
		for (int i = 0; i < n; i++) {
			String word = words[i];
			int bitmask = 0;
			for (char c : word.toCharArray()) {
				bitmask |= 1 << (c - 'a');
			}
			bitmasks[i] = bitmask;
			lengths[i] = word.length();
		}

		// Calculate the maximum product of lengths of two words with no common letters
		int maxProduct = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if ((bitmasks[i] & bitmasks[j]) == 0) { // No common letters
					maxProduct = Math.max(maxProduct, lengths[i] * lengths[j]);
				}
			}
		}

		return maxProduct;
	}

	public static void main(String[] args) {
		String[] words1 = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
		System.out.println(maxProduct(words1)); // Output: 16

		String[] words2 = {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
		System.out.println(maxProduct(words2)); // Output: 4

		String[] words3 = {"a", "aa", "aaa", "aaaa"};
		System.out.println(maxProduct(words3)); // Output: 0
	}

}