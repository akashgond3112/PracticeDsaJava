package Arrray;

public class ShuffleTheArray {

    public static int[] shuffle(int[] nums, int n) {

        int[] result = new int[nums.length];
        

        for (int i = 0; i < n; i++) {
            
            result[2 * i] = nums[i];
            result[2 * i + 1] = nums[n + i];
        }

        return nums;

    }

    public static void main(String[] args) {

        shuffle(new int[] { 2, 5, 1, 3, 4, 7 }, 3);
    }

}
