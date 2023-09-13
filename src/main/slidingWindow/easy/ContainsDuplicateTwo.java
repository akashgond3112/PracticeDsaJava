package main.slidingWindow.easy;

import java.util.HashSet;

/*
* Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
*/
public class ContainsDuplicateTwo {

    // Brute force method
/*    public static  boolean containsNearbyDuplicate(int[] nums, int k) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                if(nums[i] == nums[j] && Math.abs(i - j) <= k){
                    return true;
                }
            }
        }
        return false;
    }*/

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        int startIndex = 0;
        int endIndex = 0;

        if (nums == null || nums.length < 2 || k == 0)
            return false;

        HashSet<Integer> set = new HashSet<>();

        while (endIndex < nums.length) {
            if (!set.add(nums[endIndex])) return true;
            if (set.size() >= k + 1) set.remove(nums[startIndex++]);
            endIndex++;

        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(containsNearbyDuplicate(nums, k));

        nums = new int[]{1, 0, 1, 1};
        k = 1;
        System.out.println(containsNearbyDuplicate(nums, k));

        nums = new int[]{1, 2, 3, 1, 2, 3};
        k = 2;
        System.out.println(containsNearbyDuplicate(nums, k));

    }
}
