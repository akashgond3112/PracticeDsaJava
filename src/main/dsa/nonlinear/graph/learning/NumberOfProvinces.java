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
}
