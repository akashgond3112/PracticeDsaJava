package main.dsa.nonlinear.binary.search.learning;


import java.text.MessageFormat;

import static java.lang.System.out;
import static main.dsa.nonlinear.binary.search.learning.CapacityToShipPackagesWithinDDays.getTotalSum;
import static main.dsa.nonlinear.binary.search.learning.KokoEatingBanana.findMax;

public class AllocateBooks {

	public static int findPagesBinarySearch(int[] arr, int m) {
		// Write your code here.

		if (m > arr.length)
			return -1;

		int low = findMax(arr);
		int high = getTotalSum(arr);

		while (low <= high) {
			int mid = (low + high) / 2;

			int noOfStudent = maxPagesAllowed(arr, mid);

			if (noOfStudent > m) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}

	public static int findPagesBruteForce(int[] arr, int m) {
		// Write your code here.

		if (m > arr.length)
			return -1;

		int low = findMax(arr);
		int high = getTotalSum(arr);

		for (int i = low; i <= high; i++) {

			int noOfStudent = maxPagesAllowed(arr, i);

			if (noOfStudent == m) {
				return i;
			}
		}

		return -1;

	}

	private static int maxPagesAllowed(int[] arr, int maxPages) {

		int intialStudent = 1;
		int initialStudentPages = 0;
		for (int j : arr) {

			if (initialStudentPages + j <= maxPages) {
				initialStudentPages += j;
			} else {
				intialStudent++;
				initialStudentPages = j;
			}
		}
		return intialStudent;
	}

	public static void main(String[] args) {

		int[] v = { 12, 34, 67, 90 };
		int h = 2;
		out.println(MessageFormat.format("We are getting the minimum in the last case as :  {0} .",
				findPagesBruteForce(v, h)));
		out.println(MessageFormat.format("We are getting the minimum in the last case as :  {0} .",
				findPagesBinarySearch(v, h)));
		v = new int[] { 25, 46, 28, 49, 24 };
		h = 4;
		out.println("We are getting the minimum in the last case as :  " + findPagesBruteForce(v, h) + " .");
		out.println("We are getting the minimum in the last case as :  " + findPagesBinarySearch(v, h) + " .");
	}
}
