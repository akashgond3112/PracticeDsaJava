package main.dsa.nonlinear.binary.tree.learning;


import main.dsa.nonlinear.binary.tree.Node;

import static java.lang.System.out;
import static main.dsa.nonlinear.binary.tree.learning.BinaryTree.insert;

public class DiameterOfABinaryTree {

	public static int diameterOfABinaryTree(Node node, int[] max) {
		if (node == null)
			return 0;

		int left = diameterOfABinaryTree(node.left, max);
		int right = diameterOfABinaryTree(node.right, max);

		max[0] = Math.max(max[0], left + right);

		return 1 + Math.max(left, right);
	}

	public static void main(String[] args) {

		Node root = null;
		// Insertion of nodes
		root = insert(root, 10);
		for (int i = 20; i <= 60; i += 10)
			root = insert(root, i);
		int[] diaMeter = new int[1];
		diameterOfABinaryTree(root, diaMeter);
		out.print(diaMeter[0]);
	}
}
