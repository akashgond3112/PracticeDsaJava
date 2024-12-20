package main.blind75.binary.trees;

public class Pair<T1, T2> {
    TreeNode key;
    Integer value;

    public Pair(TreeNode key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public TreeNode getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

}
