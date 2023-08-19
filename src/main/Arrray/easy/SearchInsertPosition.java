package main.Arrray.easy;

public class SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {

        int i=0;

        while (i < nums.length) {
            if(nums[i] ==target || target< nums[i])
                return i;
            i++;
        }

        return nums.length;
    }

    public static void main(String[] args) {

        System.out.println(searchInsert(new int[] {0,3}, 1));
    }

}
