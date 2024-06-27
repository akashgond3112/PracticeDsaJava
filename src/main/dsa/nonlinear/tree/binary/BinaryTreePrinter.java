package main.dsa.nonlinear.tree.binary;


public class BinaryTreePrinter {
	public static void printNode(Node root) {
		int maxLevel = maxLevel(root);
		printNodeInternal(java.util.Collections.singletonList(root), 1, maxLevel);
	}

	private static void printNodeInternal(java.util.List<Node> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || isAllElementsNull(nodes)) {
			return;
		}

		int floor = maxLevel - level;
		int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		printWhitespaces(firstSpaces);

		java.util.List<Node> newNodes = new java.util.ArrayList<>();
		for (Node node : nodes) {
			if (node != null) {
				System.out.print(node.data);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			printWhitespaces(betweenSpaces);
		}
		System.out.println();

		for (int i = 1; i <= edgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null) {
					printWhitespaces(edgeLines + edgeLines + i + 1);
					continue;
				}

				if (nodes.get(j).left != null) {
					System.out.print("/");
				} else {
					printWhitespaces(1);
				}

				printWhitespaces(i + i - 1);

				if (nodes.get(j).right != null) {
					System.out.print("\\");
				} else {
					printWhitespaces(1);
				}

				printWhitespaces(edgeLines + edgeLines - i);
			}

			System.out.println();
		}

		printNodeInternal(newNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++) {
			System.out.print(" ");
		}
	}

	private static int maxLevel(Node node) {
		if (node == null) {
			return 0;
		}
		return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
	}

	private static boolean isAllElementsNull(java.util.List<Node> list) {
		for (Object object : list) {
			if (object != null) {
				return false;
			}
		}
		return true;
	}

}
