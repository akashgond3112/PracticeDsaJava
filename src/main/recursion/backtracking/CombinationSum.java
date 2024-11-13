package main.recursion.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombinationSum {

	public static void findCombinationSumI(int i, ArrayList<Integer> nums, int targetSum, int[] arr, int n) {

		if (i == n) {
			if (targetSum == 0) {
				System.out.println(nums.toString());
			}
			return;
		}

		// Pick the current element
		if (arr[i] <= targetSum) {
			nums.add(arr[i]);
			findCombinationSumI(i, nums, targetSum - arr[i], arr, n);
			nums.remove(nums.size() - 1);
		}

		// Do not pick the current element
		findCombinationSumI(i + 1, nums, targetSum, arr, n);

	}

	public static void findCombinationSumII(int i, ArrayList<Integer> nums, int targetSum, List<List<Integer>> res,
			int[] arr, int n) {

		if (targetSum == 0) {
			res.add(new ArrayList<>(nums));
			return;
		}

		for (int j = i; j < n; j++) {
			if (arr[j] > targetSum) {
				break;
			}
			if (j > i && arr[j] == arr[j - 1])
				continue;
			// Pick the current element
			nums.add(arr[j]);
			findCombinationSumII(i + 1, nums, targetSum - arr[j], res, arr, n);
			nums.remove(nums.size() - 1);
		}
	}

	public static List<List<Integer>> getCombinationSumII(int[] arr, int target, int n, ArrayList<Integer> nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(arr);
		findCombinationSumII(0, nums, target, res, arr, n);
		return new ArrayList<>(res);
	}

	public static void findCombinationSumIIBruteForce(int i, ArrayList<Integer> nums, Set<List<Integer>> res,
			int targetSum, int[] arr, int n) {

		if (i == n) {
			if (targetSum == 0) {
				res.add(new ArrayList<>(nums));
			}
			return;
		}

		// Pick the current element
		if (arr[i] <= targetSum) {
			nums.add(arr[i]);
			findCombinationSumIIBruteForce(i + 1, nums, res, targetSum - arr[i], arr, n);
			nums.remove(nums.size() - 1);
		}

		// Do not pick the current element
		findCombinationSumIIBruteForce(i + 1, nums, res, targetSum, arr, n);

	}

	public static List<List<Integer>> getFindCombinationSumIIBruteForce(int[] arr, int target, int n,
			ArrayList<Integer> nums) {
		Set<List<Integer>> res = new HashSet<>();
		findCombinationSumIIBruteForce(0, nums, res, target, arr, n);
		return new ArrayList<>(res);
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayList<Integer> nums = new ArrayList<>();
		int n = arr.length;
		int targetSum = 4;
		//		findCombinationSumI(0, nums, targetSum, arr, n);

		List<List<Integer>> findCombinationSumIIBruteForce = getFindCombinationSumIIBruteForce(arr, targetSum, n, nums);
		System.out.println(findCombinationSumIIBruteForce);

		List<List<Integer>> combinationSumII = getCombinationSumII(arr, targetSum, n, nums);
		System.out.println(combinationSumII);
	}
}
