package main.dsa.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationOfStringArray {

	private static void solvePermutationI(List<Integer> permutation, List<List<Integer>> ans, int[] nums,
			boolean[] visited) {

		if (permutation.size() == nums.length) {
			ans.add(new ArrayList<>(permutation));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation.add(nums[i]);
				solvePermutationI(permutation, ans, nums, visited);
				permutation.remove(permutation.size() - 1);
				visited[i] = false;
			}
		}
	}

	public static List<List<Integer>> getPermuteI(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		List<Integer> permutation = new ArrayList<>();
		boolean[] visited = new boolean[nums.length];
		solvePermutationI(permutation, ans, nums, visited);
		return ans;
	}

	private static void swap(int i, int index, int[] nums) {
		int temp = nums[i];
		nums[i] = nums[index];
		nums[index] = temp;
	}

	private static void solvePermutationII(int index, List<List<Integer>> ans, int[] nums) {
		if (index == nums.length) {
			List<Integer> permutation = new ArrayList<>();
			for (int num : nums) {
				permutation.add(num);
			}
			ans.add(new ArrayList<>(permutation));
			return;
		}

		for (int i = index; i < nums.length; i++) {
			swap(i, index, nums);
			solvePermutationII(index + 1, ans, nums);
			swap(i, index, nums);
		}
	}

	public static List<List<Integer>> getPermuteII(int index, int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		solvePermutationII(index, ans, nums);
		return ans;
	}


	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		List<List<Integer>> ans = getPermuteI(nums);
		for (List<Integer> permutation : ans) {
			System.out.println(permutation);
		}
		System.out.println("=================================");
		List<List<Integer>> ans2 = getPermuteII(0, nums);
		for (List<Integer> permutation : ans2) {
			System.out.println(permutation);
		}
	}
}
