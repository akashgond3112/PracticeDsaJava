package main.dsa.nonlinear.graph.learning;

import java.util.ArrayList;

public class NumberOfProvinces {

	public static void dfs(int node, ArrayList<ArrayList<Integer>> provinces, int[] vis) {

		vis[node] = 1;
		for (int v : provinces.get(node)) {
			if (vis[v] == 0) {
				dfs(v, provinces, vis);
			}
		}
	}

	public static int numberOfProvinces(ArrayList<ArrayList<Integer>> provinces, int vertices) {

		ArrayList<ArrayList<Integer>> province = new ArrayList<>();

		for (int i = 0; i < vertices; i++) {
			province.add(new ArrayList<>());
		}

		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (province.get(i).get(j) == 1 && i != j) {
					province.get(i).add(j);
					province.get(j).add(i);
				}
			}
		}

		int[] visited = new int[vertices];
		int count = 0;
		for (int i = 0; i < vertices; i++) {
			if (visited[i] == 0) {
				dfs(i, province, visited);
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {

	}

	public static class FloodFillAlgorithm {

		public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {

			int initialColor = image[sr][sc];
			int[][] ans = image;
			int[] delRow = { -1, 0, +1, 0 };
			int[] delCol = { 0, +1, 0, -1 };
			dfs(sr, sc, ans, image, newColor, delRow, delCol, initialColor);
			return ans;

		}

		private void dfs(int sr, int sc, int[][] image, int[][] ans, int newColor, int[] delRow, int[] delCol,
				int initialColor) {

			ans[sr][sc] = newColor;
			int n = image.length;
			int m = image[0].length;

			for (int k = 0; k < 4; k++) {
				int newRow = sr + delRow[k];
				int newCol = sc + delCol[k];

				if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && image[newRow][newCol] == initialColor && ans[newRow][newCol] != newColor) {
					dfs(newRow, newCol, ans, image, newColor, delRow, delCol, initialColor);
				}
			}
		}

		public static void main(String[] args) {
			int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

			// sr = 1, sc = 1, newColor = 2
			FloodFillAlgorithm obj = new FloodFillAlgorithm();
			int[][] ans = obj.floodFill(image, 1, 1, 2);
			for (int i = 0; i < ans.length; i++) {
				for (int j = 0; j < ans[i].length; j++)
					System.out.print(ans[i][j] + " ");
				System.out.println();
			}
		}
	}
}
