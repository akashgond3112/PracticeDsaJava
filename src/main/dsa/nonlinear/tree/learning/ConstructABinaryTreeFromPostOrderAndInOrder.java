package main.dsa.nonlinear.tree.learning;

import main.dsa.nonlinear.tree.Node;

import java.util.HashMap;
import java.util.Map;

public class ConstructABinaryTreeFromPostOrderAndInOrder {


	public static Node buildTree(int[] postOrder, int[] inOrder) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < inOrder.length; i++) {
			map.put(inOrder[i], i);
		}

		Node root = buildTree(postOrder, 0, postOrder.length - 1, inOrder, 0, inOrder.length - 1, map);
		return root;

	}

	private static Node buildTree(int[] postOrder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd,
			Map<Integer, Integer> map) {

		if (postStart > postEnd || inStart > inEnd)
			return null;

		Node root = new Node(postOrder[postEnd]);

		int rootIndex = map.get(root.data);

		int numLeftInorder = rootIndex - inStart;

		root.left = buildTree(postOrder, postStart, postStart + numLeftInorder - 1, inorder, inStart, rootIndex - 1,
				map);

		root.right = buildTree(postOrder, postStart + numLeftInorder, postEnd - 1, inorder, rootIndex + 1, inEnd, map);

		return root;

	}

	public static void main(String[] args) {

		int[] inorder = { 1, 2, 3, 4, 5, 6, 7 };
		int[] preorder = { 4, 5, 6, 7, 8 };

		buildTree(preorder, inorder);
	}

}
