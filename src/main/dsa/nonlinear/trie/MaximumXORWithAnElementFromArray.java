package main.dsa.nonlinear.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Maximum XOR With an Element From Array Hard 0/120 Average time to solve is 50m Contributed by 97 upvotes Asked in
 * companies Problem statement You are given an array/list ‘ARR’ consisting of ‘N’ non-negative integers. You are also
 * given a list ‘QUERIES’ consisting of ‘M’ queries, where the ‘i-th’ query is a list/array of two non-negative integers
 * ‘Xi’, ‘Ai’, i.e ‘QUERIES[i]’ = [‘Xi’, ‘Ai’].
 *
 * The answer to the ith query, i.e ‘QUERIES[i]’ is the maximum bitwise xor value of ‘Xi’ with any integer less than or
 * equal to ‘Ai’ in ‘ARR’.
 *
 * You should return an array/list consisting of ‘N’ integers where the ‘i-th’ integer is the answer of ‘QUERIES[i]’.
 *
 * Note:
 *
 * 1. If all integers are greater than ‘Ai’ in array/list ‘ARR’  then the answer to this ith query will be -1. Detailed
 * explanation ( Input/output format, Notes, Images ) Constraints: 1 <= T <= 50 1 <= N, M <= 10000 0 <= ARR[i], Xi, Ai
 * <= 10^9
 *
 * Where ‘T’ is the number of test cases, 'N' is the size of ‘ARR’, ‘M’  is the number of queries, ‘ARR[i]’ is an
 * element of array/list ‘ARR’ and ‘Xi’, ‘Ai’ are the integers in ‘QUERIES[i]’.
 *
 * Time limit: 1 sec Sample Input 1: 2 5 2 0 1 2 3 4 1 3 5 6 1 1 1 1 0 Sample Output 1: 3 7 -1 Explanation of sample
 * input 1: In the first test case, the answer of query [1, 3] is 3 because 1^2 = 3 and 2 <= 3,  and the answer of query
 * [5, 6] is 7 because  5 ^ 2 = 7 and 2 <= 6.
 *
 * In the second test case, no element is less than or equal to 0 in the given array ‘ARR’. Sample Input 2: 2 6 3 6 6 3
 * 5 2 4 6 3 8 1 12 4 5 2 0 0 0 0 0 1 0 1 1 Sample Output 2: 5 -1 15 1 1
 */
public class MaximumXORWithAnElementFromArray {

	static class Node {
		// Array to store links
		// to child nodes (0 and 1)
		MaximumXOR.Node[] links;

		// Constructor
		Node() {
			links = new MaximumXOR.Node[2];
		}

		// Method to check if a specific
		// bit key is present in the child nodes
		boolean containsKey(int bit) {
			// Returns true if the link at
			// index 'bit' is not null
			return links[bit] != null;
		}

		// Method to get the child node
		// corresponding to a specific bit
		MaximumXOR.Node get(int bit) {
			// Returns the child
			// node at index 'bit'
			return links[bit];
		}

		// Method to set a child node at a
		// specific index in the links array
		void put(int bit, MaximumXOR.Node node) {
			// Sets the child node at index
			// 'bit' to the provided node
			links[bit] = node;
		}
	}

	// Trie class
	static class Trie {
		// Root node of the Trie
		MaximumXOR.Node root;

		// Constructor to initialize
		// the Trie with a root node
		Trie() {
			// Creates a new root
			// node for the Trie
			root = new MaximumXOR.Node();
		}

		// Method to insert a number into the Trie
		void insert(int num) {
			// Start from the root node
			MaximumXOR.Node node = root;
			// Iterate through each bit of the
			// number (from left to right)
			for (int i = 31; i >= 0; i--) {
				// Extract the i-th bit of the number
				int bit = (num >> i) & 1;

				// If the current node doesn't have a
				// child node with the current bit
				if (!node.containsKey(bit)) {

					// Create a new child node
					// with the current bit
					node.put(bit, new MaximumXOR.Node());
				}

				// Move to the child node
				// corresponding to the current bit
				node = node.get(bit);
			}
		}

		// Method to find the maximum
		// XOR value for a given number
		int getMax(int num) {
			// Start from the root node
			MaximumXOR.Node node = root;

			// Initialize the maximum XOR value
			int maxNum = 0;

			// Iterate through each bit of
			// the number (from left to right)
			for (int i = 31; i >= 0; i--) {

				// Extract the i-th
				// bit of the number
				int bit = (num >> i) & 1;

				// If the complement of the current
				// bit exists in the Trie
				if (node.containsKey(1 - bit)) {

					// Update the maximum XOR
					// value with the current bit
					maxNum |= (1 << i);

					// Move to the child node corresponding
					// to the complement of the current bit
					node = node.get(1 - bit);
				} else {

					// Move to the child node
					// corresponding to the current bit
					node = node.get(bit);
				}
			}

			// Return the maximum XOR value
			return maxNum;
		}
	}

	public static ArrayList<Integer> maxXorQueries(ArrayList<Integer> arr, ArrayList<ArrayList<Integer>> queries) {
		// Write your code here.

		Collections.sort(arr);
		ArrayList<ArrayList<Integer>> queriesCopy = new ArrayList<>();
		int m = queries.size();
		for (int i = 0; i < m; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			temp.add(queries.get(i).get(1));
			temp.add(queries.get(i).get(0));
			temp.add(i);
			queriesCopy.add(temp);
		}

		queriesCopy.sort(Comparator.comparing(o -> o.get(0)));

		return getMaxQueries(arr, m, queriesCopy);
	}

	private static ArrayList<Integer> getMaxQueries(ArrayList<Integer> arr, int m,
			ArrayList<ArrayList<Integer>> queriesCopy) {
		int ind = 0;
		int n = arr.size();
		Trie trie = new Trie();
		ArrayList<Integer> maxQueries = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			maxQueries.add(-1);
		}

		for (int i = 0; i < m; i++) {
			while (ind < n && arr.get(ind) <= queriesCopy.get(i).get(0)) {
				trie.insert(arr.get(ind));
				ind++;
			}

			int queryIndex = queriesCopy.get(i).get(2);
			if (ind != 0) {
				maxQueries.set(queryIndex, trie.getMax(queriesCopy.get(i).get(1)));
			} else {
				maxQueries.set(queryIndex, -1);
			}
		}
		return maxQueries;
	}
}
