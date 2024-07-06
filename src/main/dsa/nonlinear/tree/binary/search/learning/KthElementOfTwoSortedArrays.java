package main.dsa.nonlinear.tree.binary.search.learning;

public class KthElementOfTwoSortedArrays {

	public static double findKthElementInTheSortedArrays(int[] nums1, int[] nums2, int kth) {

		int n1 = nums1.length;
		int n2 = nums2.length;

		if (n1 > n2)
			return findKthElementInTheSortedArrays(nums2, nums1, kth);

		int low = Math.max(0, kth - n2);
		int high = Math.min(kth, n1);

		while (low <= high) {
			int mid1 = (low + high) / 2;
			int mid2 = kth - mid1;

			int l1 = mid1 > 0 ? nums1[mid1 - 1] : Integer.MIN_VALUE;
			int l2 = mid2 > 0 ? nums2[mid2 - 1] : Integer.MIN_VALUE;

			int r1 = mid1 < n1 ? nums1[mid1] : Integer.MAX_VALUE;
			int r2 = mid2 < n2 ? nums2[mid2] : Integer.MAX_VALUE;


			if (l1 <= r2 && l2 <= r1) {
				return Math.max(l1, l2);
			} else if (l1 > r2) {
				high = mid1 - 1;
			} else {
				low = mid1 + 1;
			}
		}
		return 0.0;
	}

	public static void main(String[] args) {
		int[] a = { 1, 4, 7, 10, 12 };
		int[] b = { 2, 3, 6, 15 };
		System.out.println("The kth of two sorted arrays is " + findKthElementInTheSortedArrays(a, b, 4));
	}
}
