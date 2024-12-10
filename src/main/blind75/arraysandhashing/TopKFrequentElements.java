package main.blind75.arraysandhashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 347. Top K Frequent Elements Medium Topics Companies Given an integer array nums and an integer k, return the k most
 * frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2 Output: [1,2] Example 2:
 *
 * Input: nums = [1], k = 1 Output: [1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105 -104 <= nums[i] <= 104 k is in the range [1, the number of unique elements in the array]. It
 * is guaranteed that the answer is unique.
 *
 *
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {

	/**
	 * Time complexity: O(nlogn) Space complexity: O(n)
	 */
	public int[] topKFrequentUsingSort(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			heap.offer(new int[] { entry.getValue(), entry.getKey() });
			if (heap.size() > k) {
				heap.poll();
			}
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = heap.poll()[1];
		}
		return res;
	}

	/**
	 * Time complexity: O(nlogk) Space complexity: O(n+k)
	 * Where n is the length of the array and k is the number of
	 * top frequent elements.
	 */
	public int[] topKFrequentUsingHeap(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		for (int num : nums) {
			count.put(num, count.getOrDefault(num, 0) + 1);
		}

		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			heap.offer(new int[] { entry.getValue(), entry.getKey() });
			if (heap.size() > k) {
				heap.poll();
			}
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = Objects.requireNonNull(heap.poll())[1];
		}
		return res;
	}

	/**
	 * Time complexity: O(n)
	 * Space complexity: O(n)
	 */
	public int[] topKFrequentUsingBucketSort(int[] nums, int k) {
		Map<Integer, Integer> count = new HashMap<>();
		List<Integer>[] freq = new List[nums.length + 1];

		for (int i = 0; i < freq.length; i++) {
			freq[i] = new ArrayList<>();
		}

		for (int n : nums) {
			count.put(n, count.getOrDefault(n, 0) + 1);
		}
		for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
			freq[entry.getValue()].add(entry.getKey());
		}

		int[] res = new int[k];
		int index = 0;
		for (int i = freq.length - 1; i > 0 && index < k; i--) {
			for (int n : freq[i]) {
				res[index++] = n;
				if (index == k) {
					return res;
				}
			}
		}
		return res;
	}
}
