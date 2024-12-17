package main.dsa.nonlinear.binary.search.learning;

import static java.lang.System.out;
import static main.dsa.nonlinear.binary.search.learning.KokoEatingBanana.findMax;

public class CapacityToShipPackagesWithinDDays {

	public static int getMinCapacity(int[] arr, int days) {

		int low = findMax(arr);
		int high = getTotalSum(arr);

		while (low <= high) {
			int mid = (low + high) / 2;

			int daysRequired = numberOfDaysRequired(arr, mid);

			if (daysRequired <= days) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	static int getTotalSum(int[] arr) {
		int sum = 0;
		for (int j : arr) {
			sum += j;
		}
		return sum;
	}


	private static int numberOfDaysRequired(int[] wt, int capacity) {

		int daysRequired = 1;
		int load = 0;

		for (int j : wt) {

			if (load + j > capacity) {
				daysRequired += 1;
				load = j;
			} else {
				load += j;
			}
		}

		return daysRequired;
	}

	public static void main(String[] args) {

		int[] v = { 5, 4, 5, 2, 3, 4, 5, 6 };
		int h = 5;
		int ans = getMinCapacity(v, h);
		out.println("The least weight capacity needed is  " + ans + " .");
	}

}
