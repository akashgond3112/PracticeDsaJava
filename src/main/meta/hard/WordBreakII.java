package main.meta.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 * 140. Word Break II
 * Hard
 * Topics
 * Companies
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * Example 1:
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 *
 * Example 2:
 * Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: []
 *
 * Constraints:
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 * Input is generated in a way that the length of the answer doesn't exceed 105.
 * Topics
 * Array
 * Hash Table
 * String
 * Dynamic Programming
 * Backtracking
 * Trie
 * Memoization
 * </pre>
 */
public class WordBreakII {

	public static class SolutionUsingBacktracking {
		private Set<String> wordSet;  // Stores dictionary words for O(1) lookup
		private List<String> res;     // Stores final results

		// Main method to solve the word break problem

		/**
		 * Breaks a string into space-separated dictionary words.
		 *
		 * @param s
		 * 		The input string to be broken
		 * @param wordDict
		 * 		List of dictionary words
		 * @return All possible space-separated sentences using words from wordDict
		 *
		 * 		Time Complexity: O(m) for creating the HashSet where m is the total length of all words in wordDict Space
		 * 		Complexity: O(m) for storing the dictionary words in HashSet
		 */
		public List<String> wordBreak(String s, List<String> wordDict) {
			wordSet = new HashSet<>(wordDict);  // O(m) time and space
			res = new ArrayList<>();
			List<String> cur = new ArrayList<>();
			backtrack(s, 0, cur);
			return res;
		}

		/**
		 * Recursively builds all possible sentences by trying different word combinations.
		 *
		 * @param s
		 * 		The input string
		 * @param i
		 * 		Current position in the string
		 * @param cur
		 * 		Current list of words forming a potential sentence
		 *
		 * 		Time Complexity: O(n * 2^n) in worst case where n is string length
		 * 		- We potentially explore every substring starting at position i
		 * 		- For each position, we have two choices (include or exclude)
		 * 		- This creates a decision tree with potentially 2^n nodes
		 * 		- Each path can be up to length n Space Complexity: O(n) for recursion stack + O(2^n) for storing all
		 * 		possible solutions
		 */
		private void backtrack(String s, int i, List<String> cur) {
			// Base case: reached end of string
			if (i == s.length()) {
				res.add(String.join(" ", cur));  // Create sentence with spaces and add to results
				return;
			}

			// Try all possible words starting at position i
			for (int j = i; j < s.length(); j++) {
				String w = s.substring(i, j + 1);  // O(j-i+1) time for substring
				if (wordSet.contains(w)) {  // O(1) time for HashSet lookup
					cur.add(w);  // Add current word to our path
					backtrack(s, j + 1, cur);  // Recursively process remaining string
					cur.removeLast();  // Backtrack (remove last word to try other combinations)
				}
			}
		}
	}

	/**
	 * Word Break solution using backtracking with memoization
	 *
	 * Time Complexity: O(n^3)
	 * - We process each position in the string once: O(n)
	 * - For each position, we check all possible substrings: O(n)
	 * - Each substring operation costs O(n)
	 * - With memoization, we avoid the exponential explosion
	 *
	 * Space Complexity: O(n^2 + m)
	 * - Dictionary storage: O(m) where m is total length of all words
	 * - Memoization cache: O(n^2) for storing results for all possible substrings
	 * - Recursion stack: O(n)
	 */
	public static class SolutionWithMemoization {
		private Set<String> wordSet;
		private Map<Integer, List<String>> memo;

		public List<String> wordBreak(String s, List<String> wordDict) {
			wordSet = new HashSet<>(wordDict);
			memo = new HashMap<>();
			return backtrackWithMemo(s, 0);
		}

		/**
		 * Recursively builds all possible sentences starting from position start with memoization to avoid redundant
		 * calculations.
		 *
		 * @param s
		 * 		The input string
		 * @param start
		 * 		Current position in the string
		 * @return List of all possible word breakdowns from start to end of string
		 */
		private List<String> backtrackWithMemo(String s, int start) {
			// If we've already computed this problem, return the cached result
			if (memo.containsKey(start)) {
				return memo.get(start);
			}

			List<String> validSentences = new ArrayList<>();

			// Base case: we've reached the end of the string
			if (start == s.length()) {
				validSentences.add(""); // Add empty string to concatenate with
				return validSentences;
			}

			// Try all possible words starting at current position
			for (int end = start + 1; end <= s.length(); end++) {
				String prefix = s.substring(start, end);

				// If the prefix is a valid word in our dictionary
				if (wordSet.contains(prefix)) {
					// Get all valid sentences for the remaining string
					List<String> suffixSentences = backtrackWithMemo(s, end);

					// Combine current word with each valid sentence for the remainder
					for (String suffix : suffixSentences) {
						if (suffix.isEmpty()) {
							validSentences.add(prefix); // Last word in the sentence
						} else {
							validSentences.add(prefix + " " + suffix); // Add space between words
						}
					}
				}
			}

			// Cache the result before returning
			memo.put(start, validSentences);
			return validSentences;
		}
	}

	/**
	 * Represents a node in a Trie data structure.
	 *
	 * Space Complexity: O(1) per node (constant amount of data stored)
	 */
	static class TrieNode {
		HashMap<Character, TrieNode> children = new HashMap<>();  // Maps characters to child nodes
		boolean isWord = false;  // Flag indicating if this node represents the end of a word
	}

	/**
	 * Implementation of a Trie (prefix tree) for efficient word storage and lookup.
	 *
	 * Space Complexity: O(m) where m is the total number of characters across all words
	 */
	static class Trie {
		TrieNode root;  // Root node of the Trie

