package main.dsa.nonlinear.graph;

/*
Rotten Oranges
Difficulty: MediumAccuracy: 46.02%Submissions: 141K+Points: 4
Given a grid of dimension nxm where each cell in the grid can have values 0, 1 or 2 which has the following meaning:
0 : Empty cell
1 : Cells have fresh oranges
2 : Cells have rotten oranges

We have to determine what is the earliest time after which all the oranges are rotten.
A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1] (up, down, left and right) in unit time.

Example 1:
Input: grid = {{0,1,2},{0,1,2},{2,1,1}}
Output: 1
Explanation: The grid is-
0 1 2
0 1 2
2 1 1
Oranges at positions (0,2), (1,2), (2,0)
will rot oranges at (0,1), (1,1), (2,2) and
(2,1) in unit time.

Example 2:
Input: grid = {{2,2,0,1}}
Output: -1
Explanation: The grid is-
2 2 0 1
Oranges at (0,0) and (0,1) can't rot orange at
(0,3).


Your Task:
You don't need to read or print anything, Your task is to complete the function orangesRotting() which takes grid as input parameter and returns the minimum time to rot all the fresh oranges. If not possible returns -1.

Expected Time Complexity: O(n*m)
Expected Auxiliary Space: O(n*m)

Constraints:
1 ≤ n, m ≤ 500
* */

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

	class Pair {
		int row;
		int col;
		int count;

		public Pair(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}

	public int orangesRotting(int[][] grid) {
		// Code here

		int n = grid.length;
		int m = grid[0].length;
		Queue<Pair> q = new LinkedList<>();
		int[][] visited = new int[n][m];
		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2) {
					q.add(new Pair(i, j, 0));
					visited[i][j] = 2;
				} else {
					visited[i][j] = 0;
				}

				if (grid[i][j] == 1)
					count++;
			}
		}

		int tm = 0;
		int dRow[] = { -1, 0, +1, 0 };
		int dCol[] = { 0, 1, 0, -1 };
		int newCount = 0;
		while (!q.isEmpty()) {
			int curRow = q.peek().row;
			int curCol = q.peek().col;
			int curt = q.peek().count;

			tm = Math.max(tm, curt);
			q.remove();

			for (int i = 0; i < 4; i++) {
				int newRow = curRow + dRow[i];
				int newCol = curCol + dCol[i];

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && visited[newRow][newCol] !=2 && grid[newRow][newCol] == 1) {
					q.add(new Pair(newRow, newCol, curt + 1));
					visited[newRow][newCol] = 2;
					newCount++;
				}
			}
		}
		if (newCount != count)
			return -1;
		return tm;
	}

	public static void main(String[] args) {
		int[][] grid = {
				{ 0, 1, 2 },
				{ 0, 1, 2 },
				{ 2, 1, 1 }
		};

		RottenOranges obj = new RottenOranges();
		int ans = obj.orangesRotting(grid);
		System.out.println("The earliest time after which all the oranges are rotten : " +ans);
	}
}
