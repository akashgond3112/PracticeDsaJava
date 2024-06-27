package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.BinaryTreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class MinTimeTakenToBurnBinaryTree {


	public static int minTimeToBurn(BinaryTreeNode<Integer> root, int start) {

		HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> map = new HashMap<>();

		BinaryTreeNode<Integer> target = bfsToMapParents(root, map, start);
		int max = findMaxDistance(map, target);
		return max;

	}

	private static int findMaxDistance(HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> map,
			BinaryTreeNode<Integer> target) {

		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
		queue.add(target);
		HashMap<BinaryTreeNode<Integer>, Integer> visited = new HashMap<>();

		visited.put(target, 1);
		int max = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int f = 0;
			for (int i = 0; i < size; i++) {
				BinaryTreeNode<Integer> node = queue.poll();

				if (node.left != null && visited.get(node.left) == null) {
					f = 1;
					visited.put(node.left, 1);
					queue.offer(node.left);
				}

				if (node.right != null && visited.get(node.right) == null) {
					f = 1;
					visited.put(node.right, 1);
					queue.offer(node.right);
				}

				if (map.get(node) != null && visited.get(map.get(node)) == null) {
					f = 1;
					visited.put(map.get(node), 1);
					queue.offer(map.get(node));
				}
			}

			if (f == 1)
				max++;

		}
		return max;
	}

	private static BinaryTreeNode<Integer> bfsToMapParents(BinaryTreeNode<Integer> root,
			HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> map, int start) {
		Queue<BinaryTreeNode<Integer>> queue = new LinkedList<>();
		queue.offer(root);

		BinaryTreeNode<Integer> current = new BinaryTreeNode<>(-1);
		while (!queue.isEmpty()) {
			BinaryTreeNode<Integer> node = queue.poll();
			if (node.value == start)
				current = node;
			if (node.left != null) {
				map.put(node.left, current);
				queue.offer(node.left);
			}
			if (node.right != null) {
				map.put(node.right, current);
				queue.offer(node.right);
			}
		}
		return current;
	}

	public static void main(String[] args) {

		BinaryTreeNode root = new BinaryTreeNode(1);
		root.left = new BinaryTreeNode(2);
		root.right = new BinaryTreeNode(3);
		root.left.left = new BinaryTreeNode(4);
		root.left.right = new BinaryTreeNode(5);
		root.right.left = new BinaryTreeNode(6);
		root.right.right = new BinaryTreeNode(7);

		System.out.print(minTimeToBurn(root, 2));
	}
}
