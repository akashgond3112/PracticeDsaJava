package main.dsa.nonlinear.binary.tree.learning;


import main.dsa.nonlinear.binary.tree.Node;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class RootToNodePath {

	private static boolean getPath(Node root, ArrayList<Integer> path, int val) {
		if (root == null) {
			return false;
		}
		path.add(root.data);

		if (root.data == val) {
			return true;
		}

		if (getPath(root.left, path, val) || getPath(root.right, path, val)) {
			return true;
		}

		path.remove(path.size() - 1);
		return false;
	}

	public static List<Integer> rootToNodePath(Node root, int val) {
		ArrayList<Integer> path = new ArrayList<>();

		if (root == null) {
			return path;
		}

		getPath(root, path, val);
		return path;
	}

	public static void main(String[] args) {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		for (Integer list : rootToNodePath(root, 7))
			out.println(list);
	}
}
