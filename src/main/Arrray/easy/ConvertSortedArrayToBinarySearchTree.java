package main.Arrray.easy;

import java.util.Arrays;

/**
 * @author agond
 * @Project Learning
 * @Date 19/08/2023-08-2023
 * Copyright (C) 2023 Newcastle University, UK
 */


public class ConvertSortedArrayToBinarySearchTree {

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {

        return solution1(nums, 0, nums.length - 1);
//        return solution2(nums, 0, nums.length - 1);
    }

    private static TreeNode solution1(int nums[], int l, int r) {
        if (l > r) { // Base Condition or Recursion Stopping Condition
            return null;
        }
        // so basically in this question we have to convert a sorted array to a height-balanced tree,
        // so if we directly create a tree in given sorted order, it will become linked
        // list,
        // so we have to take a middle element as head value such it will become height
        // balanced tree
        int mid = l + (r - l) / 2; // this is the formula to find mid-value
        TreeNode root = new TreeNode(nums[mid]); // mid value or median
        root.left = solution1(nums, l, mid - 1); // assign the value for left of subtree that is l to mid -1 for given
        // array
        root.right = solution1(nums, mid + 1, r); // assign the value for right go subtree that is mid+1 to r for given
        // array
        return root;
    }

    private static TreeNode solution2(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = l + (r-l)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = solution2(nums, l, mid - 1);
        root.right = solution2(nums, mid + 1, r);
        return root;
    }
}
