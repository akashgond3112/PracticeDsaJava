package main.dsa.dp;

/*
Time Limit: 2 sec / Memory Limit: 1024 MB

Score :
100 points

Problem Statement
There are
N stones, numbered
1,2,…,N. For each
i (
1≤i≤N), the height of Stone
i is
h
i
​
 .

There is a frog who is initially on Stone
1. He will repeat the following action some number of times to reach Stone
N:

If the frog is currently on Stone
i, jump to one of the following: Stone
i+1,i+2,…,i+K. Here, a cost of
∣h
i
​
 −h
j
​
 ∣ is incurred, where
j is the stone to land on.
Find the minimum possible total cost incurred before the frog reaches Stone
N.

Constraints
All values in input are integers.
2≤N≤10
5

1≤K≤100
1≤h
i
​
 ≤10
4

Input
Input is given from Standard Input in the following format:

N
K
h
1
​

h
2
​

…
h
N
​

Output
Print the minimum possible total cost incurred.

Sample Input 1
Copy
5 3
10 30 40 50 20
Sample Output 1
Copy
30
If we follow the path
1 →
2 →
5, the total cost incurred would be
∣10−30∣+∣30−20∣=30.

Sample Input 2
Copy
3 1
10 20 10
Sample Output 2
Copy
20
If we follow the path
1 →
2 →
3, the total cost incurred would be
∣10−20∣+∣20−10∣=20.

Sample Input 3
Copy
2 100
10 10
Sample Output 3
Copy
0
If we follow the path
1 →
2, the total cost incurred would be
∣10−10∣=0.

Sample Input 4
Copy
10 4
40 10 20 70 80 10 20 70 80 60
Sample Output 4
Copy
40
If we follow the path
1 →
4 →
8 →
10, the total cost incurred would be
∣40−70∣+∣70−70∣+∣70−60∣=40.
*/
public class FrogJumpII {

	// Tabular (Bottom to Top) approach with optimized space complexity
	public static int frogJumpUsingTabularOptimized(int n, int[] heights, int k) {
		// Initialize variables to store the minimum energy of the last few stones
		int[] lastKJumps = new int[k];  // Array to track energy for the last k stones

		// Set base case: no cost to be on the first stone
		lastKJumps[0] = 0;

		for (int i = 1; i < n; i++) {
			int min = Integer.MAX_VALUE;

			// Compute the cost for all possible jumps from i-1, i-2, ..., i-k
			for (int j = 1; j <= k; j++) {
				if (i - j >= 0) {
					// Calculate the jump cost from i-j to i
					int jump = lastKJumps[(i - j) % k] + Math.abs(heights[i] - heights[i - j]);
					min = Math.min(min, jump);
				}
			}

			// Store the minimum cost in the circular buffer
			lastKJumps[i % k] = min;
		}

		// Return the minimum energy to reach the last stone
		return lastKJumps[(n - 1) % k];
	}


	// Tabular (Bottom to Top) approach
	public static int frogJumpUsingTabular(int n, int[] heights, int[] dp, int k) {
		dp[0] = 0;  // Base case: no cost to be on the first stone

		for (int i = 1; i < n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= k; j++) {
				if (i - j >= 0) {
					int jump = dp[i - j] + Math.abs(heights[n] - heights[n - j]);
					min = Math.min(min, jump);
				}
			}

			// Store the minimum cost in dp[i]
			dp[i] = min;
		}

		// Return the minimum energy to reach the last stone
		return dp[n];
	}


	// Memoization function Top to Down
	public static int frogJumpUsingMemo(int n, int[] heights, int[] dp, int k) {
		if (n == 0) {
			return 0;  // Base case: no cost for starting point
		}

		// If the result is already computed, return it
		if (dp[n] != -1) {
			return dp[n];
		}
		int min = Integer.MAX_VALUE;

		for (int i = 1; i <= k; i++) {
			int jump;
			if (n - i >= 0) {
				jump = frogJumpUsingMemo(n - i, heights, dp, k) + Math.abs(heights[n] - heights[n - i]);
				min = Math.min(min, jump);
			} else {
				break;
			}
		}

		// Store the computed result in dp[n] and return it
		dp[n] = min;
		return dp[n];
	}

	// Main function to initiate the process
	public static void main(String[] args) {
		int[] heights = { 10, 30, 40, 50, 20 };  // Example heights of stones
		int n = heights.length - 1;  // Index of the last stone

		// Initialize dp array with -1 (indicating that no value is computed yet)
		int[] dp = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			dp[i] = -1;
		}
		int k = 3;
		// Compute the minimum cost for the frog to reach the last stone
		int result = frogJumpUsingMemo(n, heights, dp, k);
		System.out.println("Minimum energy spent: " + result);
		System.out.println("Minimum energy spent: " + frogJumpUsingTabular(n, heights, dp, k));
		System.out.println("Minimum energy spent: " + frogJumpUsingTabularOptimized(n, heights, k));
	}
}
