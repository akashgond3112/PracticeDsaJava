package main.dsa.slidingWindow.easy;

import java.util.Arrays;

public class MinimumDifferenceBetweenHighestAndLowestOfKScores {

    public static int minimumDifference(int[] nums, int k) {
        int result=0;
        int startIndex = 0;
        int endIndex = k - 1;
        int min = Integer.MAX_VALUE;

        if (k == 1) {
            return 0;
        }

        Arrays.sort(nums);

        while (endIndex < nums.length) {
            result = nums[endIndex++] - nums[startIndex++];
            min = Math.min(min, result);
        }

        return min;

    }


    public static void main(String[] args) {
        int[] nums = {9, 4, 1, 7};
        int k = 3;
        System.out.println(minimumDifference(nums, k));
    }
}
