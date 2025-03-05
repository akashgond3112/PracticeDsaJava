package main.meta.medium;


/**
 * <pre>
 * 50. Pow(x, n) Medium Topics Companies Implement pow(x, n), which calculates x raised to the
 * power n (i.e., xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10 Output: 1024.00000
 * Example 2:
 * Input: x = 2.10000, n = 3 Output: 9.26100
 * Example 3:
 * Input: x = 2.00000, n = -2 Output: 0.25000 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * Constraints:
 * -100.0 < x < 100.0 -231 <= n <= 231-1 n is an integer. Either x is not zero or n > 0. -104 <= xn <= 104
 * </pre>
 */
public class PowerOfGivenNumber {


	/**
	 * <pre>
	 * The PowerOfGivenNumber class contains a method to calculate the power of a given number using
	 * a divide and conquer approach.
	 *
	 * <p>This implementation provides an efficient way to compute the power of a number by reducing
	 * the problem size by half in each recursive call.
	 *
	 * Time Complexity: O(logN) - The number of recursive calls is proportional to the logarithm
	 * of the exponent.
	 * Space Complexity: O(logN) - The recursion stack space is proportional to
	 * the logarithm of the exponent.
	 *
	 * <p>Example usage: {@code PowerOfGivenNumber.Solution solution = new
	 * PowerOfGivenNumber().new Solution(); double result = solution.myPow(2.0, 10); // result is
	 * 1024.0 }
	 * </pre>
	 */
	static class SolutionUsingRecursion {
		public double myPow(double x, int n) {

			if (n == 0) {
				return 1;
			}

			if (n < 0) {
				return 1 / (x * myPow(x, -(n + 1)));
			}

			double result = myPow(x, n / 2);

			if (n % 2 == 0) {
				return result * result;
			} else {
				return result * result * x;
			}
		}
	}

	/**
	 * <pre>
	 * Time Complexity: O(log n)
	 * Space Complexity: O(1)
	 * </pre>*/
	static class Solution {
		public double myPow(double x, int n) {
			// Handle special cases
			if (n == 0) return 1;

			// Handle negative exponents
			long N = n;  // Use long to handle Integer.MIN_VALUE
			if (N < 0) {
				x = 1 / x;
				N = -N;
			}

			double ans = 1;

			while (N > 0) {
				if ((N % 2) == 1) {
					ans *= x;
				}
				x *= x;
				N /= 2;
			}

			return ans;
		}
	}

}
