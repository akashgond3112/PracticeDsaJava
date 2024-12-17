package main.dsa.nonlinear.binary.search.learning;

public class MedianOfTwoSortedArraysOfDifferentSizes {

	public static double findMedianSortedArraysBruteForce(int[] nums1, int[] nums2) {

		int[] merged = new int[nums1.length + nums2.length];
		int i = 0;
		int j = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				merged[i++] = nums1[i++];
			} else {
				merged[i++] = nums2[j++];
			}
		}
		while (i < nums1.length) {
			merged[i++] = nums1[i++];
		}
		while (j < nums2.length) {
			merged[i++] = nums2[j++];
		}
		int n = nums1.length + nums2.length;
		if (n % 2 == 0) {
			return merged[n / 2];
		} else
			return merged[n / 2] + merged[n / 2 - 1] / 2.0;
	}

	public static double findMedianSortedArraysOptimalApproach(int[] nums1, int[] nums2) {
		int n = nums1.length + nums2.length;
		int i = 0, j = 0;

		int index2 = n / 2;
		int index1 = index2 - 1;
		int count = 0;

		int indexEle1 = -1;
		int indexEle2 = -1;

		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] < nums2[j]) {
				if (count == index1)
					indexEle1 = nums1[i];
				if (count == index2)
					indexEle2 = nums1[i];
				count++;
				i++;
			} else {
				if (count == index1)
					indexEle1 = nums2[j];
				if (count == index2)
					indexEle2 = nums2[j];
				count++;
				j++;
			}
		}

		while (i < nums1.length) {
			if (count == index1)
				indexEle1 = nums1[i];
			if (count == index2)
				indexEle2 = nums2[i];
			count++;
			i++;
		}
		while (j < nums2.length) {
			if (count == index1)
				indexEle1 = nums2[j];
			if (count == index2)
				indexEle2 = nums1[j];
			count++;
			j++;
		}

		if (n % 2 == 0) {
			return indexEle2;
		}

		return (double) (indexEle1 + indexEle2) / 2.0;
	}

	public static double findMedianSortedArraysUsingBinarySearch(int[] nums1, int[] nums2) {

		int n1 = nums1.length;
		int n2 = nums2.length;

		if (n1 > n2)
			return findMedianSortedArraysUsingBinarySearch(nums2, nums1);

		int low = 0;
		int high = n1;

		int left = (n1 + n2 + 1) / 2;
		int n = n1 + n2;

		while (low <= high) {
			int mid1 = (low + high) / 2;
			int mid2 = left - mid1;

			int l1 = Integer.MIN_VALUE;
			int l2 = Integer.MIN_VALUE;

			int r1 = Integer.MAX_VALUE;
			int r2 = Integer.MAX_VALUE;

			if (mid1 > 0)
				l1 = nums1[mid1 - 1];
			if (mid2 > 0)
				l2 = nums2[mid2 - 1];


			if (mid1 < n1)
				r1 = nums1[mid1];
			if (mid2 < n2)
				r2 = nums2[mid2];


			if (l1 <= r2 && l2 <= r1) {
				if (n % 2 == 1)
					return Math.max(l1, l2);
				else
					return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
			} else if (l1 > r2) {
				high = mid1 - 1;
			} else {
				low = mid1 + 1;
			}
		}
		return 0.0;
	}


	public static void main(String[] args) {
		int[] nums1 = new int[] { 1, 3, 5, 7 };
		int[] nums2 = new int[] { 2, 4, 6, 8 };
		System.out.println(findMedianSortedArraysBruteForce(nums1, nums2));
		System.out.println(findMedianSortedArraysOptimalApproach(nums1, nums2));
		System.out.println(findMedianSortedArraysUsingBinarySearch(nums1, nums2));

		int[] a = {1, 4, 7, 10, 12};
		int[] b = {2, 3, 6, 15};
		System.out.println("The median of two sorted arrays is " + findMedianSortedArraysBruteForce(a, b));
		System.out.println("The median of two sorted arrays is " + findMedianSortedArraysOptimalApproach(a, b));
		System.out.println("The median of two sorted arrays is " + findMedianSortedArraysUsingBinarySearch(a, b));
	}
}
