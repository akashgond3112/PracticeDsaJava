package main.Arrray.easy;

public class NumberofGoodPairs {

    public static int numIdenticalPairs(int[] nums) {
        int output = 0;

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] == nums[j] && nums[i] < nums[j]) {
                    output++;
                }
            }
        }
        System.out.println(output);
        return output;
    }

    public static void main(String[] args) {

        numIdenticalPairs(new int[] { 1, 2, 3, 1, 1, 3 });
    }

}
