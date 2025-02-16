# Binary Tree Traversal Guide

A comprehensive guide to understanding and implementing different tree traversal techniques.

## Table of Contents
- [Tree Structure](#tree-structure)
- [DFS Traversals](#dfs-traversals)
    - [Preorder](#preorder)
    - [Inorder](#inorder)
    - [Postorder](#postorder)
- [BFS Traversal](#bfs-traversal)
- [Specialized Traversals](#specialized-traversals)
- [Time & Space Complexity](#complexity)

## Tree Structure

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}
```

Example tree for all traversals:
```
       1
      / \
     2   3
    / \   \
   4   5   6
```

## DFS Traversals

### Preorder
Root → Left → Right

#### Recursive Implementation
```java
public List<Integer> preorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    preorderHelper(root, result);
    return result;
}

private void preorderHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    result.add(root.val);        // Process root
    preorderHelper(root.left);   // Process left subtree
    preorderHelper(root.right);  // Process right subtree
}
```

#### Iterative Implementation
```java
public List<Integer> preorderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) return result;
    
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        result.add(node.val);
        
        if (node.right != null) stack.push(node.right);
        if (node.left != null) stack.push(node.left);
    }
    return result;
}
```

For the example tree:
- Output: [1, 2, 4, 5, 3, 6]
- Process:
    1. Visit root (1)
    2. Traverse left subtree (2 → 4 → 5)
    3. Traverse right subtree (3 → 6)

### Inorder
Left → Root → Right

#### Recursive Implementation
```java
public List<Integer> inorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    inorderHelper(root, result);
    return result;
}

private void inorderHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    inorderHelper(root.left);    // Process left subtree
    result.add(root.val);        // Process root
    inorderHelper(root.right);   // Process right subtree
}
```

#### Iterative Implementation
```java
public List<Integer> inorderIterative(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    
    while (current != null || !stack.isEmpty()) {
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        
        current = stack.pop();
        result.add(current.val);
        current = current.right;
    }
    return result;
}
```

For the example tree:
- Output: [4, 2, 5, 1, 3, 6]
- Process:
    1. Traverse left subtree (4)
    2. Visit root (2)
    3. Traverse right subtree (5)
    4. Visit root (1)
    5. Traverse right subtree (3 → 6)

### Postorder
Left → Right → Root

#### Recursive Implementation
```java
public List<Integer> postorderRecursive(TreeNode root) {
    List<Integer> result = new ArrayList<>();
    postorderHelper(root, result);
    return result;
}

private void postorderHelper(TreeNode root, List<Integer> result) {
    if (root == null) return;
    postorderHelper(root.left);   // Process left subtree
    postorderHelper(root.right);  // Process right subtree
    result.add(root.val);         // Process root
}
```

#### Iterative Implementation
```java
public List<Integer> postorderIterative(TreeNode root) {
    LinkedList<Integer> result = new LinkedList<>();
    if (root == null) return result;
    
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    
    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        result.addFirst(node.val);
        
        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);
    }
    return result;
}
```

For the example tree:
- Output: [4, 5, 2, 6, 3, 1]
- Process:
    1. Traverse left subtree (4 → 5)
    2. Traverse right subtree (6)
    3. Visit root (2 → 3 → 1)

## BFS Traversal

### Level Order
Level by level, left to right

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> currentLevel = new ArrayList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            currentLevel.add(node.val);
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        result.add(currentLevel);
    }
    return result;
}
```

For the example tree:
- Output: [[1], [2, 3], [4, 5, 6]]
- Process:
    1. Level 0: [1]
    2. Level 1: [2, 3]
    3. Level 2: [4, 5, 6]

## Specialized Traversals

### Zigzag Level Order
Alternating left-to-right and right-to-left

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;
    
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean leftToRight = true;
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        LinkedList<Integer> currentLevel = new LinkedList<>();
        
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            if (leftToRight) {
                currentLevel.add(node.val);
            } else {
                currentLevel.addFirst(node.val);
            }
            
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        
        result.add(currentLevel);
        leftToRight = !leftToRight;
    }
    return result;
}
```

For the example tree:
- Output: [[1], [3, 2], [4, 5, 6]]
- Process:
    1. Level 0: Left to right [1]
    2. Level 1: Right to left [3, 2]
    3. Level 2: Left to right [4, 5, 6]

## Complexity

### Time Complexity
- All traversals: O(N) where N is number of nodes
    - Each node is visited exactly once

### Space Complexity
1. Recursive DFS: O(H) where H is height of tree
    - Worst case: O(N) for skewed tree
    - Best case: O(log N) for balanced tree

2. Iterative DFS: O(H)
    - Space needed for stack

3. BFS: O(W) where W is maximum width of tree
    - Worst case: O(N/2) ≈ O(N) for complete binary tree at leaf level
    - Best case: O(1) for skewed tree

### When to Use Each Traversal

1. Preorder:
    - Creating a copy of the tree
    - Serializing/deserializing tree
    - Getting prefix expression

2. Inorder:
    - BST validation
    - Getting sorted elements from BST
    - Finding kth smallest element

3. Postorder:
    - Deleting nodes
    - Computing height/depth
    - Bottom-up calculations

4. Level Order:
    - Level-based operations
    - Finding minimum depth
    - Connecting nodes at same level