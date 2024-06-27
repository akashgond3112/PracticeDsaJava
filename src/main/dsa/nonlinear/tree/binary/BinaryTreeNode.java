package main.dsa.nonlinear.tree.binary;

public class BinaryTreeNode<Integer> {
	public Integer value;
	public BinaryTreeNode<Integer> left;
	public BinaryTreeNode<Integer> right;

	public BinaryTreeNode(Integer value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
}

