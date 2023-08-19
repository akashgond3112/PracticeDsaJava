package main.Arrray.easy;

public class PlusOne {

    public static int[] plusOne(int[] digits) {

        System.out.println("array size : " + digits.length);

        int sum = 0;

        for (int i = 0; i < digits.length; i++) {
            System.out.println("index : " + i + " value: " + digits[i]);
            sum += digits[i];
        }
        System.out.println(sum);
        return digits;
    }
    
    public static void main(String[] args) {
        plusOne(new int[]{ 1, 2, 3});
    }
    
}