		/**
		 * Initializes a new Trie with an empty root node.
		 */
		Trie() {
			root = new TrieNode();
		}

		/**
		 * Adds a word to the Trie.
		 *
		 * @param word
		 * 		The word to add
		 *
		 * 		Time Complexity: O(k) where k is the length of the word Space Complexity: O(k) in worst case if all
		 * 		characters are new
		 */
		void addWord(String word) {
			TrieNode curr = root;
			for (char c : word.toCharArray()) {
				curr.children.putIfAbsent(c, new TrieNode());  // Create new node if needed
				curr = curr.children.get(c);  // Move to child node
			}
			curr.isWord = true;  // Mark the end of the word
		}
	}

	public static class SolutionBacktrackingAndTrie {
		// Main method to solve the word break problem

		/**
		 * Breaks a string into space-separated dictionary words using a Trie.
		 *
		 * @param s
		 * 		The input string to be broken
		 * @param wordDict
		 * 		List of dictionary words
		 * @return All possible space-separated sentences using words from wordDict
		 *
		 * 		Time Complexity: O(m) for building the Trie where m is the total length of all words in wordDict Space
		 * 		Complexity: O(m) for storing the Trie
		 */
		public List<String> wordBreak(String s, List<String> wordDict) {
			// Build Trie from dictionary words
			Trie trie = new Trie();
			for (String word : wordDict) {
				trie.addWord(word);  // O(k) per word where k is word length
			}

			List<String> res = new ArrayList<>();
			backtrack(0, s, new ArrayList<>(), trie, res);
			return res;
		}

		/**
		 * Recursively builds all possible sentences using Trie-based word lookup.
		 *
		 * @param index
		 * 		Current position in the string
		 * @param s
		 * 		The input string
		 * @param path
		 * 		Current list of words forming a potential sentence
		 * @param trie
		 * 		Trie containing dictionary words
		 * @param res
		 * 		List to store final results
		 *
		 * 		Time Complexity: O(n * 2^n) in worst case where n is string length
		 * 		- Similar to previous solution but with more efficient prefix matching Space Complexity: O(n) for recursion
		 * 		stack + O(2^n) for storing all possible solutions
		 */
		private void backtrack(int index, String s, List<String> path, Trie trie, List<String> res) {
			// Base case: reached end of string
			if (index == s.length()) {
				res.add(String.join(" ", path));  // Create sentence with spaces and add to results
				return;
			}

			// Start from Trie root for new word search
			TrieNode node = trie.root;
			StringBuilder word = new StringBuilder();

			// Try to match dictionary words starting at current index
			for (int i = index; i < s.length(); i++) {
				char c = s.charAt(i);

				// If character not in Trie, no valid words can be formed
				if (!node.children.containsKey(c)) {
					break;
				}

				word.append(c);  // Add character to current word
				node = node.children.get(c);  // Move to next node in Trie

				// If we've found a complete word, try it in our solution
				if (node.isWord) {
					path.add(word.toString());  // Add word to current path
					backtrack(i + 1, s, path, trie, res);  // Process remaining string
					path.removeLast();  // Backtrack
				}
			}
		}
	}

	/**
	 * Word Break solution using Trie and memoization
	 *
	 * Time Complexity: O(n^2)
	 * - We process each position in the string once: O(n)
	 * - For each position, we check all possible suffixes: O(n)
	 * - Trie lookups are O(1) per character
	 * - With memoization, we avoid redundant calculations
	 *
	 * Space Complexity: O(n^2 + m)
	 * - Trie storage: O(m) where m is total length of all words
	 * - Memoization cache: O(n^2) for storing results for all possible substrings
	 * - Recursion stack: O(n)
	 */
	public static class SolutionWithTrieAndMemoization {
		private Map<Integer, List<String>> memo;

		public List<String> wordBreak(String s, List<String> wordDict) {
			// Build Trie from dictionary
			Trie trie = new Trie();
			for (String word : wordDict) {
				trie.addWord(word);
			}

			memo = new HashMap<>();
			return memoizedBreak(s, 0, trie);
		}

		/**
		 * Recursively finds all possible word breakdowns using both Trie and memoization.
		 *
		 * @param s
		 * 		The input string
		 * @param start
		 * 		Current position in the string
		 * @param trie
		 * 		Trie containing dictionary words
		 * @return List of all possible word breakdowns from start to end of string
		 */
		private List<String> memoizedBreak(String s, int start, Trie trie) {
			// Return cached result if available
			if (memo.containsKey(start)) {
				return memo.get(start);
			}

			List<String> validSentences = new ArrayList<>();

			// Base case: reached end of string
			if (start == s.length()) {
				validSentences.add("");
				return validSentences;
			}

			// Start from Trie root for new word matching
			TrieNode node = trie.root;

			// Try to match dictionary words starting at current position
			for (int i = start; i < s.length(); i++) {
				char c = s.charAt(i);

				// If character not in Trie, no valid words can be formed
				if (!node.children.containsKey(c)) {
					break;
				}

				// Move to next node in Trie
				node = node.children.get(c);

				// If we've found a complete word, try it in our solution
				if (node.isWord) {
					String prefix = s.substring(start, i + 1);
					List<String> suffixSentences = memoizedBreak(s, i + 1, trie);

					// Combine current word with each valid sentence for the remainder
					for (String suffix : suffixSentences) {
						if (suffix.isEmpty()) {
							validSentences.add(prefix);
						} else {
							validSentences.add(prefix + " " + suffix);
						}
					}
				}
			}

			// Cache the result before returning
			memo.put(start, validSentences);
			return validSentences;
		}
	}
}
