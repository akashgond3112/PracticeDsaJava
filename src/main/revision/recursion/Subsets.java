package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

	private void generateSubsets(int index, int[] nums, List<Integer> currentSubset, List<List<Integer>> subsets) {
		if (index == nums.length) {
			subsets.add(new ArrayList<>(currentSubset)); // Add a copy of the current subset
			return;
		}

		// Include the current number in the subset
		currentSubset.add(nums[index]);
		generateSubsets(index + 1, nums, currentSubset, subsets);

		// Exclude the current number from the subset
		currentSubset.remove(currentSubset.size() - 1);
		generateSubsets(index + 1, nums, currentSubset, subsets);
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> subsets = new ArrayList<>();
		generateSubsets(0, nums, new ArrayList<>(), subsets);
		return subsets;
	}

	public static void main(String[] args) {
		Subsets generator = new Subsets();
		int[] nums = {1, 2, 3};
		List<List<Integer>> result = generator.subsets(nums);
		System.out.println(result);
	}
}
