package main.dsa.nonlinear.tree.binary.search.learning;

import static java.lang.System.out;

public class KthMissingPositiveNumber {

	public static int findKthPositive(int[] arr, int k) {

		int low = 0;
		int high = arr.length - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			int missing = arr[mid] - (mid + 1);

			if (missing < k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low + k;
	}

	public static void main(String[] args) {

		int[] v = { 2,4,5,7 };
		int h = 3;
		int ans = findKthPositive(v, h);
		out.println("The missing numbers are  " + ans + " .");
	}
}
