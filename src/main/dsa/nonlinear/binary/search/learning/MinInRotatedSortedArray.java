package main.dsa.nonlinear.binary.search.learning;

public class MinInRotatedSortedArray {

	private static int findMin(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int low = 0;
		int n = arr.length - 1;
		int high = n - 1;
		int ans = Integer.MAX_VALUE;
		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[low] <= arr[mid]) {
				ans = Math.min(ans, arr[low]);
				low = mid + 1;
			} else {
				ans = Math.min(ans, arr[mid]);
				high = mid - 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {

		int[] arr = { 10, 11, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(findMin(arr));
	}
}
