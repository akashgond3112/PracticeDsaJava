package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;
import main.dsa.nonlinear.tree.binary.Tuple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

import static java.lang.System.*;

public class VerticalOrderTraversal {

	public static List<List<Integer>> verticalOrder(Node root) {
		List<List<Integer>> result = new ArrayList<>();

		TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();

		Queue<Tuple<Node, Integer, Integer>> queue = new LinkedList<>();

		queue.offer(new Tuple<>(root, 0, 0));

		while (!queue.isEmpty()) {

			Tuple<Node, Integer, Integer> tuple = queue.poll();

			Node node = tuple.node;

			int x = tuple.row;
			int y = tuple.col;

			if (!map.containsKey(x)) {
				map.put(x, new TreeMap<>());
			}
			if (!map.get(x).containsKey(y)) {
				map.get(x).put(y, new PriorityQueue<>());
			}
			map.get(x).get(y).offer(node.data);

			if (node.left != null) {
				queue.offer(new Tuple<>(node.left, x - 1, y + 1));
			}
			if (node.right != null) {
				queue.offer(new Tuple<>(node.right, x + 1, y + 1));
			}
		}

		for (TreeMap<Integer, PriorityQueue<Integer>> map1 : map.values()) {
			result.add(new ArrayList<>());

			for (PriorityQueue<Integer> queue1 : map1.values()) {
				while (!queue1.isEmpty()) {
					result.get(result.size() - 1).add(queue1.poll());
				}
			}
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

		for (List<Integer> integerList : verticalOrder(root)) {
			integerList.forEach(out::println);
		}
	}
}
