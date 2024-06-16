package main.dsa.linear.Array.easy;

public class RunningSumof1dArray {

    public static int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++)
            nums[i] += nums[i - 1];
        return nums;
    }

    public static void main(String[] args) {

        int result[] = runningSum(new int[] { 1, 2, 3, 4 });
        System.out.println(result.toString());
    }

}
