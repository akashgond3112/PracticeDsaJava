package main.revision.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

	private static void solution(int[] candidates, int target, int index, List<List<Integer>> result,
			List<Integer> ds) {

		if (target == 0) {
			result.add(new ArrayList<>(ds));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if (i > index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			if (candidates[i] > target) {
				break;
			}
			ds.add(candidates[i]);
			solution(candidates, target - candidates[i], i + 1, result, ds);
			ds.remove(ds.size() - 1);
		}

	}


	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		solution(candidates, target, 0, result, new ArrayList<>());
		return result;
	}

	public static void main(String[] args) {

		int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
		CombinationSumII combinationSum = new CombinationSumII();
		System.out.println(combinationSum.combinationSum2(candidates, target));

	}
}
