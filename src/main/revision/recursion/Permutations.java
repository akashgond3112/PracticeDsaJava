package main.revision.recursion;

import java.util.ArrayList;
import java.util.List;

class Permutations {


	public List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> result = new ArrayList<>();
		List<Integer> ds = new ArrayList<>();
		boolean[] used = new boolean[nums.length];
		recurPermuteI(nums, ds, result, used);
		return result;
	}

	private void recurPermuteI(int[] nums, List<Integer> ds, List<List<Integer>> result, boolean[] used) {

		if (ds.size() == nums.length) {
			result.add(new ArrayList<>(ds));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
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
		Permutations permutations = new Permutations();
		List<List<Integer>> permute = permutations.permute(new int[] { 1, 2, 3 });
		System.out.println(permute);

	}
}