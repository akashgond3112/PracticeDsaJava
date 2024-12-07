package main.revision.recursion;

import java.util.*;

class WordLadderII {
	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		boolean isWord = false;

		public void addWord(String word) {
			TrieNode node = this;
			for (char c : word.toCharArray()) {
				node.children.putIfAbsent(c, new TrieNode());
				node = node.children.get(c);
			}
			node.isWord = true;
		}
	}

	public List<String> findWords(char[][] board, String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			root.addWord(word);
		}

		Set<String> res = new HashSet<>();
		boolean[][] visited = new boolean[board.length][board[0].length];

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				dfs(board, r, c, root, "", visited, res);
			}
		}

		return new ArrayList<>(res);
	}

	private void dfs(char[][] board, int r, int c, TrieNode node, String word, boolean[][] visited, Set<String> res) {
		if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || visited[r][c] || !node.children.containsKey(
				board[r][c])) {
			return;
		}

		visited[r][c] = true;
		node = node.children.get(board[r][c]);
		word += board[r][c];
		if (node.isWord) {
			res.add(word);
		}

		dfs(board, r - 1, c, node, word, visited, res);
		dfs(board, r + 1, c, node, word, visited, res);
		dfs(board, r, c - 1, node, word, visited, res);
		dfs(board, r, c + 1, node, word, visited, res);

		visited[r][c] = false; // Backtrack
	}
}
