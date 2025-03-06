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
	 * Approach: Sort and Merge Intervals
	 *
	 * This solution solves the problem of merging overlapping intervals by first sorting the intervals
	 * by their start times and then merging overlapping intervals in a single pass.
	 *
	 * Algorithm:
	 * 1. Handle edge cases (empty or single-interval inputs)
	 * 2. Sort intervals by their start times
	 * 3. Initialize result with the first interval
	 * 4. For each subsequent interval:
	 *    - If it overlaps with the current merged interval, extend the current interval
	 *    - If it doesn't overlap, add it as a new interval to our result
	 *
	 * Time Complexity: O(n log n)
	 * - Sorting the intervals takes O(n log n) time
	 * - The subsequent linear pass through the sorted intervals takes O(n)
	 * - Overall complexity is dominated by the sorting step: O(n log n)
	 *
	 * Space Complexity: O(n)
	 * - In the worst case (no intervals can be merged), we store all n intervals
	 * - The sorting may require O(log n) space depending on the implementation
	 * - The result list requires O(n) space in worst case
	 *
	 * Example visualization:
	 * Input: [[1,3], [2,6], [8,10], [15,18]]
	 * Sorted: [[1,3], [2,6], [8,10], [15,18]]
	 * Processing:
	 *   [1,3] (first interval) → add to result
	 *   [2,6] overlaps with [1,3] → merge to [1,6]
	 *   [8,10] doesn't overlap → add to result
	 *   [15,18] doesn't overlap → add to result
	 * Output: [[1,6], [8,10], [15,18]]
	 */
	public int[][] merge(int[][] intervals) {
		// Handle edge cases
		if (intervals == null || intervals.length == 0) {
			return new int[0][0];
		}
		if (intervals.length == 1) {
			return intervals;
		}

		// Sort intervals by their start time
		Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

		// Initialize result list and latest merged interval
		List<int[]> mergedIntervalsList = new ArrayList<>();
		int[] latestMergedInterval = intervals[0];
		mergedIntervalsList.add(latestMergedInterval);

		// Process remaining intervals
		for (int i = 1; i < intervals.length; i++) {
			int[] currentInterval = intervals[i];
			int currentStart = currentInterval[0];
			int currentEnd = currentInterval[1];
			int latestMergedEnd = latestMergedInterval[1];

			// If current interval overlaps with the latest merged interval
			if (currentStart <= latestMergedEnd) {
				// Extend the end of latest merged interval if necessary
				latestMergedInterval[1] = Math.max(latestMergedEnd, currentEnd);
			} else {
				// Current interval doesn't overlap, add it to result
				latestMergedInterval = currentInterval;
				mergedIntervalsList.add(latestMergedInterval);
			}
		}

		// Convert list to array and return
		return mergedIntervalsList.toArray(new int[mergedIntervalsList.size()][]);
	}
}
