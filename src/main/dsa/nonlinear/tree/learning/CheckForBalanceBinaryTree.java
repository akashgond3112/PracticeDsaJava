package main.dsa.nonlinear.tree.learning;

import main.dsa.nonlinear.tree.Node;

import static java.lang.System.out;
import static main.dsa.nonlinear.tree.learning.BinaryTree.insert;
import static main.dsa.nonlinear.tree.learning.MaxDepth.getMaxDepth;

public class CheckForBalanceBinaryTree {

	public static boolean checkForBalanceBinaryTree(Node node) {

		if (node == null)
			return true;

		int left = getMaxDepth(node.left);
		int right = getMaxDepth(node.right);

		if (right == -1 || left == -1)
			return false;
		return Math.abs(left - right) <= 1;
	}

	public static void main(String[] args) {

		Node root = null;
		// Insertion of nodes
		root = insert(root, 10);
		for (int i = 20; i <= 60; i += 10)
			root = insert(root, i);

		out.print(checkForBalanceBinaryTree(root));
	}
}
