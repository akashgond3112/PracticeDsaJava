package main.dsa.nonlinear.binarysearch;

import static java.lang.System.*;

public class LowerUpperBound {


	public static int findLowerBound(int[] array, int target, int n) {

		int low = 0;
		int high = array.length - 1;
		int ans = n;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (array[mid] >= target) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	public static int findUpperBound(int[] array, int target, int n) {

		int low = 0;
		int high = array.length - 1;
		int ans = n;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (array[mid] > target) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}


	public static void main(String[] args) {

		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		out.println(findLowerBound(array, 7, array.length));
		out.println(findUpperBound(array, 2, array.length));
	}
}
