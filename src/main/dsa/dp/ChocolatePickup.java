package main.dsa.dp;

import java.util.Arrays;

/*
Problem statement
Ninja has a 'GRID' of size 'R' X 'C'. Each cell of the grid contains some chocolates. Ninja has two friends Alice and Bob, and he wants to collect as many chocolates as possible with the help of his friends.

Initially, Alice is in the top-left position i.e. (0, 0), and Bob is in the top-right place i.e. (0, ‘C’ - 1) in the grid. Each of them can move from their current cell to the cells just below them. When anyone passes from any cell, he will pick all chocolates in it, and then the number of chocolates in that cell will become zero. If both stay in the same cell, only one of them will pick the chocolates in it.

If Alice or Bob is at (i, j) then they can move to (i + 1, j), (i + 1, j - 1) or (i + 1, j + 1). They will always stay inside the ‘GRID’.

Your task is to find the maximum number of chocolates Ninja can collect with the help of his friends by following the above rules.

Example:
Input: ‘R’ = 3, ‘C’ = 4
       ‘GRID’ = [[2, 3, 1, 2], [3, 4, 2, 2], [5, 6, 3, 5]]
Output: 21

Initially Alice is at the position (0,0) he can follow the path (0,0) -> (1,1) -> (2,1) and will collect 2 + 4 + 6 = 12 chocolates.

Initially Bob is at the position (0, 3) and he can follow the path (0, 3) -> (1,3) -> (2, 3) and will colllect 2 + 2 + 5 = 9 chocolates.

Hence the total number of chocolates collected will be 12 + 9 = 21. there is no other possible way to collect a greater number of chocolates than 21.
Detailed explanation ( Input/output format, Notes, Images )
Constraints :
1 <= ‘T’ <= 10
2 <= 'R', 'C' <= 50
0 <= 'GRID[i][j]'<= 10^2
Time Limit: 1sec
Sample Input 1 :
2
3 4
2 3 1 2
3 4 2 2
5 6 3 5
2 2
1 1
1 2
Sample Output 1 :
21
5
Explanation Of Sample Input 1 :
For the first test case, Initially Alice is at the position (0, 0) he can follow the path (0, 0) -> (1, 1) -> (2, 1) and will collect 2 + 4 + 6 = 12 chocolates.

Initially Bob is at the position (0, 3) and he can follow the path (0, 3) -> (1, 3) -> (2, 3) and will collect 2 + 2 + 5 = 9 chocolates.

Hence the total number of chocolates collected will be 12 + 9 = 21.

For the second test case, Alice will follow the path (0, 0) -> (1, 0) and Bob will follow the path (0, 1) -> (1, 1). total number of chocolates collected will be 1 + 1 + 1 + 2 = 5
Sample Input 2 :
2
2 2
3 7
7 6
3 2
4 5
3 7
4 2
Sample Output 2 :
23
25
*/
public class ChocolatePickup {

