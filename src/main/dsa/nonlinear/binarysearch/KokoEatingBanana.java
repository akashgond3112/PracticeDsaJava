package main.dsa.nonlinear.binarysearch;

import static java.lang.System.*;

public class KokoEatingBanana {

	public static int minTimeToEat(int[] arr, int k) {

		int low = 0;
		int high = findMax(arr);

		while (low <= high) {
			int mid = low + (high - low) / 2;

			int totalEat = findTotalHours(arr, mid);

			if (totalEat <= k) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	public static int findMax(int[] v) {
		int maxi = Integer.MIN_VALUE;
		//find the maximum:
		for (int j : v) {
			maxi = Math.max(maxi, j);
		}
		return maxi;
	}

	private static int findTotalHours(int[] arr, int mid) {

		int totalH = 0;
		//find total hours:
		for (int j : arr) {
			totalH += (int) Math.ceil((double) j / (double) (mid));
		}
		return totalH;
	}

	public static void main(String[] args) {

		int[] v = {7, 15, 6, 3};
		int h = 8;
		int ans = minTimeToEat(v, h);
		out.println("Koko should eat at least " + ans + " bananas/hr.");
	}
}
