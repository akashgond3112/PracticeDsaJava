package main.meta.easy;

import java.util.*;

/**
 *
 *
 * <pre>
 * 246. Strobogrammatic Number
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * Example 1:
 *
 * Input:  "69"
 * Output: true
 * Example 2:
 *
 * Input:  "88"
 * Output: true
 * Example 3:
 *
 * Input:  "962"
 * Output: false
 * Difficulty:
 * Easy
 * Lock:
 * Prime
 * Company:
 * Facebook Google
 * </pre>
 */
public class StrobogrammaticNumber {
  // Map containing strobogrammatic digit pairs
  static Map<Character, Character> m =
      new HashMap<>() {
        {
          put('0', '0');
          put('1', '1');
          put('8', '8');
          put('6', '9');
          put('9', '6');
        }
      };

  /**
   * Brute Force solution checking if a number is strobogrammatic.
   *
   * <p>Time Complexity: O(n) where n is the length of the string Space Complexity: O(1) as we use
   * constant extra space
   */
  public static class SolutionBruteForce {
    /**
     * Checks if a number is strobogrammatic.
     *
     * @param num the input number as a string
     * @return true if the number is strobogrammatic, false otherwise
     */
    public boolean isStrobogrammaticNumber(String num) {
      for (int i = 0; i <= num.length() / 2; ++i) {
        char rotatedChar = m.get(num.charAt(i));
        if (rotatedChar == '\0' || rotatedChar != num.charAt(num.length() - i - 1)) return false;
      }
      return true;
    }
  }

  /**
   * Two Pointer solution checking if a number is strobogrammatic.
   *
   * <p>Time Complexity: O(n) where n is the length of the string Space Complexity: O(1) as we use
   * constant extra space
   */
  public static class SolutionTwoPointer {
    /**
     * Checks if a number is strobogrammatic using two pointers.
     *
     * @param num the input number as a string
     * @return true if the number is strobogrammatic, false otherwise
     */
    public boolean isStrobogrammaticNumber(String num) {
      if (num == null || num.isEmpty()) {
        return true;
      }

      int i = 0;
      int j = num.length() - 1;

      while (i <= j) {
        // Check if the current digit is strobogrammatic
        if (!m.containsKey(num.charAt(i)) || num.charAt(j) != m.get(num.charAt(i))) {
          return false;
        }

        i++;
        j--; // This was incorrectly incrementing instead of decrementing
      }

      return true;
    }
  }

  /**
   * Main method to demonstrate strobogrammatic number checking.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    SolutionBruteForce s = new SolutionBruteForce();
    SolutionTwoPointer s1 = new SolutionTwoPointer(); // Fixed class name

    String num = "69";
    System.out.println(
        "Is " + num + " strobogrammatic? (Brute Force): " + s.isStrobogrammaticNumber(num));
    System.out.println(
        "Is " + num + " strobogrammatic? (Two Pointer): " + s1.isStrobogrammaticNumber(num));

    // Additional test cases
    String[] testCases = {"818", "69", "1", "962", "101"};
    for (String test : testCases) {
      System.out.println("Is " + test + " strobogrammatic? " + s1.isStrobogrammaticNumber(test));
    }
  }
}
