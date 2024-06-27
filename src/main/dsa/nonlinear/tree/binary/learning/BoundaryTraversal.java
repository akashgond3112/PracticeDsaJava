package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {

	private static boolean isLeaf(Node node) {
		return !(node.left == null && node.right == null);
	}

	private static void addLeftBoundary(Node root, ArrayList<Integer> list) {
		Node current = root.left;
		while (current != null) {

			if (!isLeaf(current))
				list.add(current.data);
			if (current.left != null)
				current = current.left;
			else
				current = current.right;
		}
	}

	private static void addRightBoundary(Node root, ArrayList<Integer> list) {
		Node current = root.right;
		List<Integer> temp = new ArrayList<>();
		while (current != null) {
			if (!isLeaf(current))
				temp.add(current.data);
			if (current.right != null)
				current = current.right;
			else
				current = current.left;
		}

		for (int i = temp.size() - 1; i >= 0; i--) {
			list.add(temp.get(i));
		}
	}

	private static void addLeaves(Node root, ArrayList<Integer> list) {

		if (isLeaf(root)) {
			list.add(root.data);
			return;
		}

		if (root.left != null)
			addLeaves(root.left, list);
		if (root.right != null)
			addLeaves(root.right, list);
	}

	public static void printBoundary(Node root) {

		ArrayList<Integer> list = new ArrayList<>();
		if (!isLeaf(root))
			list.add(root.data);
		addLeftBoundary(root, list);
		addLeaves(root, list);
		addRightBoundary(root, list);
	}
}
