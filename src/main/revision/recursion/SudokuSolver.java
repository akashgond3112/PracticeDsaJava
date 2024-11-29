package main.revision.recursion;

public class SudokuSolver {

	public void solveSudoku(char[][] board) {
		solve(board);
	}

	boolean solve(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				if (board[i][j] == '.') {

					for (char c = '1'; c <= '9'; c++) {

						if (isValidEntry(board, i, j, c)) {
							board[i][j] = c;

							if (solve(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}
						}

					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValidEntry(char[][] board, int row, int col, char c) {

		for (int k = 0; k < 9; k++) {
			if (board[k][col] == c) {
				return false;
			}

			if (board[row][k] == c) {
				return false;
			}

			int boxRow = 3 * (row / 3) + k / 3;
			int boxCol = 3 * (col / 3) + k % 3;

			if (board[boxRow][boxCol] == c) {
				return false;
			}
		}
		return true;
	}
}
