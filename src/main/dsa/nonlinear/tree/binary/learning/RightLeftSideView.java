package main.dsa.nonlinear.tree.binary.learning;


import main.dsa.nonlinear.tree.binary.Node;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class RightLeftSideView {

	public static void rightLeftSideView(Node root, int level, List<Integer> list, boolean isRightSide) {

		if (root == null)
			return;
		if (level == list.size()) {
			list.add(root.data);
		}
		rightLeftSideView(isRightSide ? root.right : root.left, level + 1, list, isRightSide);
		rightLeftSideView(isRightSide ? root.left : root.right, level + 1, list, isRightSide);
	}

	public static List<Integer> getRightOrLeftSideView(Node root, boolean isRightSide) {
		List<Integer> list = new ArrayList<>();
		rightLeftSideView(root, 0, list, isRightSide);
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

		for (Integer list : getRightOrLeftSideView(root, true))
			out.println(list);
		out.println("==========================");
		for (Integer list : getRightOrLeftSideView(root, false))
			out.println(list);
	}
}
