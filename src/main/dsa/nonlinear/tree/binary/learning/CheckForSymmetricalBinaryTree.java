package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;

import static java.lang.System.out;

public class CheckForSymmetricalBinaryTree {

	public static boolean isSymmetric(Node root) {
		return root == null || checkIsSymmetric(root.left, root.right);
	}

	private static boolean checkIsSymmetric(Node left, Node right) {

		if (left == null && right == null) {
			return true;
		}

		assert left != null;
		if (left.data != right.data)
			return false;

		return checkIsSymmetric(left.left, right.right) && checkIsSymmetric(left.right, right.left);
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		out.println(isSymmetric(root));
	}
}
