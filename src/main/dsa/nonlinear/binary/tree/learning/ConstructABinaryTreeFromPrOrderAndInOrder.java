package main.dsa.nonlinear.binary.tree.learning;

import main.dsa.nonlinear.binary.tree.Node;

import java.util.HashMap;
import java.util.Map;

public class ConstructABinaryTreeFromPrOrderAndInOrder {

	public static Node buildTree(int[] preorder, int[] inorder) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}

		Node root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
		return root;

	}

	private static Node buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> map) {

		if (preStart > preEnd || inStart > inEnd)
			return null;

		Node root = new Node(preorder[preStart]);
		int rootIndex = map.get(root.data);
		int numLeftInorder = rootIndex - inStart;

		root.left = buildTree(preorder, preStart + 1, preStart + numLeftInorder, inorder, inStart, rootIndex - 1, map);

		root.right = buildTree(preorder, preStart + numLeftInorder + 1, preEnd, inorder, rootIndex + 1, inEnd, map);

		return root;

	}


	public static void main(String[] args) {

		int[] inorder = { 1, 2, 3, 4, 5, 6, 7 };
		int[] preorder = { 4, 5, 6, 7, 8 };

		buildTree(preorder, inorder);
	}
}
