package main.meta.medium;

/**
 * 2. Add Two Numbers Medium Topics Companies You are given two non-empty linked lists representing
 * two non-negative integers. The digits are stored in reverse order, and each of their nodes
 * contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * <p>You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * <p>Example 1:
 *
 * <p>Input: l1 = [2,4,3], l2 = [5,6,4] Output: [7,0,8] Explanation: 342 + 465 = 807. Example 2:
 *
 * <p>Input: l1 = [0], l2 = [0] Output: [0] Example 3:
 *
 * <p>Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] Output: [8,9,9,9,0,0,0,1]
 *
 * <p>Constraints:
 *
 * <p>The number of nodes in each linked list is in the range [1, 100]. 0 <= Node.val <= 9 It is
 * guaranteed that the list represents a number that does not have leading zeros. Seen this question
 * in a real interview before? 1/5 Yes No Accepted 5.5M Submissions 12.1M Acceptance Rate 45.5%
 * Topics Linked List Math Recursion
 */
public class AddTwoNumbers {
  public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) {
      this.val = val;
    }
    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
  
  /**
   * Iterative solution for adding two numbers represented as linked lists
   * 
   * Time Complexity: O(max(n,m)) where n and m are lengths of l1 and l2
   * We traverse both lists once, and the time is dominated by the longer list
   * 
   * Space Complexity: O(max(n,m)) for the new list created to store the result
   * No extra space except for the output list
   */
  class SolutionIterative {
    /**
     * Add two numbers represented by linked lists
     * 
     * @param l1 First number (linked list, digits in reverse order)
     * @param l2 Second number (linked list, digits in reverse order)
     * @return Sum as a linked list in reverse order
     * 
     * Time Complexity: O(max(n,m))
     * Space Complexity: O(max(n,m)) for the result list
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      ListNode dummy = new ListNode(0);
      ListNode current = dummy;
      int carry = 0;
      
      while (l1 != null || l2 != null || carry > 0) {
        int sum = carry;
        
        if (l1 != null) {
          sum += l1.val;
          l1 = l1.next;
        }
        
        if (l2 != null) {
          sum += l2.val;
          l2 = l2.next;
        }
        
        current.next = new ListNode(sum % 10);
        carry = sum / 10;
        current = current.next;
      }
      
      return dummy.next;
    }
  }
  
  /**
   * Recursive solution for adding two numbers represented as linked lists
   * 
   * Time Complexity: O(max(n,m)) where n and m are lengths of l1 and l2
   * We make one recursive call for each digit in the longer list
   * 
   * Space Complexity: O(max(n,m)) for:
   * - The result list: O(max(n,m))
   * - The recursion stack: O(max(n,m))
   * Total: O(max(n,m))
   */
  class SolutionRecursive {
    /**
     * Add two numbers represented by linked lists using recursion
     * 
     * @param l1 First number (linked list, digits in reverse order)
     * @param l2 Second number (linked list, digits in reverse order)
     * @return Sum as a linked list in reverse order
     * 
     * Time Complexity: O(max(n,m))
     * Space Complexity: O(max(n,m)) for result list and recursion stack
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      return dfs(l1, l2, 0);
    }
    
    /**
     * Helper method for recursive approach
     * 
     * @param l1 First linked list
     * @param l2 Second linked list
     * @param carry Carry value from previous addition
     * @return New linked list representing the sum
     * 
     * Time Complexity: O(max(n,m))
     * Space Complexity: O(max(n,m)) due to recursion depth
     */
    private ListNode dfs(ListNode l1, ListNode l2, int carry) {
      // Base case: if both lists are null and no carry, we're done
      if (l1 == null && l2 == null && carry == 0) {
        return null;
      }
      
      // Calculate the sum of current digits and carry
      int sum = carry;
      if (l1 != null) {
        sum += l1.val;
      }
      if (l2 != null) {
        sum += l2.val;
      }
      
      // Create new node with the digit
      ListNode result = new ListNode(sum % 10);
      
      // Recursive call for next digits
      result.next = dfs(
          (l1 != null) ? l1.next : null,
          (l2 != null) ? l2.next : null,
          sum / 10  // New carry
      );
      
      return result;
    }
  }
  
  /**
   * Main method with test cases
   */
  public static void main(String[] args) {
    // Test cases can be implemented here
    // Example:
    // AddTwoNumbers solution = new AddTwoNumbers();
    // ListNode l1 = solution.new ListNode(2, solution.new ListNode(4, solution.new ListNode(3)));
    // ListNode l2 = solution.new ListNode(5, solution.new ListNode(6, solution.new ListNode(4)));
    // Output should be 7->0->8 (representing 342 + 465 = 807)
  }
}
