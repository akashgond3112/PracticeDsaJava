package main.dsa.nonlinear.graph.learning.minSpanningTree.kruskalAlgorithm;

/*
Connecting the graph
Difficulty: MediumAccuracy: 52.79%Submissions: 18K+Points: 4
You are given a graph with n vertices (0 to n-1) and m edges. You can remove one edge from anywhere and add that edge between any two vertices in one operation. Find the minimum number of operations that will be required to connect the graph.
If it is not possible to connect the graph, return -1.

Example 1:

Input:
n = 4
m = 3
Edges = [ [0, 1] , [0, 2] , [1, 2] ]
Output:
1
Explanation:
Remove edge between vertices 1 and 2 and add between vertices 1 and 3.


Example 2:

Input:
n = 6
m = 5
Edges = [ [0,1] , [0,2] , [0,3] , [1,2] , [1,3] ]
Output:
2
Explanation:
Remove edge between (1,2) and(0,3) and add edge between (1,4) and (3,5)

Your Task:
You don't need to read or print anything. Your task is to complete the function solve() which takes an integer n denoting a number of vertices and a 2d matrix denoting the edges of a graph and returns the minimum number of operations to connect a graph.

Expected Time Complexity: O(m*n)
Expected Space Complexity: O(m*n)

Constraints:
1<=n<=105
0<=m<=102
1<=edge[i][0],edge[i][1]<=n-1
*/
public class ConnectingTheGraph {

	public int solve(int n, int[][] edge) {
		// Code here
		DisjointSet set = new DisjointSet(n);
		int count = 0;

		for (int[] ints : edge) {
			int u = ints[0];
			int v = ints[1];
			if (set.findUPar(u) == set.findUPar(v)) {
				count++;
			} else {
				set.unionBySize(u, v);
			}
		}

		int newC = 0;
		for (int i = 0; i < n; i++) {
			if (set.parent.get(i) == i)
				newC++;
		}
		int ans = newC - 1;
		if (count >= ans)
			return ans;
		return -1;

	}

	public static void main(String[] args) {

		ConnectingTheGraph cg = new ConnectingTheGraph();
		int[][] edge = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
		System.out.println(cg.solve(4, edge));
	}
}
