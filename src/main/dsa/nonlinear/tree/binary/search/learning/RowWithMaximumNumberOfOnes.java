package main.dsa.nonlinear.tree.binary.search.learning;

import java.util.ArrayList;
import java.util.Arrays;

public class RowWithMaximumNumberOfOnes {

	public static int findLowerBound(ArrayList<Integer> array, int target, int n) {

		int low = 0;
		int high = array.size() - 1;
		int ans = n;

		while (low <= high) {
			int mid = (low + high) / 2;
			if (array.get(mid) >= target) {
				ans = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return ans;
	}

	public static int rowMaxOnes(ArrayList<ArrayList<Integer>> matrix, int rows, int coulmns) {

		int count_max = 0;
		int index_max = -1;

		for (int i = 0; i < rows; i++) {

			int count = coulmns - findLowerBound(matrix.get(i), 1, coulmns);
			if (count > count_max) {
				count_max = count;
				index_max = i;
			}
		}

		return index_max;
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
		matrix.add(new ArrayList<>(Arrays.asList(0, 0, 0, 1)));
		matrix.add(new ArrayList<>(Arrays.asList(0, 1, 1, 1)));
		matrix.add(new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
		matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1, 1)));

		System.out.println(RowWithMaximumNumberOfOnes.rowMaxOnes(matrix, 4, 4));
	}
}
