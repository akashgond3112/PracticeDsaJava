package main.dsa.nonlinear.tree.binary.search.learning;

public class MinDaysToMakeMBouquets {

	public static int getMinDaysToMakeMBouquets(int[] arr, int m, int k) {

		long val = (long) m * k;
		int n = arr.length;

		if (val > n)
			return -1;

		int low = findMin(arr);
		int high = findMax(arr);

		while (low < high) {
			int mid = (low + high) / 2;

			if (possible(arr, mid, m, k)) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	private static boolean possible(int[] arr, int mid, int m, int k) {

		int count = 0;
		int noOfBouquets = 0;

		for (int j : arr) {
			if (j <= mid) {
				count++;
			} else {
				noOfBouquets += (count / k);
				count = 0;
			}
		}
		noOfBouquets += (count / k);
		return noOfBouquets >= m;
	}

	public static int findMax(int[] v) {
		int maxi = Integer.MIN_VALUE;
		//find the maximum:
		for (int j : v) {
			maxi = Math.max(maxi, j);
		}
		return maxi;
	}

	public static int findMin(int[] v) {
		int min = Integer.MAX_VALUE;
		//find the min:
		for (int j : v) {
			min = Math.min(min, j);
		}
		return min;
	}

	public static void main(String[] args) {
		int[] arr = { 7, 7, 7, 7, 13, 11, 12, 7 };
		int k = 3;
		int m = 2;
		int ans = getMinDaysToMakeMBouquets(arr, k, m);
		if (ans == -1)
			System.out.println("We cannot make m bouquets.");
		else
			System.out.println("We can make bouquets on day " + ans);
	}
}
