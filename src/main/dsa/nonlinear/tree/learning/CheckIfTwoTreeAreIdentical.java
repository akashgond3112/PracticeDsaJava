package main.dsa.nonlinear.tree.learning;


import main.dsa.nonlinear.tree.Node;

import static java.lang.System.out;
import static main.dsa.nonlinear.tree.learning.BinaryTree.insert;

public class CheckIfTwoTreeAreIdentical {

	public static boolean isSameTree(Node n1, Node n2) {
		if (n1 == null && n2 == null) {
			return true;
		}

		return (n1.data == n2.data) && isSameTree(n1.left, n2.left) && isSameTree(n1.right, n2.right);
	}

	public static void main(String[] args) {
		Node root1 = null;
		Node root2 = null;
		// Insertion of nodes
		root1 = insert(root1, 10);
		root2 = insert(root1, 10);
		for (int i = 20; i <= 40; i += 10) {
			root1 = insert(root1, i);
			root2 = insert(root2, i);
		}

		out.print(isSameTree(root1, root2));
	}
}
