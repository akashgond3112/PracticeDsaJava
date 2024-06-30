package main.dsa.nonlinear.tree.binary.search.learning;

import static java.lang.System.*;

public class IntroductionFindATarget {

	public static long findTarget(int[] array, int target, int low, int high) {

		if (low > high) {
			return -1;
		}

		int mid = low + (high - low) / 2;
		if (array[mid] == target) {
			return mid;
		}

		if (target > array[mid]) {
			return findTarget(array, target, mid + 1, high);
		}
		return findTarget(array, target, low, mid - 1);
	}

	public static void main(String[] args) {

		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int target = 8;
		out.println(findTarget(array, target, 0, array.length - 1));
	}
}
