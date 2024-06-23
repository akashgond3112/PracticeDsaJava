package main.dsa.nonlinear.tree.learning;

import main.dsa.nonlinear.tree.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class NodesAtADistanceOfKInBiinaryTree {

	private static void markParent(Map<Node, Node> parents, Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.left != null) {
				parents.put(current.left, current);
				queue.offer(current.left);
			}
			if (current.right != null) {
				parents.put(current.right, current);
				queue.offer(current.right);
			}
		}
	}

	public static List<Integer> distanceK(Node root, Node target, int K) {
		Map<Node, Node> parents = new HashMap<>();
		markParent(parents, root);
		Map<Node, Boolean> visited = new HashMap<>();
		Queue<Node> queue = new LinkedList<>();
		queue.offer(target);
		visited.put(target, true);
		int currentLevel = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			if (currentLevel == K)
				break;
			currentLevel++;
			for (int i = 0; i < size; i++) {
				Node current = queue.poll();

				if (current.left != null && visited.get(current.left) == null) {
					queue.offer(current.left);
					visited.put(current.left, true);
				}
				if (current.right != null && visited.get(current.right) == null) {
					queue.offer(current.right);
					visited.put(current.right, true);
				}
				if (parents.get(current) != null && visited.get(parents.get(current)) == null) {
					queue.offer(parents.get(current));
					visited.put(parents.get(current), true);
				}
			}
		}
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			res.add(current.data);
		}
		return res;
	}

	public static void main(String[] args) {

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);

		for (Integer list : distanceK(root, root.left.left, 1)) {
			System.out.println(list);
		}
	}
}
