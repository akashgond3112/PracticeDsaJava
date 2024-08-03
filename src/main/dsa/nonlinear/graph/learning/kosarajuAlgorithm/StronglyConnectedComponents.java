package main.dsa.nonlinear.graph.learning.kosarajuAlgorithm;

import java.util.ArrayList;
import java.util.Stack;

/*
Strongly Connected Components (Kosaraju's Algo)
Difficulty: MediumAccuracy: 50.61%Submissions: 89K+Points: 4
Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges, Find the number of strongly connected components in the graph.

Example 1:

Input:

Output:
3
Explanation:

We can clearly see that there are 3 Strongly
Connected Components in the Graph
Example 2:

Input:

Output:
1
Explanation:
All of the nodes are connected to each other.
So, there's only one SCC.

Your Task:
You don't need to read input or print anything. Your task is to complete the function kosaraju() which takes the number of vertices V and adjacency list of the graph of size V as inputs and returns an integer denoting the number of strongly connected components in the given graph.

Expected Time Complexity: O(V+E).
Expected Auxiliary Space: O(V+E).

Constraints:
1 ≤ V ≤ 5000
0 ≤ E ≤ (V*(V-1))
0 ≤ u, v ≤ V-1
Sum of E over all testcases will not exceed 25*106
*/
public class StronglyConnectedComponents {

    public int kosaraju(int v, ArrayList<ArrayList<Integer>> adj) {
        // Step 1 : Sort all edges according to the finish time,
        int[] visited = new int[v];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, adj, stack);
            }
        }

        // Step 2: Reverse the edges;
        ArrayList<ArrayList<Integer>> adjT = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjT.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            visited[i] = 0;
            for (Integer it : adj.get(i)) {
                adjT.get(it).add(i);
            }
        }

        // Step 3 : Do dfs on reverse graph
        int scc = 0;
        while (!stack.empty()) {
            int node = stack.peek();
            stack.pop();

            if (visited[node] == 0) {
                scc++;
                dfsNew(node, visited, adjT);
            }
        }
        return scc;

    }

    private void dfsNew(int node, int[] visited, ArrayList<ArrayList<Integer>> adjT) {

        visited[node] = 1;

        for (Integer it : adjT.get(node)) {
            if (visited[it] == 0) {
                dfsNew(it, visited, adjT);
            }
        }
    }

    private void dfs(int i, int[] visited, ArrayList<ArrayList<Integer>> adj, Stack<Integer> stack) {

        visited[i] = 1;
        for (Integer it : adj.get(i)) {
            if (visited[it] == 0) {
                dfs(it, visited, adj, stack);
            }
        }
        stack.push(i);
    }


    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {1, 0}, {0, 2},
                {2, 1}, {0, 3},
                {3, 4}
        };
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
        }
        StronglyConnectedComponents obj = new StronglyConnectedComponents();
        int ans = obj.kosaraju(n, adj);
        System.out.println("The number of strongly connected components is: " + ans);
    }
}
