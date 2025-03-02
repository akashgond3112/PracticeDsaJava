package main.dsa.nonlinear.graph.Dijkstra;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
Number of Ways to Arrive at Destination
Difficulty: MediumAccuracy: 61.13%Submissions: 60K+Points: 4
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 109 + 7.

Example 1:

Input:
n=7, m=10
edges= [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]

Output:
4
Explaination:

The four ways to get there in 7 minutes are:
- 0  6
- 0  4  6
- 0  1  2  5  6
- 0  1  3  5  6


Example 2:

Input:
n=6, m=8
edges= [[0,5,8],[0,2,2],[0,1,1],[1,3,3],[1,2,3],[2,5,6],[3,4,2],[4,5,2]]

Output:
3
Explaination:

The three ways to get there in 8 minutes are:
- 0  5
- 0  2  5
- 0  1  3  4  5


Constraints:
1 <= n <= 200
n - 1 <= roads.length <= n * (n - 1) / 2
roads[i].length == 3
0 <= ui, vi <= n - 1
1 <= timei <= 109
ui != vi
There is at most one road connecting any two intersections.
You can reach any intersection from any other intersection.

Expected Time Complexity: O(M * logN + N)
Expected Space Complexity: O(M+N)
*/
public class NumberOfWaysToArriveAtDestination {

	static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	static int countPaths(int n, List<List<Integer>> roads) {

		List<AbstractList<Pair>> adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}
		int m = roads.size();
		for (int i = 0; i < m; i++) {
			adjList.get(roads.get(i).get(0)).add(new Pair(roads.get(i).get(1), roads.get(i).get(2)));
			adjList.get(roads.get(i).get(1)).add(new Pair(roads.get(i).get(0), roads.get(i).get(2)));
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.first));
		pq.add(new Pair(0, 0));

		int[] ways = new int[n];
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			dist[i] = (int) 1e9;
			ways[i] = 0;
		}
		dist[0] = 0;
		ways[0] = 1;
		int mod = (int) (1e9 + 7);
		while (!pq.isEmpty()) {
			Pair pair = pq.poll();
			int dis = pair.first;
			int node = pair.second;

			for (Pair p : adjList.get(node)) {
				int adjNode = p.first;
				int edW = p.second;

				if (dis + edW < dist[adjNode]) {
					dist[adjNode] = dis + edW;
					pq.add(new Pair(dis + edW, adjNode));
					ways[adjNode] = ways[node];
				} else if (dis + edW == dist[adjNode]) {
					ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
				}
			}
		}
		return ways[n - 1] % mod;

	}

	public static void main(String[] args) {

		int n = 7;
		int m = 10;
		List<List<Integer>> roads = Collections.unmodifiableList(new ArrayList<>() {
			{
				add(new ArrayList<>(Arrays.asList(0, 6, 7)));
				add(new ArrayList<>(Arrays.asList(0, 1, 2)));
				add(new ArrayList<>(Arrays.asList(1, 2, 3)));
				add(new ArrayList<>(Arrays.asList(1, 3, 3)));
				add(new ArrayList<>(Arrays.asList(6, 3, 3)));
				add(new ArrayList<>(Arrays.asList(3, 5, 1)));
				add(new ArrayList<>(Arrays.asList(6, 5, 1)));
				add(new ArrayList<>(Arrays.asList(2, 5, 1)));
				add(new ArrayList<>(Arrays.asList(0, 4, 5)));
				add(new ArrayList<Integer>(Arrays.asList(4, 6, 2)));

			}
		});

		System.out.println(countPaths(n, roads));
	}
}
