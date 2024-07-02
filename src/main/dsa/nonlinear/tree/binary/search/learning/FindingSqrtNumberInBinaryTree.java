package main.dsa.nonlinear.tree.binary.search.learning;

public class FindingSqrtNumberInBinaryTree {

	public static int findingSqrt(int target) {

		int low = 1;
		int high = target;
		while (low <= high) {
			long mid = (low + high) / 2;
			long val = mid * mid;

			if (val <= target) {
				low = (int) mid + 1;
			} else
				high = (int) mid - 1;

		}

		return high;
	}

	public static void main(String[] args) {

		System.out.println("The floor of square root of " + 25 + " is: " + findingSqrt(25));
	}
}
