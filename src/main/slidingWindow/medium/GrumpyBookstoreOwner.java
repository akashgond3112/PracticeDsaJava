package main.slidingWindow.medium;

import org.junit.Assert;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 24/09/2023
 * @Project PracticeDSA
 * <p>
 * 1052. Grumpy Bookstore Owner
 * Hint
 * There is a bookstore owner that has a store open for n minutes. Every minute, some number of customers enter the store.
 * You are given an integer array customers of length n where customers[i] is the number of the customer that enters the store at the start of the ith minute and all those customers leave after the end of that minute.
 * <p>
 * On some minutes, the bookstore owner is grumpy.
 * You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.
 * <p>
 * When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise, they are satisfied.
 * <p>
 * The bookstore owner knows a secret technique to keep themselves not grumpy for minutes consecutive minutes, but can only use it once.
 * <p>
 * Return the maximum number of customers that can be satisfied throughout the day.
 * <p>
 * Example 1:
 * <p>
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * Example 2:
 * <p>
 * Input: customers = [1], grumpy = [0], minutes = 1
 * Output: 1
 */
public class GrumpyBookstoreOwner {

    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int startIndex = 0;
        int endIndex = 0;
        int result = 0;
        int max = Integer.MIN_VALUE;

        /*Find the max subarray of length minutes, then calculate the sum of only index having grumpy time.*/

        while (endIndex < customers.length) {

            if (grumpy[endIndex] == 1)
                result = result + customers[endIndex];

            if (endIndex - startIndex + 1 < minutes) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == minutes) {
                max = Math.max(max, result);
                if (grumpy[startIndex] == 1)
                    result = result - customers[startIndex];
                startIndex++;
                endIndex++;
            }

        }

        endIndex = 0;
        result = 0;
        /*Find total sum of customer when store owner is not grumpy.*/
        while (endIndex < customers.length) {
            if (grumpy[endIndex] == 0) {
                result += customers[endIndex];
            }
            endIndex++;
        }

        /*Return the total of max subarray which include only the grumpiness time and the total when
         * the owner was nto grumpy.*/

        return max + result;

    }

    public static int maxSatisfiedOptimal(int[] customers, int[] grumpy, int minutes) {
        int satisfied = 0; // Total satisfied customers when the owner is not grumpy
        int maxSatisfied = 0; // Maximum satisfied customers during grumpy minutes
        int windowSum = 0; // Current sum of customers during grumpy minutes
        int right = 0; // Left pointer of the sliding window
        int left = 0; // Left pointer of the sliding window

        while (right < customers.length) {
            if (grumpy[right] == 0) {
                satisfied += customers[right];
            } else {
                windowSum += customers[right];
            }

            // Slide the window to keep it of size 'minutes'
            if (right - left + 1 > minutes) {
                if (grumpy[left] == 1) {
                    windowSum -= customers[left];
                }
                left++;
            }

            right++;
            // Update maxSatisfied
            maxSatisfied = Math.max(maxSatisfied, windowSum);
        }

        return satisfied + maxSatisfied;
    }


    public static void main(String[] args) {
        int[] customer;
        int[] grumpy;
        int minutes = 0;
        int result = 0;

        customer = new int[]{1, 0, 1, 2, 1, 1, 7, 5};
        grumpy = new int[]{0, 1, 0, 1, 0, 1, 0, 1};
        minutes = 3;
        result = maxSatisfiedOptimal(customer, grumpy, minutes);
        Assert.assertEquals("Test 1", 16, result);

        customer = new int[]{1};
        grumpy = new int[]{0};
        minutes = 1;
        result = maxSatisfiedOptimal(customer, grumpy, minutes);
        Assert.assertEquals("Test 2", 1, result);
    }
}
