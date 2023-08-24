package main.slidingWindow.fixedSize;

public class MaxMinSubArrayOfSizeK {

    public static int solutionMax(int[] arr, int windowSize) {
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MIN_VALUE; // Initialize max with a smaller value

        while (endIndex < arr.length) {
            result = result + arr[endIndex];

            if (endIndex - startIndex + 1 < windowSize) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == windowSize) {
                max = Math.max(max, result);
                result = result - arr[startIndex];
                startIndex++;
                endIndex++;
            }
        }
        return max;
    }

    public static int solutionMin(int[] arr, int windowSize) {
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        int min = Integer.MAX_VALUE; // Initialize min with a large value

        while (endIndex < arr.length) {
            result = result + arr[endIndex];

            if (endIndex - startIndex + 1 < windowSize) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == windowSize) {
                min = Math.min(min, result);
                result = result - arr[startIndex];
                startIndex++;
                endIndex++;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int arr[] = {1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 2;
        System.out.println(solutionMax(arr, k));
        System.out.println(solutionMin(arr, k));
    }
}
