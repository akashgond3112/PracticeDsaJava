package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class ZigZagTraversal {

	public static Vector<Vector<Integer>> zigZagLevelOrder(Node root) {

		Vector<Vector<Integer>> result = new Vector<>();
		if (root == null) {
			return result;
		}

		boolean leftToRight = true;

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			Vector<Integer> vector = new Vector<>(size);

			for (int i = 0; i < size; i++) {
				Node node = queue.peek();
				queue.poll();

				assert node != null;
				vector.add(node.data);
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			if (!leftToRight)
				Collections.reverse(vector);
			leftToRight = !leftToRight;
			result.add(vector);
		}

		return result;
	}

	public static void main(String[] args) {
		// Create a sample binary tree
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		// Perform ZigZag traversal
		Vector<Vector<Integer>> result = ZigZagTraversal.zigZagLevelOrder(root);

		// Print the result
		for (Vector<Integer> level : result) {
			System.out.println(level);
		}
	}
}