	public static long maximumChocolatesUsingTabularSpaceOptimal(int r, int c, int[][] grid) {
		// Initialize the dp array
		long[][] front = new long[c][c];
		long[][] curr = new long[c][c];

		// Base case: fill the last row
		for (int j1 = 0; j1 < c; j1++) {
			for (int j2 = 0; j2 < c; j2++) {
				if (j1 == j2) {
					front[j1][j2] = grid[r - 1][j1];
				} else {
					front[j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
				}
			}
		}

		// Fill dp array from bottom to top
		for (int i = r - 2; i >= 0; i--) {
			for (int j1 = 0; j1 < c; j1++) {
				for (int j2 = 0; j2 < c; j2++) {
					long max = (long) -1e8;  // Initialize to very small value

					for (int dj1 = -1; dj1 <= 1; dj1++) {
						for (int dj2 = -1; dj2 <= 1; dj2++) {
							int nextJ1 = j1 + dj1;
							int nextJ2 = j2 + dj2;

							if (nextJ1 >= 0 && nextJ1 < c && nextJ2 >= 0 && nextJ2 < c) {
								long value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
								value += front[nextJ1][nextJ2];
								max = Math.max(max, value);
							}
						}
					}

					curr[j1][j2] = max;  // Store the result for this state
				}
			}
			// Update the front array with the values from the cur array for the next row
			for (int a = 0; a < c; a++) {
				front[a] = curr[a].clone();
			}
		}

		// Return the maximum chocolates collected starting from the top row with initial positions
		return front[0][c - 1];
	}

	public static long maximumChocolatesUsingTabular(int r, int c, int[][] grid) {
		// Initialize the dp array
		long[][][] dp = new long[r][c][c];

		// Base case: fill the last row
		for (int j1 = 0; j1 < c; j1++) {
			for (int j2 = 0; j2 < c; j2++) {
				if (j1 == j2) {
					dp[r - 1][j1][j2] = grid[r - 1][j1];
				} else {
					dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
				}
			}
		}

		// Fill dp array from bottom to top
		for (int i = r - 2; i >= 0; i--) {
			for (int j1 = 0; j1 < c; j1++) {
				for (int j2 = 0; j2 < c; j2++) {
					long max = (long) -1e8;  // Initialize to very small value

					for (int dj1 = -1; dj1 <= 1; dj1++) {
						for (int dj2 = -1; dj2 <= 1; dj2++) {
							int nextJ1 = j1 + dj1;
							int nextJ2 = j2 + dj2;

							if (nextJ1 >= 0 && nextJ1 < c && nextJ2 >= 0 && nextJ2 < c) {
								long value = (j1 == j2) ? grid[i][j1] : grid[i][j1] + grid[i][j2];
								value += dp[i + 1][nextJ1][nextJ2];
								max = Math.max(max, value);
							}
						}
					}

					dp[i][j1][j2] = max;  // Store the result for this state
				}
			}
		}

		// Return the maximum chocolates collected starting from the top row with initial positions
		return dp[0][0][c - 1];
	}


	public static long maximumChocolatesUsingMemo(int i, int j1, int j2, int r, int c, int[][] grid, long[][][] dp) {
		// Check if the current state is out of bounds
		if (j1 < 0 || j2 < 0 || j1 >= c || j2 >= c) {
			return (long) -1e8;  // Return a very small number for invalid cases
		}

		// If we have reached the last row, return the appropriate sum of chocolates
		if (i == r - 1) {
			if (j1 == j2) {
				return grid[i][j1];  // Both robots on the same spot
			} else {
				return grid[i][j1] + grid[i][j2];  // Robots on different spots
			}
		}

		// Check if this state has already been computed
		if (dp[i][j1][j2] != -1) {
			return dp[i][j1][j2];
		}

		// Explore all possible moves for both robots
		long max = (long) -1e8;

		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				long value;
				if (j1 == j2) {
					value = grid[i][j1];  // If robots are on the same cell
				} else {
					value = grid[i][j1] + grid[i][j2];  // If robots are on different cells
				}

				// Recursively calculate for the next row with updated positions
				value += maximumChocolatesUsingMemo(i + 1, j1 + dj1, j2 + dj2, r, c, grid, dp);
				max = Math.max(max, value);  // Take the maximum
			}
		}

		// Store the result in dp array and return
		return dp[i][j1][j2] = max;
	}

	public static long maximumChocolates(int i, int j1, int j2, int r, int c, int[][] grid) {
		// Write your code here.

		if (j1 < 0 || j2 < 0 || j1 >= c || j2 >= c) {
			return (long) -1e8;
		}

		if (i == r - 1) {
			if (j1 == j2) {
				return grid[i][j1];
			} else {
				return grid[i][j1] + grid[i][j2];
			}
		}

		long max = (long) -1e8;

		for (int dj1 = -1; dj1 <= 1; dj1++) {
			for (int dj2 = -1; dj2 <= 1; dj2++) {
				long value = 0;
				if (j1 == j2) {
					value = grid[i][j1];
				} else {
					value = grid[i][j1] + grid[i][j2];
				}
				// Recursively call for the next row
				value += maximumChocolates(i + 1, j1 + dj1, j2 + dj2, r, c, grid);
				max = Math.max(max, value);  // Track the maximum chocolates collected
			}
		}
		return max;
	}

	public static void main(String[] args) {

		int[][] grid = new int[][] { { 2, 3, 1, 2, }, { 3, 4, 2, 2, }, { 5, 6, 3, 5 } };
		int n = grid.length;
		int m = grid[0].length;
		System.out.println(maximumChocolates(0, 0, m - 1, n, m, grid));

		// Initialize the dp array with -1
		long[][][] dp = new long[n][m][m];
		for (long[][] row : dp) {
			for (long[] col : row) {
				Arrays.fill(col, -1);
			}
		}

		System.out.println(maximumChocolatesUsingMemo(0, 0, m - 1, n, m, grid, dp));
		System.out.println(maximumChocolatesUsingTabular(n, m, grid));
		System.out.println(maximumChocolatesUsingTabularSpaceOptimal(n, m, grid));
	}
}
