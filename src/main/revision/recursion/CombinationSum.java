package main.revision.recursion;


import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
	private static void solution(int[] candidates, int target, int index, List<List<Integer>> result,
			List<Integer> ds) {

		if (index == candidates.length) {
			if (target == 0) {
				result.add(new ArrayList<>(ds));
			}
			return;
		}
		if (candidates[index] <= target) {
			ds.add(candidates[index]);
			solution(candidates, target - candidates[index], index, result, ds);
			ds.remove(ds.size() - 1);
		}
		solution(candidates, target, index + 1, result, ds);

	}


	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		solution(candidates, target, 0, result, new ArrayList<>());
		return result;
	}

	public static void main(String[] args) {

		int[] candidates = { 2, 3, 6, 7 };
		int target = 7;
		CombinationSum combinationSum = new CombinationSum();
		combinationSum.combinationSum(candidates, target);

	}
}
