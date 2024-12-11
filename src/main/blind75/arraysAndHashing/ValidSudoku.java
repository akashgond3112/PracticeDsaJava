package main.blind75.arraysAndHashing;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
36. Valid Sudoku
Medium
Topics
Companies
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.

Example 1:

Input: board =
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true
Example 2:

Input: board =
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.*/
public class ValidSudoku {
	/**
	 * Time & Space Complexity Time complexity: O(n^2 ) Space complexity: O(n^2 )
	 */
	public boolean isValidSudoku(char[][] board) {
		Map<Integer, Set<Character>> cols = new HashMap<>();
		Map<Integer, Set<Character>> rows = new HashMap<>();
		Map<String, Set<Character>> squares = new HashMap<>();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board[r][c] == '.')
					continue;

				String squareKey = (r / 3) + "," + (c / 3);

				if (rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) || cols.computeIfAbsent(c,
						k -> new HashSet<>()).contains(board[r][c]) || squares.computeIfAbsent(squareKey,
						k -> new HashSet<>()).contains(board[r][c])) {
					return false;
				}

				rows.get(r).add(board[r][c]);
				cols.get(c).add(board[r][c]);
				squares.get(squareKey).add(board[r][c]);
			}
		}
		return true;
	}

}
