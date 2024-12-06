package main.revision.recursion;

import java.util.ArrayList;

public class RatMaze {

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
			solvePath(0, 0, mat, mat.length, path, "", visited);
		}
		return path;
	}

	public static void main(String[] args) {
		int[][] mat = new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 1, 1, 1 } };
		System.out.println(findPath(mat));
	}
}
