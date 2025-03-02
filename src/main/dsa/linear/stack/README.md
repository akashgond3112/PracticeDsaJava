# **📌 README: Stack in Java**
## **What is a Stack?**
A **stack** follows **LIFO (Last-In-First-Out)**, meaning the last added element is removed first.

## **Key Operations & Time Complexity**
| Operation | Technique Used | Time Complexity | Space Complexity |
|-----------|--------------|----------------|----------------|
| **Push (Insert)** | Array / Linked List | **O(1)** | O(n) |
| **Pop (Remove)** | Remove last element | **O(1)** | O(n) |
| **Peek (Top Element)** | Direct Access | **O(1)** | O(1) |

## **Implementation Using Java’s Stack Class**
```java
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
stack.push(10);  // O(1)
stack.push(20);
stack.pop();  // O(1)
System.out.println(stack.peek()); // O(1)
```

## **Implementation Using an Array**
```java
class Stack {
    int[] arr;
    int top;

    Stack(int capacity) {
        arr = new int[capacity];
        top = -1;
    }

    void push(int data) { // O(1)
        if (top == arr.length - 1) return;
        arr[++top] = data;
    }

    int pop() { // O(1)
        if (top == -1) return -1;
        return arr[top--];
    }

    int peek() { // O(1)
        return top == -1 ? -1 : arr[top];
    }
}
```