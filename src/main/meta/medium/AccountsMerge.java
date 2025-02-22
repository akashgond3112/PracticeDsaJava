package main.meta.medium;

import main.dsa.nonlinear.graph.learning.minSpanningTree.kruskalAlgorithm.DisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 721. Accounts Merge
 * Medium
 * Topics
 * Companies
 * Hint
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name,
 * and the rest of the elements are emails representing emails of the account.
 * <p>
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts.
 * Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
 * A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 * <p>
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements
 * are emails in sorted order.
 * The accounts themselves can be returned in any order.
 * <p>
 * Example 1:
 * Input: accounts = [
 * ["John","johnsmith@mail.com","john_newyork@mail.com"],
 * ["John","johnsmith@mail.com","john00@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]]
 * <p>
 * Output: [
 * ["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],
 * ["Mary","mary@mail.com"],
 * ["John","johnnybravo@mail.com"]]
 * <p>
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * <p>
 *
 * Constraints:
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 * Topics
 * Array
 * Hash Table
 * String
 * Depth-First Search
 * Breadth-First Search
 * Union Find
 * Sorting*/
public class AccountsMerge {

	private static final Map<String, Integer> emailIdx = new HashMap<>(); // email -> id
	private static final List<String> emails = new ArrayList<>(); // set of emails of all accounts
	private static final Map<Integer, Integer> emailToAcc = new HashMap<>(); // email_index -> account_Id
	private static List<List<Integer>> adj;
	private static final Map<Integer, List<String>> emailGroup = new HashMap<>(); // index of acc -> list of emails
	private static boolean[] visited;

	private static int getAdjacencyList(List<List<String>> accounts) {
		int n = accounts.size();
		int m = 0;

		// Build email index and mappings
		for (int accId = 0; accId < n; accId++) {
			List<String> account = accounts.get(accId);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				if (!emailIdx.containsKey(email)) {
					emails.add(email);
					emailIdx.put(email, m);
					emailToAcc.put(m, accId);
					m++;
				}
			}
		}

		// Build adjacency list
		adj = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			adj.add(new ArrayList<>());
		}
		for (List<String> account : accounts) {
			for (int i = 2; i < account.size(); i++) {
				int id1 = emailIdx.get(account.get(i));
				int id2 = emailIdx.get(account.get(i - 1));
				adj.get(id1).add(id2);
				adj.get(id2).add(id1);
			}
		}

		// Initialize visited array
		visited = new boolean[m];
		return m;
	}

	private static List<List<String>> buildResult(List<List<String>> accounts) {
		List<List<String>> res = new ArrayList<>();
		for (int accId : emailGroup.keySet()) {
			List<String> group = emailGroup.get(accId);
			Collections.sort(group);
			List<String> merged = new ArrayList<>();
			merged.add(accounts.get(accId).getFirst());
			merged.addAll(group);
			res.add(merged);
		}

		return res;
	}

	/**
	 * Time & Space Complexity Time complexity: O((n∗m)log(n∗m)) Space complexity: O(n∗m) Where n is the number of
	 * accounts and m is the number of emails.
	 */
	static public class SolutionUsingDFS {

		public List<List<String>> accountsMerge(List<List<String>> accounts) {
			int m = getAdjacencyList(accounts);

			// DFS traversal
			for (int i = 0; i < m; i++) {
				if (!visited[i]) {
					int accId = emailToAcc.get(i);
					emailGroup.putIfAbsent(accId, new ArrayList<>());
					dfs(i, accId);
				}
			}

			// Build result
			return buildResult(accounts);
		}

		private void dfs(int node, int accId) {
			visited[node] = true;
			emailGroup.get(accId).add(emails.get(node));
			for (int neighbor : adj.get(node)) {
				if (!visited[neighbor]) {
					dfs(neighbor, accId);
				}
			}
		}
	}

	public static class SolutionUsingBFS {

		public List<List<String>> accountsMerge(List<List<String>> accounts) {
			int m = getAdjacencyList(accounts);

			// BFS traversal
			for (int i = 0; i < m; i++) {
				if (!visited[i]) {
					int accId = emailToAcc.get(i);
					emailGroup.putIfAbsent(accId, new ArrayList<>());
					bfs(i, accId);
				}
			}

			// Build result
			return buildResult(accounts);
		}

		private void bfs(int start, int accId) {
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(start);
			visited[start] = true;

			while (!queue.isEmpty()) {
				int node = queue.poll();
				emailGroup.get(accId).add(emails.get(node));
				for (int neighbor : adj.get(node)) {
					if (!visited[neighbor]) {
						visited[neighbor] = true;
						queue.offer(neighbor);
					}
				}
			}
		}
	}

	public static class SolutionUsingDisjointSet {
		static List<List<String>> accountsMerge(List<List<String>> accounts) {
			// code here

			int n = accounts.size();
			DisjointSet ds = new DisjointSet(n);
			HashMap<String, Integer> map = new HashMap<>();

			for (int i = 0; i < n; i++) {
				for (int j = 1; j < accounts.get(i).size(); j++) {
					String u = accounts.get(i).get(j);
					if (!map.containsKey(u)) {
						map.put(u, i);
					} else {
						ds.unionBySize(i, map.get(u));
					}
				}
			}

			ArrayList<String>[] merges = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				merges[i] = new ArrayList<>();
			}

			for (Map.Entry<String, Integer> entry : map.entrySet()) {
				String u = entry.getKey();
				int v = ds.findUPar(entry.getValue());
				merges[v].add(u);
			}

			List<List<String>> result = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				if (merges[i].isEmpty())
					continue;
				Collections.sort(merges[i]);
				List<String> temp = new ArrayList<>();
				temp.add(accounts.get(i).get(0));

				temp.addAll(merges[i]);
				result.add(temp);
			}

			return result;
		}
	}
}
