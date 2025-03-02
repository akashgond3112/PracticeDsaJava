package main.dsa.nonlinear.graph.minSpanningTree.kruskalAlgorithm;

/*
Number of Provinces
Difficulty: MediumAccuracy: 54.29%Submissions: 108K+Points: 4
Given an undirected graph with V vertices. We say two vertices u and v belong to a single province if there is a path from u to v or v to u. Your task is to find the number of provinces.

Note: A province is a group of directly or indirectly connected cities and no other cities outside of the group.

Example 1:

Input:
[
 [1, 0, 1],
 [0, 1, 0],
 [1, 0, 1]
]

Output:
2
Explanation:
The graph clearly has 2 Provinces [1,3] and [2]. As city 1 and city 3 has a path between them they belong to a single province. City 2 has no path to city 1 or city 3 hence it belongs to another province.

Example 2:
Input:
[
 [1, 1],
 [1, 1]
]

Output :
1

Your Task:
You don't need to read input or print anything. Your task is to complete the function numProvinces() which takes an integer V and an adjacency matrix adj(as a 2d vector) as input and returns the number of provinces. adj[i][j] = 1, if nodes i and j are connected and adj[i][j] = 0, if not connected.


Expected Time Complexity: O(V2)
Expected Auxiliary Space: O(V)


Constraints:
1 ≤ V ≤ 500
*/

import java.util.ArrayList;
import java.util.Arrays;

public class NumberOfProvinces {

	public static int numberOfProvinces(ArrayList<ArrayList<Integer>> provinces, int vertices) {

		DisjointSet set = new DisjointSet(vertices);
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				if (provinces.get(i).get(j) == 1) {
					set.unionBySize(i, j);
				}
			}
		}

		int count = 0;
		for (int i = 0; i < vertices; i++) {
			if (set.parent.get(i) == i) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> provinces = new ArrayList<>();

		provinces.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
		provinces.add(new ArrayList<>(Arrays.asList(0, 1, 0)));
		provinces.add(new ArrayList<>(Arrays.asList(1, 0, 1)));

		int vertices = provinces.size();
		int result = numberOfProvinces(provinces, vertices);

		System.out.println("Number of Provinces: " + result);
	}

}
