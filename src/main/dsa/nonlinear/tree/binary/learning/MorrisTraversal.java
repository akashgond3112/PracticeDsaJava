package main.dsa.nonlinear.tree.binary.learning;

import main.dsa.nonlinear.tree.binary.Node;

import java.util.ArrayList;
import java.util.List;

public class MorrisTraversal {

	public static List<Integer> morrisTraversalUsingInOrderOrPreOrder(Node root) {
		List<Integer> result = new ArrayList<>();
		if (root == null)
			return result;

		Node current = root;
		while (current != null) {
			if (current.left == null) {
				result.add(current.data);
				current = current.right;
			} else {
				Node pre = current.left;
				while (pre.right != null && pre.right != current) {
					pre = pre.right;
				}

				if (pre.right == null) {
					pre.right = current;
//					result.add(current.data); If enabled PreOrder is used.
					current = current.left;
				} else {
					pre.right = null;
					result.add(current.data); // If Disabled, PreOrder is used.
					current = current.right;
				}
			}
		}
		return result;
	}
}
