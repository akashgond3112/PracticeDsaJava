package main.dsa.nonlinear.tree.learning;

import main.dsa.nonlinear.tree.Node;

import static java.lang.System.*;
import static main.dsa.nonlinear.tree.learning.BinaryTree.*;

public class MaxDepth {

	public static int getMaxDepth(Node node) {
		if (node == null)
			return 0;

		int left = getMaxDepth(node.left);
		int right = getMaxDepth(node.right);

		return 1 + Math.max(left, right);
	}

	public static void main(String[] args) {

		Node root = null;
		// Insertion of nodes
		root = insert(root, 10);
		for (int i = 20; i <= 60; i += 10)
			root = insert(root, i);

		out.print(getMaxDepth(root));
	}
}
