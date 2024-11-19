package main.dsa.two_pointers.medium;

import java.util.Arrays;

/*
838. Push Dominoes
Solved
Medium
Topics
Companies
There are n dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.

After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

You are given a string dominoes representing the initial state where:

dominoes[i] = 'L', if the ith domino has been pushed to the left,
dominoes[i] = 'R', if the ith domino has been pushed to the right, and
dominoes[i] = '.', if the ith domino has not been pushed.
Return a string representing the final state.

Example 1:
Input: dominoes = "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.

Example 2:
Input: dominoes = ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
*/
public class PushDominoes {

	public String pushDominoes(String dominoes) {
		int n = dominoes.length();
		char[] doms = dominoes.toCharArray();

		// Initialize the DP arrays
		int[] left = new int[n];
		int[] right = new int[n];

		// Fill with a large number (representing infinity)
		Arrays.fill(left, Integer.MAX_VALUE);
		Arrays.fill(right, Integer.MAX_VALUE);

		// First pass: left to right for 'R'
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (doms[i] == 'R') {
				time = 0;
			} else if (doms[i] == 'L') {
				time = Integer.MAX_VALUE;
			} else if (time != Integer.MAX_VALUE) {
				time++;
			}
			right[i] = time;
		}

		// Second pass: right to left for 'L'
		time = Integer.MAX_VALUE;
		for (int i = n - 1; i >= 0; i--) {
			if (doms[i] == 'L') {
				time = 0;
			} else if (doms[i] == 'R') {
				time = Integer.MAX_VALUE;
			} else if (time != Integer.MAX_VALUE) {
				time++;
			}
			left[i] = time;
		}

		// Determine the final state of each domino
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (left[i] == right[i]) {
				result.append('.');
			} else if (left[i] < right[i]) {
				result.append('L');
			} else {
				result.append('R');
			}
		}

		return result.toString();
	}
}
