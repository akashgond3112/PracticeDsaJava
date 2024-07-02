package main.dsa.nonlinear.tree.binary.search.learning;

public class FindingNthRootOfAnInteger {

	public static long findingNthRoot(int n , int m) {

		int low = 1;
		int high = m;
		while (low <= high) {
			int mid = (low + high) / 2;
			int val = findingNthRootValue(mid, n , m);

			if (val == 1) {
				return mid;
			} else if (val == 0) {
				low = mid + 1;
			} else
				high = mid - 1;

		}

		return -1;
	}

	private static int findingNthRootValue(int mid, int n, int m) {

		long ans = 1;
		for (int i = 1; i <= n; i++) {
			ans = ans * mid;
			if (ans > m)
				return 2;
		}
		if (ans == m)
			return 1;
		return 0;
	}

	public static void main(String[] args) {

		System.out.println("The answer is: " + findingNthRoot(3, 25));
	}
}
