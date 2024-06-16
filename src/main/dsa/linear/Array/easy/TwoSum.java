package main.dsa.linear.Array.easy;

import java.util.HashMap;

public class TwoSum {
    //My Solution
    public static int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }
    
    // Best Soulution
    public static int[] twoSumNew(int[] nums, int target) {
        HashMap<Integer, Integer> check = new HashMap<Integer, Integer>();
        for(int i= 0; i<nums.length; i++){
            if(check.containsKey(nums[i])){
                return new int[] {i, (int) check.get(nums[i])};
            }
            check.put(target-nums[i], i);
        }
     
            return new int[]{0,0};
        }
    
    public static void main(String[] args) {
        long start = System.nanoTime();
        int[] nums = new int[] { 3,2,3 };
        System.out.println(twoSumNew(nums, 6));
        System.out.println(twoSum(nums, 6));
        long end = System.nanoTime();
        System.out.println(end-start);
        
    }
    
}
