package main.recursion.backtracking;

import java.util.ArrayList;

public class findSubsequenceOfGivenKSum {

	public static void findSubsequence(int i, ArrayList<Integer> nums, int currentSum, int targetSum, int[] arr,
			int n) {

		if (i == n) {
			if (currentSum == targetSum) {
				System.out.println(nums.toString());
			}
			return;
		}

		// Pick the current element
		nums.add(arr[i]);
		findSubsequence(i + 1, nums, currentSum + arr[i], targetSum, arr, n);
		nums.remove(nums.size() - 1);

		// Do not pick the current element
		findSubsequence(i + 1, nums, currentSum, targetSum, arr, n);
	}

	public static boolean findSubsequenceOnlyOnce(int i, ArrayList<Integer> nums, int currentSum, int targetSum,
			int[] arr, int n) {

		if (i == n) {
			if (currentSum == targetSum) {
				System.out.println(nums.toString());
				return true;
			}
			return false;
		}

		// Pick the current element
		nums.add(arr[i]);
		if (findSubsequenceOnlyOnce(i + 1, nums, currentSum + arr[i], targetSum, arr, n))
			return true;
		nums.remove(nums.size() - 1);

		// Do not pick the current element
		return findSubsequenceOnlyOnce(i + 1, nums, currentSum, targetSum, arr, n);
	}

	public static int findSubsequenceCount(int i, int currentSum, int targetSum, int[] arr, int n) {

		if (currentSum > targetSum)
			return 0;

		if (i == n) {
			if (currentSum == targetSum) {
				return 1;
			}
			return 0;
		}

		// Pick the current element
		int left = findSubsequenceCount(i + 1, currentSum + arr[i], targetSum, arr, n);

		// Do not pick the current element
		int right = findSubsequenceCount(i + 1, currentSum, targetSum, arr, n);

		return left + right;
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		ArrayList<Integer> nums = new ArrayList<>();
		int n = arr.length;
		int targetSum = 9;
		findSubsequence(0, nums, 0, targetSum, arr, n);
		findSubsequenceOnlyOnce(0, nums, 0, targetSum, arr, n);
		System.out.print("The number of subsequence count is  : " + findSubsequenceCount(0, 0, targetSum, arr, n));
	}
}
