package main.dsa.nonlinear.tree.binary.search.learning;

import java.util.Arrays;

import static java.lang.System.out;

public class AggressiveCowsBinarySearchHard {

	public static int searchUsingBinarySearch(int[] arr, int cows) {

		Arrays.sort(arr);

		int low = 1;
		int high = arr[arr.length - 1] - arr[0];

		while (low <= high) {
			int mid = (low + high) / 2;

			if (canWePlaceTheCow(arr, mid, cows)) {
				low = mid + 1;

			} else {
				high = mid - 1;

			}
		}

		return high;
	}

	public static int searchBruteForce(int[] arr, int cows) {

		Arrays.sort(arr);

		int max = arr[arr.length - 1] - arr[0];

		for (int i = 1; i <= max; i++) {

			if (!canWePlaceTheCow(arr, i, cows)) {
				return i - 1;
			}
		}

		return max;
	}

	private static boolean canWePlaceTheCow(int[] arr, int dist, int cows) {

		int count = 1;
		int cordinate = arr[0];
		for (int j = 1; j < arr.length; j++) {

			if (arr[j] - cordinate >= dist) {
				count++;
				cordinate = arr[j];
			}
			if (count >= cows)
				return true;
		}

		return false;
	}

	public static void main(String[] args) {

		int[] v = { 0, 3, 4, 7, 10, 9 };
		int h = 4;
		out.println(
				"The maximum possible minimum distance between any two cows will be  " + searchBruteForce(v, h) + " .");
		out.println("The maximum possible minimum distance between any two cows will be  " + searchUsingBinarySearch(v,
				h) + " .");
		v = new int[] { 4, 2, 1, 3, 6 };
		h = 2;
		out.println(
				"The maximum possible minimum distance between any two cows will be  " + searchBruteForce(v, h) + " .");
		out.println("The maximum possible minimum distance between any two cows will be  " + searchUsingBinarySearch(v,
				h) + " .");
	}
}
