package main.recursion.backtracking;

import java.util.ArrayList;

/*
Rat in a Maze Problem - I
Difficulty: MediumAccuracy: 35.75%Submissions: 290K+Points: 4
Consider a rat placed at (0, 0) in a square matrix mat of order n* n. It has to reach the destination at (n - 1, n - 1). Find all possible paths that the rat can take to reach from source to destination. The directions in which the rat can move are 'U'(up), 'D'(down), 'L' (left), 'R' (right). Value 0 at a cell in the matrix represents that it is blocked and rat cannot move to it while value 1 at a cell in the matrix represents that rat can be travel through it.
Note: In a path, no cell can be visited more than one time. If the source cell is 0, the rat cannot move to any other cell. In case of no path, return an empty list. The driver will output "-1" automatically.

Examples:

Input: mat[][] = [[1, 0, 0, 0],
                [1, 1, 0, 1],
                [1, 1, 0, 0],
                [0, 1, 1, 1]]
Output: DDRDRR DRDDRR
Explanation: The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.
Input: mat[][] = [[1, 0],
                [1, 0]]
Output: -1
Explanation: No path exists and destination cell is blocked.
Expected Time Complexity: O(3n^2)
Expected Auxiliary Space: O(l * x)
Here l = length of the path, x = number of paths.

Constraints:
2 ≤ n ≤ 5
0 ≤ mat[i][j] ≤ 1
*/
public class RatInAMazeProblemI {


	private static void solvePathOptimal(int i, int j, int[][] mat, int n, ArrayList<String> path, String s,
			boolean[][] visited) {

		if (i == n - 1 && j == n - 1) {
			path.add(s);
			return;
		}
		char[] dir = new char[] { 'D', 'L', 'R', 'U' };

		// Down, Left, Right, Up
		int[] dirI = new int[] { 1, 0, 0, -1 };
		int[] dirJ = new int[] { 0, -1, 1, 0 };

		for (int index = 0; index < 4; index++) {
			int x = i + dirI[index];
			int y = j + dirJ[index];
			if (x >= 0 && y >= 0 && x < n && y < n && !visited[x][y] && mat[x][y] == 1) {
				visited[x][y] = true;
				solvePathOptimal(x, y, mat, n, path, s + dir[index], visited);
				visited[x][y] = false;
			}
		}

	}

	private static void solvePath(int i, int j, int[][] mat, int n, ArrayList<String> path, String s,
			boolean[][] visited) {

		if (i == n - 1 && j == n - 1) {
			path.add(s);
			return;
		}

		//Down
		if (i + 1 < n && !visited[i][j] && mat[i + 1][j] == 1) {
			visited[i][j] = true;
			solvePath(i + 1, j, mat, n, path, s + "D", visited);
			visited[i][j] = false;
		}
		//Left
		if (j - 1 > 0 && !visited[i][j - 1] && mat[i][j - 1] == 1) {
			visited[i][j] = true;
			solvePath(i, j - 1, mat, n, path, s + "L", visited);
			visited[i][j] = false;
		}
		//Right
		if (j + 1 < n && !visited[i][j + 1] && mat[i][j + 1] == 1) {
			visited[i][j] = true;
			solvePath(i, j + 1, mat, n, path, s + "R", visited);
			visited[i][j] = false;
		}
		//Up
		if (i - 1 >= 0 && !visited[i - 1][j] && mat[i - 1][j] == 1) {
			visited[i][j] = true;
			solvePath(i - 1, j, mat, n, path, s + "U", visited);
			visited[i][j] = false;
		}

	}

	public static ArrayList<String> findPath(int[][] mat) {
		// Your code here

		ArrayList<String> path = new ArrayList<>();
		boolean[][] visited = new boolean[mat.length][mat[0].length];
		if (mat[0][0] == 1) {
			solvePathOptimal(0, 0, mat, mat.length, path, "", visited);
		}
		return path;
	}

	public static void main(String[] args) {
		int[][] mat = new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };
		System.out.println(findPath(mat));
	}
}
