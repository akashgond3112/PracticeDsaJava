package main.dsa.linear.stack;


import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such: MinStack obj = new MinStack(); obj.push(val);
 * obj.pop(); int param_3 = obj.top(); int param_4 = obj.getMin();
 * 155. Min Stack Medium Topics Companies Hint Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 * Implement the MinStack class:
 *
 * MinStack() initializes the stack object. void push(int val) pushes the element val onto the stack. void pop() removes
 * the element on the top of the stack. int top() gets the top element of the stack. int getMin() retrieves the minimum
 * element in the stack. You must implement a solution with O(1) time complexity for each function.
 *
 *
 *
 * Example 1:
 *
 * Input ["MinStack","push","push","push","getMin","pop","top","getMin"] [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output [null,null,null,null,-3,null,0,-2]
 *
 * Explanation MinStack minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3);
 * minStack.getMin(); // return -3 minStack.pop(); minStack.top();    // return 0 minStack.getMin(); // return -2
 *
 *
 * Constraints:
 *
 * -231 <= val <= 231 - 1 Methods pop, top and getMin operations will always be called on non-empty stacks. At most 3 *
 * 104 calls will be made to push, pop, top, and getMin.
 */

class MinStack {

	static class Pair<I extends Number, I1 extends Number> {
		int min;
		int val;

		public Pair(int min, int val) {
			this.min = min;
			this.val = val;
		}
	}

	Stack<Pair<Integer, Integer>> stack = new Stack<>();
	Stack<Long> st = new Stack<>();

	Long mini = Long.MAX_VALUE;

	public void push(int val) {
		if (stack.isEmpty()) {
			stack.push(new Pair<>(val, val));
		} else {
			stack.push(new Pair<>(val, Math.min(val, stack.peek().val)));
		}
	}

	public void pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		} else {
			stack.pop().val
		}
	}

	public int top() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		} else {
			return stack.peek().val;
		}
	}

	public int getMin() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.peek().min;

	}


	public void pushNew(int value) {
		Long val = (long) value;
		if (st.isEmpty()) {
			mini = val;
			st.push(val);
		} else {
			if (val < mini) {
				st.push(2 * val - mini);
				mini = val;
			} else {
				st.push(val);
			}
		}
	}

	public void popNew() {
		if (st.isEmpty())
			return;

		Long val = st.pop();
		if (val < mini) {
			mini = 2 * mini - val;
		}
	}

	public int topNew() {
		Long val = st.peek();
		if (val < mini) {
			return mini.intValue();
		}
		return val.intValue();
	}

	public int getMinNew() {
		return mini.intValue();
	}

	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		minStack.push(-2);
	}
}
