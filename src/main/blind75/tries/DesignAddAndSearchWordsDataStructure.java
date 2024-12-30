package main.blind75.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * 211. Design Add and Search Words Data Structure Medium Topics Companies Hint Design a data structure that supports
 * adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object. void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]] Output [null,null,null,null,false,true,true,true]
 *
 * Explanation WordDictionary wordDictionary = new WordDictionary(); wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad"); wordDictionary.addWord("mad"); wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * Constraints:
 *
 * 1 <= word.length <= 25 word in addWord consists of lowercase English letters. word in search consist of '.' or
 * lowercase English letters. There will be at most 2 dots in word for search queries. At most 104 calls will be made to
 * addWord and search.
 */
public class DesignAddAndSearchWordsDataStructure {

	/**
	 * Time complexity: O(1) for addWord(), O(m∗n) for search(). Space complexity: O(m∗n) Where m  is the number of
	 * words added and n is the length of the string.
	 */
	public static class WordDictionaryBruteForce {

		private final List<String> store;

		public WordDictionaryBruteForce() {
			store = new ArrayList<>();
		}

		public void addWord(String word) {
			store.add(word);
		}

		public boolean search(String word) {
			for (String w : store) {
				if (w.length() != word.length())
					continue;
				int i = 0;
				while (i < w.length()) {
					if (w.charAt(i) == word.charAt(i) || word.charAt(i) == '.') {
						i++;
					} else {
						break;
					}
				}
				if (i == w.length()) {
					return true;
				}
			}
			return false;
		}
	}


	public static class WordDictionary {

		public static class TrieNode {

			TrieNode[] children;
			boolean endWord;

			public TrieNode() {
				children = new TrieNode[26];
				endWord = false;
			}
		}

		private final TrieNode root;

		public WordDictionary() {
			root = new TrieNode();
		}

		public void addWord(String word) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				if (cur.children[c - 'a'] == null) {
					cur.children[c - 'a'] = new TrieNode();
				}
				cur = cur.children[c - 'a'];
			}
			cur.endWord = true;
		}

		public boolean search(String word) {
			return dfs(word, 0, root);
		}

		private boolean dfs(String word, int j, TrieNode root) {
			TrieNode cur = root;

			for (int i = j; i < word.length(); i++) {
				char c = word.charAt(i);
				if (c == '.') {
					for (TrieNode child : cur.children) {
						if (child != null && dfs(word, i + 1, child)) {
							return true;
						}
					}
					return false;
				} else {
					if (cur.children[c - 'a'] == null) {
						return false;
					}
					cur = cur.children[c - 'a'];
				}
			}
			return cur.endWord;
		}
	}

}
