package main.blind75.backTracking;

import main.blind75.binary.trees.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Code Testcase Test Result
 * 79. Word Search Medium Topics Companies Given an m x n grid of characters board and a string word, return true if
 * word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * Example 1: Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED" Output: true
 * Example 2: Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE" Output: true Example
 * 3: Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB" Output: false
 * <p>
 * Constraints: m == board.length n = board[i].length 1 <= m, n <= 6 1 <= word.length <= 15 board and word consists of
 * only lowercase and uppercase English letters.
 * <p>
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 */
public class WordSearch {

	public static class Pair<T1, T2> {
		Integer key;
		Integer value;

		public Pair(Integer key, Integer value) {
			this.key = key;
			this.value = value;
		}

		public Integer getKey() {
			return key;
		}

		public Integer getValue() {
			return value;
		}

	}

	/**
	 * Time complexity: O(m∗4^n )
	 * Space complexity: O(n)
	 * Where m is the number of cells in the board and n is the length of the w o r d word.
	 */
	static class SolutionUsingHashSet {
		private int ROWS, COLS;
		private final Set<Pair<Integer, Integer>> path = new HashSet<>();

		public boolean exist(char[][] board, String word) {
			ROWS = board.length;
			COLS = board[0].length;

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (dfs(board, word, r, c, 0)) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean dfs(char[][] board, String word, int r, int c, int i) {
			if (i == word.length()) {
				return true;
			}

			if (r < 0 || c < 0 || r >= ROWS || c >= COLS || board[r][c] != word.charAt(i) || path.contains(
					new Pair<>(r, c))) {
				return false;
			}

			path.add(new Pair<>(r, c));
			boolean res = dfs(board, word, r + 1, c, i + 1) || dfs(board, word, r - 1, c, i + 1) || dfs(board, word, r,
					c + 1, i + 1) || dfs(board, word, r, c - 1, i + 1);
			path.remove(new Pair<>(r, c));

			return res;
		}
	}

	/**
	 * Time complexity: O(m∗4^n )
	 * Space complexity: O(n)
	 * Where m is the number of cells in the board and n is the length of the w o r d word.
	 */
	static class SolutionUsingVisitedArrayDFS {
		private int ROWS, COLS;
		private boolean[][] visited;

		public boolean exist(char[][] board, String word) {
			ROWS = board.length;
			COLS = board[0].length;
			visited = new boolean[ROWS][COLS];

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (dfs(board, word, r, c, 0)) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean dfs(char[][] board, String word, int r, int c, int i) {
			if (i == word.length()) {
				return true;
			}

			if (r < 0 || c < 0 || r >= ROWS || c >= COLS || board[r][c] != word.charAt(i) || visited[r][c]) {
				return false;
			}

			visited[r][c] = true;
			boolean res = dfs(board, word, r + 1, c, i + 1) || dfs(board, word, r - 1, c, i + 1) || dfs(board, word, r,
					c + 1, i + 1) || dfs(board, word, r, c - 1, i + 1);
			visited[r][c] = false;

			return res;
		}
	}

	/**
	 * Time complexity: O(m∗4^n )
	 * Space complexity: O(n)
	 * Where m is the number of cells in the board and n is the length of the w o r d word.
	 */
	static class SolutionUsingDFSOptimal {
		private int ROWS, COLS;

		public boolean exist(char[][] board, String word) {
			ROWS = board.length;
			COLS = board[0].length;

			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					if (dfs(board, word, r, c, 0)) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean dfs(char[][] board, String word, int r, int c, int i) {
			if (i == word.length()) {
				return true;
			}
			if (r < 0 || c < 0 || r >= ROWS || c >= COLS || board[r][c] != word.charAt(i) || board[r][c] == '#') {
				return false;
			}

			board[r][c] = '#';
			boolean res = dfs(board, word, r + 1, c, i + 1) || dfs(board, word, r - 1, c, i + 1) || dfs(board, word, r,
					c + 1, i + 1) || dfs(board, word, r, c - 1, i + 1);
			board[r][c] = word.charAt(i);
			return res;
		}
	}
}
