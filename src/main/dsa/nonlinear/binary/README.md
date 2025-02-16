# Binary Tree Problems Cheat Sheet

## 1. Tree Traversal Patterns

### DFS (Depth-First Search)
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

### BFS (Breadth-First Search)
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

## 2. Common Tree Problems & Templates

### Height/Depth Problems
```java
int getHeight(TreeNode root) {
    if (root == null) return 0;
    return 1 + Math.max(getHeight(root.left), getHeight(root.right));
}

int getMinDepth(TreeNode root) {
    if (root == null) return 0;
    if (root.left == null) return 1 + getMinDepth(root.right);
    if (root.right == null) return 1 + getMinDepth(root.left);
    return 1 + Math.min(getMinDepth(root.left), getMinDepth(root.right));
}
```

### Path Problems
```java
// Path Sum
boolean hasPathSum(TreeNode root, int target) {
    if (root == null) return false;
    if (root.left == null && root.right == null) return target == root.val;
    return hasPathSum(root.left, target - root.val) || 
           hasPathSum(root.right, target - root.val);
}

// Collect all paths
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
// Validate BST
boolean isValidBST(TreeNode root, long min, long max) {
    if (root == null) return true;
    if (root.val <= min || root.val >= max) return false;
    
    return isValidBST(root.left, min, root.val) && 
           isValidBST(root.right, root.val, max);
}

// Find in BST
TreeNode search(TreeNode root, int target) {
    if (root == null || root.val == target) return root;
    return target < root.val ? search(root.left, target) : search(root.right, target);
}
```

### Tree Construction Problems
```java
// From preorder and inorder traversals
TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> inMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
        inMap.put(inorder[i], i);
    }
    return buildTreeHelper(preorder, 0, preorder.length - 1,
                          inorder, 0, inorder.length - 1, inMap);
}
```

## 3. Key Problem-Solving Techniques

### 1. Use Global Variables for State
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

### 2. Level-Based Problems
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

### 3. Parent Pointer Technique
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

## 4. Time & Space Complexity

- Most DFS solutions: Time O(N), Space O(H) where H is height
- Most BFS solutions: Time O(N), Space O(W) where W is max width
- Balanced tree height: O(log N)
- Worst-case tree height: O(N) for skewed tree
- BST operations average: O(log N)
- BST operations worst: O(N) for skewed tree

## 5. Common Edge Cases to Test

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

## 6. Common Mistakes to Avoid

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