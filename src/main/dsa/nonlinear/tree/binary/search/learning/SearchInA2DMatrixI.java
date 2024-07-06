package main.dsa.nonlinear.tree.binary.search.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchInA2DMatrixI {

	public static int searchInA2DMatrixBestApproach(List<ArrayList<Integer>> matrix, int rows, int columns,
			int target) {

		int low = 0;
		int high = rows * columns - 1;

		while (low <= high) {
			int mid = (low + high) / 2;

			int currRow = mid / columns;
			int currCol = mid % columns;

			if (matrix.get(currRow).get(currCol) == target) {
				return mid;
			} else if (matrix.get(currRow).get(currCol) < target) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}
		return -1;
	}

	public static int searchInA2DMatrixOptimalApproach(List<ArrayList<Integer>> matrix, int target) {

		ArrayList<Integer> singleIdList = new ArrayList<>();

		for (ArrayList<Integer> row : matrix) {
			singleIdList.addAll(row);
		}
		return binarySearchMethod(singleIdList, target, 0, singleIdList.size());


	}

	public static int binarySearchMethod(List<Integer> arr, int k, int low, int high) {

		int mid;

		if (low > high)
			return 0;
		else
			mid = (low + high) / 2;

		if (k == arr.get(mid)) {
			return mid;
		} else if (k < arr.get(mid)) {
			return binarySearchMethod(arr, k, low, mid - 1);
		} else {
			return binarySearchMethod(arr, k, mid + 1, high);
		}
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
		matrix.add(new ArrayList<>(Arrays.asList(5, 6, 7, 8)));
		matrix.add(new ArrayList<>(Arrays.asList(12, 13, 14, 30)));
		matrix.add(new ArrayList<>(Arrays.asList(31, 32, 35, 40)));

		System.out.println(searchInA2DMatrixOptimalApproach(matrix, 2));
		System.out.println(searchInA2DMatrixBestApproach(matrix, 4, 4, 2));
	}
}
