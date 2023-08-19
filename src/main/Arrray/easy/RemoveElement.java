package main.Arrray.easy;

public class RemoveElement {
    
    public static int removeElement(int[] nums, int val) {

        int index = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        
    }
}
