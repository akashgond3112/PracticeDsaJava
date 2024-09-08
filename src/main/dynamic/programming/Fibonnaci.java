package main.dynamic.programming;

public class Fibonnaci {


	public static int fibonnaciUsingDpTabularOptimal(int n) {
		if (n == 0)
			return 0; // Handle the edge case for n = 0

		int prev = 0; // Fib(0)
		int prev2 = 1; // Fib(1)
		for (int i = 2; i <= n; i++) {
			int cur = prev + prev2;
			prev = prev2;
			prev2 = cur;
		}
		return prev2; // Return Fib(n)
	}

	public static int fibonnaciUsingDpTabular(int n) {

		int[] dp = new int[n + 1];

		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}


	public static int fibonnaciUsingDpMemoization(int n) {
		int[] dp = new int[n + 1]; // Array for memoization
		return memoize(n, dp);
	}

	private static int memoize(int n, int[] dp) {
		if (n <= 1)
			return n; // Base case
		if (dp[n] != 0)
			return dp[n]; // Return memoized value if already computed

		dp[n] = memoize(n - 1, dp) + memoize(n - 2, dp); // Recursive memoization
		return dp[n]; // Return the result
	}


	public static int fibonnaci(int n) {
		if (n <= 1)
			return n;
		return fibonnaci(n - 1) + fibonnaci(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(fibonnaci(4));
		System.out.println(fibonnaciUsingDpMemoization(4));
		System.out.println(fibonnaciUsingDpTabular(4));
		System.out.println(fibonnaciUsingDpTabularOptimal(4));
	}
}
