# Binary Search Tree (BST) Cheat Sheet - Java

## Definition
A binary search tree is a binary tree with the following properties:
- Each node has at most two children (left and right)
- The left subtree of a node contains only nodes with keys less than the node's key
- The right subtree of a node contains only nodes with keys greater than the node's key
- All subtrees are also binary search trees

## Node Structure
```java
class Node {
    int key;
    Node left, right;
    
    public Node(int item) {
        key = item;
        left = right = null;
    }
}
```

## Basic Operations

### Insertion - O(log n) average, O(n) worst case
```java
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
```

### Search - O(log n) average, O(n) worst case
```java
public Node search(Node root, int key) {
    if (root == null || root.key == key) {
        return root;
    }
    
    if (key < root.key) {
        return search(root.left, key);
    }
    
    return search(root.right, key);
}
```

### Deletion - O(log n) average, O(n) worst case
```java
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
```

## Traversal Techniques

### In-order Traversal - O(n)
Visits left subtree, then root, then right subtree
```java
public void inorderTraversal(Node root) {
    if (root != null) {
        inorderTraversal(root.left);
        System.out.print(root.key + " ");
        inorderTraversal(root.right);
    }
}
```

### Pre-order Traversal - O(n)
Visits root, then left subtree, then right subtree
```java
public void preorderTraversal(Node root) {
    if (root != null) {
        System.out.print(root.key + " ");
        preorderTraversal(root.left);
        preorderTraversal(root.right);
    }
}
```

### Post-order Traversal - O(n)
Visits left subtree, then right subtree, then root
```java
public void postorderTraversal(Node root) {
    if (root != null) {
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        System.out.print(root.key + " ");
    }
}
```

### Level-order Traversal (BFS) - O(n)
```java
public void levelOrderTraversal(Node root) {
    if (root == null) {
        return;
    }
    
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    
    while (!queue.isEmpty()) {
        Node current = queue.poll();
        System.out.print(current.key + " ");
        
        if (current.left != null) {
            queue.add(current.left);
        }
        
        if (current.right != null) {
            queue.add(current.right);
        }
    }
}
```

## BST Properties

### Height - O(n)
```java
public int height(Node root) {
    if (root == null) {
        return 0;
    }
    
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);
    
    return Math.max(leftHeight, rightHeight) + 1;
}
```

### Size (number of nodes) - O(n)
```java
public int size(Node root) {
    if (root == null) {
        return 0;
    }
    
    return size(root.left) + size(root.right) + 1;
}
```

### Checking if a binary tree is a BST - O(n)
```java
public boolean isBST(Node root) {
    return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private boolean isBSTUtil(Node root, int min, int max) {
    if (root == null) {
        return true;
    }
    
    if (root.key <= min || root.key >= max) {
        return false;
    }
    
    return isBSTUtil(root.left, min, root.key) && 
           isBSTUtil(root.right, root.key, max);
}
```

## Advanced Operations

### Find Lowest Common Ancestor (LCA) - O(log n) average
```java
public Node findLCA(Node root, int n1, int n2) {
    if (root == null) {
        return null;
    }
    
    // If both n1 and n2 are smaller than root, then LCA lies in left
    if (root.key > n1 && root.key > n2) {
        return findLCA(root.left, n1, n2);
    }
    
    // If both n1 and n2 are greater than root, then LCA lies in right
    if (root.key < n1 && root.key < n2) {
        return findLCA(root.right, n1, n2);
    }
    
    // If one value is less than root and other is greater, or one of them is equal to root
    return root;
}
```

### Find Kth Smallest Element - O(n)
```java
public int kthSmallest(Node root, int k) {
    List<Integer> inorder = new ArrayList<>();
    inorderToList(root, inorder);
    
    return inorder.get(k - 1);
}

private void inorderToList(Node root, List<Integer> list) {
    if (root == null) {
        return;
    }
    
    inorderToList(root.left, list);
    list.add(root.key);
    inorderToList(root.right, list);
}
```

### Find Floor and Ceiling - O(log n) average
```java
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
```

## Self-Balancing BSTs

### AVL Tree
A self-balancing BST where the difference between heights of left and right subtrees is at most 1

```java
class AVLNode {
    int key, height;
    AVLNode left, right;
    
    public AVLNode(int key) {
        this.key = key;
        this.height = 1;
    }
}

class AVLTree {
    private AVLNode root;
    
    // Get height of the node
    private int height(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    // Get balance factor of node
    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    
    // Right rotate subtree rooted with y
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
        
        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        
        return x;
    }
    
    // Left rotate subtree rooted with x
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        
        // Perform rotation
        y.left = x;
        x.right = T2;
        
        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        
        return y;
    }
    
    // Insert a node
    public AVLNode insert(AVLNode node, int key) {
        // Standard BST insert
        if (node == null) {
            return new AVLNode(key);
        }
        
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            // Duplicate keys not allowed
            return node;
        }
        
        // Update height
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        
        // Get balance factor
        int balance = getBalance(node);
        
        // If unbalanced, then there are 4 cases
        
        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        
        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        
        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        
        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        
        return node;
    }
}
```

### Red-Black Tree
Core implementation using Java's TreeMap/TreeSet which use Red-Black trees internally:

```java
import java.util.TreeMap;
import java.util.TreeSet;

// Using TreeMap for key-value pairs
TreeMap<Integer, String> rbTree = new TreeMap<>();
rbTree.put(10, "Ten");
rbTree.put(20, "Twenty");
rbTree.put(5, "Five");

// Check if key exists
boolean containsKey = rbTree.containsKey(10);  // true

// Get value
String value = rbTree.get(10);  // "Ten"

// Remove key
rbTree.remove(5);

// Using TreeSet for unique keys
TreeSet<Integer> rbSet = new TreeSet<>();
rbSet.add(10);
rbSet.add(20);
rbSet.add(5);

// Check if element exists
boolean contains = rbSet.contains(10);  // true

// Remove element
rbSet.remove(5);
```

## Common Applications of BSTs

1. Dictionary implementations
2. Priority queues
3. Symbol tables in compilers
4. Database indexing
5. Implementing sets and maps in programming languages

## Complexity Summary

| Operation | Average Case | Worst Case |
|-----------|--------------|------------|
| Insert    | O(log n)     | O(n)       |
| Delete    | O(log n)     | O(n)       |
| Search    | O(log n)     | O(n)       |
| Traversal | O(n)         | O(n)       |
| Height    | O(log n)     | O(n)       |
| Space     | O(n)         | O(n)       |