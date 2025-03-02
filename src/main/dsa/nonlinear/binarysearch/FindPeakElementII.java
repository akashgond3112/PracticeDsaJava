package main.dsa.nonlinear.binarysearch;

import java.util.ArrayList;
import java.util.List;

public class FindPeakElementII {

	public static int[] findPeakElement(List<ArrayList<Integer>> matrix) {

		int row = matrix.size();
		int col = matrix.get(0).size();

		int low = 0;
		int high = row - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			int maxRowIndex = findMaxElement(matrix, row, col, mid);
			int left = mid - 1 >= 0 ? matrix.get(maxRowIndex).get(mid - 1) : -1;
			int right = mid + 1 < col ? matrix.get(maxRowIndex).get(mid + 1) : -1;

			if (matrix.get(maxRowIndex).get(mid) > left && matrix.get(maxRowIndex).get(mid) > right) {
				return new int[] { maxRowIndex, mid };
			} else if (matrix.get(maxRowIndex).get(mid) < left) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}

		}
		return new int[] { -1, -1 };
	}

	private static int findMaxElement(List<ArrayList<Integer>> matrix, int row, int col, int mid) {

		int max = -1;
		int index = -1;
		for (int i = 0; i < row; i++) {
			if (matrix.get(i).get(mid) > max) {
				max = matrix.get(i).get(mid);
				index = i;
			}
		}
		return index;
	}

	public static void main(String[] args) {

		List<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<>(List.of(1, 3, 5, 7)));
		matrix.add(new ArrayList<>(List.of(10, 11, 16, 20)));
		matrix.add(new ArrayList<>(List.of(23, 0, 29, 60)));
		matrix.add(new ArrayList<>(List.of(23, 3, 34, 60)));

		int[] result = findPeakElement(matrix);
		System.out.println("Target found at: " + result[0] + ", " + result[1]);

	}

}
