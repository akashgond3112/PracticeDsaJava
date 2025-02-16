package main.meta.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. Merge Intervals Medium Topics Companies Given an array of intervals where intervals[i] = [starti, endi], merge
 * all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the
 * input.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 104 intervals[i].length == 2 0 <= starti <= endi <= 104 Seen this question in a real
 * interview before? 1/5 Yes No Accepted
 * 2.9M Submissions 6M Acceptance Rate
 * 48.7% Topics Array Sorting
 */
public class MergeIntervals {

	/**
	 * The sorting step is usually O(n log n) time when n is the number of intervals. Then, there's a single pass
	 * through the sorted intervals, which is O(n). So the total time complexity is O(n log n) + O(n) = O(n log n). For
	 * space, the merged list can be up to O(n) in the worst case where no intervals are merged. So space complexity is
	 * O(n).
	 */
	static class Solution {
		public int[][] merge(int[][] intervals) {

			if (intervals == null || intervals.length == 0) {
				return new int[0][0];
			}
			if (intervals.length == 1) {
				return intervals;
			}

			Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

			List<int[]> mergedIntervals = new ArrayList<>();

			int[] currentInterval = intervals[0];
			mergedIntervals.add(currentInterval);

			for (int i = 1; i < intervals.length; i++) {
				if (intervals[i][0] <= currentInterval[1]) {
					currentInterval[1] = Math.max(currentInterval[1], intervals[i][1]);
				} else {
					currentInterval = intervals[i];
					mergedIntervals.add(currentInterval);
				}
			}
			return mergedIntervals.toArray(new int[mergedIntervals.size()][]);

		}
	}
}
