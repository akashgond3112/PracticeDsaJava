package main.dsa.nonlinear.tree.learning;


import main.dsa.nonlinear.tree.Node;

public class CountTotalNodesInACompleteBinaryTree {

	public static int countNodes(Node root) {
		if (root == null) {
			return 0;
		}

		int leftCount = getHeightLeft(root.left);
		int rightCount = getHeightRight(root.right);

		if (leftCount == rightCount)
			return ((2 << leftCount) - 1);

		else
			return countNodes(root.left) + countNodes(root.right) + 1;
	}

	private static int getHeightLeft(Node root) {
		int height = 0;
		while (root.left != null) {
			height++;
			root = root.left;
		}
		return height;
	}

	private static int getHeightRight(Node root) {
		int height = 0;
		while (root.right != null) {
			height++;
			root = root.right;
		}
		return height;
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		System.out.println(countNodes(root));
	}
}
