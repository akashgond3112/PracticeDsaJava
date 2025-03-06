package main.meta.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	 * Solution for the "Longest Common Prefix" problem. This class finds the longest common prefix string amongst an
	 * array of strings.
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
		 * @param strs
		 * 		Array of strings
		 * @return The longest common prefix string
		 *
		 * 		Time Complexity: O(n log n + m) where n is the number of strings and m is the length of the shortest string
		 * 		Space Complexity: O(m) for the StringBuilder and character arrays
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
	 * Solution for the "Longest Common Prefix" problem using a Trie. This class finds the longest common prefix string
	 * amongst an array of strings.
	 */
	static class TrieSolution {
		static class TrieNode {
			public List<TrieNode> children;
			public int childCount;
			public boolean isLeaf;

			public TrieNode() {
				children = new ArrayList<>(26);
				for (int i = 0; i < 26; i++) {
					children.add(null);
				}
				childCount = 0;
				isLeaf = false;
			}
		}

		// If not present, inserts the key into the trie
		// If the key is a prefix of trie node, just mark leaf node
		static void insert(TrieNode root, String key) {
			TrieNode curr = root;
			for (char ch : key.toCharArray()) {
				int idx = ch - 'a';
				if (curr.children.get(idx) == null) {
					curr.children.set(idx, new TrieNode());
					curr.childCount++;
				}

				curr = curr.children.get(idx);
			}

			// mark last node as leaf
			curr.isLeaf = true;
		}

		// Perform a walk on the trie and return the
		// longest common prefix string
		static String walkTrie(TrieNode root, String s) {
			TrieNode curr = root;
			int i = 0;

			while (curr.childCount == 1 && !curr.isLeaf) {
				int idx = s.charAt(i) - 'a';
				i++;
				curr = curr.children.get(idx);
			}
			return s.substring(0, i);
		}

		// A Function that returns the longest common prefix
		// from the array of strings
		static String longestCommonPrefix(String[] arr) {
			TrieNode root = new TrieNode();

			// Insert all strings to the trie
			for (String s : arr)
				insert(root, s);

			// Perform a walk on the trie
			return walkTrie(root, arr[0]);
		}
	}
}
