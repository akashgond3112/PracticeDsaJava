package main.dsa.linear.Array.learning.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Problem statement
Ninja is playing with intervals. He has an array of intervals called ‘Arr’ having ‘N’ intervals.



However, he doesn’t like overlapping intervals. So you must help Ninja by merging all the overlapping intervals in ‘Arr’ and return an array of non-overlapping intervals.



Note:
Two intervals [L1, R1] and [L2, R2] such that L1 <= L2, are said to be overlapping if and only if L2 <= R1.
For example:
For ‘N’ = 4, and
‘Arr’ = {{1, 3}, {2, 4}, {3, 5}, {6, 7}}
We can see that {1, 3} and {2, 4} overlap, so if we merge them, we get {1, 4}.
Now ‘Arr’ becomes: {{1, 4}, {3, 5}, {6, 7}}
Still, we observe that {1, 4} and {3, 5} overlap, hence we merge them into {1, 5}.
Hence, ‘Arr’ becomes {{1, 5}, {6, 7}}.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1:
9
1 2
1 3
1 6
3 4
4 4
4 5
5 5
6 6
6 6
Sample Output 1 :
1 6
Explanation Of Sample Input 1:
Arr after each merge
{{1, 2}, {1, 3}, {1, 6}, {3, 4}, {4, 4}, {4, 5}, {5, 5}, {6, 6}, {6, 6}}
{{1, 3}, {1, 6}, {3, 4}, {4, 4}, {4, 5}, {5, 5}, {6, 6}, {6, 6}}
{{1, 6}, {3, 4}, {4, 4}, {4, 5}, {5, 5}, {6, 6}, {6, 6}}
{{1, 6}, {4, 4}, {4, 5}, {5, 5}, {6, 6}, {6, 6}}
{{1, 6}, {4, 5}, {5, 5}, {6, 6}, {6, 6}}
{{1, 6}, {5, 5}, {6, 6}, {6, 6}}
{{1, 6}, {6, 6}, {6, 6}}
{{1, 6}, {6, 6}}
{{1, 6}}
Sample Input 2:
7
2 2
2 3
2 5
3 6
4 4
4 5
6 6
Sample Output 2:
2 6
Constraints:
1 <= N <= 10^5
1 <= L <= R <= 10^9

Time Limit: 1 sec.
*/
public class MergeAllOverlappingIntervals {

	public static List<List<Integer>> mergeOverlappingIntervals(int[][] arr) {
		// Sort intervals based on the start time
		Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

		List<List<Integer>> res = new ArrayList<>();

		int start = arr[0][0];
		int end = arr[0][1];

		for (int i = 1; i < arr.length; i++) {
			// If the current interval overlaps with the previous one
			if (arr[i][0] <= end) {
				end = Math.max(end, arr[i][1]);
			} else {
				// No overlap, so add the previous interval and update the current one
				res.add(Arrays.asList(start, end));
				start = arr[i][0];
				end = arr[i][1];
			}
		}

		// Add the last interval
		res.add(Arrays.asList(start, end));

		return res;
	}

	public static List<List<Integer>> mergeOverlappingIntervalsBrute(int[][] arr) {
		int n = arr.length; // size of the array
		//sort the given intervals:
		Arrays.sort(arr, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});

		List<List<Integer>> ans = new ArrayList<>();

		for (int i = 0; i < n; i++) { // select an interval:
			int start = arr[i][0];
			int end = arr[i][1];

			//Skip all the merged intervals:
			if (!ans.isEmpty() && end <= ans.get(ans.size() - 1).get(1)) {
				continue;
			}

			//check the rest of the intervals:
			for (int j = i + 1; j < n; j++) {
				if (arr[j][0] <= end) {
					end = Math.max(end, arr[j][1]);
				} else {
					break;
				}
			}
			ans.add(Arrays.asList(start, end));
		}
		return ans;
	}

	public static void main(String[] args) {
		int[][] arr = new int[][] { { 1, 3 }, { 2, 4 }, { 3, 5 }, { 6, 7 } };
		List<List<Integer>> res = mergeOverlappingIntervals(arr);
		System.out.println(res);
		System.out.println(mergeOverlappingIntervalsBrute(arr));
	}

}
