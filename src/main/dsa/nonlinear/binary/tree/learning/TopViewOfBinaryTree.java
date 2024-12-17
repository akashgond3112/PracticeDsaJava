package main.dsa.nonlinear.binary.tree.learning;


import main.dsa.nonlinear.binary.tree.Node;
import main.dsa.nonlinear.binary.tree.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewOfBinaryTree {

	public static List<Integer> topView(Node root) {

		ArrayList<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Queue<Pair<Node, Integer>> queue = new LinkedList<>();
		Map<Integer, Integer> map = new TreeMap<>();
		queue.add(new Pair<>(root, 0));

		while (!queue.isEmpty()) {

			Pair<Node, Integer> pair = queue.remove();
			int hd = pair.val;

			Node node = pair.node;

			map.computeIfAbsent(hd, k -> node.data);
			if (node.left != null) {
				queue.add(new Pair<>(node.left, hd - 1));
			}
			if (node.right != null) {
				queue.add(new Pair<>(node.right, hd + 1));
			}
		}

		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			list.add(entry.getValue());
		}

		return list;
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		for (Integer list : topView(root)) {
			System.out.println(list);
		}
	}
}
