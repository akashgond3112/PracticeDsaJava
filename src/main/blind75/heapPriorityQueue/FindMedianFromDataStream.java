package main.blind75.heapPriorityQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295. Find Median from Data Stream Hard Topics Companies The median is the middle value in an ordered integer list. If
 * the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3. For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 *
 * MedianFinder() initializes the MedianFinder object. void addNum(int num) adds the integer num from the data stream to
 * the data structure. double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual
 * answer will be accepted.
 *
 *
 * Example 1:
 *
 * Input ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"] [[], [1], [2], [], [3], []] Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation MedianFinder medianFinder = new MedianFinder(); medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2] medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3] medianFinder.findMedian(); // return 2.0
 *
 *
 * Constraints:
 *
 * -105 <= num <= 105 There will be at least one element in the data structure before calling findMedian. At most 5 *
 * 104 calls will be made to addNum and findMedian.
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution? If 99% of all
 * integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class FindMedianFromDataStream {

	/**
	 * Time complexity: O(m) for addNum(), O(m∗nlogn) for findMedian(). Space complexity: O(n) Where m is the number of
	 * function calls and n is the length of the array.
	 */
	static public class MedianFinderBruteForce {
		private ArrayList<Integer> data;

		public MedianFinderBruteForce() {
			data = new ArrayList<>();
		}

		public void addNum(int num) {
			data.add(num);
		}

		public double findMedian() {
			Collections.sort(data);
			int n = data.size();
			if ((n & 1) == 1) {
				return data.get(n / 2);
			} else {
				return (data.get(n / 2) + data.get(n / 2 - 1)) / 2.0;
			}
		}
	}

	public class MedianFinder {

		private Queue<Integer> smallHeap; //small elements - maxHeap
		private Queue<Integer> largeHeap; //large elements - minHeap

		public MedianFinder() {
			smallHeap = new PriorityQueue<>((a, b) -> b - a);
			largeHeap = new PriorityQueue<>((a, b) -> a - b);
		}

		public void addNum(int num) {
			smallHeap.add(num);
			if (smallHeap.size() - largeHeap.size() > 1 || !largeHeap.isEmpty() && smallHeap.peek() > largeHeap.peek()) {
				largeHeap.add(smallHeap.poll());
			}
			if (largeHeap.size() - smallHeap.size() > 1) {
				smallHeap.add(largeHeap.poll());
			}
		}

		public double findMedian() {
			if (smallHeap.size() == largeHeap.size()) {
				return (double) (largeHeap.peek() + smallHeap.peek()) / 2;
			} else if (smallHeap.size() > largeHeap.size()) {
				return (double) smallHeap.peek();
			} else {
				return (double) largeHeap.peek();
			}
		}
	}
}
