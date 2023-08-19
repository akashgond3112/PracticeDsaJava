package main.Arrray.easy;

import java.util.Arrays;

/**
 * @author agond
 * @Project Learning
 * @Date 18/08/2023-08-2023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        System.out.println(i);
        int j = n - 1;
        System.out.println(j);
        int k = m + n - 1;
        System.out.println(k);

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1, finished = m + n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            nums1[finished--] = (nums1[tail1] > nums2[tail2]) ?
                    nums1[tail1--] : nums2[tail2--];
        }

        while (tail2 >= 0) { //only need to combine with remaining nums2, if any
            nums1[finished--] = nums2[tail2--];
        }
        System.out.println(Arrays.toString(nums1));
    }
}
