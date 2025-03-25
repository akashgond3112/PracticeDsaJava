package main.dsa.nonlinear.binarysearchtree;

public class BinarySearchTree {

	public Node insert(Node root, int key) {
		if (root == null) {
			return new Node(key);
		}

		if (key < root.key) {
			root.left = insert(root.left, key);
		} else if (key > root.key) {
			root.right = insert(root.right, key);
		}

		return root;
	}

	public Node search(Node root, int key) {
		if (root == null || root.key == key) {
			return root;
		}

		if (key < root.key) {
			return search(root.left, key);
		}

		return search(root.right, key);
	}

	public Node delete(Node root, int key) {
		if (root == null) {
			return root;
		}

		if (key < root.key) {
			root.left = delete(root.left, key);
		} else if (key > root.key) {
			root.right = delete(root.right, key);
		} else {
			// Node with only one child or no child
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			// Node with two children: Get the inorder successor
			root.key = minValue(root.right);

			// Delete the inorder successor
			root.right = delete(root.right, root.key);
		}

		return root;
	}

	private int minValue(Node root) {
		int minValue = root.key;
		while (root.left != null) {
			minValue = root.left.key;
			root = root.left;
		}
		return minValue;
	}

	public Integer floor(Node root, int key) {
		if (root == null) {
			return null;
		}

		if (root.key == key) {
			return root.key;
		}

		if (root.key > key) {
			return floor(root.left, key);
		}

		Integer floorValue = floor(root.right, key);
		return (floorValue != null) ? floorValue : root.key;
	}

	public Integer ceiling(Node root, int key) {
		if (root == null) {
			return null;
		}

		if (root.key == key) {
			return root.key;
		}

		if (root.key < key) {
			return ceiling(root.right, key);
		}

		Integer ceilingValue = ceiling(root.left, key);
		return (ceilingValue != null) ? ceilingValue : root.key;
	}

}
