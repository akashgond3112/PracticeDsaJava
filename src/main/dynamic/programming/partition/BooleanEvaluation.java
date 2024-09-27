package main.dynamic.programming.partition;

import java.util.Arrays;

/*
Problem statement
You are given an expression 'exp' in the form of a string where operands will be : (TRUE or FALSE), and operators will be : (AND, OR or XOR).



Now you have to find the number of ways we can parenthesize the expression such that it will evaluate to TRUE.



As the answer can be very large, return the output modulo 1000000007.



Note :

‘T’ will represent the operand TRUE.
‘F’ will represent the operand FALSE.
‘|’ will represent the operator OR.
‘&’ will represent the operator AND.
‘^’ will represent the operator XOR.
Example :

Input: 'exp’ = "T|T & F".

Output: 1

Explanation:
There are total 2  ways to parenthesize this expression:
    (i) (T | T) & (F) = F
    (ii) (T) | (T & F) = T
Out of 2 ways, one will result in True, so we will return 1.
Detailed explanation ( Input/output format, Notes, Images )
Sample Input 1 :
T^T^F
Sample Output 1 :
0
Explanation For Sample Input 1:
There are total 2  ways to parenthesize this expression:
(i) (T^T)^(F) = F
(ii) (T)^(T^F) = F
Both ways will result in False, so we will return 0.
Sample Input 2 :
F|T^F
Sample Output 2 :
2
Explanation For Sample Input 2:
For the first test case:
There are total 2  ways to parenthesize this expression:
(i) (F|T)^(F) = T
(ii) (F)|(T^F) = T
Both ways will result in True, so we will return 2.
Expected time complexity
The expected time complexity is O(n ^ 3), where 'n' denotes the length of 'exp'.
Constraints:
3 <= |‘exp’| <= 200
Where |'exp'| denotes the length of 'exp'.

Time Limit: 1 sec
*/
public class BooleanEvaluation {

	// Recursive method to evaluate boolean expression
	public static int evaluateExp(char[] arr, int i, int j, boolean isTrue) {
		// Base case: if there's only one character
		if (i == j) {
			if (isTrue)
				return arr[i] == 'T' ? 1 : 0;
			else
				return arr[i] == 'F' ? 1 : 0;
		}

		int ways = 0;

		// Iterate over operators in the expression
		for (int k = i + 1; k < j; k += 2) {
			int leftTrue = evaluateExp(arr, i, k - 1, true);
			int leftFalse = evaluateExp(arr, i, k - 1, false);
			int rightTrue = evaluateExp(arr, k + 1, j, true);
			int rightFalse = evaluateExp(arr, k + 1, j, false);

			// Evaluate based on the operator
			if (arr[k] == '&') {
				if (isTrue)
					ways += leftTrue * rightTrue;
				else
					ways += (leftTrue * rightFalse) + (leftFalse * rightTrue) + (leftFalse * rightFalse);
			} else if (arr[k] == '|') {
				if (isTrue)
					ways += (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue);
				else
					ways += leftFalse * rightFalse;
			} else if (arr[k] == '^') {
				if (isTrue)
					ways += (leftTrue * rightFalse) + (leftFalse * rightTrue);
				else
					ways += (leftTrue * rightTrue) + (leftFalse * rightFalse);
			}
		}

		return ways;
	}

	// Memoized version of the same function
	public static int evaluateExpMemo(char[] arr, int i, int j, boolean isTrue, int[][][] dp) {
		if (i == j) {
			if (isTrue)
				return arr[i] == 'T' ? 1 : 0;
			else
				return arr[i] == 'F' ? 1 : 0;
		}

		// If the result is already computed, return it
		if (dp[i][j][isTrue ? 1 : 0] != -1)
			return dp[i][j][isTrue ? 1 : 0];

		int ways = 0;

		for (int k = i + 1; k < j; k += 2) {
			int leftTrue = evaluateExpMemo(arr, i, k - 1, true, dp);
			int leftFalse = evaluateExpMemo(arr, i, k - 1, false, dp);
			int rightTrue = evaluateExpMemo(arr, k + 1, j, true, dp);
			int rightFalse = evaluateExpMemo(arr, k + 1, j, false, dp);

			if (arr[k] == '&') {
				if (isTrue)
					ways += leftTrue * rightTrue;
				else
					ways += (leftTrue * rightFalse) + (leftFalse * rightTrue) + (leftFalse * rightFalse);
			} else if (arr[k] == '|') {
				if (isTrue)
					ways += (leftTrue * rightTrue) + (leftTrue * rightFalse) + (leftFalse * rightTrue);
				else
					ways += leftFalse * rightFalse;
			} else if (arr[k] == '^') {
				if (isTrue)
					ways += (leftTrue * rightFalse) + (leftFalse * rightTrue);
				else
					ways += (leftTrue * rightTrue) + (leftFalse * rightFalse);
			}
		}

		return dp[i][j][isTrue ? 1 : 0] = ways;
	}

	public static void main(String[] args) {
		String str = "T|T&F^T";
		char[] arr = str.toCharArray();
		int n = arr.length;

		// Recursive solution
		System.out.println("Number of ways (Recursive): " + evaluateExp(arr, 0, n - 1, true));

		// Initialize the dp array for memoization
		int[][][] dp = new int[n][n][2];
		for (int[][] rows : dp) {
			for (int[] row : rows) {
				Arrays.fill(row, -1);
			}
		}

		// Memoized solution
		System.out.println("Number of ways (Memoized): " + evaluateExpMemo(arr, 0, n - 1, true, dp));
	}
}
