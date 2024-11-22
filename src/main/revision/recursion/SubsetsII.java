package main.revision.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {

	private void generateSubsets(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {
		subsets.add(new ArrayList<>(currentSubset)); // Add a copy of the current subset

		for (int i = index; i < nums.length; i++) {
			if (i != index && nums[i] == nums[i - 1]) {
				continue;
			}

			currentSubset.add(nums[i]);
			generateSubsets(i + 1, nums, currentSubset, subsets);
			currentSubset.remove(currentSubset.size() - 1);
		}
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> subsets = new ArrayList<>();
		generateSubsets(0, nums, new ArrayList<>(), subsets);
		return subsets;
	}

	public static void main(String[] args) {
		SubsetsII generator = new SubsetsII();
		int[] nums = { 1, 2, 2 };
		List<List<Integer>> result = generator.subsetsWithDup(nums);
		System.out.println(result);
	}
}
