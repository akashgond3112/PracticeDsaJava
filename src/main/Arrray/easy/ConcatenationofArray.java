package main.Arrray.easy;

public class ConcatenationofArray {

    public static int[] getConcatenation(int[] nums) {

        int ans[] = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[i];
            ans[i + nums.length] = nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {

        int result[] = getConcatenation(new int[] { 0, 2, 1, 5, 3, 4 });
        System.out.println(result.toString());
    }
    
}
