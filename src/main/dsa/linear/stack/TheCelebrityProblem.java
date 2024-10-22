package main.dsa.linear.stack;

/**
 * You go to a party of N people, and you have to find out the celebrity over there.
 *
 * Scope This article tells about how to solve celebrity problem.
 *
 * In this article we will learn brute force approach to solve celebrity problem.
 *
 * In this article we will learn to solve celebrity problem using graphs
 *
 * In this article we will learn to solve celebrity problem using recursion.
 *
 * Takeaways Two Pointers approach is the most efficient way to solve the problem.
 *
 * Problem Statement The celebrity problem goes like this: you go to a party of people, and you have to find out the
 * celebrity over there.
 *
 * According to the problem, the definition of celebrity is — A celebrity is a person who is known to everyone in a
 * party, but he does not knows anyone over there.
 *
 * You will be given a square matrix with dimensions N X N. The matrix represents the people in the party. If an element
 * of row and column is set to 1, then it means that the person knows the person. Please note that, here the will always
 * be 0.
 *
 * Rule: A celebrity is known to everyone, but he does not know anyone at the party.
 *
 * Input Format: You will be given a matrix M, where the elements in the matrix represent whether a person is known to
 * another person or not. If the person is known to another person then, , else it is equal to 0.
 *
 * Task: Your task is to find out the celebrity at the party. Print the id of the celebrity, if present. If there is no
 * celebrity at the party, then print -1.
 *
 * Example 1: Input:
 *
 * N = 3 M[][] = {{0 1 0}, {0 0 0}, {0 1 0}} Output:
 *
 * 1 Explanation: The person with id = 1 does not know anyone, hence there are all 0’s marked in its row, whereas, all
 * the other people know the person 1. Please note, there is 1 marked at .
 *
 * Example 2: Input:
 *
 * N = 2 M[][] = {{0 1}, {1 0}} Output:
 *
 * -1 Explanation: Both of the person know each other in the party, so there is no celebrity in the party.
 *
 * Constraints The constraints for the problem is given below :- Constraints:
 *
 *
 *
 *
 * A
 */

public class TheCelebrityProblem {

	public static int findTheCelebrityOptimal(int[][] arr, int n) {
		int top = 0;
		int down = n - 1; // Initialize down to the last index (n-1)

		// Find the potential celebrity
		while (top < down) {
			if (arr[top][down] == 1) {
				// If top knows down, top cannot be the celebrity
				top++;
			} else {
				// If top doesn't know down, down cannot be the celebrity
				down--;
			}
		}

		// Now, top points to the potential celebrity
		int potentialCelebrity = top;

		// Validate the potential celebrity by checking the rows and columns
		for (int i = 0; i < n; i++) {
			if (i != potentialCelebrity) {
				// A celebrity knows no one, and everyone knows the celebrity
				if (arr[potentialCelebrity][i] == 1 || arr[i][potentialCelebrity] == 0) {
					return -1; // Not a celebrity
				}
			}
		}

		return potentialCelebrity; // Celebrity found
	}

	public static int findTheCelebrityBrute(int[][] arr, int n) {
		int[] knowMe = new int[n]; // Tracks how many people know person i
		int[] iKnow = new int[n];  // Tracks how many people person i knows

		// Fill the arrays based on the input matrix
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 1) {
					knowMe[j]++;    // Person j is known by person i
					iKnow[i]++;     // Person i knows person j
				}
			}
		}

		// Check for a celebrity
		for (int i = 0; i < n; i++) {
			// A celebrity is known by everyone (n-1 people) and knows no one (iKnow[i] == 0)
			if (knowMe[i] == n - 1 && iKnow[i] == 0) {
				return i;
			}
		}
		return -1; // No celebrity found
	}

	public static void main(String[] args) {
		int[][] input = new int[][] { { 0, 1, 0 },  // Person 0 knows person 1
				{ 0, 0, 0 },  // Person 1 knows no one (potential celebrity)
				{ 0, 1, 0 }   // Person 2 knows person 1
		};
		int n = input.length;
		System.out.println(findTheCelebrityBrute(input, n));  // Output should be 1
		System.out.println(findTheCelebrityOptimal(input, n));  // Output should be 1
	}
}
