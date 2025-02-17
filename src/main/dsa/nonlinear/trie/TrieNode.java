package main.dsa.nonlinear.trie;

public class TrieNode {

	TrieNode[] children = new TrieNode[26];
	boolean isEnd;
	int cntEndWith = 0;
	int cntPrefix = 0;

	TrieNode() {
		this.isEnd = false;
		for (int i = 0; i < 26; i++)
			children[i] = null;
	}

	boolean containsKey(char key) {
		return children[key - 'a'] != null;
	}

	TrieNode getChild(char key) {
		return children[key - 'a'];
	}

	void put(char key, TrieNode child) {
		children[key - 'a'] = child;
	}

	void setEnd() {
		this.isEnd = true;
	}

	boolean isEnd() {
		return this.isEnd;
	}

	void increasePrefix() {
		this.cntPrefix++;
	}

	void increaseEndWith() {
		this.cntEndWith++;
	}

	void decreasePrefix() {
		this.cntPrefix--;
	}

	void decreaseEndWith() {
		this.cntEndWith--;
	}

	int getEndWith() {
		return this.cntEndWith;
	}

	int getPrefix() {
		return this.cntPrefix;
	}
}
