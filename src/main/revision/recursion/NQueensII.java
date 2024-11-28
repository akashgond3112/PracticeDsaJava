package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

public class NQueensII {


	public int totalNQueens(int n) {
		List<Integer> cols = new ArrayList<>(); // Tracks the column of the queen in each row
		return solve(0, cols, n);
	}

	private int solve(int row, List<Integer> cols, int n) {
		// Base case: all rows are filled
		if (row == n) {
			return 1; // Found one valid solution
		}

		int count = 0;
		for (int col = 0; col < n; col++) {
			if (isValid(row, col, cols)) {
				cols.add(col); // Place the queen
				count += solve(row + 1, cols, n); // Recurse for the next row
				cols.remove(cols.size() - 1); // Backtrack
			}
		}
		return count;
	}

	private boolean isValid(int row, int col, List<Integer> cols) {
		for (int r = 0; r < cols.size(); r++) {
			int c = cols.get(r);
			// Check if the column or diagonal is occupied
			if (c == col || Math.abs(c - col) == Math.abs(r - row)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		NQueensII nQueens = new NQueensII();
		int n = 4;
		System.out.println("Total distinct solutions for " + n + "-Queens: " + nQueens.totalNQueens(n));
	}

}
