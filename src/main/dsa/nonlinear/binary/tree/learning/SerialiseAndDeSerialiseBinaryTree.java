package main.dsa.nonlinear.binary.tree.learning;

import main.dsa.nonlinear.binary.tree.Node;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class SerialiseAndDeSerialiseBinaryTree {

	public static String serialize(Node root) {

		if (root == null)
			return "";

		Queue<Node> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (node == null) {
				sb.append("#").append(" ");
				continue;
			}
			sb.append(node.data).append(" ");
			queue.add(node.left);
			queue.add(node.right);
		}
		return sb.toString();
	}

	public static Node deserialize(String data) {
		if (Objects.equals(data, ""))
			return null;
		Queue<Node> queue = new LinkedList<>();
		String[] tokens = data.split(" ");
		Node root = new Node(Integer.parseInt(tokens[0]));
		queue.add(root);
		for (int i = 1; i < tokens.length; i++) {
			Node node = queue.poll();

			if (!tokens[i].equals("#")) {
				Node left = new Node(Integer.parseInt(tokens[i]));
				node.left = left;
				queue.add(left);
			}
			if (!tokens[++i].equals("#")) {
				Node right = new Node(Integer.parseInt(tokens[i]));
				node.right = right;
				queue.add(right);
			}
		}
		return root;
	}
}
