package main.dynamic.programming;

/**
 * Problem statement There is a frog on the '1st' step of an 'N' stairs long staircase. The frog wants to reach the
 * 'Nth' stair. 'HEIGHT[i]' is the height of the '(i+1)th' stair.If Frog jumps from 'ith' to 'jth' stair, the energy
 * lost in the jump is given by absolute value of ( HEIGHT[i-1] - HEIGHT[j-1] ). If the Frog is on 'ith' staircase, he
 * can jump either to '(i+1)th' stair or to '(i+2)th' stair. Your task is to find the minimum total energy used by the
 * frog to reach from '1st' stair to 'Nth' stair.
 *
 * For Example If the given ‘HEIGHT’ array is [10,20,30,10], the answer 20 as the frog can jump from 1st stair to 2nd
 * stair (|20-10| = 10 energy lost) and then a jump from 2nd stair to last stair (|10-20| = 10 energy lost). So, the
 * total energy lost is 20. Detailed explanation ( Input/output format, Notes, Images ) Constraints: 1 <= T <= 10 1 <= N
 * <= 100000. 1 <= HEIGHTS[i] <= 1000 .
 *
 * Time limit: 1 sec Sample Input 1: 2 4 10 20 30 10 3 10 50 10 Sample Output 1: 20 0 Explanation of sample input 1: For
 * the first test case, The frog can jump from 1st stair to 2nd stair (|20-10| = 10 energy lost). Then a jump from the
 * 2nd stair to the last stair (|10-20| = 10 energy lost). So, the total energy lost is 20 which is the minimum. Hence,
 * the answer is 20.
 *
 * For the second test case: The frog can jump from 1st stair to 3rd stair (|10-10| = 0 energy lost). So, the total
 * energy lost is 0 which is the minimum. Hence, the answer is 0. Sample Input 2: 2 8 7 4 4 2 6 6 3 4 6 4 8 3 10 4 4
 * Sample Output 2: 7 2
 *
 * Hints:
 * 1. Think about all the possibilities at each stair.
 * 2. Using recursion, try to divide the problem into subproblems and calculate the answer for each subproblem only
 * once
 * - store it for reusing in the future.
 * 3. The above can also be done iteratively.
 */
public class FrogJump {

	// Tabular (Bottom to Top) approach. reduce space complexity
	public static int frogJumpUsingTabularOptimal(int n, int[] heights) {

		int prev = 0;
		int prev2 = 0;

		for (int i = 1; i < n; i++) {
			// Calculate the cost for single step
			int fs = prev + Math.abs(heights[i] - heights[i - 1]);

			// Calculate the cost for two steps (only if i > 1)
			int ss = Integer.MAX_VALUE;
			if (i > 1) {
				ss = prev2 + Math.abs(heights[i] - heights[i - 2]);
			}

			// Store the minimum cost in dp[i]
			int curr = Math.min(fs, ss);
			prev2 = prev;
			prev = curr;
		}

		// Return the minimum energy to reach the last stone
		return prev;
	}

	// Tabular (Bottom to Top) approach
	public static int frogJumpUsingTabular(int n, int[] heights) {
		int[] dp = new int[n];  // Create a dp array for storing the minimum energy

		dp[0] = 0;  // Base case: no cost to be on the first stone

		for (int i = 1; i < n; i++) {
			// Calculate the cost for single step
			int fs = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);

			// Calculate the cost for two steps (only if i > 1)
			int ss = Integer.MAX_VALUE;
			if (i > 1) {
				ss = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
			}

			// Store the minimum cost in dp[i]
			dp[i] = Math.min(fs, ss);
		}

		// Return the minimum energy to reach the last stone
		return dp[n - 1];
	}

	// Memoization function Top to Down
	public static int frogJumpUsingMemo(int n, int[] heights, int[] dp) {
		if (n == 0) {
			return 0;  // Base case: no cost for starting point
		}

		// If the result is already computed, return it
		if (dp[n] != -1) {
			return dp[n];
		}

		// Cost for jumping from (n-1) to n
		int left = frogJumpUsingMemo(n - 1, heights, dp) + Math.abs(heights[n] - heights[n - 1]);

		// Cost for jumping from (n-2) to n (only if n > 1)
		int right = Integer.MAX_VALUE;
		if (n > 1) {
			right = frogJumpUsingMemo(n - 2, heights, dp) + Math.abs(heights[n] - heights[n - 2]);
		}

		// Store the minimum cost in dp array
		dp[n] = Math.min(left, right);

		return dp[n];
	}

	// Main function to initiate the process
	public static void main(String[] args) {
		int[] heights = { 2, 3, 1, 1, 4 };  // Example heights of stones
		int n = heights.length;  // Index of the last stone

		// Initialize dp array with -1 (indicating that no value is computed yet)
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}

		// Compute the minimum cost for the frog to reach the last stone
		int result = frogJumpUsingMemo(n - 1, heights, dp);
		System.out.println("Minimum energy spent: " + result);
		System.out.println("Minimum energy spent 2: " + frogJumpUsingTabular(n, heights));
		System.out.println("Minimum energy spent 3: " + frogJumpUsingTabularOptimal(n, heights));
	}

}
