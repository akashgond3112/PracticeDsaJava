package main.meta.medium;

/**
 * <pre>
 * 348. Design Tic-Tac-Toe
 * Design a Tic-tac-toe game that is played between two players on a n x n grid.
 *
 * You may assume the following rules:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves is allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Example:
 *
 * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
 *
 * TicTacToe toe = new TicTacToe(3);
 *
 * toe.move(0, 0, 1); -> Returns 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * toe.move(0, 2, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * toe.move(2, 2, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * toe.move(1, 1, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * toe.move(2, 0, 1); -> Returns 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * toe.move(1, 0, 2); -> Returns 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * Follow up:
 * Could you do better than O(n2) per move() operation?
 *
 * Difficulty:
 * Medium
 * Lock:
 * Prime
 * Company:
 * Amazon Apple DoorDash Facebook Google Microsoft TripleByte Uber
 * </pre>
 */
public class DesignTicTacToe {
	int[][] matrix;

	/**
	 * Initialize your data structure here.
	 */
	public DesignTicTacToe(int n) {
		matrix = new int[n][n];
	}

	/**
	 * Player {player} makes a move at ({row}, {col}).
	 *
	 * @param row
	 * 		The row of the board.
	 * @param col
	 * 		The column of the board.
	 * @param player
	 * 		The player, can be either 1 or 2.
	 * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
	 */
	public int move(int row, int col, int player) {

		int[] rows = new int[3];
		int[] cols = new int[3];
		int[] diag = new int[2];

		int val = player == 1 ? 1 : -1;

		rows[row] += val; // assumption: valid input, no override for same location
		cols[col] += val;

		if (row == col) {
			diag[0] += val;
		}
		if (row + col == 2) {
			diag[1] += val;
		}

		if (Math.abs(rows[row]) == 3 || Math.abs(cols[col]) == 3 || Math.abs(diag[0]) == 3 || Math.abs(diag[1]) == 3) {
			return player;
		}

		return 0;
	}

	public int move_naive_solution(int row, int col, int player) {

		matrix[row][col] = player;

		//check row
		boolean win = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != player) {
				win = false;
				break;
			}
		}
		if (win)
			return player;
		//check column
		win = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][col] != player) {
				win = false;
				break;
			}
		}
		if (win)
			return player;
		//check back diagonal
		win = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][i] != player) {
				win = false;
				break;
			}
		}
		if (win)
			return player;
		//check forward diagonal
		win = true;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][matrix.length - i - 1] != player) {
				win = false;
				break;
			}
		}
		if (win)
			return player;
		return 0;
	}
}
