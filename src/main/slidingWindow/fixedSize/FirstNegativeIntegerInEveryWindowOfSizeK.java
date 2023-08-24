package main.slidingWindow.fixedSize;

import java.util.*;

public class FirstNegativeIntegerInEveryWindowOfSizeK {

    public static long[] printFirstNegativeInteger(long arr[], int n, int k) {
        int numWindows = n - k + 1;
        long[] result = new long[numWindows];
        int startIndex = 0;
        int endIndex = 0;
        Queue<Long> negativeNumbers = new LinkedList<>();

        while (endIndex < n) {
            if (arr[endIndex] < 0) {
                negativeNumbers.add(arr[endIndex]);
            }
            if (endIndex - startIndex + 1 < k) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == k) {
                if (negativeNumbers.isEmpty()) {
                    result[startIndex] = 0;
                } else {
                    result[startIndex] = negativeNumbers.peek();
                }
                if (negativeNumbers.peek() != null && arr[startIndex] == negativeNumbers.peek()) {
                    negativeNumbers.poll();
                }
                startIndex++;
                endIndex++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        long arr[] = {-8, 2, 3, -6, 10};
        int n = arr.length;
        int k = 2;
        System.out.println(Arrays.toString(printFirstNegativeInteger(arr, n, k)));
    }
}
