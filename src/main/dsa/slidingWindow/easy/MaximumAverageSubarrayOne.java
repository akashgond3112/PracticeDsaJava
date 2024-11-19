package main.dsa.slidingWindow.easy;

public class MaximumAverageSubarrayOne {

    public static double findMaxAverage(int[] arr, int k) {
        if (arr.length == 1 && arr[0] < 0 && k != 0) {
            return -1.00000;
        }
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        double max = Integer.MIN_VALUE; // Initialize max with a smaller value

        while (endIndex < arr.length) {
            result = result + arr[endIndex];

            if (endIndex - startIndex + 1 < k) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == k) {
                max = Math.max(max, result);
                result = result - arr[startIndex];
                startIndex++;
                endIndex++;
            }
        }
        return max / k;
    }


    public static void main(String[] args) {
        int[] arr = {1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAverage(arr, k));

    }
}
