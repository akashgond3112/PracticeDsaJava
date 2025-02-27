package main.meta.easy;

import java.util.Arrays;

/**
 * <pre>
 * 14. Longest Common Prefix
 * Easy
 * Topics
 * Companies
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lowercase English letters if it is non-empty.
 * Seen this question in a real interview before?
 * Topics
 * String
 * Trie
 * </pre>
 */
public class LongestCommonPrefix {
	/**
	 * Solution for the "Longest Common Prefix" problem.
	 * This class finds the longest common prefix string amongst an array of strings.
	 */
	static class Solution {
		/**
		 * Finds the longest common prefix string amongst an array of strings.
		 *
		 * Algorithm:
		 * 1. Sort the array of strings lexicographically
		 * 2. Compare only the first and last strings in the sorted array
		 * 3. The common prefix of these two strings will be the common prefix for all strings
		 *
		 * @param strs Array of strings
		 * @return The longest common prefix string
		 *
		 * Time Complexity: O(n log n + m) where n is the number of strings and m is the length of the shortest string
		 * Space Complexity: O(m) for the StringBuilder and character arrays
		 */
		public String longestCommonPrefix(String[] strs) {
			// Handle edge case
			if (strs == null || strs.length == 0) {
				return "";
			}

			StringBuilder sb = new StringBuilder();

			// Sort the array lexicographically
			Arrays.sort(strs);

			// After sorting, we only need to compare the first and last strings
			char[] first = strs[0].toCharArray();
			char[] last = strs[strs.length - 1].toCharArray();

			// Compare characters until we find a mismatch or reach the end of the shorter string
			for (int i = 0; i < first.length && i < last.length; i++) {
				if (first[i] != last[i]) {
					break;
				}
				sb.append(first[i]);
			}

			return sb.toString();
		}
	}

	/**
	 * Solution for the "Longest Common Prefix" problem using a Trie.
	 * This class finds the longest common prefix string amongst an array of strings.
	 */
	static class TrieSolution {
		/**
		 * Trie node class to represent each character in the prefix tree.
		 */
		private static class TrieNode {
			private final TrieNode[] children;
			private boolean isEndOfWord;
			private int count;

			public TrieNode() {
				this.children = new TrieNode[26]; // Assuming only lowercase English letters
				this.isEndOfWord = false;
				this.count = 0;
			}
		}

		/**
		 * Inserts a word into the trie.
		 *
		 * @param root The root of the trie
		 * @param word The word to insert
		 */
		private void insert(TrieNode root, String word) {
			TrieNode current = root;

			for (char c : word.toCharArray()) {
				int index = c - 'a';
				if (current.children[index] == null) {
					current.children[index] = new TrieNode();
				}
				current = current.children[index];
				current.count++;
			}

			current.isEndOfWord = true;
		}

		/**
		 * Finds the longest common prefix using a trie.
		 *
		 * @param strs Array of strings
		 * @return The longest common prefix string
		 *
		 * Time Complexity: O(m*n) where n is the number of strings and m is the total number of characters
		 * Space Complexity: O(m) for the trie structure
		 */
		public String longestCommonPrefix(String[] strs) {
			if (strs == null || strs.length == 0) {
				return "";
			}

			// Build trie with all strings
			TrieNode root = new TrieNode();
			for (String str : strs) {
				if (str.isEmpty()) {
					return ""; // If any string is empty, there's no common prefix
				}
				insert(root, str);
			}

			// Find the longest common prefix
			StringBuilder sb = new StringBuilder();
			TrieNode current = root;

			// Traverse the trie until we reach a node that doesn't have exactly
			// the same number of traversals as the number of strings
			while (true) {
				// Count how many children this node has
				TrieNode singleChild = null;
				int childCount = 0;

				for (TrieNode child : current.children) {
					if (child != null) {
						childCount++;
						singleChild = child;
					}
				}

				// If this node has exactly one child, and that child has been traversed
				// by all strings, continue down that path
				if (childCount == 1 && singleChild.count == strs.length && !current.isEndOfWord) {
					char c = 0;
					// Find which character this child represents
					for (int i = 0; i < 26; i++) {
						if (current.children[i] == singleChild) {
							c = (char) (i + 'a');
							break;
						}
					}
					sb.append(c);
					current = singleChild;
				} else {
					break;
				}

				// If we've reached the end of any word, we can't go further
				if (current.isEndOfWord) {
					break;
				}
			}

			return sb.toString();
		}
	}
}
