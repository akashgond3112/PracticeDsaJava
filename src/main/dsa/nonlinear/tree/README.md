# Comprehensive Binary Tree Guide

A complete reference for understanding, implementing, and solving problems with binary trees.

## Table of Contents
- [Tree Structure](#tree-structure)
- [Tree Traversals](#tree-traversals)
    - [DFS Traversals](#dfs-traversals)
        - [Preorder](#preorder)
        - [Inorder](#inorder)
        - [Postorder](#postorder)
    - [BFS Traversal](#bfs-traversal)
        - [Level Order](#level-order)
        - [Zigzag Level Order](#zigzag-level-order)
- [Common Tree Problems](#common-tree-problems)
    - [Height/Depth Problems](#heightdepth-problems)
    - [Path Problems](#path-problems)
    - [Binary Search Tree (BST) Problems](#binary-search-tree-bst-problems)
    - [Tree Construction Problems](#tree-construction-problems)
- [Problem-Solving Techniques](#problem-solving-techniques)
    - [Using Global Variables](#using-global-variables-for-state)
    - [Level-Based Processing](#level-based-problems)
    - [Parent Pointer Technique](#parent-pointer-technique)
- [Time & Space Complexity](#time--space-complexity)
- [Common Edge Cases](#common-edge-cases-to-test)
- [Common Mistakes to Avoid](#common-mistakes-to-avoid)

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

## Tree Traversals

There are two main approaches to traversing a tree:
1. Depth-First Search (DFS)
2. Breadth-First Search (BFS)

### DFS Traversals

DFS explores as far as possible along each branch before backtracking.

```java
void dfs(TreeNode root) {
    if (root == null) return;
    
    // Preorder: Process BEFORE traversing children
    process(root);                 // Preorder position
    dfs(root.left);
    // Inorder: Process BETWEEN traversing children (for BST, gives sorted order)
    process(root);                 // Inorder position
    dfs(root.right);
    // Postorder: Process AFTER traversing children
    process(root);                 // Postorder position
}
```

#### Preorder
**Sequence**: Root → Left → Right

**Recursive Implementation**:
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

**Iterative Implementation**:
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

**For the example tree**:
- Output: [1, 2, 4, 5, 3, 6]
- Process:
    1. Visit root (1)
    2. Traverse left subtree (2 → 4 → 5)
    3. Traverse right subtree (3 → 6)

**Uses**:
- Creating a copy of the tree
- Serializing/deserializing tree
- Getting prefix expression

#### Inorder
**Sequence**: Left → Root → Right

**Recursive Implementation**:
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

**Iterative Implementation**:
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

**For the example tree**:
- Output: [4, 2, 5, 1, 3, 6]
- Process:
    1. Traverse left subtree (4)
    2. Visit root (2)
    3. Traverse right subtree (5)
    4. Visit root (1)
    5. Traverse right subtree (3 → 6)

**Uses**:
- BST validation
- Getting sorted elements from BST
- Finding kth smallest element

#### Postorder
**Sequence**: Left → Right → Root

**Recursive Implementation**:
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

**Iterative Implementation**:
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

**For the example tree**:
- Output: [4, 5, 2, 6, 3, 1]
- Process:
    1. Traverse left subtree (4 → 5)
    2. Traverse right subtree (6)
    3. Visit root (2 → 3 → 1)

**Uses**:
- Deleting nodes
- Computing height/depth
- Bottom-up calculations

### BFS Traversal

BFS explores all nodes at the present depth before moving on to nodes at the next depth level.

```java
void bfs(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        int levelSize = queue.size();  // Important for level-by-level processing
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            process(node);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }
}
```

#### Level Order
**Sequence**: Level by level, left to right

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

**For the example tree**:
- Output: [[1], [2, 3], [4, 5, 6]]
- Process:
    1. Level 0: [1]
    2. Level 1: [2, 3]
    3. Level 2: [4, 5, 6]

**Uses**:
- Level-based operations
- Finding minimum depth
- Connecting nodes at same level

#### Zigzag Level Order
**Sequence**: Alternating left-to-right and right-to-left

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

**For the example tree**:
- Output: [[1], [3, 2], [4, 5, 6]]
- Process:
    1. Level 0: Left to right [1]
    2. Level 1: Right to left [3, 2]
    3. Level 2: Left to right [4, 5, 6]

## Common Tree Problems

### Height/Depth Problems

```java
// Get height of a tree
int getHeight(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(getHeight(root.left), getHeight(root.right));
}

// Get minimum depth of a tree
int getMinDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null) return 1 + getMinDepth(root.right);
    if (root.right == null) return 1 + getMinDepth(root.left);
    return 1 + Math.min(getMinDepth(root.left), getMinDepth(root.right));
}
```

### Path Problems

```java
// Check if tree has a root-to-leaf path with specified sum
boolean hasPathSum(TreeNode root, int target) {
    if (root == null) return false;
    if (root.left == null && root.right == null) return target == root.val;
    return hasPathSum(root.left, target - root.val) || 
           hasPathSum(root.right, target - root.val);
}

// Collect all root-to-leaf paths
void collectPaths(TreeNode root, List<Integer> path, List<List<Integer>> result) {
    if (root == null) return;
    
    path.add(root.val);
    if (root.left == null && root.right == null) {
        result.add(new ArrayList<>(path));
    }
    
    collectPaths(root.left, path, result);
    collectPaths(root.right, path, result);
    path.remove(path.size() - 1);  // Backtrack
}
```

### Binary Search Tree (BST) Problems

```java
// Validate if a tree is a valid BST
boolean isValidBST(TreeNode root, long min, long max) {
    if (root == null) return true;
    if (root.val <= min || root.val >= max) return false;
    
    return isValidBST(root.left, min, root.val) && 
           isValidBST(root.right, root.val, max);
}

// Search for a value in BST
TreeNode search(TreeNode root, int target) {
    if (root == null || root.val == target) return root;
    return target < root.val ? search(root.left, target) : search(root.right, target);
}
```

### Tree Construction Problems

```java
// Build tree from preorder and inorder traversals
TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }
    return buildTreeHelper(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inMap);
}

private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                             int[] inorder, int inStart, int inEnd,
                             Map<Integer, Integer> inMap) {
    if (preStart > preEnd || inStart > inEnd) return null;
    
    TreeNode root = new TreeNode(preorder[preStart]);
    int inRoot = inMap.get(root.val);
    int numsLeft = inRoot - inStart;
    
    root.left = buildTreeHelper(preorder, preStart + 1, preStart + numsLeft,
                             inorder, inStart, inRoot - 1, inMap);
    
    root.right = buildTreeHelper(preorder, preStart + numsLeft + 1, preEnd,
                              inorder, inRoot + 1, inEnd, inMap);
    
    return root;
}
```

## Problem-Solving Techniques

### Using Global Variables for State

```java
class Solution {
    private int maxDiameter = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        calculateHeight(root);
        return maxDiameter;
    }
    
    private int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
```

### Level-Based Problems

```java
// For problems requiring level information
void processLevels(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int level = 0;
    
    while (!queue.isEmpty()) {
        int size = queue.size();
        // Process entire level
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            // Do level-specific processing here
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        level++;
    }
}
```

### Parent Pointer Technique

```java
class NodeWithParent {
    TreeNode node;
    NodeWithParent parent;
    int level;
    
    NodeWithParent(TreeNode node, NodeWithParent parent, int level) {
        this.node = node;
        this.parent = parent;
        this.level = level;
    }
}
```

## Time & Space Complexity

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

### Algorithm Performance
- BST operations average: O(log N)
- BST operations worst: O(N) for skewed tree
- Balanced tree height: O(log N)
- Skewed tree height: O(N)

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

## Common Edge Cases to Test

1. Null tree
2. Single node tree
3. Left-skewed tree
4. Right-skewed tree
5. Perfect binary tree
6. Tree with missing children
7. Tree with negative values
8. Tree with duplicate values
9. Deep tree (for stack overflow)
10. Wide tree (for memory limits)

## Common Mistakes to Avoid

1. Forgetting null checks
2. Not handling leaf nodes specially when needed
3. Incorrect base cases in recursion
4. Not maintaining parent/path information when needed
5. Forgetting to handle negative numbers in BST problems
6. Not considering duplicate values
7. Incorrect handling of level information in BFS
8. Not backtracking in path-finding problems
9. Integer overflow in height/sum calculations
10. Modifying input tree when not allowed