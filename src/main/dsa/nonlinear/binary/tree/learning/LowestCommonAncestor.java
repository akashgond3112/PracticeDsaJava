package main.dsa.nonlinear.binary.tree.learning;

import main.dsa.nonlinear.binary.tree.Node;

import static java.lang.System.out;

public class LowestCommonAncestor {

	public static Node lowestCommonAncestor(Node root, Node p, Node q) {

		if (root == null || root == p || root == q)
			return root;

		Node left = lowestCommonAncestor(root.left, p, q);
		Node right = lowestCommonAncestor(root.right, p, q);

		if (left == null)
			return right;
		else if (right == null)
			return left;
		else
			return root;
	}


	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		out.println(lowestCommonAncestor(root, root.left.left, root.right).data);

	}
}
