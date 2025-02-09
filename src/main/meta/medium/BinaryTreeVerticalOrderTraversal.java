package main.meta.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Problem statement You have been given a Binary Tree of 'N'
 *
 * nodes, where the nodes have integer values.
 *
 * Your task is to return the ln-Order, Pre-Order, and Post-Order traversals of the given binary
 * tree.
 *
 * For example : For the given binary tree: The Inorder traversal will be [5, 3, 2, 1, 7, 4, 6]. The
 * Preorder traversal will be [1, 3, 5, 2, 4, 7, 6]. The Postorder traversal will be [5, 2, 3, 7, 6,
 * 4, 1]. Detailed explanation ( Input/output format, Notes, Images ) Sample Input 1 : 1 2 3 -1 -1
 * -1 6 -1 -1 Sample Output 1 : 2 1 3 6 1 2 3 6 2 6 3 1 Explanation of Sample Output 1 : The given
 * binary tree is shown below:
 *
 * Inorder traversal of given tree = [2, 1, 3, 6] Preorder traversal of given tree = [1, 2, 3, 6]
 * Postorder traversal of given tree = [2, 6, 3, 1] Sample Input 2 : 1 2 4 5 3 -1 -1 -1 -1 -1 -1
 * Sample Output 2 : 5 2 3 1 4 1 2 5 3 4 5 3 2 4 1 Explanation of Sample Output 2 : The given binary
 * tree is shown below:
 *
 * Inorder traversal of given tree = [5, 2, 3, 1, 4] Preorder traversal of given tree = [1, 2, 5, 3,
 * 4] Postorder traversal of given tree = [5, 3, 2, 4, 1] Constraints : 1 <= 'N' <= 10^5 0 <= 'data'
 * <= 10^5
 *
 * where 'N' is the number of nodes and 'data' denotes the node value of the binary tree nodes.
 *
 * Time limit: 1 sec
 */
public class BinaryTreeVerticalOrderTraversal {

	static class TreeNode {
		int data;
		TreeNode left;
		TreeNode right;

		TreeNode() {
			this.data = 0;
			this.left = null;
			this.right = null;
		}

