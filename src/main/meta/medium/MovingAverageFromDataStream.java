package main.meta.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 346 - Moving Average from Data Stream
 * Posted on November 10, 2016 · 4 minute read
 * Welcome to Subscribe On Youtube
 *
 * 346. Moving Average from Data Stream
 * Description
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *
 * Constraints:
 *
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * At most 104 calls will be made to next.*/
public class MovingAverageFromDataStream {

	/**
	 * A class that calculates the moving average for a sliding window of fixed size.
	 * The moving average is computed as the mean of the last 'n' numbers in the stream,
	 * where 'n' is the size of the window.
	 *
	 * Example usage:
	 * <pre>
	 * MovingAverage ma = new MovingAverage(3);
	 * ma.next(1); // returns 1.0
	 * ma.next(2); // returns 1.5 (average of 1, 2)
	 * ma.next(3); // returns 2.0 (average of 1, 2, 3)
	 * ma.next(4); // returns 3.0 (average of 2, 3, 4)
	 * </pre>
	 *
	 * Time Complexity: O(1) for each operation
	 * Space Complexity: O(size) where size is the window size
	 */
	public static class MovingAverage {

		/** Queue to store the numbers in the current window */
		private final Queue<Integer> queue;

		/** Maximum size of the sliding window */
		private final int size;

		/** Running sum of all numbers in the current window */
		private int sum;

		/**
		 * Constructs a MovingAverage calculator with specified window size.
		 *
		 * @param size the size of the sliding window
		 * @throws IllegalArgumentException if size is less than or equal to 0
		 */
		public MovingAverage(int size) {
			if (size <= 0) {
				throw new IllegalArgumentException("Window size must be positive");
			}
			this.queue = new LinkedList<>();
			this.size = size;
			this.sum = 0;
		}

		/**
		 * Adds a new number to the sliding window and returns the moving average.
		 * If the window exceeds its size limit, the oldest number is removed.
		 *
		 * @param value the new number to add to the window
		 * @return the moving average of all numbers in the current window
		 *
		 * Example:
		 * For window size 3:
		 * next(1) → returns 1.0
		 * next(2) → returns 1.5
		 * next(3) → returns 2.0
		 * next(4) → returns 3.0 (1 is removed from window)
		 */
		public double next(int value) {
			sum += value;
			queue.add(value);

			if (queue.size() > size) {
				sum -= queue.poll();
			}

			return (double) sum / queue.size();
		}

		/**
		 * Returns the current size of the window.
		 *
		 * @return the number of elements currently in the window
		 */
		public int getCurrentSize() {
			return queue.size();
		}

		/**
		 * Returns the maximum size of the window.
		 *
		 * @return the maximum number of elements that can be in the window
		 */
		public int getMaxSize() {
			return size;
		}
	}
}
