package main.dsa.linear.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * Nearest Smaller Element Programming Stacks And Queues easy
 * 51.8% Success 598 18 Bookmark Asked In: Given an array, find the nearest smaller element G[i] for every element A[i]
 * in the array such that the element has an index smaller than i.
 *
 * More formally,
 *
 * G[i] for an element A[i] = an element A[j] such that j is maximum possible AND j < i AND A[j] < A[i] Elements for
 * which no smaller element exist, consider next smaller element as -1.
 *
 * Input Format
 *
 * The only argument given is integer array A. Output Format
 *
 * Return the integer array G such that G[i] contains nearest smaller number than A[i].If no such element occurs G[i]
 * should be -1. For Example
 *
 * Input 1: A = [4, 5, 2, 10, 8] Output 1: G = [-1, 4, -1, 2, 2] Explanation 1: index 1: No element less than 4 in left
 * of 4, G[1] = -1 index 2: A[1] is only element less than A[2], G[2] = A[1] index 3: No element less than 2 in left of
 * 2, G[3] = -1 index 4: A[3] is the nearest element which is less than A[4], G[4] = A[3] index 4: A[3] is the nearest
 * element which is less than A[5], G[5] = A[3]
 *
 * Input 2: A = [3, 2, 1] Output 2: [-1, -1, -1] Explanation 2: index 1: No element less than 3 in left of 3, G[1] = -1
 * index 2: No element less than 2 in left of 2, G[2] = -1 index 3: No element less than 1 in left of 1, G[3] = -1
 * Time Complexity: The time complexity is O(n) because each element is pushed and popped from the stack at most once.
 * Space Complexity: The space complexity is O(n) due to the stack and the result array.
 */
public class NearestSmallerElement {

	public int[] prevSmaller(int[] A) {

		int[] res = new int[A.length];
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < A.length; i++) {
			while (!stack.isEmpty() && stack.peek() >= A[i]) {
				stack.pop();
			}
			res[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(A[i]);
		}
		return res;
	}

	public static void main(String[] args) {
		NearestSmallerElement nearestSmallerElement = new NearestSmallerElement();
		System.out.println(Arrays.toString(nearestSmallerElement.prevSmaller(new int[] { 4, 5, 2, 10, 8 })));
	}
}