		TreeNode(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		TreeNode(int data, TreeNode left, TreeNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	};

	static class Tuple {
		TreeNode node;
		int x;
		int y;

		Tuple(TreeNode node, int x, int y) {
			this.node = node;
			this.x = x;
			this.y = y;
		}


	}


	/**
	 * Space Complexity:
	 * The space complexity of this solution is O(N) where N is the number of nodes in the binary tree.
	 * This is because we are using a TreeMap to store the nodes and a Queue for the BFS traversal.
	 *
	 * Time Complexity:
	 * The time complexity of this solution is O(N log N) where N is the number of nodes in the binary tree.
	 * This is because we are inserting nodes into a TreeMap which takes O(log N) time for each insertion.
	 *
	 * Edge Cases:
	 * 1. The binary tree is empty (root is null). In this case, the function should return an empty list.
	 * 2. The binary tree has only one node. The function should return a list containing a single list with that node.
	 * 3. All nodes have the same value. The function should still return the correct vertical order traversal.
	 * 4. The binary tree is skewed to the left or right. The function should handle this and return the correct vertical order traversal.
	 */
	public static class InOrderVerticalTraversal {
		/**
		 * Returns the vertical order traversal of a binary tree.
		 *
		 * @param root the root of the binary tree
		 * @return a list of lists of integers representing the vertical order traversal
		 */
		public static List<List<Integer>> getTreeTraversal(TreeNode root) {
			// Handle the case when the binary tree is empty
			if (root == null) {
				return new ArrayList<>();
			}

			TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
			Queue<Tuple> queue = new LinkedList<Tuple>();
			queue.add(new Tuple(root, 0, 0));

			while (!queue.isEmpty()) {
				Tuple tuple = queue.poll();
				TreeNode node = tuple.node;
				int x = tuple.x;
				int y = tuple.y;

				if (!map.containsKey(x)) {
					map.put(x, new TreeMap<>());
				}

				if (!map.get(x).containsKey(y)) {
					map.get(x).put(y, new ArrayList<>());
				}

				map.get(x).get(y).add(node.data);

				if (node.left != null) {
					queue.offer(new Tuple(node.left, x - 1, y + 1));
				}

				if (node.right != null) {
					queue.offer(new Tuple(node.right, x + 1, y + 1));
				}
			}

			List<List<Integer>> result = new ArrayList<>();
			for (TreeMap<Integer, List<Integer>> ys : map.values()) {
				List<Integer> vertical = new ArrayList<>();
				for (List<Integer> nodes : ys.values()) {
					vertical.addAll(nodes);
				}
				result.add(vertical);
			}

			return result;
		}
	}

	/**
	 * Time Complexity (PreOrderVerticalTraversal): O(n) Space Complexity
	 * (PreOrderVerticalTraversal): O(n)
	 */
	public class PreOrderVerticalTraversal {

		// TreeNode definition for reference
		static class TreeNode {
			int data;
			TreeNode left, right;

			TreeNode(int data) {
				this.data = data;
			}
		}

		// A map of x -> map of y -> list of node values
		// We'll gather values as we traverse in pre-order
		private final TreeMap<Integer, TreeMap<Integer, List<Integer>>> nodeMap = new TreeMap<>();

		public List<List<Integer>> verticalOrderPre(TreeNode root) {
			dfsPre(root, 0, 0); // start from root with x=0, y=0
			return buildResult();
		}

		private void dfsPre(TreeNode node, int x, int y) {
			if (node == null)
				return;

			// Pre-order: Visit node first
			nodeMap.computeIfAbsent(x, k -> new TreeMap<>())
					.computeIfAbsent(y, k -> new ArrayList<>()).add(node.data);

			// Recurse left
			dfsPre(node.left, x - 1, y + 1);
			// Recurse right
			dfsPre(node.right, x + 1, y + 1);
		}

		private List<List<Integer>> buildResult() {
			List<List<Integer>> result = new ArrayList<>();
			for (TreeMap<Integer, List<Integer>> ys : nodeMap.values()) {
				List<Integer> vertical = new ArrayList<>();
				for (List<Integer> nodes : ys.values()) {
					vertical.addAll(nodes);
				}
				result.add(vertical);
			}
			return result;
		}

	}

	/**
	 * Time Complexity (PostOrderVerticalTraversal): O(n) Space Complexity
	 * (PostOrderVerticalTraversal): O(n)
	 */
	public class PostOrderVerticalTraversal {

		// TreeNode definition for reference
		static class TreeNode {
			int data;
			TreeNode left, right;

			TreeNode(int data) {
				this.data = data;
			}
		}

		// A map of x -> map of y -> list of node values
		// We'll gather values as we traverse in post-order
		private final TreeMap<Integer, TreeMap<Integer, List<Integer>>> nodeMap = new TreeMap<>();

		public List<List<Integer>> verticalOrderPost(TreeNode root) {
			dfsPost(root, 0, 0); // start from root with x=0, y=0
			return buildResult();
		}

		private void dfsPost(TreeNode node, int x, int y) {
			if (node == null)
				return;

			// Post-order: left, right, then visit node
			dfsPost(node.left, x - 1, y + 1);
			dfsPost(node.right, x + 1, y + 1);

			nodeMap.computeIfAbsent(x, k -> new TreeMap<>())
					.computeIfAbsent(y, k -> new ArrayList<>()).add(node.data);
		}

		private List<List<Integer>> buildResult() {
			List<List<Integer>> result = new ArrayList<>();
			for (TreeMap<Integer, List<Integer>> ys : nodeMap.values()) {
				List<Integer> vertical = new ArrayList<>();
				for (List<Integer> nodes : ys.values()) {
					vertical.addAll(nodes);
				}
				result.add(vertical);
			}
			return result;
		}

	}

	public static void main(String[] args) {
		// Edge case 1: Empty tree
		TreeNode root1 = null;
		System.out.println(InOrderVerticalTraversal.getTreeTraversal(root1)); // Expected output: []

		// Edge case 2: Single node tree
		TreeNode root2 = new TreeNode(1);
		System.out.println(InOrderVerticalTraversal.getTreeTraversal(root2)); // Expected output: [[1]]

		// Edge case 3: All nodes have the same value
		TreeNode root3 = new TreeNode(1, new TreeNode(1), new TreeNode(1));
		System.out.println(InOrderVerticalTraversal.getTreeTraversal(root3)); // Expected output: [[1], [1], [1]]

		// Edge case 4: Left skewed tree
		TreeNode root4 = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);
		System.out.println(InOrderVerticalTraversal.getTreeTraversal(root4)); // Expected output: [[3], [2], [1]]

		// Edge case 5: Right skewed tree
		TreeNode root5 = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));
		System.out.println(InOrderVerticalTraversal.getTreeTraversal(root5)); // Expected output: [[1], [2], [3]]
	}
}
