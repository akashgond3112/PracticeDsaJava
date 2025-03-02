# **📌 README: Queue in Java**
## **What is a Queue?**
A **queue** follows the **FIFO (First-In-First-Out)** principle. Elements are added at the **rear** and removed from the **front**.

## **Types of Queues**
1. **Simple Queue** → Standard FIFO behavior.
2. **Circular Queue** → Rear connects back to the front.
3. **Priority Queue** → Elements dequeued based on priority.
4. **Deque (Double-Ended Queue)** → Insert/delete from both ends.

## **Key Operations & Time Complexity**
| Operation | Technique Used | Time Complexity | Space Complexity |
|-----------|--------------|----------------|----------------|
| **Enqueue (Insert)** | LinkedList / Circular Buffer | **O(1)** | O(n) |
| **Dequeue (Remove)** | LinkedList / Circular Buffer | **O(1)** | O(n) |
| **Peek (Front Element)** | Direct Access | **O(1)** | O(1) |

## **Implementation Using LinkedList**
```java
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();
queue.add(10);  // Enqueue O(1)
queue.add(20);
queue.remove();  // Dequeue O(1)
System.out.println(queue.peek()); // O(1)
```

## **Implementation Using Array**
```java
class Queue {
    int[] arr;
    int front, rear, size;

    Queue(int capacity) {
        arr = new int[capacity];
        front = rear = -1;
    }

    void enqueue(int data) { // O(1)
        if (rear == arr.length - 1) return; 
        arr[++rear] = data;
        if (front == -1) front = 0;
    }

    int dequeue() { // O(1)
        if (front == -1) return -1;
        int item = arr[front++];
        if (front > rear) front = rear = -1;
        return item;
    }
}
```