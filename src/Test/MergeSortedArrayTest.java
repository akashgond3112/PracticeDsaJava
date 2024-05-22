package Test;

import main.Arrray.easy.MergeSortedArray;
import org.junit.Test;


/**
 * @author agond
 * @Project Learning
 * @Date 18/08/2023-08-2023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class MergeSortedArrayTest extends MergeSortedArray {

    @Test
    public void testMerge1() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        var m = 3;
        int[] nums2 = {2, 5, 6};
        var n = 3;
        MergeSortedArray.merge(nums1, m, nums2, n);
    }

    @Test
    public void testMerge2() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        var m = 3;
        int[] nums2 = {2, 5, 6};
        var n = 3;
        MergeSortedArray.merge2(nums1, m, nums2, n);
    }
}