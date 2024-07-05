package main.dsa.nonlinear.tree.binary.search.learning;

public class MedianOfTwoSortedArraysOfDifferentSizes {

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

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
			return (double) merged[n / 2] + (double) merged[n / 2 - 1] / 2.0;
	}

	public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
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

	public static void main(String[] args) {
		int[] nums1 = new int[] { 1, 3, 5, 7 };
		int[] nums2 = new int[] { 2, 4, 6, 8 };
		System.out.println(findMedianSortedArrays(nums1, nums2));
		System.out.println(findMedianSortedArrays2(nums1, nums2));
	}
}
