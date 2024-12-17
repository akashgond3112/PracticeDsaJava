package main.dsa.nonlinear.binary.search.learning;

import java.util.Objects;
import java.util.PriorityQueue;

public class MinimiseMaximumDistanceBetweenGasStations {

	public static class Pair {
		double first;
		int second;

		Pair(double first, int second) {
			this.first = first;
			this.second = second;
		}
	}

	public static double minimumGasStationsTimeLimitExceeded(int[] arr, int k) {

		int[] howMany = new int[arr.length - 1];

		for (int i = 1; i <= k; i++) {

			double max = -1;
			int maxIndex = -1;

			for (int j = 0; j < arr.length - 1; j++) {
				double diff = (arr[j + 1] - arr[j]);
				double sections = diff / (howMany[j] + 1);
				if (sections > max) {
					max = sections;
					maxIndex = j;
				}
			}
			howMany[maxIndex]++;

		}

		double maxAns = -1;
		for (int i = 0; i < arr.length - 1; i++) {
			double diff = (arr[i + 1] - arr[i]);
			double sections = diff / (howMany[i] + 1);
			maxAns = Math.max(maxAns, sections);
		}
		return maxAns;
	}

	public static double minimumGasStationsUsingPriorityQueue(int[] arr, int k) {

		int n = arr.length;
		int[] howMany = new int[n - 1];

		PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.first, a.first));

		for (int i = 0; i < n-1; i++) {
			pq.offer(new Pair((arr[i + 1] - arr[i]), i));
		}

		for (int i = 1; i <= k; i++) {
			var top = pq.peek();
			pq.poll();
			int sectionIndex = top.second;
			howMany[sectionIndex]++;
			double initialDiff = (double) arr[sectionIndex + 1] - (double) arr[sectionIndex];
			double newDiff = initialDiff / (howMany[sectionIndex] + 1);
			pq.offer(new Pair(newDiff, sectionIndex));
		}


		assert pq.peek() != null;
		return pq.peek().first;
	}

	public static int numberOfGasStationsRequired(double dist, int[] arr) {
		int n = arr.length; // size of the array
		int cnt = 0;
		for (int i = 1; i < n; i++) {
			int numberInBetween = (int)((arr[i] - arr[i - 1]) / dist);
			if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
				numberInBetween--;
			}
			cnt += numberInBetween;
		}
		return cnt;
	}

	public static double minimiseMaxDistance(int[] arr, int k) {
		int n = arr.length; // size of the array
		double low = 0;
		double high = 0;

		//Find the maximum distance:
		for (int i = 0; i < n - 1; i++) {
			high = Math.max(high, arr[i + 1] - arr[i]);
		}

		//Apply Binary search:
		double diff = 1e-6 ;
		while (high - low > diff) {
			double mid = (low + high) / (2.0);
			int cnt = numberOfGasStationsRequired(mid, arr);
			if (cnt > k) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return high;
	}

	public static void main(String[] args) {

		int[] arr = { 2, 3, 4, 5, 6, 7 };
		System.out.println("The answer is: " + minimumGasStationsTimeLimitExceeded(arr, 6));
		System.out.println("The answer is: " + minimumGasStationsUsingPriorityQueue(arr, 6));
		System.out.println("The answer is: " + minimiseMaxDistance(arr, 6));
	}

}
