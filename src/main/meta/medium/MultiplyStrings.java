package main.meta.medium;

import java.util.*;

/**
 *
 *
 * <pre>
 * 43. Multiply Strings
 * Medium
 * Topics
 * Companies
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 *
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 *
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 *
 * Constraints:
 *
 * 1 <= num1.length, num2.length <= 200
 * num1 and num2 consist of digits only.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 *
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 * Accepted
 * 940.5K
 * Submissions
 * 2.2M
 * Acceptance Rate
 * 41.9%
 * Topics
 * Math
 * String
 * Simulation
 * </pre>
 */
public class MultiplyStrings {

  /**
   * Solution class for multiplying two large numbers represented as strings. This solution uses the
   * elementary school multiplication algorithm (multiply each digit and sum).
   */
  static class Solution {
    /**
     * Multiplies two numbers represented as strings.
     *
     * <p>Time Complexity: O(m*n) where m and n are the lengths of num1 and num2 Space Complexity:
     * O(m+n) for the result array
     *
     * @param num1 First number as a string
     * @param num2 Second number as a string
     * @return Result of multiplication as a string
     */
    public String multiply(String num1, String num2) {
      if (num1.equals("0") || num2.equals("0")) {
        return "0";
      }

      int[] result = new int[num1.length() + num2.length()];

      // The Collections.reverse(List.of(num1)) approach doesn't work because
      // List.of() returns an immutable list, and the original string is not modified.
      // We need to process the digits from right to left (least significant to most)

      for (int i = num1.length() - 1; i >= 0; i--) {
        for (int j = num2.length() - 1; j >= 0; j--) {
          int digit1 = num1.charAt(i) - '0';
          int digit2 = num2.charAt(j) - '0';
          int product = digit1 * digit2;

          // Positions in result array (in reverse order)
          int pos1 = i + j + 1; // Current position
          int pos2 = i + j; // Carry position

          // Add product to current position
          int sum = product + result[pos1];

          // Update current position and carry
          result[pos1] = sum % 10;
          result[pos2] += sum / 10;
        }
      }

      // Convert result array to string, skipping leading zeros
      StringBuilder sb = new StringBuilder();
      boolean leadingZero = true;

      for (int digit : result) {
        if (digit == 0 && leadingZero) {
          continue;
        }
        leadingZero = false;
        sb.append(digit);
      }

      return sb.toString();
    }
  }

  public static void main(String[] args) {}
}
