package main.dsa.linear.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 901. Online Stock Span Medium Topics Companies Design an algorithm that collects daily price quotes for some stock
 * and returns the span of that stock's price for the current day.
 *
 * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going
 * backward) for which the stock price was less than or equal to the price of that day.
 *
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2,
 * then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4
 * consecutive days. Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock
 * today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8
 * for 3 consecutive days. Implement the StockSpanner class:
 *
 * StockSpanner() Initializes the object of the class. int next(int price) Returns the span of the stock's price given
 * that today's price is price.
 *
 *
 * Example 1:
 *
 * Input ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"] [[], [100], [80], [60], [70], [60],
 * [75], [85]] Output [null, 1, 1, 1, 2, 1, 4, 6]
 *
 * Explanation StockSpanner stockSpanner = new StockSpanner(); stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1 stockSpanner.next(60);  // return 1 stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1 stockSpanner.next(75);  // return 4, because the last 4 prices (including today's
 * price of 75) were less than or equal to today's price. stockSpanner.next(85);  // return 6
 *
 *
 * Constraints:
 *
 * 1 <= price <= 105 At most 104 calls will be made to next.
 */
public class OnlineStockSpan {

	static class Pair {
		public Integer value;
		public Integer index;

		public Pair(Integer value, Integer index) {
			this.value = value;
			this.index = index;
		}
	}

	List<Integer> res;

	Stack<Pair> stack; // Stores pairs of (price, span)
	int index = -1;

	OnlineStockSpan() {
		res = new ArrayList<>();
		stack = new Stack<>();

	}

	public int next(int price) {

		res.add(price);
		int count = 1;

		for (int i = res.size() - 2; i >= 0; i--) {
			if (res.get(i) < price) {
				count++;
			} else {
				break;
			}
		}
		return count;
	}

	public int nextOptimal(int price) {
		index = index + 1;

		// Pop elements from the stack while the current price is greater or equal
		// to the price on top of the stack, and accumulate the span
		while (!stack.isEmpty() && stack.peek().value <= price) {
			stack.pop();
		}

		int ans = index - (stack.isEmpty() ? -1 : stack.peek().index);

		// Push the current price and its span onto the stack
		stack.push(new Pair(price, index));

		return ans;
	}


	public static void main(String[] args) {
		OnlineStockSpan stockSpan = new OnlineStockSpan();
		System.out.println(stockSpan.next(100)); // 1
		System.out.println(stockSpan.next(80));  // 1
		System.out.println(stockSpan.next(60));  // 1
		System.out.println(stockSpan.next(70));  // 2
		System.out.println(stockSpan.next(60));  // 1
		System.out.println(stockSpan.next(75));  // 4
		System.out.println(stockSpan.next(85));  // 6


		System.out.println("Optimal solution");
		System.out.println(stockSpan.nextOptimal(100)); // 1
		System.out.println(stockSpan.nextOptimal(80));  // 1
		System.out.println(stockSpan.nextOptimal(60));  // 1
		System.out.println(stockSpan.nextOptimal(70));  // 2
		System.out.println(stockSpan.nextOptimal(60));  // 1
		System.out.println(stockSpan.nextOptimal(75));  // 4
		System.out.println(stockSpan.nextOptimal(85));  // 6
	}
}
