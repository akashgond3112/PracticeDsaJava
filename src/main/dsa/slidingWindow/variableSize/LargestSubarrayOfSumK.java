package main.dsa.slidingWindow.variableSize;

import java.util.HashMap;

public class LargestSubarrayOfSumK {

    public static int solutionMax(int[] arr, int expectedSum) {
        int actualSum = 0;
        int startIndex = 0;
        int endIndex = 0;
        int max = Integer.MIN_VALUE; // Initialize max with a smaller value

        while (endIndex < arr.length) {
            actualSum = actualSum + arr[endIndex];

            if (actualSum < expectedSum) {
                endIndex++;
            } else if (actualSum == expectedSum) {
                max = Math.max(max, endIndex - startIndex + 1);
                endIndex++;
            } else {
                while (actualSum > expectedSum) {
                    actualSum = actualSum - arr[startIndex];
                    startIndex++;
                }
                if (actualSum == expectedSum) {
                    max = Math.max(max, endIndex - startIndex + 1);
                }
                endIndex++;

            }

        }
        return max;
    }

    public static int solutionMax(int arr[], int n, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int max = 0;
        int sum = 0;

        for(int i=0;i<n;i++){
            sum += arr[i];
            if(sum == k){
                max = Math.max(i+1 , max);
            }
            else if(map.containsKey(sum-k)){
                max = Math.max(i-map.get(sum-k) , max);
            }
            if(!(map.containsKey(sum))){
                map.put(sum, i);

            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        int expectedSum1 = 9;
//        System.out.println("Test 1: " + solutionMax(test1, expectedSum1)); // Output: 2 (Sub-array: [2, 3, 4])

        int[] test2 = {-1, 2, 3, -4, 5};
        int expectedSum2 = 6;
//        System.out.println("Test 2: " + solutionMax(test2, expectedSum2)); // Output: 4 (Sub-array: [2, 3, -4, 5])
        System.out.println("Test 2: " + solutionMax(test2,test2.length, expectedSum2)); // Output: 4 (Sub-array: [2, 3, -4, 5])

        int[] test3 = {4, -1, 2, 3, -4, 5};
        int expectedSum3 = 4;
//        System.out.println("Test 3: " + solutionMax(test3, expectedSum3)); // Output: 4 (Sub-array: [4])

        int[] test4 = {-2, -3, 4, -1, -2, 1, 5, -3};
        int expectedSum4 = 0;
//        System.out.println("Test 4: " + solutionMax(test4, expectedSum4)); // Output: 5 (Sub-array: [-2, -3, 4, -1, -2])

        int[] test5 = {1, 1, 1, 1, 1, 1, 1, 1};
        int expectedSum5 = 4;
//        System.out.println("Test 5: " + solutionMax(test5, expectedSum5)); // Output: 4 (Sub-array: [1, 1, 1, 1])
    }

}
