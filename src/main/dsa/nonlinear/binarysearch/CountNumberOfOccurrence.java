package main.dsa.nonlinear.binarysearch;

import static java.lang.System.*;
import static main.dsa.nonlinear.binarysearch.FirstAndLastOccurrence.findFirstOccurrence;
import static main.dsa.nonlinear.binarysearch.FirstAndLastOccurrence.findLastOccurrence;

public class CountNumberOfOccurrence {

	private static int getNumberOfOccurrence(int[] arr, int target) {

		if (findFirstOccurrence(arr, target) == -1)
			return 0;
		return findLastOccurrence(arr, target) - findFirstOccurrence(arr, target) + 1;
	}

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10 };
		out.println(getNumberOfOccurrence(arr, 10));
	}
}
