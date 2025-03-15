package main.meta.medium;

import java.util.*;

/**
 * <pre>
 * 22. Generate Parentheses
 * Medium
 * Topics
 * Companies
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints:
 * 1 <= n <= 8
 * Accepted
 * 2.2M
 * Submissions
 * 2.9M
 * Acceptance Rate
 * 76.6%
 * Topics
 * String
 * Dynamic Programming
 * Backtracking
 * </pre>
 */

public class GenerateParentheses {
	/**
	 * A class that generates all valid combinations of n pairs of parentheses.
	 *
	 * Time Complexity: O(4^n / √n)
	 * - The algorithm generates all valid combinations of parentheses, which is equal to the nth Catalan number.
	 * - The Catalan number C(n) is approximately 4^n / (n^(3/2) * √π).
	 * - For each combination, we perform O(n) work for string concatenation.
	 *
	 * Space Complexity: O(4^n / √n)
	 * - Recursion stack depth: O(2n) = O(n)
	 * - Output space: O(n * C(n)) = O(4^n / √n)
	 * - Each recursive call creates a new string, but due to DFS nature, we only keep one path in memory at a time.
	 */
	static class Solution {
		/**
		 * Generates all valid combinations of n pairs of parentheses.
		 *
		 * @param n
		 * 		The number of pairs of parentheses to generate
		 * @return A list containing all valid combinations of n pairs of parentheses
		 *
		 * 		Time Complexity: O(4^n / √n) Space Complexity: O(4^n / √n)
		 */
		public List<String> generateParenthesis(int n) {
			List<String> result = new ArrayList<>();
			int opening = 0;
			int closing = 0;
			dfs(result, n, opening, closing, "");
			return result;
		}

		/**
		 * Helper method that uses depth-first search with backtracking to generate valid parentheses.
		 *
		 * @param result
		 * 		The list to store valid parentheses combinations
		 * @param n
		 * 		The total number of pairs to generate
		 * @param opening
		 * 		The current count of opening parentheses
		 * @param closing
		 * 		The current count of closing parentheses
		 * @param str
		 * 		The current parentheses combination being built
		 *
		 * 		The method follows these rules:
		 * 		1. If opening < n, we can add an opening parenthesis
		 * 		2. If closing < opening, we can add a closing parenthesis
		 * 		3. When opening == n and closing == n, we have a valid combination
		 *
		 * 		Time Complexity: O(1) per call, but total calls are O(4^n / √n) Space Complexity: O(n) for the recursion
		 * 		stack
		 */
		private static void dfs(List<String> result, int n, int opening, int closing, String str) {
			// Base case: when we have used all n pairs of parentheses
			if (opening == n && closing == n) {
				result.add(str);
				return;
			}

			// We can add an opening parenthesis if we haven't used all n
			if (opening < n) {
				dfs(result, n, opening + 1, closing, str + "(");
			}

			// We can add a closing parenthesis if its count is less than opening parentheses
			if (closing < opening) {
				dfs(result, n, opening, closing + 1, str + ")");
			}
		}
	}

	/**
	 * Main method to demonstrate the generateParenthesis function.
	 *
	 * @param args
	 * 		Command line arguments (not used)
	 */
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.generateParenthesis(3));
	}
}
