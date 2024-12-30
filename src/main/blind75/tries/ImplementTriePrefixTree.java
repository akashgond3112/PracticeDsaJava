package main.blind75.tries;

import java.util.HashMap;

/**
 * 208. Implement Trie (Prefix Tree)
 * Medium
 * Topics
 * Companies
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix, and false otherwise.
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.*/
public class ImplementTriePrefixTree {

	/**
	 * Time complexity: O(n) for each function call.
	 * Space complexity: O(t) Where n is the length of
	 * the string and t is the total number of TrieNodes created in the Trie.
	 */
	static class TrieUsingArray {
		public static class TrieNode {
			TrieNode[] children = new TrieNode[26];
			boolean endOfWord = false;
		}

		private final TrieNode root;

		public TrieUsingArray() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				int i = c - 'a';
				if (cur.children[i] == null) {
					cur.children[i] = new TrieNode();
				}
				cur = cur.children[i];
			}
			cur.endOfWord = true;
		}

		public boolean search(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				int i = c - 'a';
				if (cur.children[i] == null) {
					return false;
				}
				cur = cur.children[i];
			}
			return cur.endOfWord;
		}

		public boolean startsWith(String prefix) {
			TrieNode cur = root;
			for (char c : prefix.toCharArray()) {
				int i = c - 'a';
				if (cur.children[i] == null) {
					return false;
				}
				cur = cur.children[i];
			}
			return true;
		}
	}

	/**
	 * Time complexity: O(n) for each function call.
	 * Space complexity: O(t) Where n is the length of
	 * the string and t is the total number of TrieNodes created in the Trie.
	 */
	public static class Trie {

		public static class TrieNode {
			HashMap<Character, TrieNode> children = new HashMap<>();
			boolean endOfWord = false;
		}

		private final TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				cur.children.putIfAbsent(c, new TrieNode());
				cur = cur.children.get(c);
			}
			cur.endOfWord = true;
		}

		public boolean search(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				if (!cur.children.containsKey(c)) {
					return false;
				}
				cur = cur.children.get(c);
			}
			return cur.endOfWord;
		}

		public boolean startsWith(String prefix) {
			TrieNode cur = root;
			for (char c : prefix.toCharArray()) {
				if (!cur.children.containsKey(c)) {
					return false;
				}
				cur = cur.children.get(c);
			}
			return true;
		}
	}
}
