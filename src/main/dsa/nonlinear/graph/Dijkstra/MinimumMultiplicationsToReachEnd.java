package main.dsa.nonlinear.graph.Dijkstra;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Minimum Multiplications to reach End
Difficulty: MediumAccuracy: 48.94%Submissions: 105K+Points: 4
Given start, end and an array arr of n numbers. At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.

Your task is to find the minimum steps in which end can be achieved starting from start. If it is not possible to reach end, then return -1.

Example 1:

Input:
arr[] = {2, 5, 7}
start = 3, end = 30
Output:
2
Explanation:
Step 1: 3*2 = 6 % 100000 = 6
Step 2: 6*5 = 30 % 100000 = 30
Example 2:

Input:
arr[] = {3, 4, 65}
start = 7, end = 66175
Output:
4
Explanation:
Step 1: 7*3 = 21 % 100000 = 21
Step 2: 21*3 = 63 % 100000 = 63
Step 3: 63*65 = 4095 % 100000 = 4095
Step 4: 4095*65 = 266175 % 100000 = 66175
Your Task:
You don't need to print or input anything. Complete the function minimumMultiplications() which takes an integer array arr, an integer start and an integer end as the input parameters and returns an integer, denoting the minumum steps to reach in which end can be achieved starting from start.

Expected Time Complexity: O(105)
Expected Space Complexity: O(105)

Constraints:

1 <= n <= 104
1 <= arr[i] <= 104
1 <= start, end < 105
*/
public class MinimumMultiplicationsToReachEnd {

	public static class Pair {
		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	int minimumMultiplications(int[] arr, int start, int end) {

		// Your code here
		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(start, 0));
		int[] dist = new int[100000];
		Arrays.fill(dist, (int) 1e9);
		dist[start] = 0;
		int mod = 100000;
		while (!queue.isEmpty()) {
			Pair pair = queue.poll();
			int node = pair.first;
			int step = pair.second;

			for (int j : arr) {
				int newDist = (j * node) % mod;

				if (step + 1 < dist[newDist]) {
					dist[newDist] = step + 1;

					if (newDist == end)
						return step + 1;
					queue.add(new Pair(newDist, step + 1));
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) {

		int start = 3;
		int end = 30;
		int[] arr = { 2, 5, 7 };

		MinimumMultiplicationsToReachEnd obj = new MinimumMultiplicationsToReachEnd();
		int ans = obj.minimumMultiplications(arr, start, end);

		System.out.print(ans);
		System.out.println();
	}
}
