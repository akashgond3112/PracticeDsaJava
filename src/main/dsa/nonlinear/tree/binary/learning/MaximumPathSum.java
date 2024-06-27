package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;

import static java.lang.System.out;
import static main.dsa.nonlinear.tree.binary.BinaryTreePrinter.printNode;
import static main.dsa.nonlinear.tree.binary.learning.BinaryTree.insert;

public class MaximumPathSum {

	public static int maximumPathSum(Node node, int[] max) {
		if (node == null)
			return 0;

		int left = Math.max(0, maximumPathSum(node.left, max));
		int right = Math.max(0, maximumPathSum(node.right, max));

		max[0] = Math.max(max[0], left + right + node.data);

		return Math.max(left, right) + node.data;
	}

	public static void main(String[] args) {

		Node root = null;
		// Insertion of nodes
		root = insert(root, 10);
		for (int i = 20; i <= 60; i += 10)
			root = insert(root, i);
		int[] diaMeter = new int[1];
		printNode(root);
		maximumPathSum(root, diaMeter);
		out.print(diaMeter[0]);
	}
}
