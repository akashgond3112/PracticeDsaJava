package main.dsa.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubsetSumIAndII {

	public static void subsetI(int[] arr, int sum, int index, ArrayList<Integer> result) {

		if (index == arr.length) {
			result.add(sum);
			return;
		}

		subsetI(arr, sum + arr[index], index + 1, result);
		subsetI(arr, sum, index + 1, result);
	}

	public static ArrayList<Integer> getSubsetSumI(int[] arr) {
		ArrayList<Integer> result = new ArrayList<>();
		subsetI(arr, 0, 0, result);
		Collections.sort(result);
		return result;
	}

	private static void subsetII(int[] arr, int index, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> nums) {
		result.add(new ArrayList<>(nums));

		for (int i = index; i < arr.length; i++) {
			if (i != index && arr[i] == arr[i - 1]) {
				continue;
			}
			nums.add(arr[i]);

			subsetII(arr, i + 1, result, nums);
			nums.remove(nums.size() - 1);
		}
	}

	public static ArrayList<ArrayList<Integer>> getSubsetSumII(int[] arr) {
		Arrays.sort(arr);
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		ArrayList<Integer> nums = new ArrayList<>();
		subsetII(arr, 0, result, nums);
		return result;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		System.out.println(getSubsetSumI(arr));
		System.out.println(getSubsetSumII(arr));
	}
}
