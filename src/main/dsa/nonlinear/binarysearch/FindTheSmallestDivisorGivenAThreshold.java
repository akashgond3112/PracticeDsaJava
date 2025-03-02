package main.dsa.nonlinear.binarysearch;

import static java.lang.System.out;
import static main.dsa.nonlinear.binarysearch.KokoEatingBanana.findMax;

public class FindTheSmallestDivisorGivenAThreshold {

	public static int findTheSmallestDivisor(int[] arr, int threshold) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int n = arr.length;
		if (n > threshold)
			return -1;

		int low = 0;
		int high = findMax(arr);

		while (low <= high) {
			int mid = low + (high - low) / 2;

			int sum = findTotalSum(arr, mid);

			if (sum > threshold) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static int findTotalSum(int[] arr, int mid) {

		int totalH = 0;
		//find total hours:
		for (int j : arr) {
			totalH += (int) Math.ceil((double) j / (double) (mid));
		}
		return totalH;
	}

	public static void main(String[] args) {

		int[] v = { 7, 15, 6, 3 };
		int h = 8;
		int ans = findTheSmallestDivisor(v, h);
		out.println("The min threshold will be  " + ans + " .");
	}
}
