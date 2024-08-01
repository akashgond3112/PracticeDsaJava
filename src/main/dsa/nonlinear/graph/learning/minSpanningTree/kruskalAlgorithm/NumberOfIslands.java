package main.dsa.nonlinear.graph.learning.minSpanningTree.kruskalAlgorithm;

import java.util.ArrayList;
import java.util.List;

/*
Number Of Islands
Difficulty: MediumAccuracy: 60.65%Submissions: 38K+Points: 4
You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting the number of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two integer A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in the matrix after each operation.You need to return an array of size k.
Note : An island means group of 1s such that they share a common side.



Example 1:

Input: n = 4
m = 5
k = 4
A = {{1,1},{0,1},{3,3},{3,4}}

Output: 1 1 2 2
Explanation:
0.  00000
    00000
    00000
    00000
1.  00000
    01000
    00000
    00000
2.  01000
    01000
    00000
    00000
3.  01000
    01000
    00000
    00010
4.  01000
    01000
    00000
    00011




Example 2:

Input: n = 4
m = 5
k = 4
A = {{0,0},{1,1},{2,2},{3,3}}

Output: 1 2 3 4
Explanation:
0.  00000
    00000
    00000
    00000
1.  10000
    00000
    00000
    00000
2.  10000
    01000
    00000
    00000
3.  10000
    01000
    00100
    00000
4.  10000
    01000
    00100
    00010


Your Task:
You don't need to read or print anything. Your task is to complete the function numOfIslands() which takes an integer n denoting no. of rows in the matrix, an integer m denoting the number of columns in the matrix and a 2D array of size k denoting  the number of operators.

Expected Time Complexity: O(m * n)
Expected Auxiliary Space: O(m * n)

Constraints:

1 <= n,m <= 100
1 <= k <= 1000
*/
public class NumberOfIslands {

	static boolean isValid(int row, int col, int n, int m) {
		return row >= 0 && row < n && col >= 0 && col < m;
	}

	public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
		//Your code here
		DisjointSet set = new DisjointSet(rows * cols);
		int[][] visited = new int[rows][cols];
		int cnt = 0;
		List<Integer> islands = new ArrayList<>();

		for (int[] operator : operators) {
			int row = operator[0];
			int col = operator[1];

			if (visited[row][col] == 1) {
				islands.add(cnt);
				continue;
			}
			visited[row][col] = 1;
			cnt++;

			int[] dr = { -1, 0, 1, 0 };
			int[] dc = { 0, 1, 0, -1 };
			for (int d = 0; d < 4; d++) {

				int adjacentRow = row + dr[d];
				int adjacentCol = col + dc[d];

				if (isValid(adjacentRow, adjacentCol, rows, cols)) {
					if (visited[adjacentRow][adjacentCol] == 1) {
						int node = row * cols + col;
						int adjNodeNo = adjacentRow * cols + adjacentCol;

						if (set.findUPar(node) != set.findUPar(adjNodeNo)) {
							cnt--;
							set.unionBySize(node, adjNodeNo);
						}
					}
				}
			}
			islands.add(cnt);
		}
		return islands;
	}

	public static void main(String[] args) {
		int n = 4, m = 5;
		int[][] operators = { { 0, 0 }, { 0, 0 }, { 1, 1 }, { 1, 0 }, { 0, 1 }, { 0, 3 }, { 1, 3 }, { 0, 4 }, { 3, 2 },
				{ 2, 2 }, { 1, 2 }, { 0, 2 } };

		NumberOfIslands obj = new NumberOfIslands();
		List<Integer> ans = obj.numOfIslands(n, m, operators);

		int sz = ans.size();
		for (Integer an : ans) {
			System.out.print(an + " ");
		}
		System.out.println();
	}
}
