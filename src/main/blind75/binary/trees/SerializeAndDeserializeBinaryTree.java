package main.blind75.binary.trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 297. Serialize and Deserialize Binary Tree Hard Topics Companies Serialization is the process of converting a data
 * structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted
 * across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to
 * a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily
 * need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 *
 *
 * Example 1: Input: root = [1,2,3,null,null,4,5] Output: [1,2,3,null,null,4,5] Example 2: Input: root = [] Output: []
 * <p>
 * Constraints: The number of nodes in the tree is in the range [0, 104]. -1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeBinaryTree {

	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static class CodecUsingBfs {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			if (root == null)
				return "N";
			StringBuilder res = new StringBuilder();
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);

			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				if (node == null) {
					res.append("N,");
				} else {
					res.append(node.val).append(",");
					queue.add(node.left);
					queue.add(node.right);
				}
			}
			return res.toString();
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			String[] vals = data.split(",");
			if (vals[0].equals("N"))
				return null;
			TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			int index = 1;

			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				if (!vals[index].equals("N")) {
					node.left = new TreeNode(Integer.parseInt(vals[index]));
					queue.add(node.left);
				}
				index++;
				if (!vals[index].equals("N")) {
					node.right = new TreeNode(Integer.parseInt(vals[index]));
					queue.add(node.right);
				}
				index++;
			}
			return root;
		}
	}

	public static class CodecUsingDfs {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {
			List<String> res = new ArrayList<>();
			dfsSerialize(root, res);
			return String.join(",", res);
		}

		private void dfsSerialize(TreeNode node, List<String> res) {
			if (node == null) {
				res.add("N");
				return;
			}
			res.add(String.valueOf(node.val));
			dfsSerialize(node.left, res);
			dfsSerialize(node.right, res);
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			String[] vals = data.split(",");
			int[] i = { 0 };
			return dfsDeserialize(vals, i);
		}

		private TreeNode dfsDeserialize(String[] vals, int[] i) {
			if (vals[i[0]].equals("N")) {
				i[0]++;
				return null;
			}
			TreeNode node = new TreeNode(Integer.parseInt(vals[i[0]]));
			i[0]++;
			node.left = dfsDeserialize(vals, i);
			node.right = dfsDeserialize(vals, i);
			return node;
		}
	}
}
