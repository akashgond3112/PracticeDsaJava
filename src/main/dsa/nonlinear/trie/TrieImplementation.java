package main.dsa.nonlinear.trie;

/**
 * Trie Implementation Moderate 0/80 Average time to solve is 25m Contributed by 21 upvotes Asked in companies Problem
 * statement Implement a Trie Data Structure which supports the following three operations:
 *
 * Operation 1 - insert(word) - To insert a string WORD in the Trie.
 *
 * Operation 2-  search(word) - To check if a string WORD is present in Trie or not.
 *
 * Operation 3-  startsWith(word) - To check if there is a string that has the prefix WORD.
 *
 *
 * Trie is a data structure that is like a tree data structure in its organisation. It consists of nodes that store
 * letters or alphabets of words, which can be added, retrieved, and deleted from the trie in a very efficient way.
 *
 * In other words, Trie is an information retrieval data structure, which can beat naive data structures like Hashmap,
 * Tree, etc in the time complexities of its operations.
 *
 *
 * The above figure is the representation of a Trie. New words that are added are inserted as the children of the root
 * node.
 *
 * Alphabets are added in the top to bottom fashion in parent to children hierarchy. Alphabets that are highlighted with
 * blue circles are the end nodes that mark the ending of a word in the Trie.
 *
 * For Example:- Type = ["insert", "search"], Query = ["coding", "coding]. We return ["null", "true"] as coding is
 * present in the trie after 1st operation. Detailed explanation ( Input/output format, Notes, Images ) Sample Input 1 :
 * 5 insert search startWith coding ninjas coding ninja Sample Output 1 : null true false true Explanation to Sample
 * Input 1 : Query 1: "coding" is inserted
 *
 * Query 2: "ninjas" is inserted
 *
 * Query 3: "true" is printed as "coding" is present
 *
 * Query 4: "false" is printed as "ninja" is not present, but "ninjas" is present.
 *
 * Query 5: "true" is printed as there is a word "ninjas" that starts with "ninja". Sample Input 2 : 3 insert search
 * startWith book books b Sample Output 2 : null false true Constraints : 1 <= 'Q' <= 10^4 1 <= | WORD | <= 2000
 */
public class TrieImplementation {

	TrieNode root;

	TrieImplementation() {
		root = new TrieNode();
	}

	// Abstract method for insertion
	public void insert(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!current.containsKey(c)) {
				current.put(c, new TrieNode());
			}
			current = current.getChild(c);
		}
		current.setEnd();
	}

	// Abstract method for searching
	public boolean search(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!current.containsKey(c)) {
				return false;
			}
			current = current.getChild(c);
		}
		return current.isEnd();
	}

	// Abstract method for startsWith
	public boolean startsWith(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (!current.containsKey(c)) {
				return false;
			}
			current = current.getChild(c);
		}
		return true;
	}

	public static void main(String[] args) {
		TrieImplementation trie = new TrieImplementation();
		System.out.println("Inserting words: Striver, Striving, String, Strike");
		trie.insert("striver");
		trie.insert("striving");
		trie.insert("string");
		trie.insert("strike");

		System.out.println("Search if Strawberry exists in trie: " + (trie.search("strawberry") ? "True" : "False"));

		System.out.println("Search if Strike exists in trie: " + (trie.search("strike") ? "True" : "False"));

		System.out.println("If words in Trie start with Stri: " + (trie.startsWith("stri") ? "True" : "False"));
	}
}
