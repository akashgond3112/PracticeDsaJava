package main.dsa.nonlinear.binary.tree.learning;

import main.dsa.nonlinear.binary.tree.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import static java.lang.System.*;


public class BinaryTree {

	public static class Pair {
		Node node;
		int val;

		private Pair(Node node, int val) {
			if (node == null)
				throw new IllegalArgumentException("Node cannot be null");

			node = this.node;
			val = this.val;
		}

		public static Pair createPair(Node node, int val) {
			return new Pair(node, val);
		}
	}

	public static Node insert(Node root, int data) {
		// If a tree is empty, new node becomes the root
		if (root == null) {
			root = new Node(data);
			return root;
		}
		// Queue to traverse the tree and find the position to
		// insert the node
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		if (!q.isEmpty()) {
			do {
				Node temp = q.poll();
				// Insert node as the left child of the parent node
				if (temp.left == null) {
					temp.left = new Node(data);
					break;
				}
				// If the left child is not null, push it to the
				// queue
				else
					q.offer(temp.left);
				// Insert node as the right child of parent node
				if (temp.right == null) {
					temp.right = new Node(data);
					break;
				}
				// If the right child is not null, push it to the
				// queue
				else
					q.offer(temp.right);
			} while (!q.isEmpty());
		}
		return root;
	}

	/* function to delete the given deepest node
	(d_node) in a binary tree */
	public static void deleteDeepest(Node root, Node d_node) {
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		// Do level order traversal until last node
		Node temp;
		while (!q.isEmpty()) {
			temp = q.poll();
			if (temp == d_node) {
				return;
			}
			if (temp.right != null) {
				if (temp.right == d_node) {
					temp.right = null;
					return;
				} else
					q.offer(temp.right);
			}
			if (temp.left != null) {
				if (temp.left == d_node) {
					temp.left = null;
					return;
				} else
					q.offer(temp.left);
			}
		}
	}

	/* function to delete an element in a binary tree */
	public static Node deletion(Node root, int key) {
		if (root == null)
			return null;
		if (root.left == null && root.right == null) {
			if (root.data == key)
				return null;
			else
				return root;
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		Node temp = new Node(0);
		Node key_node = null;
		// Do level order traversal to find deepest
		// node(temp) and node to be deleted (key_node)
		while (!q.isEmpty()) {
			temp = q.poll();
			if (temp.data == key)
				key_node = temp;
			if (temp.left != null)
				q.offer(temp.left);
			if (temp.right != null)
				q.offer(temp.right);
		}
		if (key_node != null) {
			key_node.data = temp.data;
			deleteDeepest(root, temp);
		}
		return root;
	}

	// Inorder tree traversal (Left - Root - Right)
	public static void inorderTraversal(Node root) {
		if (root == null)
			return;
		inorderTraversal(root.left);
		out.print(root.data + " ");
		inorderTraversal(root.right);
	}

	public static List<Integer> inOrderTraversalUsingStack(Node root) {
		List<Integer> inOrder = new ArrayList<>();

		Stack<Node> stack = new Stack<>();

		Node node = root;

		while (true) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				if (stack.isEmpty()) {
					break;
				}
				node = stack.pop();
				inOrder.add(node.data);
				node = node.right;
			}
		}

