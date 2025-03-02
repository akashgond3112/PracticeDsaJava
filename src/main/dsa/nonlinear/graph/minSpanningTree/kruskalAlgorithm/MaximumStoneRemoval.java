package main.dsa.nonlinear.graph.minSpanningTree.kruskalAlgorithm;

import java.util.HashMap;
import java.util.Map;

/*
Maximum Stone Removal
Difficulty: MediumAccuracy: 49.82%Submissions: 15K+Points: 4
There are n stones at some integer coordinate points on a 2D plane. Each coordinate point may have at most one stone.

You need to remove some stones.

A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the maximum possible number of stones that you can remove.

Example 1:

Input:
n=6
[[0 0] ,[ 0 1], [1 0] ,[1 2] ,[2 1] ,[2 2]]

Output:
5

Example:
One way to remove 5 stones are
1--[0,0]
2--[1,0]
3--[0,1]
4--[2,1]
5--[1,2]

Your Task:

You don't need to print or input anything. Complete the function maxRemove() which takes an integer array arr, an integer n as the input parameters and returns an integer, denoting the maximum number of stones that can be removed.

Expected Time Complexity: O(N+K)

Expected Space Complexity: O(K)

Constraints:

1 <= n <=1000
0 <= x[i], y[i]<= 104
No two stones are at same position.
*/
public class MaximumStoneRemoval {

    static int maxRemove(int[][] stones, int n) {
        // Code here

        int maxROw = 0;
        int maxCol = 0;

        for (int i = 0; i < n; i++) {
            maxROw = Math.max(maxROw, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet disjointSet = new DisjointSet(maxROw + maxCol + 1);

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxROw + 1;

            disjointSet.unionBySize(nodeRow, nodeCol);
            map.put(nodeRow, 1);
            map.put(nodeCol, 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> it : map.entrySet()) {
            if (disjointSet.findUPar(it.getKey()) == it.getKey()) {
                count++;
            }
        }
        return n- count;
    }

    public static void main(String[] args) {

        int n = 6;
        int[][] stones = {{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};

        System.out.println(maxRemove(stones, n));
    }
}
