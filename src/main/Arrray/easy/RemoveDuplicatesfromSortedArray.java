package main.Arrray.easy;


public class RemoveDuplicatesfromSortedArray {

    public static int removeDuplicates(int[] nums) {

        int size = 1;
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] != nums[i + 1]) {
                nums[size++] = nums[i + 1];
            }
            i++;
        }

        return size;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 }; // Input array
        int[] expectedNums = new int[]{}; // The expected answer with correct length

        int k = removeDuplicates(nums); // Calls your implementation

        assert k == expectedNums.length;
        for (int i = 0; i < k; i++) {
            assert nums[i] == expectedNums[i];
        }
    }

}
