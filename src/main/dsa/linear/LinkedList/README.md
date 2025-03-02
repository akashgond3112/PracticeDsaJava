# **📌 README: Linked List in Java**
## **What is a Linked List?**
A **Linked List** is a linear data structure where elements are **not stored in contiguous memory locations**. Instead, each node contains a pointer to the next node.

## **Types of Linked Lists**
1. **Singly Linked List (SLL)** → Each node points to the next.
2. **Doubly Linked List (DLL)** → Each node has next & prev pointers.
3. **Circular Linked List (CLL)** → Last node connects to the first.

## **Key Operations & Time Complexity**
| Operation | Technique Used | Time Complexity | Space Complexity |
|-----------|--------------|----------------|----------------|
| **Insertion at Head** | Pointer Update | **O(1)** | O(n) |
| **Insertion at Tail** | Traversal + Pointer Update | **O(n)** | O(n) |
| **Deletion** | Pointer Update | **O(n)** | O(n) |
| **Search** | Linear Search | **O(n)** | O(1) |

## **Implementation of Singly Linked List**
```java
class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    void insert(int data) { // O(n) for tail insertion
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    void display() { // O(n)
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}

// Usage
LinkedList list = new LinkedList();
list.insert(10);
list.insert(20);
list.display(); // 10 -> 20 -> null
```

## **Deleting a Node in a Linked List**
```java
void delete(int key) { // O(n)
    if (head == null) return;
    if (head.data == key) {
        head = head.next;
        return;
    }
    Node temp = head;
    while (temp.next != null && temp.next.data != key) {
        temp = temp.next;
    }
    if (temp.next != null) {
        temp.next = temp.next.next;
    }
}
```