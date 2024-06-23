package main.dsa.nonlinear.tree.learning;

import main.dsa.nonlinear.tree.Node;

public class ChildrenSumProperty {

	public static void sum(Node root) {
		if (root == null) {
			return;
		}

		int childrenSum = 0;

		if (root.left != null) {
			childrenSum += root.left.data;
		}

		if (root.right != null) {
			childrenSum += root.right.data;
		}

		if (childrenSum >= root.data)
			root.data = childrenSum;
		else {
			if (root.left != null)
				root.left.data = root.data;
			else if (root.right != null)
				root.right.data = root.data;
		}

		sum(root.left);
		sum(root.right);

		int totalChildrenSum = 0;
		if (root.left != null)
			totalChildrenSum += root.left.data;
		if (root.right != null)
			totalChildrenSum += root.right.data;
		if (root.left != null || root.right != null)
			root.data += totalChildrenSum;
	}
}
