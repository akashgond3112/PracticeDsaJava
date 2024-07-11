package main.dsa.nonlinear.graph.learning;

import java.util.Arrays;

/*
Replace O's with X's
Difficulty: MediumAccuracy: 34.0%Submissions: 103K+Points: 4
Given a matrix mat of size N x M where every element is either 'O' or 'X'. Replace all 'O' or a group of 'O' with 'X' that are surrounded by 'X'.

A 'O' (or a set of 'O') is considered to be surrounded by 'X' if there are 'X' at locations just below, just above, just left and just right of it.

Example 1:

Input:
n = 5, m = 4
mat = {{'X', 'X', 'X', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'O', 'O', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'X', 'O', 'O'}}
Output:
ans = {{'X', 'X', 'X', 'X'},
       {'X', 'X', 'X', 'X'},
       {'X', 'X', 'X', 'X'},
       {'X', 'X', 'X', 'X'},
       {'X', 'X', 'O', 'O'}}
Explanation:
Following the rule the above matrix is the resultant matrix.
Example 2:

Input:
n = 5, m = 4
mat = {{'X', 'O', 'X', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'O', 'O', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'X', 'O', 'O'}}
Output:
ans = {{'X', 'O', 'X', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'O', 'O', 'X'},
       {'X', 'O', 'X', 'X'},
       {'X', 'X', 'O', 'O'}}
Explanation:
Following the rule the above matrix is the resultant matrix.
Your Task:
You do not need to read input or print anything. Your task is to complete the function fill() which takes N, M and mat as input
parameters ad returns a 2D array representing the resultant matrix.

Expected Time Complexity: O(n*m)
Expected Auxiliary Space: O(n*m)

Constraints:
1 ≤ n, m ≤ 500
* */
public class ReplaceOWithX {

	static final int[] delRow = { -1, 0, +1, 0 };
	static final int[] delCol = { 0, 1, 0, -1 };

	static void dfs(int row, int col, int[][] vis, char[][] mat) {

		vis[row][col] = 1;
		int n = mat.length;
		int m = mat[0].length;

		for (int i = 0; i < 4; i++) {
			int newRow = row + delRow[i];
			int newCol = col + delCol[i];

			if (newRow >= 0 && newRow < n && newCol >= 0 && newRow < m && vis[newRow][newCol] == 0 && mat[newRow][newCol] == '0') {
				dfs(newRow, newCol, vis, mat);
			}
		}

	}

	static char[][] fill(int n, int m, char[][] a) {
		// code here
		int[][] visited = new int[n][m];

		for (int i = 0; i < m; i++) {
			// first row
			if (visited[0][i] == 0 && a[0][i] == 'O') {
				dfs(0, i, visited, a);
			}

			// last row
			if (visited[n - 1][i] == 0 && a[n - 1][i] == 'O') {
				dfs(n - 1, i, visited, a);
			}
		}

		for (int i = 0; i < n; i++) {

			// first column
			if (visited[i][0] == 0 && a[i][0] == 'O') {
				dfs(i, 0, visited, a);
			}

			//last column
			if (visited[i][m - 1] == 0 && a[i][m - 1] == 'O') {
				dfs(i, m - 1, visited, a);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0 && a[i][j] == 'O') {
					a[i][j] = 'X';
				}
			}
		}

		return a;
	}

	public static void main(String[] args) {
		int n = 5;
		int m = 4;
		char[][] a = new char[][] { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X' }, { 'X', 'O', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' }, { 'X', 'X', 'O', 'O' } };

		System.out.println(Arrays.deepToString(fill(n, m, a)));
	}
}
