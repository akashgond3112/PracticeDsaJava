package main.dsa.nonlinear.graph.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
Cheapest Flights Within K Stops
Difficulty: MediumAccuracy: 49.99%Submissions: 32K+Points: 4
There are n cities and m edges connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from the city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.

Note: The price from city A to B may be different From the price from city B to A.

Example 1:
Input:
n = 4
flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]]
src = 0
dst = 3
k = 1
Output:
700
Explanation:
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.

Constraint:
1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between the two cities.
0 <= src, dst, k < n
src != dst
*/
public class CheapestFlightsWithinKStops {

	public static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static class Tuple {
		int first;
		int second;
		int third;

		public Tuple(int first, int second, int third) {
			this.first = first;
			this.second = second;
			this.third = third;
		}
	}

	public int cheapestFLight(int n, int[][] flights, int src, int dst, int k) {
		// Code here
		List<ArrayList<Pair>> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new ArrayList<>());
		}

		for (int[] flight : flights) {
			list.get(flight[0]).add(new Pair(flight[1], flight[2]));
		}

		Queue<Tuple> q = new LinkedList<>();
		q.add(new Tuple(0, src, 0));

		int[] dist = new int[n];
		Arrays.fill(dist, (int) 1e9);

		dist[src] = 0;
		while (!q.isEmpty()) {
			Tuple t = q.peek();
			q.remove();
			int stop = t.first;
			int node = t.second;
			int distance = t.third;

			if (stop > k)
				continue;
			for (Pair pair : list.get(node)) {
				int adjNode = pair.first;
				int adjDist = pair.second;

				if (distance + adjDist < dist[adjNode]) {
					dist[adjNode] = distance + adjDist;
					q.add(new Tuple(stop + 1, adjNode, distance + adjDist));
				}
			}
		}

		if (dist[dst] == (int) 1e9)
			return -1;
		return dist[dst];
	}

	public static void main(String[] args) {

		int n = 4;
		int[][] flights = { { 0, 1, 100 }, { 1, 2, 100 }, { 2, 0, 100 }, { 1, 3, 600 }, { 2, 3, 200 } };
		int src = 0;
		int dst = 2;
		int k = 1;

		CheapestFlightsWithinKStops cheapestFlights = new CheapestFlightsWithinKStops();
		System.out.println(cheapestFlights.cheapestFLight(n, flights, src, dst, k));
	}
}
