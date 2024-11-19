package main.dsa.slidingWindow.medium;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agond
 * @package main.dsa.slidingWindow.medium
 * @Date 17/10/2023
 * @Project PracticeDSA
 * <p>
 * 2260. Minimum Consecutive Cards to Pick Up
 * Medium
 * Topics
 * Companies
 * Hint
 * You are given an integer array cards where cards[i] represents the value of the ith card. A pair of cards are matching if the cards have the same value.
 * <p>
 * Return the minimum number of consecutive cards you have to pick up to have a pair of matching cards among the picked cards.
 * If it is impossible to have matching cards, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: cards = [3,4,2,3,4,7]
 * Output: 4
 * Explanation: We can pick up the cards [3,4,2,3] which contain a matching pair of cards with value 3. Note that picking up the cards [4,2,3,4] is also optimal.
 * Example 2:
 * <p>
 * Input: cards = [1,0,5,3]
 * Output: -1
 * Explanation: There is no way to pick up a set of consecutive cards that contain a pair of matching cards.
 * <p>
 * Constraints:
 * <p>
 * 1 <= cards.length <= 105
 * 0 <= cards[i] <= 106
 */
public class MinimumConsecutiveCardsToPickUp {

    public static int minimumCardPickup(int[] cards) {

        int result = Integer.MAX_VALUE;
        int endIndex = 0;
        int startIndex = 0;

        Map<Integer, Integer> integerMap = new HashMap<>();

        while (endIndex < cards.length) {

            integerMap.put(cards[endIndex], integerMap.getOrDefault(cards[endIndex], 0) + 1);

            while (integerMap.get(cards[endIndex]) > 1) {
                result = Math.min(result, endIndex - startIndex + 1);
                integerMap.put(cards[startIndex], integerMap.getOrDefault(cards[startIndex], 0) - 1);

                if (integerMap.get(cards[startIndex]) == 0) {
                    integerMap.remove(cards[startIndex]);
                }

                startIndex++;
            }
            endIndex++;

        }

        if (result == Integer.MAX_VALUE) return -1;
        return result;

    }

    public static void main(String[] args) {
        int[] nums1;
        int result;

        nums1 = new int[]{1, 2, 3, 2, 1};
        result = minimumCardPickup(nums1);
        Assert.assertEquals("Test 1", 4, result);

        nums1 = new int[]{1,0,5,3};
        result = minimumCardPickup(nums1);
        Assert.assertEquals("Test 2", -1, result);

        nums1 = new int[]{95, 11, 8, 65, 5, 86, 30, 27, 30, 73, 15, 91, 30, 7, 37, 26, 55, 76, 60, 43, 36, 85, 47, 96, 6};
        result = minimumCardPickup(nums1);
        Assert.assertEquals("Test 2", 3, result);
    }
}
