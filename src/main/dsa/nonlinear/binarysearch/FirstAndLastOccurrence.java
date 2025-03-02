package main.dsa.nonlinear.binarysearch;

import static java.lang.System.*;
import static main.dsa.nonlinear.binarysearch.LowerUpperBound.findLowerBound;
import static main.dsa.nonlinear.binarysearch.LowerUpperBound.findUpperBound;

public class FirstAndLastOccurrence {

	private static int[] getFirstAndLastOccurrence(int[] arr, int n, int target) {

		int lowerBound = findLowerBound(arr, target, n);

		if (lowerBound == n || arr[lowerBound] != target) {

			return new int[] { -1, -1 };
		}
		return new int[] { lowerBound, findUpperBound(arr, target, n) - 1 };

	}

	static int findFirstOccurrence(int[] arr, int target) {

		int low = 0, high = arr.length - 1, found = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == target) {
				found = mid;
				high = mid - 1;
			} else if (arr[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return found;
	}

	static int findLastOccurrence(int[] arr, int target) {
		int low = 0, high = arr.length - 1, found = -1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == target) {
				found = mid;
				low = mid + 1;
			} else if (arr[mid] < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return found;
	}

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int n = arr.length;
		int target = 9;
		int[] firstAndLastOccurrence = getFirstAndLastOccurrence(arr, n, target);
		out.println(firstAndLastOccurrence[0] + " " + firstAndLastOccurrence[1]);

		out.println(findFirstOccurrence(arr, target) == -1 ?
				0 :
				findFirstOccurrence(arr, target) + " " + findLastOccurrence(arr, target));
	}
}
