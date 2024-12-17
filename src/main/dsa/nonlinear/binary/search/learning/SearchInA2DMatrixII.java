package main.dsa.nonlinear.binary.search.learning;

import java.util.ArrayList;
import java.util.List;

public class SearchInA2DMatrixII {

	public static int[] searchInA2DMatrixBestApproach(List<ArrayList<Integer>> matrix, int target) {

		int currentRow = 0;
		int currentCol = matrix.get(0).size() - 1;

		while (currentRow < matrix.size() - 1 && currentCol >= 0) {
			if (matrix.get(currentRow).get(currentCol) == target) {
				return new int[] { currentRow, currentCol };
			} else if (matrix.get(currentRow).get(currentCol) > target) {
				currentCol--;
			} else {
				currentRow++;
			}
		}
		return new int[] { -1, -1 };
	}

	public static int[] searchInA2DMatrixOptimalApproach(List<ArrayList<Integer>> matrix, int target) {
		for (int i = 0; i < matrix.size(); i++) {
			int colIndex = binarySearchMethod(matrix.get(i), target, 0, matrix.get(i).size() - 1);
			if (colIndex != -1) {
				return new int[] { i, colIndex };
			}
		}
		return new int[] { -1, -1 }; // Indicates target not found
	}

	public static int binarySearchMethod(List<Integer> arr, int k, int low, int high) {
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr.get(mid) == k) {
				return mid;
			} else if (arr.get(mid) < k) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1; // Indicates target not found in the row
	}

	public static void main(String[] args) {
		List<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<>(List.of(1, 3, 5, 7)));
		matrix.add(new ArrayList<>(List.of(10, 11, 16, 20)));
		matrix.add(new ArrayList<>(List.of(23, 30, 34, 60)));

		int target = 11;
		int[] result = searchInA2DMatrixOptimalApproach(matrix, target);
		System.out.println("Target " + target + " found at: " + result[0] + ", " + result[1]);

		result = searchInA2DMatrixBestApproach(matrix, target);

		System.out.println(
				"Target " + target + " found at: " + result[0] + ", " + result[1]);// Should print: Target 3 found at: 0, 1

		target = 13;
		result = searchInA2DMatrixOptimalApproach(matrix, target);
		System.out.println(
				"Target " + target + " found at: " + result[0] + ", " + result[1]); // Should print: Target 13 found at: -1, -1
	}
}

