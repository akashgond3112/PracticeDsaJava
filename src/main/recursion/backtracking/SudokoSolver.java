package main.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

import static main.recursion.backtracking.FIndNQueens.solve;

public class SudokoSolver {

	public static void solveSudoku(List<List<Character>> board) {
		System.out.println(solve(board));
	}

	private static boolean solve(List<List<Character>> board) {

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(0).size(); j++) {

				if (board.get(i).get(j) == '.') {

					for (char c = '1'; c <= '9'; c++) {
						if (isValid(board, i, j, c)) {
							board.get(i).set(j, c);

							if (solve(board))
								return true;
							board.get(i).set(j, '.');
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isValid(List<List<Character>> board, int row, int col, char c) {

		for (int i = 0; i < 9; i++) {
			if (board.get(i).get(col) == c) {
				return false;
			}

			if (board.get(row).get(i) == c) {
				return false;
			}

			if (board.get(3 * (row / 3) + i / 3).get(3 * (col / 3) + i % 3) == c) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		solveSudoku(new ArrayList<List<Character>>());
	}
}
