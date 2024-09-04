package main.recursion.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FIndNQueens {

	public static List<List<String>> solveNQueens(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		List<List<String>> res = new ArrayList<List<String>>();
		dfs(0, board, res);
		return res;
	}

	static boolean validate(char[][] board, int row, int col) {
		int duprow = row;
		int dupcol = col;
		while (row >= 0 && col >= 0) {
			if (board[row][col] == 'Q')
				return false;
			row--;
			col--;
		}

		row = duprow;
		col = dupcol;
		while (col >= 0) {
			if (board[row][col] == 'Q')
				return false;
			col--;
		}

		col = dupcol;
		while (col >= 0 && row < board.length) {
			if (board[row][col] == 'Q')
				return false;
			col--;
			row++;
		}
		return true;
	}

	static void dfs(int col, char[][] board, List<List<String>> res) {
		if (col == board.length) {
			res.add(construct(board));
			return;
		}

		for (int row = 0; row < board.length; row++) {
			if (validate(board, row, col)) {
				board[row][col] = 'Q';
				dfs(col + 1, board, res);
				board[row][col] = '.';
			}
		}
	}


	static List<String> construct(char[][] board) {
		List<String> res = new LinkedList<String>();
		for (char[] chars : board) {
			String s = new String(chars);
			res.add(s);
		}
		return res;
	}


	public static List<List<String>> solveNQueensII(int n) {
		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';
		List<List<String>> res = new ArrayList<List<String>>();
		int leftRow[] = new int[n];
		int upperDiagonal[] = new int[2 * n - 1];
		int lowerDiagonal[] = new int[2 * n - 1];
		solveII(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
		return res;
	}


	static void solveII(int col, char[][] board, List<List<String>> res, int leftRow[], int lowerDiagonal[],
			int upperDiagonal[]) {
		if (col == board.length) {
			res.add(construct(board));
			return;
		}

		for (int row = 0; row < board.length; row++) {
			if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0 && upperDiagonal[board.length - 1 + col - row] == 0) {
				board[row][col] = 'Q';
				leftRow[row] = 1;
				lowerDiagonal[row + col] = 1;
				upperDiagonal[board.length - 1 + col - row] = 1;
				solveII(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);
				board[row][col] = '.';
				leftRow[row] = 0;
				lowerDiagonal[row + col] = 0;
				upperDiagonal[board.length - 1 + col - row] = 0;
			}
		}
	}

	public static void main(String args[]) {
		int N = 4;
		List<List<String>> queen = solveNQueens(N);
		int i = 1;
		for (List<String> it : queen) {
			System.out.println("Arrangement " + i);
			for (String s : it) {
				System.out.println(s);
			}
			System.out.println();
			i += 1;
		}


		List<List<String>> queenII = solveNQueensII(N);
		int j = 1;
		for (List<String> it : queenII) {
			System.out.println("Arrangement II " + i);
			for (String s : it) {
				System.out.println(s);
			}
			System.out.println();
			j += 1;
		}

	}
}