		return inOrder;
	}

	// Preorder tree traversal (Root - Left - Right)
	public static void preOrderTraversal(Node root) {
		if (root == null)
			return;
		out.print(root.data + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}

	public static List<Integer> preOrderTraversalUsingStack(Node root) {
		List<Integer> preOrder = new ArrayList<>();

		if (root == null)
			return preOrder;

		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			preOrder.add(root.data);
			if (root.right != null) {
				stack.push(root.right);
			}
			if (root.left != null) {
				stack.push(root.left);
			}
		}

		return preOrder;
	}

	// Postorder tree traversal (Left - Right - Root)
	public static void postOrderTraversal(Node root) {
		if (root == null)
			return;
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		out.print(root.data + " ");
	}

	public static List<Integer> postOrderTraversalUsingTwoStack(Node root) {

		Stack<Node> stack1 = new Stack<>();
		Stack<Node> stack2 = new Stack<>();
		List<Integer> integerList = new ArrayList<>();

		if (root == null)
			return integerList;

		stack1.push(root);
		while (!stack1.isEmpty()) {
			root = stack1.pop();
			stack2.add(root);
			if (root.left != null)
				stack1.push(root.left);
			if (root.right != null)
				stack1.push(root.right);
		}

		while (!stack2.isEmpty()) {
			integerList.add(stack2.pop().data);
		}
		return integerList;
	}

	public static List<Integer> postOrderTraversalUsingSingleStack(Node root) {

		Stack<Node> stack1 = new Stack<>();
		List<Integer> integerList = new ArrayList<>();

		Node curr = root;
		Node temp;
		//		stack1.push(root);
		while (curr != null || !stack1.isEmpty()) {
			if (curr != null) {
				stack1.push(curr);
				curr = curr.left;
			} else {
				temp = stack1.pop().right;
				if (temp == null) {
					temp = stack1.pop();
					stack1.pop();
					integerList.add(temp.data);

					while (!stack1.isEmpty() && temp == stack1.pop().right) {
						temp = stack1.pop();
						integerList.add(stack1.pop().data);
					}
				} else {
					curr = temp;
				}
			}
		}
		return integerList;
	}

	// Function for Level order tree traversal
	public static void levelOrderTraversal(Node root) {
		if (root == null)
			return;

		// Queue for level order traversal
		Queue<Node> q = new LinkedList<>();
		q.offer(root);
		while (!q.isEmpty()) {
			Node temp = q.poll();
			out.print(temp.data + " ");
			// Push left child in the queue
			if (temp.left != null)
				q.offer(temp.left);
			// Push right child in the queue
			if (temp.right != null)
				q.offer(temp.right);
		}
	}

	public static Map<String, List<Integer>> allLevelTraversal(Node root) {
		List<Integer> preOrder = new ArrayList<>();
		List<Integer> postOrder = new ArrayList<>();
		List<Integer> inOrder = new ArrayList<>();

		Map<String, List<Integer>> output = new HashMap<>();

		Stack<Pair> pairStack = new Stack<>();
		pairStack.push(Pair.createPair(root, 1));
		if (root == null)
			return output;

		while (!pairStack.isEmpty()) {

			Pair pair = pairStack.pop();

			if (pair.val == 1) {
				preOrder.add(pair.node.data);
				pair.val++;
				pairStack.push(pair);

				if (pair.node.left != null) {
					pairStack.push(Pair.createPair(pair.node.left, 1));
				}
			} else if (pair.val == 2) {
				inOrder.add(pair.node.data);
				pair.val++;
				pairStack.push(pair);

				if (pair.node.right != null) {
					pairStack.push(Pair.createPair(pair.node.right, 1));
				}
			} else {
				postOrder.add(pair.node.data);
			}
		}

		output.put("preOrder", preOrder);
		output.put("postOrder", postOrder);
		output.put("inOrder", inOrder);

		return output;
	}


	/* Driver function to check the above algorithm. */
	public static void main(String[] args) {
		Node root = null;
		// Insertion of nodes
		root = insert(root, 10);
		for (int i = 20; i <= 40; i += 10)
			root = insert(root, i);

		out.print("Preorder traversal: ");
		preOrderTraversal(root);

		out.print("\nInorder traversal: ");
		inorderTraversal(root);

		out.print("\nPostorder traversal: ");
		postOrderTraversal(root);

		out.print("\nLevel order traversal: ");
		levelOrderTraversal(root);

		// Delete the node with data = 20
		root = deletion(root, 20);

		out.print("\nInorder traversal after deletion: ");
		inorderTraversal(root);
	}
}
