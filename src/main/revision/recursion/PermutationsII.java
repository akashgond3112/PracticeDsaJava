package main.revision.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsII {

	public List<List<Integer>> permuteUnique(int[] nums) {

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> ds = new ArrayList<>();
		boolean[] used = new boolean[nums.length];
		Arrays.sort(nums);
		recurPermuteI(nums, ds, result, used);
		return result;
	}

	private void recurPermuteI(int[] nums, List<Integer> ds, List<List<Integer>> result, boolean[] used) {

		if (ds.size() == nums.length) {
			result.add(new ArrayList<>(ds));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			// Skip duplicates: only proceed if it's the first occurrence or the previous duplicate was used
			if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
				continue;
			}
			if (!used[i]) {
				used[i] = true;
				ds.add(nums[i]);
				recurPermuteI(nums, ds, result, used);
				used[i] = false;
				ds.remove(ds.size() - 1);
			}
		}
	}

	public static void main(String[] args) {
		PermutationsII permutations = new PermutationsII();
		List<List<Integer>> permute = permutations.permuteUnique(new int[] { 1, 1, 2});
		System.out.println(permute);

	}
}
