package main.dsa.nonlinear.tree.binary.learning;

import main.dsa.nonlinear.tree.binary.Node;

import java.util.Stack;

public class FlattenABinaryTreeToLinkedList {

	public static Node flattenRecursive(Node root) {
		if (root == null)
			return null;

		Node prev = null;

		flattenRecursive(root.right);
		flattenRecursive(root.left);

		root.left = prev;
		root.right = null;
		prev = root;
		return root;

	}

	public static Node flattenIterative(Node root) {

		if (root == null)
			return null;

		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node node = stack.pop();

			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}

			if (!stack.isEmpty()) {
				node.right = stack.peek();
			}
			node.left = null;
		}
		return root;
	}

	// Morris travel
	public static Node usingMST(Node root) {

		Node curr = root;

		while (curr != null) {
			if (curr.left != null) {
				Node pre = curr.left;
				while (pre.right != null) {
					pre = pre.right;
				}
				pre.right = curr.right;
				curr.right = curr.left;
			}
			curr = curr.right;
		}
		return root;

	}
}
