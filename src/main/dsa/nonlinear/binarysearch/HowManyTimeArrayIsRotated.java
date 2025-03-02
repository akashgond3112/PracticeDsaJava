package main.dsa.nonlinear.binarysearch;

public class HowManyTimeArrayIsRotated {

	public static int getNumberOfTimes(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int low = 0;
		int n = arr.length;
		int high = n - 1;
		int ans = Integer.MAX_VALUE;
		int index = -1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (arr[low] <= arr[high]) {
				if (arr[low] < ans) {
					index = low;
					ans = arr[low];
				}
				break;
			}

			if (arr[low] < arr[mid]) {
				if (arr[low] < ans) {
					index = low;
					ans = arr[low];
				}
				low = mid + 1;
			} else {
				if (arr[mid] < ans) {
					index = mid;
					ans = arr[mid];
				}
				high = mid - 1;
			}
		}
		return index;
	}

	public static void main(String[] args) {

		int[] arr = { 10, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println(getNumberOfTimes(arr));
	}
}
