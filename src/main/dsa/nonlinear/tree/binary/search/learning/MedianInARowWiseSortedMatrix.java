package main.dsa.nonlinear.tree.binary.search.learning;

import java.util.ArrayList;
import java.util.List;


public class MedianInARowWiseSortedMatrix {

	public static int findMedianSortedArrays(List<ArrayList<Integer>> matrix) {

		int rows = matrix.size();
		int cols = matrix.get(0).size();

		int low = Integer.MAX_VALUE;
		int high = Integer.MIN_VALUE;

		for (ArrayList<Integer> integers : matrix) {
			low = Math.min(low, integers.get(0));
			high = Math.max(high, integers.get(cols - 1));
		}

		int req = (rows * cols) / 2;

		while (low <= high) {
			int mid = (low + high) / 2;

			int smallest = getNumberOfLesserValuesNumber(matrix, mid);

			if (smallest <= req) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;


	}

	private static int getNumberOfLesserValuesNumber(List<ArrayList<Integer>> matrix, int target) {
		int count = 0;

		for (List<Integer> row : matrix) {
			count += findUpperBound(row, target);
		}
		return count;
	}

	private static int findUpperBound(List<Integer> row, int target) {
		int low = 0;
		int high = row.size() - 1;
		int ans = row.size();

		while (low <= high) {
			int mid = (low + high) / 2;
			if (row.get(mid) > target) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	public static void main(String[] args) {

		List<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<>(List.of(1, 5, 7, 9, 11)));
		matrix.add(new ArrayList<>(List.of(2, 3, 4, 8, 9)));
		matrix.add(new ArrayList<>(List.of(4, 11, 14, 9, 20)));
		matrix.add(new ArrayList<>(List.of(6, 10, 22, 99, 100)));
		matrix.add(new ArrayList<>(List.of(7, 15, 17, 24, 28)));

		int result = findMedianSortedArrays(matrix);
		System.out.println("Target found at: " + result);

	}
}
