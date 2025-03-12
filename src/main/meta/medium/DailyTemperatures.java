package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 739. Daily Temperatures
 * Medium
 * Topics
 * Companies
 * Hint
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 *
 * Constraints:
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 1.2M
 * Submissions
 * 1.8M
 * Acceptance Rate
 * 67.0%
 * Topics
 * Array
 * Stack
 * Monotonic Stack</pre>
 */
public class DailyTemperatures {

  /**
   * Original stack-based solution (right-to-left approach)
   *
   * <p>Time Complexity: O(n) where n is the length of the array Space Complexity: O(n) in worst
   * case
   */
  static class StackSolutionRightToLeft {
    public int[] dailyTemperatures(int[] temperatures) {
      Stack<Integer> stack = new Stack<>();
      int n = temperatures.length;
      int[] result = new int[n];

      for (int idx = n - 1; idx >= 0; idx--) {
        while (!stack.isEmpty() && temperatures[idx] >= temperatures[stack.peek()]) {
          stack.pop();
        }

        if (!stack.isEmpty()) {
          result[idx] = stack.peek() - idx;
        }

        stack.push(idx);
      }

      return result;
    }
  }

  /**
   * Alternative stack-based solution (left-to-right approach) This is more intuitive for some and
   * provides the same results.
   *
   * <p>Time Complexity: O(n) Space Complexity: O(n) in worst case
   */
  static class StackSolutionLeftToRight {
    public int[] dailyTemperatures(int[] temperatures) {
      Stack<Integer> stack = new Stack<>();
      int n = temperatures.length;
      int[] result = new int[n];

      for (int idx = 0; idx < n; idx++) {
        // Pop elements from stack and update their result when we find a warmer day
        while (!stack.isEmpty() && temperatures[idx] > temperatures[stack.peek()]) {
          int prevIdx = stack.pop();
          result[prevIdx] = idx - prevIdx;
        }

        stack.push(idx);
      }

      // All remaining indices in stack have no warmer temperature (result already 0)
      return result;
    }
  }

  /**
   * Optimized solution using array instead of Stack This eliminates overhead from Stack operations.
   *
   * <p>Time Complexity: O(n) Space Complexity: O(n) in worst case
   */
  static class ArrayBasedSolution {
    public int[] dailyTemperatures(int[] temperatures) {
      int n = temperatures.length;
      int[] result = new int[n];
      int[] stack = new int[n]; // Array used as stack
      int top = -1; // Stack pointer

      for (int idx = 0; idx < n; idx++) {
        // Process elements while stack is not empty and current temp is higher
        while (top >= 0 && temperatures[idx] > temperatures[stack[top]]) {
          int prevIdx = stack[top--]; // Pop from stack
          result[prevIdx] = idx - prevIdx;
        }

        stack[++top] = idx; // Push to stack
      }

      return result;
    }
  }

  /**
   * Most optimized solution - Backward traversal without stack This approach uses constant extra
   * space and optimizes for cache locality.
   *
   * <p>Time Complexity: O(n) - Technically O(n + m) where m is the maximum possible wait but since
   * m is bounded by n, it's effectively O(n) Space Complexity: O(1) extra space (not counting the
   * output array)
   */
  static class OptimizedConstantSpaceSolution {
    public int[] dailyTemperatures(int[] temperatures) {
      int n = temperatures.length;
      int[] result = new int[n];

      // Start from the right and work backwards
      for (int i = n - 1; i >= 0; i--) {
        int nextWarmerIdx = i + 1;

        // While we haven't found a warmer temperature
        while (nextWarmerIdx < n && temperatures[nextWarmerIdx] <= temperatures[i]) {
          // If the result at nextWarmerIdx is 0, there's no warmer day ahead
          if (result[nextWarmerIdx] == 0) {
            nextWarmerIdx = n; // No warmer day exists
            break;
          }
          // Skip to the next warmer day using previously computed results
          nextWarmerIdx += result[nextWarmerIdx];
        }

        // If we found a warmer day, calculate the wait time
        if (nextWarmerIdx < n) {
          result[i] = nextWarmerIdx - i;
        }
      }

      return result;
    }
  }

  /**
   * Solution that uses the constraints of temperatures (30 ≤ temperatures[i] ≤ 100) This uses an
   * array to track the next occurrence of each possible temperature.
   *
   * <p>Time Complexity: O(n) Space Complexity: O(1) since the range of temperatures is constant
   */
  static class ConstraintBasedSolution {
    public int[] dailyTemperatures(int[] temperatures) {
      int n = temperatures.length;
      int[] result = new int[n];

      // nextWarmerTemp[t] = position of next occurrence of temperature t+30
      int[] nextWarmerTemp = new int[71]; // Max temp 100, min temp 30

      // Initialize all positions to infinity
      Arrays.fill(nextWarmerTemp, Integer.MAX_VALUE);

      for (int i = n - 1; i >= 0; i--) {
        int temp = temperatures[i];

        // Find the closest warmer temperature
        int warmerDay = Integer.MAX_VALUE;
        for (int j = temp + 1; j <= 100; j++) {
          if (nextWarmerTemp[j - 30] < warmerDay) {
            warmerDay = nextWarmerTemp[j - 30];
          }
        }

        // If we found a warmer day, update result
        if (warmerDay != Integer.MAX_VALUE) {
          result[i] = warmerDay - i;
        }

        // Update the next occurrence of current temperature
        nextWarmerTemp[temp - 30] = i;
      }

      return result;
    }
  }

  public static void main(String[] args) {
    // Example usage:
    // int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
    // DailyTemperatures solution = new DailyTemperatures();
    // int[] result = solution.new OptimizedConstantSpaceSolution().dailyTemperatures(temperatures);
    // Expected output: [1, 1, 4, 2, 1, 1, 0, 0]
  }
}
