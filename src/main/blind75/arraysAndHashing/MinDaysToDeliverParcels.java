package main.blind75.arraysAndHashing;

import java.util.*;

public class MinDaysToDeliverParcels {


	public static int minDaysToDeliverParcels(int[] parcels) {
		// Step 1: Sort the array
		Arrays.sort(parcels);

		// Step 2: Initialize days counter
		int days = 0;

		// Step 3: Iterate through the sorted array
		for (int i = 0; i < parcels.length; i++) {
			if (parcels[i] > 0) {
				// A new unique non-zero value found
				days++;

				// Skip over parcels[i] identical values as the rest are effectively reduced
				while (i + 1 < parcels.length && parcels[i] == parcels[i + 1]) {
					i++;
				}
			}
		}

		return days;
	}

	/**
	 * Which Solution is Best? If space is limited, use this approach (O(1) space), but it’s slower for edge
	 * cases with high maxValue. If speed is critical, the O(n) solution with a
	 * HashSet is better. Let me know which one you'd prefer or if you'd like further refinements!
	 */

	public static class MinDaysToDeliverParcelsUsingHashSet {

		public static int minDaysToDeliverParcels(int[] parcels) {
			HashSet<Integer> uniqueParcels = new HashSet<>();

			for (int parcel : parcels) {
				if (parcel > 0) {
					uniqueParcels.add(parcel);
				}
			}

			return uniqueParcels.size();
		}
	}

	public static class MinDaysToDeliverParcelsWithoutAnySpace {

		public static int minDaysToDeliverParcels(int[] parcels) {
			int days = 0;
			int maxParcel = 0;

			// Find the maximum value in the array
			for (int parcel : parcels) {
				if (parcel > 0) {
					maxParcel = Math.max(maxParcel, parcel);
				}
			}

			while (maxParcel > 0) {
				days++;
				// Simulate one day of delivery
				for (int i = 0; i < parcels.length; i++) {
					if (parcels[i] > 0) {
						parcels[i]--;
					}
				}

				// Recalculate the max parcel value
				maxParcel = 0;
				for (int parcel : parcels) {
					maxParcel = Math.max(maxParcel, parcel);
				}
			}

			return days;
		}
	}



	public static void main(String[] args) {
		// Example Inputs
		int[] example1 = {2, 3, 4, 3, 3};
		int[] example2 = {0, 0, 0};
		int[] example3 = {5, 1, 1, 5, 2, 3};
		int[] example4 = {1000, 0, 1000};
		int[] example5 = {1, 2, 3, 4, 5};

		// Print Results
		System.out.println("Example 1: " + minDaysToDeliverParcels(example1)); // Output: 3
		System.out.println("Example 2: " + minDaysToDeliverParcels(example2)); // Output: 0
		System.out.println("Example 3: " + minDaysToDeliverParcels(example3)); // Output: 4
		System.out.println("Example 4: " + minDaysToDeliverParcels(example4)); // Output: 2
		System.out.println("Example 5: " + minDaysToDeliverParcels(example5)); // Output: 5
	}

}

