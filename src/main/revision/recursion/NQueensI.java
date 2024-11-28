package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueensI {

	static boolean validate(List<List<Character>> board, int row, int col) {
		int dupRow = row;
		int dupCol = col;

		// Check upper-left diagonal
		while (row >= 0 && col >= 0) {
			if (board.get(row).get(col) == 'Q')
				return false;
			row--;
			col--;
		}

		row = dupRow;
		col = dupCol;

		// Check left side
		while (col >= 0) {
			if (board.get(row).get(col) == 'Q')
				return false;
			col--;
		}

		col = dupCol;

		// Check lower-left diagonal
		while (col >= 0 && row < board.size()) {
			if (board.get(row).get(col) == 'Q')
				return false;
			col--;
			row++;
		}

		return true;
	}

	static void solve(int col, List<List<Character>> board, List<List<String>> res) {
		if (col == board.size()) {
			res.add(construct(board));
			return;
		}

		for (int row = 0; row < board.size(); row++) {
			if (validate(board, row, col)) {
				board.get(row).set(col, 'Q');
				solve(col + 1, board, res);
				board.get(row).set(col, '.');
			}
		}
	}

	public static List<List<String>> solveNQueens(int n) {
		List<List<Character>> board = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			List<Character> row = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				row.add('.');
			}
			board.add(row);
		}

		List<List<String>> res = new ArrayList<>();
		solve(0, board, res);
		return res;
	}

	static List<String> construct(List<List<Character>> board) {
		List<String> result = new ArrayList<>();
		for (List<Character> row : board) {
			StringBuilder sb = new StringBuilder();
			for (char c : row) {
				sb.append(c);
			}
			result.add(sb.toString());
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 4;
		List<List<String>> result = solveNQueens(n);
		for (List<String> solution : result) {
			for (String row : solution) {
				System.out.println(row);
			}
			System.out.println();
		}
	}
}
