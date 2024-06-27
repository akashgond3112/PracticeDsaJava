package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;
import main.dsa.nonlinear.tree.binary.Pair;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.System.*;

public class WidthOfBinaryTree {

	public static int widthOfBinaryTree(Node root) {
		if (root == null)
			return 0;

		int ans = 0;
		Queue<Pair<Node, Integer>> q = new LinkedList<>();
		q.add(new Pair<>(root, 0));
		while (!q.isEmpty()) {
			int size = q.size();
			int min = q.peek().val;
			int first = 0;
			int last = 0;
			for (int i = 0; i < size; i++) {
				assert q.peek() != null;
				Node currNode = q.peek().node;
				int currVal = q.peek().val - min;

				q.poll();

				if (i == 0)
					first = currVal;
				if (i == size - 1)
					last = currVal;
				if (currNode.left != null)
					q.offer(new Pair<>(currNode.left, currVal * 2 + 1));
				if (currNode.right != null)
					q.offer(new Pair<>(currNode.right, currVal * 2 + 2));
			}
			ans = Math.max(ans, last - first + 1);
		}
		return ans;
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.left.left = new Node(2);
		root.left.left.left = new Node(2);
		root.right = new Node(3);
		root.right.right = new Node(3);
		root.right.right.right = new Node(3);



		out.println(widthOfBinaryTree(root));
	}
}
